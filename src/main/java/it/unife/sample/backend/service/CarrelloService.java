package it.unife.sample.backend.service;


import it.unife.sample.backend.model.Carrello;
import it.unife.sample.backend.model.Viaggio1;
import it.unife.sample.backend.repository.CarrelloRepository;
import it.unife.sample.backend.repository.UtenteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import it.unife.sample.backend.repository.Viaggio1Repository;
import it.unife.sample.backend.repository.UtenteRepository;
import java.util.List;
import java.util.UUID;

import it.unife.sample.backend.model.Utente;
@Service
public class CarrelloService {

    @Autowired
    private CarrelloRepository repository;
    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private Viaggio1Repository viaggioRepository; // 


    //aggiungo viaggi al carrello
    public Carrello addViaggioToCarrello(UUID idUtente, UUID idViaggio, int quantita) {
        // 1. Recupera utente e viaggio esistenti
        Utente utente = utenteRepository.findById(idUtente)
            .orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
    
        Viaggio1 viaggio = viaggioRepository.findById(idViaggio)
            .orElseThrow(() -> new IllegalArgumentException("Viaggio non trovato"));
    
        // 2. Crea il carrello e imposta i dati
        Carrello carrello = new Carrello();
        carrello.setUtente(utente);
        carrello.setViaggio(viaggio);
        carrello.setQuantita(quantita);
    
        return repository.save(carrello);
    }
    
    
    //visualizzo i viaggi del cliente
    public List<Carrello> getCarrelloByUtente(UUID idUtente) {
        return repository.findByUtenteId(idUtente); // Recupera tutti i viaggi associati all'utente
    }

    //elimina un viaggio dal carrello
    public void removeViaggioFromCarrello(UUID idUtente, UUID idViaggio) {
        Carrello carrello = repository.findByUtenteIdAndViaggioId(idUtente, idViaggio)
                .orElseThrow(() -> new IllegalArgumentException("Elemento non trovato nel carrello"));
        repository.delete(carrello); // Elimina la riga dal carrello
    }

    //calcolo il totale del carrello http://localhost:8080/api/carrello/totale/5c10ff5b-25fc-4846-a308-f5d22501f2eb
    public double calcolaTotaleCarrello(UUID idUtente) {
        List<Carrello> carrello = repository.findByUtenteId(idUtente);
        return carrello.stream()
                .mapToDouble(c -> c.getViaggio().getCosto() * c.getQuantita())
                .sum();
    }
}
