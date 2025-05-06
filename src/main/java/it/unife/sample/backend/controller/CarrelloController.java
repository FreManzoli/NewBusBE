package it.unife.sample.backend.controller;

import it.unife.sample.backend.model.Carrello;
import it.unife.sample.backend.model.Viaggio1;
import it.unife.sample.backend.security.JwtUtil;
import it.unife.sample.backend.service.CarrelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import it.unife.sample.backend.model.Utente;
import java.util.List;
import java.util.UUID;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/api/carrello")
@CrossOrigin(origins = "*")
public class CarrelloController {

    @Autowired
    private CarrelloService carrelloService;

    @Autowired
    private JwtUtil jwtUtil;

    private UUID extractUserIdFromToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Token mancante o non valido");
        }
        String token = authHeader.substring(7); // Rimuove "Bearer " dall'intestazione
        return jwtUtil.extractUserId(token); // Estrai l'ID utente dal token
    }

    @PostMapping("/aggiungi")
    public ResponseEntity<Carrello> addViaggioToCarrello(HttpServletRequest request, @RequestParam UUID idViaggio, @RequestParam int quantita) {
        try {
            String authHeader = request.getHeader("Authorization");
            System.out.println("Authorization Header: " + authHeader);
            UUID idUtente = extractUserIdFromToken(request);
            System.out.println("ID Utente estratto: " + idUtente);
            Carrello carrello = carrelloService.addViaggioToCarrello(idUtente, idViaggio, quantita);
            return ResponseEntity.ok(carrello);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Carrello>> getCarrelloByUtente(HttpServletRequest request) {
        UUID idUtente = extractUserIdFromToken(request);
        List<Carrello> carrello = carrelloService.getCarrelloByUtente(idUtente);
        return ResponseEntity.ok(carrello);
    }

    @DeleteMapping("/rimuovi")
    public ResponseEntity<Void> removeViaggioFromCarrello(HttpServletRequest request,@RequestParam UUID idViaggio) {

        UUID idUtente = extractUserIdFromToken(request);
        carrelloService.removeViaggioFromCarrello(idUtente, idViaggio);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/totale")
    public ResponseEntity<Double> calcolaTotaleCarrello(HttpServletRequest request) {
        UUID idUtente = extractUserIdFromToken(request);
        double totale = carrelloService.calcolaTotaleCarrello(idUtente);
        return ResponseEntity.ok(totale);
    }

    @DeleteMapping("/svuota")
    public ResponseEntity<Void> svuotaCarrello(HttpServletRequest request) {
        try {
            UUID idUtente = extractUserIdFromToken(request);
            carrelloService.svuotaCarrello(idUtente);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}

