package it.unife.sample.backend.controller;

import it.unife.sample.backend.model.Viaggio1;
import it.unife.sample.backend.service.Viaggio1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Viaggio1-controller")
@CrossOrigin(origins = "*")  //per permettere le chiamate al server da parte del front-end
public class Viaggio1Controller {

    @Autowired
    private Viaggio1Service service1;

    @GetMapping("/partenza-arrivo")  //nel front-end avremo la form che chiama un API collegata all'ulr http://localhost:8080/api/Viaggio1-controller/partenza-arrivo?partenza=...&arrivo=...
    public ResponseEntity<List<Viaggio1>> searchByPartenzaAndArrivo(@RequestParam String partenza, @RequestParam String arrivo) {   //ResponseEntity rappresenta l'intera risposta http
        List<Viaggio1> viaggio = service1.findByPartenzaAndArrivo(partenza, arrivo);

        if((partenza.equals("") || arrivo.equals(""))){ //se il form è stato compilato male dall'utente ritorni codice 400 BAD REQUEST senza body nella risposta
            ResponseEntity.badRequest().build();
        }

        if(viaggio.isEmpty()){
            return ResponseEntity.notFound().build();   //se non trova enssun record nel db che soddisfi la richiesta ritorna codice 404 NOT FOUND
        }else{
            return ResponseEntity.ok(viaggio);  //se invece lo trova resituisce codice 200 OK e mette nel body della risposta i record del db che soddisfano la richiesta
        }
    }


    @GetMapping("/partenza")  //nel front-end avremo la form che chiama un API collegata all'ulr http://localhost:8080/api/Viaggio1-controller/partenza?partenza=...
    public ResponseEntity<List<Viaggio1>> searchByPartenza(@RequestParam String partenza) {
        List<Viaggio1> viaggio = service1.findByPartenza(partenza);

        if((partenza.equals(""))){ //se il form è stato compilato male dall'utente ritorni codice 400 BAD REQUEST senza body nella risposta
            ResponseEntity.badRequest().build();
        }

        if(viaggio.isEmpty()){
            return ResponseEntity.notFound().build();   //se non trova enssun record nel db che soddisfi la richiesta ritorna codice 404 NOT FOUND
        }else{
            return ResponseEntity.ok(viaggio);  //se invece lo trova resituisce codice 200 OK e mette nel body della risposta i record del db che soddisfano la richiesta
        }
    }


    @GetMapping("/parcheggio")  //nel front-end avremo la form che chiama un API collegata all'ulr http://localhost:8080/api/Viaggio1-controller/parcheggio?partenza=...&arrivo=...
    public ResponseEntity<List<Object[]>> findCustomByPartenzaAndArrivo(@RequestParam String partenza, @RequestParam String arrivo) {   //ResponseEntity rappresenta l'intera risposta http
        List<Object[]> viaggio = service1.findCustomByPartenzaAndArrivo(partenza, arrivo);

        if((partenza.equals("") || arrivo.equals(""))){ //se il form è stato compilato male dall'utente ritorni codice 400 BAD REQUEST senza body nella risposta
            ResponseEntity.badRequest().build();
        }

        if(viaggio.isEmpty()){
            return ResponseEntity.notFound().build();   //se non trova enssun record nel db che soddisfi la richiesta ritorna codice 404 NOT FOUND
        }else{
            return ResponseEntity.ok(viaggio);  //se invece lo trova resituisce codice 200 OK e mette nel body della risposta i record del db che soddisfano la richiesta
        }
    }


