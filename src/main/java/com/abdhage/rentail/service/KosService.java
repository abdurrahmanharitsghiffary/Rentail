package com.abdhage.rentail.service;

import com.abdhage.rentail.dtos.kos.CreateKosDto;
import com.abdhage.rentail.dtos.kos.UpdateKosDto;
import com.abdhage.rentail.response.KosResponse;
import com.abdhage.rentail.service.common.AuthorizedCrudService;

import java.util.UUID;

public interface KosService extends AuthorizedCrudService<KosResponse, CreateKosDto, UpdateKosDto, UUID, UUID> {

}
