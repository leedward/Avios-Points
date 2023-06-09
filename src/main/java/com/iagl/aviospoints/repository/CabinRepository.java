package com.iagl.aviospoints.repository;

import com.iagl.aviospoints.model.Cabin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabinRepository extends JpaRepository<Cabin, String> {
}
