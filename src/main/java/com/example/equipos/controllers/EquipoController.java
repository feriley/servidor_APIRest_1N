package com.example.equipos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.equipos.models.Equipo;
import com.example.equipos.models.Jugador;
import com.example.equipos.services.EquipoService;
import com.example.equipos.services.JugadorService;

@RestController
@RequestMapping("/api/equipos")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @Autowired
    private JugadorService jugadorService;

    // 1. Consultar todos los equipos (con jugadores)
    @GetMapping
    public List<Equipo> getAllEquipos() {
        return equipoService.findAll();
    }

    // 2. Consultar un solo equipo (con jugadores)
    @GetMapping("/{id}")
    public ResponseEntity<Equipo> getEquipoById(@PathVariable Long id) {
        Equipo equipo = equipoService.findById(id);
        return equipo != null ? ResponseEntity.ok(equipo) : ResponseEntity.notFound().build();
    }

    // 5. Crear nuevo equipo
    @PostMapping
    public Equipo createEquipo(@RequestBody Equipo equipo) {
        return equipoService.save(equipo);
    }

    // 6. Inscribir a un jugador en un equipo
    @PostMapping("/{equipoId}/jugadores/{jugadorId}")
    public ResponseEntity<String> inscribirJugador(@PathVariable Long equipoId, @PathVariable Long jugadorId) {
        Equipo equipo = equipoService.findById(equipoId);
        Jugador jugador = jugadorService.findById(jugadorId);
        
        if (equipo == null || jugador == null) {
            return ResponseEntity.notFound().build();
        }

        jugador.setEquipo(equipo);
        jugadorService.save(jugador);
        return ResponseEntity.ok("Jugador inscrito en el equipo");
    }

    // 7. Borrar equipo (sin borrar sus jugadores)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEquipo(@PathVariable Long id) {
        Equipo equipo = equipoService.findById(id);
        if (equipo == null) {
            return ResponseEntity.notFound().build();
        }
        // Quitamos el equipo de cada jugador asociado
        for (Jugador jugador : equipo.getJugadores()) {
            jugador.setEquipo(null);
            jugadorService.save(jugador); // Actualizamos el jugador para eliminar la relación con el equipo
        }
        equipoService.deleteById(id);
        return ResponseEntity.ok("Equipo eliminado");
    }
}
