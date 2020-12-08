package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Professors;

public interface ProfessorsRepository extends JpaRepository<Professors, Long>{

}
