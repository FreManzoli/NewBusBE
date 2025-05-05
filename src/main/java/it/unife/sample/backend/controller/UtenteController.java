package it.unife.sample.backend.controller;

import it.unife.sample.backend.model.Utente;
import it.unife.sample.backend.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.Map;

@RestController
@RequestMapping("/api/Utente-controller")
@CrossOrigin(origins = "*")  //per permettere le chiamate al server da parte del front-end
public class UtenteController {

    @Autowired
    private UtenteService service;

    // Metodo per creare un nuovo utente
    @PostMapping("/utenti")
    public ResponseEntity<Utente> createUtente(@RequestBody Utente utente) {
        Utente nuovoUtente = service.createUtente(utente);
        return ResponseEntity.ok(nuovoUtente);
    }


    // Metodo per eliminare un utente
    @DeleteMapping("/utenti/{email}")
    public ResponseEntity<Void> deleteUtente(@PathVariable String email) {
        service.deleteUtente(email);
        return ResponseEntity.noContent().build();
    }

    //servizio di autenticazione nel caso in cui si sia già registrati
    @PostMapping("/utenti/authenticate")
    public ResponseEntity<Utente> authenticateUtente(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        Utente utente = service.authenticateUtente(email, password);
        return ResponseEntity.ok(utente);
    }

    //metodo per ottenere dati sull'utente a partire dall'email
    @GetMapping("/utenti/email/{email}")
    public ResponseEntity<Utente> getUtenteByEmail(@PathVariable String email) {
    Utente utente = service.getUtenteByEmail(email);
    return ResponseEntity.ok(utente);
    }
}
