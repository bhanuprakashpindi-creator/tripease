package com.example.tripease.repository;

import com.example.tripease.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver,Integer> {
    List<Driver> findByAge(int age);
    @Query("select d from Driver d where d.age > :age")
    List<Driver> findDriverByAgeGreaterThan(@Param("age") int age);

}
