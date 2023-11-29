package com.gamsung.backend.domain.accommodation.controller;
import com.gamsung.backend.domain.accommodation.dto.response.AccommodationDetailResponse;
import com.gamsung.backend.domain.accommodation.dto.response.AccommodationSummaryResponse;
import com.gamsung.backend.domain.accommodation.service.AccommodationService;
import com.gamsung.backend.global.common.ApiResponse;
import com.gamsung.backend.global.openapi.OpenApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "숙소")
@RestController
@RequestMapping("/v1/accommodations")
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationService accommodationService;
    private final OpenApiService openApiService;

    @GetMapping("/data")
    @Operation(summary = "open api를 활용하여 숙박 정보를 저장시키는 API", description = "공공데이터를 이용해 숙소 정보를 가져옵니다.")
    public ResponseEntity<Void> registerAccommodationInfo() {
        openApiService.getAccommodationInfo();
        return null;
    }

    @GetMapping
    @Operation(summary = "메인 페이지 숙소 정보 가져오는 API", description = "지역의 번호를 사용해 원하는 지역에 위치하는 숙소 정보를 가져옵니다.")  
    public ResponseEntity<ApiResponse<List<AccommodationSummaryResponse>>> accommodationsAllEntry(
        @RequestParam Long location,
        @Parameter(hidden = true) @PageableDefault(page = 0, size = 16) Pageable pageable
    ) {
        return ResponseEntity.ok(ApiResponse.create(
                3000
                , accommodationService.getAccommodationInfoByLocation(location, pageable)
            )
        );
    }

    @GetMapping("/{accommodationId}")
    @Operation(summary = "상세 페이지 숙소 정보 가져오는 API", description = "숙소 id를 이용해 해당 숙소의 상세 정보를 가져옵니다.")
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
