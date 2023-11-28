package com.gamsung.backend.domain.accommodation.controller;

import static com.gamsung.backend.global.config.SwaggerDescriptionConfig.ALL_ENTRY_DESCRIPTION;
import static com.gamsung.backend.global.config.SwaggerDescriptionConfig.ENTRY_DESCRIPTION;

import com.gamsung.backend.domain.accommodation.dto.response.AccommodationDetailResponse;
import com.gamsung.backend.domain.accommodation.dto.response.AccommodationSummaryResponse;
import com.gamsung.backend.domain.accommodation.service.AccommodationService;
import com.gamsung.backend.global.common.ApiResponse;
import com.gamsung.backend.global.openapi.OpenApiService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/accommodations")
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationService accommodationService;
    private final OpenApiService openApiService;

    @GetMapping("/data")
    @Operation(summary = "open api를 활용하여 숙박 정보를 저장시키는 API")
    public ResponseEntity<Void> registerAccommodationInfo() {
        openApiService.getAccommodationInfo();
        return null;
    }

    @GetMapping
    @Operation(summary = "메인 페이지 숙소 정보 가져오는 API", description = ALL_ENTRY_DESCRIPTION)
    public ResponseEntity<ApiResponse<List<AccommodationSummaryResponse>>> accommodationsAllEntry(
        @RequestParam Long location,
        @PageableDefault(page = 0, size = 16) Pageable pageable
    ) {
        return ResponseEntity.ok(ApiResponse.create(
                3000
                , accommodationService.getAccommodationInfoByLocation(location, pageable)
            )
        );
    }

    // 작업중
    @GetMapping("/{accommodationId}")
    @Operation(summary = "상세 페이지 숙소 정보 가져오는 API", description = ENTRY_DESCRIPTION)
    public ResponseEntity<ApiResponse<AccommodationDetailResponse>> accommodationsEntry(
        @PathVariable Long accommodationId
    ) {
        return ResponseEntity.ok(ApiResponse.create(
                3001
                , accommodationService.findAccommodationDetailById(accommodationId)
            )
        );
    }
}
