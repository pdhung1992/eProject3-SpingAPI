package com.springapi.repositories;

import com.springapi.entities.ServeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServeTypeRepository extends JpaRepository<ServeType, Integer> {
}
