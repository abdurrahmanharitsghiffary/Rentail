package com.abdhage.rentail.booking.impl;

import com.abdhage.rentail.accommodationunit.AccommodationUnitService;
import com.abdhage.rentail.accommodationunit.model.AccommodationUnit;
import com.abdhage.rentail.auth.AuthService;
import com.abdhage.rentail.booking.BookingMapper;
import com.abdhage.rentail.booking.BookingRepository;
import com.abdhage.rentail.booking.BookingResponse;
import com.abdhage.rentail.booking.BookingService;
import com.abdhage.rentail.booking.dto.BookUnitDTO;
import com.abdhage.rentail.booking.model.Booking;
import com.abdhage.rentail.booking.model.BookingId;
import com.abdhage.rentail.booking.model.BookingStatus;
import com.abdhage.rentail.common.constants.Constants;
import com.abdhage.rentail.common.exception.BadRequestException;
import com.abdhage.rentail.common.exception.ForbiddenException;
import com.abdhage.rentail.common.exception.NotFoundException;
import com.abdhage.rentail.common.model.MidtransTransactionDTO;
import com.abdhage.rentail.payment.PaymentService;
import com.abdhage.rentail.tenant.TenantRepository;
import com.abdhage.rentail.tenant.model.Tenant;
import com.abdhage.rentail.tenant.model.TenantId;
import com.abdhage.rentail.user.model.User;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final Constants constant;
    private final BookingRepository bookingRepository;
    private final AccommodationUnitService accommodationUnitService;
    private final AuthService authService;
    private final BookingMapper bookingMapper;
    private final TenantRepository tenantRepository;
    private final PaymentService paymentService;

    public BookingServiceImpl(Constants constant, BookingRepository bookingRepository, AccommodationUnitService accommodationUnitService, AuthService authService,
                              BookingMapper bookingMapper,
                              TenantRepository tenantRepository, PaymentService paymentService) {
        this.constant = constant;
        this.bookingRepository = bookingRepository;
        this.accommodationUnitService = accommodationUnitService;
        this.authService = authService;
        this.bookingMapper = bookingMapper;
        this.tenantRepository = tenantRepository;
        this.paymentService = paymentService;
    }

    @Override
    public List<BookingResponse> findAllByUnit(UUID unitId) {
        return bookingRepository
                .findById_UnitId(unitId)
                .stream()
                .map(bookingMapper::bookingToBookingResponse)
                .collect(Collectors.toList());
    }

    // TODO : should we add payment?
    @Override
    public BookingResponse bookAccommodationUnit(BookUnitDTO dto) {
        User loggedUser = authService.getLoggedUserInformations();

        AccommodationUnit unit = accommodationUnitService
                .checkAccommodationUnitMustExists(dto.getUnitId());

        Optional<Booking> booking = bookingRepository
                .findById(new BookingId(loggedUser.getId(), dto.getUnitId()));
        Optional<Tenant> tenant = tenantRepository
                .findById(new TenantId(dto.getUnitId(), loggedUser.getId()));

        if (tenant.isEmpty() || booking.isEmpty()) {
            throw new BadRequestException("You already booked this place");
        }

        Booking book = new Booking();

        book.setUnit(unit);
        book.setUser(loggedUser);
        book.setStatus(BookingStatus.PENDING);
        book.setStartFrom(dto.getStartFrom());
        if (dto.getNotes() != null) book.setNotes(dto.getNotes());

        return bookingMapper
                .bookingToBookingResponse(bookingRepository.save(book));
    }

    @Override
    public BookingResponse updateStatus(BookingId id, BookingStatus status) {
        Booking booking = checkBookingMustExists(id);

        booking.setStatus(status);

        return bookingMapper
                .bookingToBookingResponse(bookingRepository.save(booking));
    }

    @Override
    public BookingResponse updateStatus(Booking booking, BookingStatus status) {
        booking.setStatus(status);
        return bookingMapper
                .bookingToBookingResponse(bookingRepository.save(booking));
    }

    @Override
    public BookingResponse findOne(BookingId bookingId) {
        return bookingMapper
                .bookingToBookingResponse(checkBookingMustExists(bookingId));
    }

    @Override
    public Booking checkBookingMustExists(BookingId bookingId) {
        return bookingRepository
                .findById(bookingId)
                .orElseThrow(() -> new NotFoundException("Booking data does not found"));
    }

    // TODO : do authorization just the booker and agent.LANDLORD can cancel the booking
    @Override
    public void cancelBooking(BookingId bookingId) {
        Booking booking = checkBookingMustExists(bookingId);
        updateStatus(booking, BookingStatus.CANCELLED);
    }

    // TODO : do authorization only LANDLORD can confirm the booking
    @Override
    public void confirmBooking(BookingId bookingId) {
        Booking booking = checkBookingMustExists(bookingId);
        updateStatus(booking, BookingStatus.CONFIRMED);
    }

    // TODO : this should run after booker already doing payment?
    // Should be called after midtrans webhook successful payment
    // deposit?
    @Override
    public void completeBooking(BookingId bookingId) {
        Booking booking = checkBookingMustExists(bookingId);
        updateStatus(booking, BookingStatus.SUCCESS);

        Date endAt = new Date(System.currentTimeMillis() + (int) constant.DAY_IN_MS);

        Tenant tenant = new Tenant();
        tenant.setUser(booking.getUser());
        tenant.setUnit(booking.getUnit());

        tenant.setEndAt(endAt);
        tenantRepository.save(tenant);
    }

    @Override
    public void payBooking(BookingId bookingId) {
        Booking booking = checkBookingMustExists(bookingId);
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
        AccommodationUnit unit = booking.getUnit();
        List<MidtransTransactionDTO.ItemDetail> itemDetails = new ArrayList<>();

        MidtransTransactionDTO.ItemDetail bookingDetail = new MidtransTransactionDTO.ItemDetail();
        bookingDetail.setName("Booking unit until" + dt.format(booking.getStartFrom()));

        if (unit.getBookingPrice() != null) {
            long totalBookingDays = ChronoUnit.DAYS.between(
                    LocalDate.now(),
                    booking.getStartFrom()
                            .toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()
            );

            bookingDetail.setPrice((double) unit.getBookingPrice() * totalBookingDays);
        }

        itemDetails.add(bookingDetail);

        MidtransTransactionDTO dto = new MidtransTransactionDTO();
        dto.setItemDetails(itemDetails);

        paymentService.createTransaction(dto);
    }

    @Override
    public void verifyPermission(Booking booking) {
        User loggedUser = authService.getLoggedUserInformations();
        if (booking == null || booking.getUser().getId() != loggedUser.getId()) {
            throw new ForbiddenException();
        }
    }

    @Override
    public void verifyPermission(BookingId bookingId) {
        Booking booking = checkBookingMustExists(bookingId);

        User loggedUser = authService.getLoggedUserInformations();
        if (booking == null || booking.getUser().getId() != loggedUser.getId()) {
            throw new ForbiddenException();
        }
    }
}
