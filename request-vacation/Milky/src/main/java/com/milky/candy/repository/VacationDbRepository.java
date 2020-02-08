package com.milky.candy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.milky.candy.domain.Vacation;

public interface VacationDbRepository extends JpaRepository<Vacation, Long> {
}
