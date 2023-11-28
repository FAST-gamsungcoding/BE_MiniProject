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

    public String getAccommodationImageWithAccommodationId(Long accommodationId) {
        return imageRepository.findFirstByAccommodationIdAndImgType(accommodationId, 1)
            .map(Image::getUrl)
            .orElseThrow(ImageNotFoundException::new);
    }

    public List<String> getRoomImagesWithAccommodationId(Long accommodationId) {
        return imageRepository.findByAccommodationIdAndImgType(accommodationId, 2)
            .stream()
            .map(Image::getUrl)
            .collect(Collectors.toList());
    }
}
