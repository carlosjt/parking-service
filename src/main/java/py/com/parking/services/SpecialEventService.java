package py.com.parking.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import py.com.parking.models.dto.SpecialEventDTO;
import py.com.parking.models.mappers.SpecialEventMapper;
import py.com.parking.models.repository.SpecialEventRepository;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class SpecialEventService {

    @Inject
    SpecialEventRepository specialEventRepository;

    public List<SpecialEventDTO> getAllSpecialEvents() {
        return specialEventRepository.findAll().stream()
                .map(SpecialEventMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }
}

