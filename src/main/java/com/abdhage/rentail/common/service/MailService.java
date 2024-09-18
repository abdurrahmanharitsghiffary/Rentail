package com.abdhage.rentail.common.service;

import com.abdhage.rentail.common.dto.EmailDetailsDTO;

public interface MailService {
    public void sendMail(EmailDetailsDTO dto);
}
