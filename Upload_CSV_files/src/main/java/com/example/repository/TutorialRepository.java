package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.model.Tutorial;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long>{

}
