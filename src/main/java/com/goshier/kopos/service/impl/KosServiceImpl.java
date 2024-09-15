package com.goshier.kopos.service.impl;

import com.goshier.kopos.dtos.kos.CreateKosDto;
import com.goshier.kopos.dtos.kos.UpdateKosDto;
import com.goshier.kopos.mapper.KosMapper;
import com.goshier.kopos.model.Accommodation;
import com.goshier.kopos.model.ids.AgentId;
import com.goshier.kopos.response.KosResponse;
import com.goshier.kopos.repository.KosRepository;
import com.goshier.kopos.service.guard.KosGuard;
import com.goshier.kopos.service.KosService;
import com.goshier.kopos.service.checker.KosChecker;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class KosServiceImpl implements KosService {
    private final KosRepository kosRepository;
    private final KosMapper kosMapper;
    private final KosChecker kosChecker;
    private final KosGuard kosGuard;

    public KosServiceImpl(KosRepository kosRepository, KosMapper kosMapper, KosChecker kosChecker, KosGuard kosGuard) {
        this.kosRepository = kosRepository;
        this.kosMapper = kosMapper;
        this.kosChecker = kosChecker;
        this.kosGuard = kosGuard;
    }

    public List<KosResponse> findAll() {
        return kosRepository.findAll().stream().peek(System.out::println).map(kosMapper::kosToKosResponse).collect(Collectors.toList());
    }

    @Transactional
    public KosResponse create(CreateKosDto dto, UUID userId) {
        kosChecker.checkKosNameMustUnique(dto.getName());
        final Accommodation accommodation = kosMapper.createKosDtoToKos(dto);
        accommodation.setIsVerified(false);

        return kosMapper.kosToKosResponse(kosRepository.save(accommodation));
    }

    @Override
    @Transactional
    public KosResponse update(UUID kosId, UUID userId, UpdateKosDto dto) {
        final Accommodation theAccommodation = kosChecker.checkKosMustExists(kosId);
        kosGuard.verifyPermissionById(new AgentId(kosId, userId));

        final boolean isKosNameSameAsDto = Objects.equals(theAccommodation.getName(), dto.getName());

        if (dto.getName() != null && !isKosNameSameAsDto)
            kosChecker.checkKosNameMustUnique(dto.getName());

        final Accommodation updatedAccommodation = kosMapper.updateKosDtoToKos(dto);

        return kosMapper.kosToKosResponse(kosRepository.save(updatedAccommodation));
    }

    @Override
    @Transactional
    public void destroy(UUID kosId, UUID userId) {
        kosChecker.checkKosMustExists(kosId);
        kosGuard.verifyPermissionById(new AgentId(kosId, userId));
        kosRepository.deleteById(kosId);
    }

    public KosResponse findOne(UUID id) {
        return kosMapper.kosToKosResponse(kosChecker.checkKosMustExists(id));
    }
}
