package com.gamsung.backend.domain.accommodation.service;

import com.gamsung.backend.domain.accommodation.dto.response.detail.AccommodationDetailResponse;
import com.gamsung.backend.domain.accommodation.dto.response.summary.AccommodationSummaryListResponse;
import com.gamsung.backend.domain.accommodation.dto.response.summary.AccommodationSummaryResponse;
import com.gamsung.backend.domain.accommodation.entity.Accommodation;
import com.gamsung.backend.domain.accommodation.exception.AccommodationNotFoundException;
import com.gamsung.backend.domain.accommodation.repository.AccommodationRepository;
import com.gamsung.backend.domain.image.entity.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;

    @Transactional(readOnly = true)
    public AccommodationDetailResponse findAccommodationDetailById(Long id) {
        Accommodation accommodation = findById(id);

        String accommodationImage = accommodation.getImages().get(0).getUrl();

        List<String> roomImages = getRoomImages(accommodation);

        return AccommodationDetailResponse.from(accommodation, accommodationImage, roomImages);
    }

    @Transactional(readOnly = true)
    public AccommodationSummaryListResponse getAccommodationInfoByLocation(List<Long> regionIds,
        Pageable pageable) {
        Page<Accommodation> accommodationPage =
            accommodationRepository.findByLocationIn(regionIds, pageable);

        List<AccommodationSummaryResponse>
            summaryResponses = getAccommodationSummaryResponses(accommodationPage);

        return AccommodationSummaryListResponse.to(
            summaryResponses,
            accommodationPage.getTotalPages(),
            accommodationPage.getNumber()
        );
    }

    private static List<String> getRoomImages(Accommodation accommodation) {
        return accommodation.getImages().stream()
            .skip(1)
            .map(Image::getUrl)
            .collect(Collectors.toList());
    }

    private static List<AccommodationSummaryResponse> getAccommodationSummaryResponses(
        Page<Accommodation> accommodationPage) {
        return accommodationPage.getContent().stream()
            .map(accommodation -> AccommodationSummaryResponse.to(
                    accommodation,
                    accommodation.getImages().get(0).getUrl()
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
