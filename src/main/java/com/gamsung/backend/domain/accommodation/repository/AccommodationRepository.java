package com.gamsung.backend.domain.accommodation.repository;

import com.gamsung.backend.domain.accommodation.entity.Accommodation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
    Page<Accommodation> findAccommodationByLocation(Long location, Pageable pageable);
}
