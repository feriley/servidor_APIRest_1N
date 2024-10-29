package com.example.equipos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.equipos.dto.JugadorDTO;
import com.example.equipos.models.Jugador;
import com.example.equipos.services.JugadorService;

@RestController
@RequestMapping("/api/jugadores")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class JugadorController {

    @Autowired
    private JugadorService jugadorService;

    // 3. Consultar todos los jugadores (Debe verse solo el nombre del equipo)
    @GetMapping
    public List<JugadorDTO> getAllJugadores() {
        return jugadorService.findAll().stream().map(jugador -> {
            JugadorDTO dto = new JugadorDTO();
            dto.setIdjugador(jugador.getIdjugador());
            dto.setNombre(jugador.getNombre());
            dto.setEdad(jugador.getEdad());
            if (jugador.getEquipo() != null) {
                dto.setNombreEquipo(jugador.getEquipo().getNombre());
            }
            return dto;
        }).collect(Collectors.toList());
    }

    // 4. Crear nuevo jugador
    @PostMapping
    public Jugador createJugador(@RequestBody Jugador jugador) {
        return jugadorService.save(jugador);
    }

    // 8. Borrar jugador (No debe borrarse su equipo)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJugador(@PathVariable Long id) {
        Jugador jugador = jugadorService.findById(id);
        if (jugador == null) {
            return ResponseEntity.notFound().build();
        }
        jugador.setEquipo(null); // Eliminamos la relación con el equipo
        jugadorService.save(jugador); // Guardamos el cambio antes de borrar el jugador
        jugadorService.deleteById(id);
        return ResponseEntity.ok("Jugador eliminado");
    }
}
