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

    //funzione che estrae l'ID utente dal token JWT presente nell'intestazione della richiesta ( le operazioni del carrello possono svolgersi solo da autenticati)
    private UUID extractUserIdFromToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Token mancante o non valido");
        }
        String token = authHeader.substring(7); // Rimuove "Bearer " dall'intestazione
        return jwtUtil.extractUserId(token); // Estrai l'ID utente dal token
    }

    @PostMapping("/aggiungi") // Aggiunge un viaggio al carrello
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

    @GetMapping // visualizza i viaggi del cliente
    public ResponseEntity<List<Carrello>> getCarrelloByUtente(HttpServletRequest request) {
        UUID idUtente = extractUserIdFromToken(request);
        List<Carrello> carrello = carrelloService.getCarrelloByUtente(idUtente);
        return ResponseEntity.ok(carrello);
    }

    @DeleteMapping("/rimuovi")// Elimina un viaggio dal carrello
    public ResponseEntity<Void> removeViaggioFromCarrello(HttpServletRequest request,@RequestParam UUID idViaggio) {

        UUID idUtente = extractUserIdFromToken(request);
        carrelloService.removeViaggioFromCarrello(idUtente, idViaggio);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/totale")//calcola il totale del carrello
    public ResponseEntity<Double> calcolaTotaleCarrello(HttpServletRequest request) {
        UUID idUtente = extractUserIdFromToken(request);
        double totale = carrelloService.calcolaTotaleCarrello(idUtente);
        return ResponseEntity.ok(totale);
    }

    @DeleteMapping("/svuota")//svuota il carrello
    public ResponseEntity<Void> svuotaCarrello(HttpServletRequest request) {
        try {
            UUID idUtente = extractUserIdFromToken(request);
            System.out.println("ID utente estratto: " + idUtente);
            carrelloService.svuotaCarrello(idUtente);
            System.out.println("Carrello svuotato con successo");
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}

