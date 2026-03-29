package com.facens.app_biblioteca_api.repository;

import  org.springframework.data.jpa.repository.JpaRepository;

import com.facens.app_biblioteca_api.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>
{

    
}
