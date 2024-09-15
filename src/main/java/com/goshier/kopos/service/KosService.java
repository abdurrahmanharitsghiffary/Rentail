package com.goshier.kopos.service;

import com.goshier.kopos.dtos.kos.CreateKosDto;
import com.goshier.kopos.dtos.kos.UpdateKosDto;
import com.goshier.kopos.response.KosResponse;
import com.goshier.kopos.service.common.AuthorizedCrudService;

import java.util.UUID;

public interface KosService extends AuthorizedCrudService<KosResponse, CreateKosDto, UpdateKosDto, UUID, UUID> {

}
