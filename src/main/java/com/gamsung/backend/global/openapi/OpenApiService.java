package com.gamsung.backend.global.openapi;

import static com.gamsung.backend.global.openapi.UrlConstants.ACCOMODATION_PATH;
import static com.gamsung.backend.global.openapi.UrlConstants.ARRANGE;
import static com.gamsung.backend.global.openapi.UrlConstants.CONTENT_TYPE_ID;
import static com.gamsung.backend.global.openapi.UrlConstants.DEFAULT_NUM_OF_ROWS;
import static com.gamsung.backend.global.openapi.UrlConstants.DESCRIPTION_PATH;
import static com.gamsung.backend.global.openapi.UrlConstants.END_POINT;
import static com.gamsung.backend.global.openapi.UrlConstants.MOBILE_APP;
import static com.gamsung.backend.global.openapi.UrlConstants.MOBILE_OS;
import static com.gamsung.backend.global.openapi.UrlConstants.ROOM_PATH;
import static com.gamsung.backend.global.openapi.UrlConstants.TYPE;
import static com.gamsung.backend.global.openapi.UrlConstants.YES;

import com.fasterxml.jackson.databind.JsonNode;
import com.gamsung.backend.domain.accomodation.entity.Accomodation;
import com.gamsung.backend.domain.accomodation.repository.AccomodationRepository;
import com.gamsung.backend.domain.image.entity.Image;
import com.gamsung.backend.domain.image.repository.ImageRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class OpenApiService {

    private final AccomodationRepository accomodationRepository;
    private final ImageRepository imageRepository;

    @Value("${open-api.key}")
    private String decodeApiKey;

    public void getAccomodationInfo() {
        processAccomodationInfo();
    }

    private Optional<JsonNode> makeApiCall(String url) {
        System.out.println(url);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode> responseEntity =
            restTemplate.exchange(url, HttpMethod.GET, null, JsonNode.class);
        return Optional.ofNullable(responseEntity.getBody());
    }

    private String buildApiUrl(String baseUrl, String... queryParams) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl);
        for (int i = 0; i < queryParams.length; i += 2) {
            String key = queryParams[i];
            String value = queryParams[i + 1];
            builder.queryParam(key, value);
        }
        return builder.build().toUriString();
    }

    private void processAccomodationInfo() {
        String accomodationInfoUrl = buildApiUrl(
            END_POINT + ACCOMODATION_PATH
            , "numOfRows", DEFAULT_NUM_OF_ROWS
            , "mobileOS", MOBILE_OS
            , "MobileApp", MOBILE_APP
            , "_type", TYPE
            , "arrange", ARRANGE
            , "serviceKey", decodeApiKey
        );
        Optional<JsonNode> responseNode = makeApiCall(accomodationInfoUrl);
        if (responseNode.isPresent()) {
            JsonNode items = responseNode.get().path("response").path("body").path("items");

            if (!items.isEmpty()) {
                JsonNode itemList = items.path("item");

                for (JsonNode item : itemList) {
                    String location = item.path("areacode").asText();
                    String address = item.path("addr1").asText();
                    String contentId = item.path("contentid").asText();
                    String firstImage = item.path("firstimage").asText();
                    String name = item.path("title").asText();

                    if (location.isEmpty() || address.isEmpty() || contentId.isEmpty()
                        || firstImage.isEmpty() || name.isEmpty() || endsWithJpgOrJpeg(
                        firstImage)) {
                        continue;
                    }

                    Image accomodationImage = Image.builder().imgType(1).url(firstImage).build();
                    processAccommodationDescription(contentId, accomodationImage, name, address,
                        location);
                }
            }
        }
    }

    private void processAccommodationDescription(String contentId, Image accomodationImage,
        String name,
        String address, String location) {
        String accomodationDescriptionUrl = buildApiUrl(
            END_POINT + DESCRIPTION_PATH
            , "numOfRows", DEFAULT_NUM_OF_ROWS
            , "mobileOS", MOBILE_OS
            , "MobileApp", MOBILE_APP
            , "contentId", contentId
            , "contentTypeId", CONTENT_TYPE_ID
            , "overviewYN", YES
            , "_type", TYPE
            , "serviceKey", decodeApiKey
        );
        Optional<JsonNode> responseNode = makeApiCall(accomodationDescriptionUrl);

        if (responseNode.isPresent()) {
            JsonNode items = responseNode.get().path("response").path("body").path("items");

            if (!items.isEmpty()) {
                JsonNode itemList = items.path("item");

                // 여러 객실 중 첫 번째 객실만 사용한다.
                if (itemList.get(0) != null) {
                    String description = itemList.get(0).path("overview").asText();

                    if (description.isEmpty()) {
                        return;
                    }

                    processRoomInfo(contentId, accomodationImage, name, address, location,
                        description);
                }
            }
        }
    }

    private void processRoomInfo(String contentId, Image accomodationImage, String name,
        String address, String location, String description) {
        String roomInfoUrl = buildApiUrl(
            END_POINT + ROOM_PATH
            , "MobileOS", MOBILE_OS
            , "MobileApp", MOBILE_APP
            , "contentId", contentId
            , "contentTypeId", CONTENT_TYPE_ID
            , "_type", TYPE
            , "serviceKey", decodeApiKey
        );

        Optional<JsonNode> responseNode = makeApiCall(roomInfoUrl);
        if (responseNode.isPresent()) {
            JsonNode items = responseNode.get().path("response").path("body").path("items");
            if (!items.isEmpty()) {
                JsonNode itemList = items.path("item");
                if (itemList.get(0) != null) {
                    String accomodationPrice =
                        itemList.get(0).path("roomoffseasonminfee1").asText();
                    String limitPeople = itemList.get(0).path("roommaxcount").asText();

                    if (accomodationPrice.equals("0") || limitPeople.equals("0")) {
                        return;
                    }

                    if (!itemList.get(0).path("roomimg1").asText().isEmpty()) {
                        List<Image> roomImages = new ArrayList<>();
                        for (int i = 1; i <= 5; i++) {
                            String imageUrl = itemList.get(0).path("roomimg" + i).asText();
                            if (!imageUrl.isEmpty() || endsWithJpgOrJpeg(imageUrl)) {
                                Image roomImage = Image.builder().imgType(2).url(imageUrl).build();
                                roomImages.add(roomImage);
                            }
                        }

                        saveAccomodationAndImages(accomodationImage,
                            roomImages, name,
                            address, location, description, accomodationPrice, limitPeople);
                    }
                }
            }
        }
    }

    public void saveAccomodationAndImages(Image accomodationImage,
        List<Image> roomImages, String name,
        String address, String location, String description, String accomodationPrice,
        String limitPeople
    ) {

        Accomodation accomodation = Accomodation.builder()
            .name(name)
            .description(description)
            .address(address)
            .location(Long.valueOf(location))
            .limitPeople(Long.valueOf(limitPeople))
            .price(Long.valueOf(accomodationPrice))
            .build();

        // Accomodation Info 저장
        accomodationRepository.save(accomodation);

        // Accomodation Image 저장
        accomodationImage.setAccomodation(accomodation);
        imageRepository.save(accomodationImage);
        accomodation.addImage(accomodationImage);

        // Room Images 저장
        for (Image roomImage : roomImages) {
            roomImage.setAccomodation(accomodation);
            imageRepository.save(roomImage);
            accomodation.addImage(roomImage);
        }

//        printProductInfo(accomodation);
    }

    private boolean endsWithJpgOrJpeg(String url) {
        int lastDotIndex = url.lastIndexOf(".");
        if (lastDotIndex == -1 || lastDotIndex == url.length() - 1) {
            return false;
        }
        String extension = url.substring(lastDotIndex + 1).toLowerCase();
        return extension.equals("jpg") || extension.equals("jpeg");
    }

    private void printProductInfo(Accomodation accomodation) {
        System.out.println("Address: " + accomodation.getAddress());
        System.out.println("AreaCode: " + accomodation.getLocation());
        System.out.println("Title: " + accomodation.getName());
        System.out.println("RoomFee: " + accomodation.getPrice());
        System.out.println("MaxCount: " + accomodation.getLimitPeople());
        System.out.println("description: " + accomodation.getDescription());
        System.out.println("Image: ");
        accomodation.getImages().stream()
            .forEach(image1 -> System.out.println(
                "Type: " + image1.getImgType() + ", URL: "
                    + image1.getUrl()));
        System.out.println("---------------------------------");
    }
}
