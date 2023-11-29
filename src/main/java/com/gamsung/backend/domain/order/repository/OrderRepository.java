package com.gamsung.backend.domain.order.repository;

import com.gamsung.backend.domain.order.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByMemberIdOrderByCreatedAtDesc(long memberId, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.accommodationId = ?1 AND o.startDate < ?2 AND o.endDate > ?3")
    Optional<Order> existsByAccommodationIdAndStartDateBeforeAndEndDateAfter(
            @Param("accommodationId") long accommodationId,
            @Param("endDate") LocalDate endDate,
            @Param("startDate") LocalDate startDate);


    // 주문 테이블에서 1. 해당 숙소의 id값을 숙소 외래키로 가지고
    // 2. 예약 시작일이 사용자의 예약 종료일보다 작고
    // 3. 예약 종료일이 사용자의 예약 시작일보다 큰 것이 있는가?

    //SQL문
//    SELECT * FROM orders a WHERE a.accommodation_id = id
//    AND a.start_date < endDate
//    AND a.end_date > startDate
//    LIMIT 1 해서 있는지 체크


}
