package com.example.autopartz.repository;

import com.example.autopartz.model.manytomany.PartIsAppropriateForCar;
import com.example.autopartz.model.manytomany.PartIsAppropriateForCarId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartIsAppropriateForCarRepository extends JpaRepository<PartIsAppropriateForCar, PartIsAppropriateForCarId> {
}
