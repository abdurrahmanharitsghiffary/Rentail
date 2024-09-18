package com.abdhage.rentail.features.accommodation.impl;

import com.abdhage.rentail.features.accommodation.dto.CreateAccommodationDTO;
import com.abdhage.rentail.features.accommodation.dto.UpdateAccommodationDTO;
import com.abdhage.rentail.features.accommodation.AccommodationMapper;
import com.abdhage.rentail.features.accommodation.model.Accommodation;
import com.abdhage.rentail.features.accommodationagent.model.AccommodationAgent;
import com.abdhage.rentail.features.accommodation.AccommodationChecker;
import com.abdhage.rentail.features.accommodation.AccommodationGuard;
import com.abdhage.rentail.features.accommodation.AccommodationService;
import com.abdhage.rentail.features.accommodation.model.AccommodationType;
import com.abdhage.rentail.features.user.model.User;
import com.abdhage.rentail.features.accommodationagent.model.AgentId;
import com.abdhage.rentail.features.accommodation.AccommodationResponse;
import com.abdhage.rentail.features.accommodation.repository.AccommodationRepository;
import com.abdhage.rentail.features.auth.AuthService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AuthService authService;
    private final AccommodationRepository accommodationRepository;
    private final AccommodationMapper accommodationMapper;
    private final AccommodationChecker accommodationChecker;
    private final AccommodationGuard accommodationGuard;

    public AccommodationServiceImpl(AuthService authService, AccommodationRepository accommodationRepository, AccommodationMapper accommodationMapper, AccommodationChecker accommodationChecker, AccommodationGuard accommodationGuard) {
        this.authService = authService;
        this.accommodationRepository = accommodationRepository;
        this.accommodationMapper = accommodationMapper;
        this.accommodationChecker = accommodationChecker;
        this.accommodationGuard = accommodationGuard;
    }

    @Override
    public List<AccommodationResponse> findAll() {
        return accommodationRepository.findAll().stream().peek(System.out::println).map(accommodationMapper::accommodationToAccommodationResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AccommodationResponse create(CreateAccommodationDTO dto) {
        User loggedUser = authService.getLoggedUserInformations();
        accommodationChecker.checkAccommodationNameMustUnique(dto.getName());
        final Accommodation accommodation = accommodationMapper.createAccommodationDtoToAccommodation(dto);
        final AccommodationAgent agent = AccommodationAgent.builder().user(loggedUser).accommodation(accommodation).build();

        final HashSet<AccommodationAgent> agents = new HashSet<>();
        agents.add(agent);

        accommodation.setCollaborators(agents);
        return accommodationMapper.accommodationToAccommodationResponse(accommodationRepository.save(accommodation));
    }

    @Override
    @Transactional
    public AccommodationResponse update(UUID id, UpdateAccommodationDTO dto) {
        User loggedUser = authService.getLoggedUserInformations();
        final Accommodation theAccommodation = accommodationChecker.checkAccommodationMustExists(id);
        accommodationGuard.verifyPermissionById(new AgentId(id, loggedUser.getId()));

        final boolean isKosNameSameAsDto = Objects.equals(theAccommodation.getName(), dto.getName());

        if (dto.getName() != null && !isKosNameSameAsDto)
            accommodationChecker.checkAccommodationNameMustUnique(dto.getName());

        final Accommodation updatedAccommodation = accommodationMapper.updateAccommodationDtoToAccommodation(dto);

        return accommodationMapper.accommodationToAccommodationResponse(accommodationRepository.save(updatedAccommodation));
    }

    @Override
    @Transactional
    public void destroy(UUID id) {
        final User loggedUser = authService.getLoggedUserInformations();

        accommodationChecker.checkAccommodationMustExists(id);
        accommodationGuard.verifyPermissionById(new AgentId(id, loggedUser.getId()));
        accommodationRepository.deleteById(id);
    }

    @Override
    public AccommodationResponse findOne(UUID id) {
        return accommodationMapper.accommodationToAccommodationResponse(accommodationChecker.checkAccommodationMustExists(id));
    }

    @Override
    public List<AccommodationResponse> findAllByType(AccommodationType type) {
        return accommodationRepository.findAll().stream().map(accommodationMapper::accommodationToAccommodationResponse).collect(Collectors.toList());
    }
}
