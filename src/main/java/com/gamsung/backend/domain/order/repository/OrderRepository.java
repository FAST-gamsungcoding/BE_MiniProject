package com.gamsung.backend.domain.order.repository;

import com.gamsung.backend.domain.order.entity.Order;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByMemberIdOrderByCreatedAtDesc(long memberId, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.accommodationId = :accommodationId " +
            "AND o.endDate > :startDate " +
            "AND o.startDate < :endDate " +
            "ORDER BY o.startDate ASC")
    Optional<Order> findFirstByAccommodationIdAndEndDateGreaterThanAndStartDateLessThanOrderByStartDateAsc(
            @Param("accommodationId") Long accommodationId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
    boolean existsByAccommodationIdAndStartDateBeforeAndEndDateAfter(
            long accommodationId, LocalDate endDate, LocalDate startDate);

    //SQL문
//    SELECT * FROM orders a WHERE a.accommodation_id = id
//    AND a.start_date < endDate
//    AND a.end_date > startDate
//    LIMIT 1 해서 있는지 체크


}
