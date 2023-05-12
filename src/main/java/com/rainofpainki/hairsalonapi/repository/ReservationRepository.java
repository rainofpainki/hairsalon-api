package com.rainofpainki.hairsalonapi.repository;

import com.rainofpainki.hairsalonapi.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ReservationRepository extends JpaRepository<Reservation, Long>, QuerydslPredicateExecutor<Reservation>, ReservationRepositoryCustom {
}
