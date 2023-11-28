package com.gamsung.backend.domain.accomodation.repository;

import com.gamsung.backend.domain.accomodation.entity.Accomodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccomodationRepository extends JpaRepository<Accomodation, Long> {

}
