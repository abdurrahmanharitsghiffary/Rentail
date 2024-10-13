package com.abdhage.rentail.payment.impl;

import com.abdhage.rentail.auth.AuthService;
import com.abdhage.rentail.common.exception.BadRequestException;
import com.abdhage.rentail.common.model.MidtransTransactionDTO;
import com.abdhage.rentail.common.util.Util;
import com.abdhage.rentail.membership.repository.InvoiceRepository;
import com.abdhage.rentail.payment.PaymentService;
import com.abdhage.rentail.payment.TransactionTokenResponse;
import com.abdhage.rentail.payment.model.*;
import com.abdhage.rentail.user.model.User;
import com.midtrans.httpclient.error.MidtransError;
import com.midtrans.service.MidtransSnapApi;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final Util util;
    private final MidtransSnapApi midtransSnapApi;
    private final AuthService authService;
    private final InvoiceRepository invoiceRepository;

    public PaymentServiceImpl(Util util, MidtransSnapApi midtransSnapApi, AuthService authService,
                              InvoiceRepository invoiceRepository) {
        this.util = util;
        this.midtransSnapApi = midtransSnapApi;
        this.authService = authService;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public TransactionTokenResponse createTransaction(MidtransTransactionDTO dto) {
        User loggedUser = authService.getLoggedUserInformations();
        try {

            Set<InvoiceCharge> invoiceCharges = new HashSet<>();
            Set<InvoiceItem> invoiceItems = new HashSet<>();

            long amount = 0L;

            for (MidtransTransactionDTO.ItemDetail itemDetail : dto.getItemDetails()) {
                InvoiceItem invoiceItem = new InvoiceItem();

                if (itemDetail.getId() != null) invoiceItem.setItemId(itemDetail.getId());
                if (itemDetail.getName() != null) invoiceItem.setName(itemDetail.getName());
                if (itemDetail.getQuantity() != null) invoiceItem
                        .setQuantity(itemDetail.getQuantity().longValue());
                if (itemDetail.getPrice() != null) invoiceItem.setPrice(itemDetail.getPrice().longValue());
                if (itemDetail.getPrice() != null) {
                    amount += itemDetail.getPrice().longValue();
                    invoiceItem.setPrice(itemDetail.getPrice().longValue());
                }

                invoiceItems.add(invoiceItem);
            }

            double tax = amount * 0.01;

            amount += (long) tax;

            InvoiceCharge defaultTax = InvoiceCharge
                    .builder()
                    .amount((long) tax)
                    .type(ChargeType.TAX)
                    .build();

            invoiceCharges.add(defaultTax);

            UUID orderId = UUID.randomUUID();

            Invoice invoice = Invoice
                    .builder()
                    .id(orderId)
                    .amount(amount)
                    .charges(invoiceCharges)
                    .items(invoiceItems)
                    .status(InvoiceStatus.UNPAID)
                    .user(loggedUser)
                    .invoiceId(util.genInvoiceId())
                    .build();

            dto.setOrderId(orderId.toString());
            dto.setGrossAmount((double) amount);
            JSONObject obj = midtransSnapApi.createTransaction(dto.toMap());
            invoiceRepository.save(invoice);

            return TransactionTokenResponse
                    .builder()
                    .redirectUrl(obj.get("redirect_url").toString())
                    .token(obj.get("token").toString())
                    .build();

        } catch (MidtransError error) {
            throw new BadRequestException(error.getMessage());
        }
    }

}
