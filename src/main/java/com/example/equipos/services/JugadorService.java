package com.example.equipos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.equipos.models.Jugador;
import com.example.equipos.repositories.JugadorRepository;

@Service
public class JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    public List<Jugador> findAll() {
        return jugadorRepository.findAll();
    }

    public Jugador findById(Long id) {
        return jugadorRepository.findById(id).orElse(null);
    }

    public Jugador save(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    public void deleteById(Long id) {
        jugadorRepository.deleteById(id);
    }
}