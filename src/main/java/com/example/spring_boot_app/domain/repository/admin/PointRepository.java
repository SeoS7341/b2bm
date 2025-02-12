package com.example.spring_boot_app.domain.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.spring_boot_app.domain.Point;
@Repository
public interface PointRepository extends JpaRepository<Point, Integer> {


}