package com.abdhage.rentail.features.accommodationunit.impl;

import com.abdhage.rentail.features.accommodation.AccommodationGuard;
import com.abdhage.rentail.features.accommodationagent.model.AgentId;
import com.abdhage.rentail.features.accommodationunit.AccommodationUnitMapper;
import com.abdhage.rentail.features.accommodationunit.AccommodationUnitRepository;
import com.abdhage.rentail.features.accommodationunit.AccommodationUnitResponse;
import com.abdhage.rentail.features.accommodationunit.AccommodationUnitService;
import com.abdhage.rentail.features.accommodationunit.dto.CreateUnitDTO;
import com.abdhage.rentail.features.accommodationunit.dto.UpdateUnitDTO;
import com.abdhage.rentail.features.accommodationunit.model.AccommodationUnit;
import com.abdhage.rentail.features.auth.AuthService;
import com.abdhage.rentail.common.exception.NotFoundException;
import com.abdhage.rentail.features.user.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccommodationUnitServiceImpl implements AccommodationUnitService {

    private final AuthService authService;
    private final AccommodationGuard accommodationGuard;
    private final AccommodationUnitRepository accommodationUnitRepository;
    private final AccommodationUnitMapper accommodationUnitMapper;

    public AccommodationUnitServiceImpl(AuthService authService, AccommodationGuard accommodationGuard, AccommodationUnitRepository accommodationUnitRepository,
                                        AccommodationUnitMapper accommodationUnitMapper) {
        this.authService = authService;
        this.accommodationGuard = accommodationGuard;
        this.accommodationUnitRepository = accommodationUnitRepository;
        this.accommodationUnitMapper = accommodationUnitMapper;
    }

    @Override
    public void destroyMany(List<UUID> uuids) {
    }

    @Override
    public void updateMany(List<UUID> uuids, UpdateUnitDTO dto) {
    }

    @Override
    public List<AccommodationUnitResponse> findAll() {
        return accommodationUnitRepository.findAll()
                .stream()
                .map(accommodationUnitMapper::accommodationUnitToAccommodationUnitResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AccommodationUnitResponse create(CreateUnitDTO dto) {
        User loggedUser = authService.getLoggedUserInformations();

        accommodationGuard.verifyPermissionById(new AgentId(dto.getAccommodationId(), loggedUser.getId()));

        AccommodationUnit accommodationUnit = accommodationUnitMapper.createAccommodationUnitDtoToAccommodationUnit(dto);
        
        return accommodationUnitMapper.accommodationUnitToAccommodationUnitResponse(accommodationUnitRepository.save(accommodationUnit));
    }

    @Override
    public AccommodationUnitResponse update(UUID id, UpdateUnitDTO dto) {
        User loggedUser = authService.getLoggedUserInformations();

        AccommodationUnit unit = checkAccommodationUnitMustExists(id);
        accommodationGuard.verifyPermissionById(new AgentId(unit.getAccommodation().getId(), loggedUser.getId()));

        return accommodationUnitMapper.accommodationUnitToAccommodationUnitResponse(accommodationUnitRepository.save(unit));
    }

    @Override
    public void destroy(UUID id) {

        User loggedUser = authService.getLoggedUserInformations();

        checkAccommodationUnitMustExists(id);
        accommodationGuard.verifyPermissionById(new AgentId(id, loggedUser.getId()));
        accommodationUnitRepository.deleteById(id);

    }

    @Override
    public AccommodationUnitResponse findOne(UUID id) {
        return accommodationUnitMapper
                .accommodationUnitToAccommodationUnitResponse
                        (checkAccommodationUnitMustExists(id));
    }

    @Override
    public AccommodationUnit checkAccommodationUnitMustExists(UUID id) {
        return accommodationUnitRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Accommodation Unit not found"));
    }

    @Override
    public List<AccommodationUnitResponse> findAllByAccommodationId(UUID accommodationId) {
        return accommodationUnitRepository.findByAccommodation_Id(accommodationId)
                .stream()
                .map(accommodationUnitMapper::accommodationUnitToAccommodationUnitResponse)
                .collect(Collectors.toList());
    }

}
