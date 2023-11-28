package com.gamsung.backend.domain.accommodation.service;

import com.gamsung.backend.domain.accommodation.dto.response.AccommodationDetailResponse;
import com.gamsung.backend.domain.accommodation.dto.response.AccommodationSummaryResponse;
import com.gamsung.backend.domain.accommodation.entity.Accommodation;
import com.gamsung.backend.domain.accommodation.exception.AccommodationNotFoundException;
import com.gamsung.backend.domain.accommodation.repository.AccommodationRepository;
import com.gamsung.backend.domain.image.service.ImageService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final ImageService imageService;

    @Transactional(readOnly = true)
    public AccommodationDetailResponse findAccommodationDetailById(Long id) {
        Accommodation accommodation = findById(id);
        String accommodationImage = imageService.getAccommodationImageWithAccommodationId(id);
        List<String> roomImages = imageService.getRoomImagesWithAccommodationId(id);
        return AccommodationDetailResponse.from(accommodation, accommodationImage, roomImages);
    }

    @Transactional(readOnly = true)
    public List<AccommodationSummaryResponse> getAccommodationInfoByLocation(Long location,
        Pageable pageable) {
        Page<Accommodation> accommodationPage =
            accommodationRepository.findAccommodationByLocation(location, pageable);

        return accommodationPage.getContent().stream()
            .map(accommodation -> AccommodationSummaryResponse.from(
                    accommodation,
                    imageService.getAccommodationImageWithAccommodationId(accommodation.getId())
                )
            )
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Accommodation findById(Long id) {
        return accommodationRepository.findById(id)
            .orElseThrow(AccommodationNotFoundException::new);
    }
}
