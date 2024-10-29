package com.example.equipos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.equipos.models.Jugador;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {
}