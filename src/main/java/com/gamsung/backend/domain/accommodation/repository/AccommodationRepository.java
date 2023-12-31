package com.gamsung.backend.domain.accommodation.repository;

import com.gamsung.backend.domain.accommodation.entity.Accommodation;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    Page<Accommodation> findByLocationIn(List<Long> location, Pageable pageable);
}
