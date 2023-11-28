package com.gamsung.backend.domain.accomodation.service;

import com.gamsung.backend.domain.accomodation.dto.response.AccomodationDetailResponse;
import com.gamsung.backend.domain.accomodation.entity.Accommodation;
import com.gamsung.backend.domain.accomodation.exception.AccomodationNotFoundException;
import com.gamsung.backend.domain.accomodation.repository.AccomodationRepository;
import com.gamsung.backend.domain.image.service.ImageService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccomodationService {

    private final AccomodationRepository accomodationRepository;
    private final ImageService imageService;

    @Transactional(readOnly = true)
    public AccomodationDetailResponse findAccomodationDetailById(Long id) {
        Accommodation accommodation = findById(id);
        String accomodationImage = imageService.getAccomodationImageWithAccomdoationId(id);
        List<String> roomImages = imageService.getRoomImagesWithAccomodationId(id);
        return AccomodationDetailResponse.from(accommodation, accomodationImage, roomImages);
    }

    @Transactional(readOnly = true)
    public Accommodation findById(Long id) {
        return accomodationRepository.findById(id)
            .orElseThrow(AccomodationNotFoundException::new);
    }

}

