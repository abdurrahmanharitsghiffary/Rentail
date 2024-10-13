package com.abdhage.rentail.payment;

import com.abdhage.rentail.common.model.MidtransTransactionDTO;

public interface PaymentService {

    public TransactionTokenResponse createTransaction(MidtransTransactionDTO dto);

}
