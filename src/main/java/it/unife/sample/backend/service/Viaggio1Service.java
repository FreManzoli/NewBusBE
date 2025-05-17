package it.unife.sample.backend.service;

import it.unife.sample.backend.model.Viaggio1;
import it.unife.sample.backend.repository.Viaggio1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class Viaggio1Service {

    @Autowired
    private Viaggio1Repository repository;

    // public List<Viaggio1> findAll() {
    //     return repository.findAll();
    // }

    /*
     ritorna tutti i record della tabella Viaggio1 con Partenza = String partenza & Arrivo = String arrivo
     */
    public List<Viaggio1> findByPartenzaAndArrivo(String partenza, String arrivo) {
        return repository.findByPartenzaAndArrivo(partenza, arrivo);
    }

    /*
     ritorna tutti i record della tabella Viaggio1 con Partenza = String partenza 
    */
    public List<Viaggio1> findByPartenza(String partenza) {
        return repository.findByPartenza(partenza);
    }

    /*
     ritorna tutti i record della tabella Viaggio1
    */
    public List<Viaggio1> findAll() {
        return repository.findAll();
    }

    /*
     ritorna la destinzaione e i dati fisici dell'autobus
    */
    public List<Object[]> findBus() {
        return repository.findBus();
    }

    /*
     ritorna tutti i record della tabella Viaggio1 con Partenza = String partenza e arrivo = String arrivo con costo compreso tra min e max
    */
    public List<Viaggio1> findByCosto(double min, double max, String partenza, String arrivo) {
        return repository.findByCosto(min, max, partenza, arrivo);
    }

    /*
     ritorna tutti i record della tabella Viaggio1 con Partenza = String partenza con costo compreso tra min e max
    */
    public List<Viaggio1> findByCosto1(double min, double max, String partenza) {
        return repository.findByCosto1(min, max, partenza);
    }

    /*
     ritorna tutti i record della tabella Viaggio1 con Partenza = String partenza e arrivo = String arrivo con orario di partenza >= min
    */
    public List<Viaggio1> findByOrario(String min, String partenza, String arrivo) {
        return repository.findByOrario(min, partenza, arrivo);
    }

    /*
     ritorna tutti i record della tabella Viaggio1 con Partenza = String partenza e con orario di partenza >= min
    */
    public List<Viaggio1> findByOrario1(String min, String partenza) {
        return repository.findByOrario1(min, partenza);
    }
}