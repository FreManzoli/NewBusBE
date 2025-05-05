package it.unife.sample.backend.service;

import it.unife.sample.backend.model.Utente;
import it.unife.sample.backend.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class UtenteService {
    
    @Autowired
    private UtenteRepository repository;

    // Recupera un utente per ID
    public Utente getUtenteById(UUID id) {
        return repository.findById(id).orElseThrow(() -> 
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato con ID: " + id));
    }

    public Utente getUtenteByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> 
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato con email: " + email));
    }

    // Crea un nuovo utente
    public Utente createUtente(Utente utente) {
        System.out.println("Creazione utente: " + utente);

    // Controlla se l'email è già in uso
    if (repository.findByEmail(utente.getEmail()).isPresent()) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email già in uso: " + utente.getEmail());
    }
        
        return repository.save(utente);
    }

    public Utente authenticateUtente(String email, String password) {
        // Cerca l'utente per email
        Utente utente = repository.findByEmail(email).orElseThrow(() -> 
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato con email: " + email));
    
        // Controlla se la password corrisponde
        if (!utente.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password errata");
        }
    
        // Restituisce l'utente se email e password sono corrette
        return utente;
    }

    // Elimina un utente
    public void deleteUtente(String email) {
        Utente utente = getUtenteByEmail(email);
        repository.delete(utente);
    }
}
