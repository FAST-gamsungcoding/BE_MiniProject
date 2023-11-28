package com.gamsung.backend.domain.image.service;

import com.gamsung.backend.domain.image.entity.Image;
import com.gamsung.backend.domain.image.exception.ImageNotFoundException;
import com.gamsung.backend.domain.image.repository.ImageRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ImageService {

    private final ImageRepository imageRepository;

    public String getAccomodationImageWithAccomdoationId(Long accomodationId) {
        return imageRepository.findFirstByAccomodationIdAndImgType(accomodationId, 1)
            .map(Image::getUrl)
            .orElseThrow(ImageNotFoundException::new);
    }

    public List<String> getRoomImagesWithAccomodationId(Long accomodationId) {
        return imageRepository.findByAccomodationIdAndImgType(accomodationId, 2)
            .stream()
            .map(Image::getUrl)
            .collect(Collectors.toList());
    }
}
