package com.example.repository;

import com.example.entity.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailableDateRepository extends JpaRepository<AvailableDate,Long> {
    boolean existsByAvailableDate(AvailableDate availableDate);


}
