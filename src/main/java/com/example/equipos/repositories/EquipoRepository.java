package com.example.equipos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.equipos.models.Equipo;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {
}