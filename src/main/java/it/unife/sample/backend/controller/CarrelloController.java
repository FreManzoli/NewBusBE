package it.unife.sample.backend.controller;

import it.unife.sample.backend.model.Carrello;
import it.unife.sample.backend.model.Viaggio1;
import it.unife.sample.backend.service.CarrelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import it.unife.sample.backend.model.Utente;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/carrello")
@CrossOrigin(origins = "*") // Permette chiamate dal front-end
public class CarrelloController {

    @Autowired
    private CarrelloService carrelloService;

    // Aggiungi un viaggio al carrello //http://localhost:8080/api/carrello/aggiungi?idUtente=5c10ff5b-25fc-4846-a308-f5d22501f2eb&idViaggio=56303037-0000-0000-0000-000000000000&quantita=2
    @PostMapping("/aggiungi")
    public ResponseEntity<Carrello> addViaggioToCarrello(@RequestParam UUID idUtente,@RequestParam UUID idViaggio,@RequestParam int quantita) {
        Carrello carrello = carrelloService.addViaggioToCarrello(idUtente, idViaggio, quantita);
        return ResponseEntity.ok(carrello);
    }

    // Visualizza tutti i viaggi nel carrello di un utente
    @GetMapping("/{idUtente}")//http://localhost:8080/api/carrello/5c10ff5b-25fc-4846-a308-f5d22501f2eb
    public ResponseEntity<List<Carrello>> getCarrelloByUtente(@PathVariable UUID idUtente) {
        List<Carrello> carrello = carrelloService.getCarrelloByUtente(idUtente);
        return ResponseEntity.ok(carrello);
    }

    // Rimuovi un viaggio dal carrello
    @DeleteMapping("/rimuovi")//http://localhost:8080/api/carrello/rimuovi?idUtente=5c10ff5b-25fc-4846-a308-f5d22501f2eb&idViaggio=56303037-0000-0000-0000-000000000000
    public ResponseEntity<Void> removeViaggioFromCarrello(
            @RequestParam UUID idUtente,
            @RequestParam UUID idViaggio) {
        carrelloService.removeViaggioFromCarrello(idUtente, idViaggio);
        return ResponseEntity.noContent().build();
    }

    // Calcola il totale del carrello
    @GetMapping("/totale/{idUtente}")
    public ResponseEntity<Double> calcolaTotaleCarrello(@PathVariable UUID idUtente) {
        double totale = carrelloService.calcolaTotaleCarrello(idUtente);
        return ResponseEntity.ok(totale);
    }
}
