package com.klaudiodev.financesis.financesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klaudiodev.financesis.financesis.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
