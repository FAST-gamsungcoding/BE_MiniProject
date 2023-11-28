package com.gamsung.backend.domain.order.repository;

import com.gamsung.backend.domain.order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByMemberIdOrderByCreatedAtDesc(long memberId, Pageable pageable);
    boolean existsByAccommodationIdAndStartDateBeforeAndEndDateAfter(
            long accommodationId, LocalDate endDate, LocalDate startDate);

    //SQL문
//    SELECT * FROM orders a WHERE a.accommodation_id = id
//    AND a.start_date < endDate
//    AND a.end_date > startDate
//    LIMIT 1 해서 있는지 체크


}