    @GetMapping("/costo-partenza-arrivo")  //nel front-end avremo la form che chiama un API collegata all'ulr http://localhost:8080/api/Viaggio1-controller/costo-partenza-arrivo?min=...&max=...&partenza=...&arrivo=...
    public ResponseEntity<List<Viaggio1>> findByCosto(@RequestParam double min, @RequestParam double max, @RequestParam String partenza, @RequestParam String arrivo) {   //ResponseEntity rappresenta l'intera risposta http
        List<Viaggio1> viaggio = service1.findByCosto(min,max, partenza, arrivo);

        if(min<0 || max<min || partenza.equals("") || arrivo.equals("")){ //se il form è stato compilato male dall'utente ritorni codice 400 BAD REQUEST senza body nella risposta
            ResponseEntity.badRequest().build();
        }

        if(viaggio.isEmpty()){
            return ResponseEntity.notFound().build();   //se non trova enssun record nel db che soddisfi la richiesta ritorna codice 404 NOT FOUND
        }else{
            return ResponseEntity.ok(viaggio);  //se invece lo trova resituisce codice 200 OK e mette nel body della risposta i record del db che soddisfano la richiesta
        }
    }


    @GetMapping("/costo-partenza")  //nel front-end avremo la form che chiama un API collegata all'ulr http://localhost:8080/api/Viaggio1-controller/costo-partenza?min=...&max=...&partenza=...
    public ResponseEntity<List<Viaggio1>> findByCosto1(@RequestParam double min, @RequestParam double max, @RequestParam String partenza) {   //ResponseEntity rappresenta l'intera risposta http
        List<Viaggio1> viaggio = service1.findByCosto1(min,max, partenza);

        if(min<0 || max<min || partenza.equals("")){ //se il form è stato compilato male dall'utente ritorni codice 400 BAD REQUEST senza body nella risposta
            ResponseEntity.badRequest().build();
        }

        if(viaggio.isEmpty()){
            return ResponseEntity.notFound().build();   //se non trova enssun record nel db che soddisfi la richiesta ritorna codice 404 NOT FOUND
        }else{
            return ResponseEntity.ok(viaggio);  //se invece lo trova resituisce codice 200 OK e mette nel body della risposta i record del db che soddisfano la richiesta
        }
    }


    @GetMapping("/orario-partenza-arrivo")  //nel front-end avremo la form che chiama un API collegata all'ulr http://localhost:8080/api/Viaggio1-controller/orario-partenza-arrivo?min=...&partenza=...&arrivo=...
    public ResponseEntity<List<Viaggio1>> findByOrario(@RequestParam String min, @RequestParam String partenza, @RequestParam String arrivo) {   //ResponseEntity rappresenta l'intera risposta http
        List<Viaggio1> viaggio = service1.findByOrario(min, partenza, arrivo);

        if(min.equals("") || partenza.equals("") || arrivo.equals("")){ //se il form è stato compilato male dall'utente ritorni codice 400 BAD REQUEST senza body nella risposta
            ResponseEntity.badRequest().build();
        }

        if(viaggio.isEmpty()){
            return ResponseEntity.notFound().build();   //se non trova enssun record nel db che soddisfi la richiesta ritorna codice 404 NOT FOUND
        }else{
            return ResponseEntity.ok(viaggio);  //se invece lo trova resituisce codice 200 OK e mette nel body della risposta i record del db che soddisfano la richiesta
        }
    }


    @GetMapping("/orario-partenza")  //nel front-end avremo la form che chiama un API collegata all'ulr http://localhost:8080/api/Viaggio1-controller/orario-partenza?min=...&partenza=...
    public ResponseEntity<List<Viaggio1>> findByOrario1(@RequestParam String min, @RequestParam String partenza) {   //ResponseEntity rappresenta l'intera risposta http
        List<Viaggio1> viaggio = service1.findByOrario1(min, partenza);

        if(min.equals("") || partenza.equals("")){ //se il form è stato compilato male dall'utente ritorni codice 400 BAD REQUEST senza body nella risposta
            ResponseEntity.badRequest().build();
        }

        if(viaggio.isEmpty()){
            return ResponseEntity.notFound().build();   //se non trova enssun record nel db che soddisfi la richiesta ritorna codice 404 NOT FOUND
        }else{
            return ResponseEntity.ok(viaggio);  //se invece lo trova resituisce codice 200 OK e mette nel body della risposta i record del db che soddisfano la richiesta
        }
    }
}