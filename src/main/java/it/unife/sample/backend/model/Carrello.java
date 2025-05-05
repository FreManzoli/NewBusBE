package it.unife.sample.backend.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;
import lombok.Data;

@Data
@Entity
public class Carrello {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_carrello;

    @ManyToOne
    @JoinColumn(name = "utente_id", referencedColumnName = "id_utente")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "viaggio_id", referencedColumnName = "id_viaggio")
    private Viaggio1 viaggio;

    private int quantita;

    

    public UUID getUtenteId() {
        return utente.getId_utente();
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    // Getter per viaggio
    public Viaggio1 getViaggio() {
        return viaggio;
    }

    // Setter per viaggio
    public void setViaggio(Viaggio1 viaggio) {
        this.viaggio = viaggio;
    }

    // Getter e Setter per quantita
    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}