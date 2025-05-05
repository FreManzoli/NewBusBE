package it.unife.sample.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.util.UUID;

@Data
@Entity
public class Utente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_utente;

    private String nome;
    private String cognome;
    private String telefono;
    private String email;
    private String password;

    public String getPassword() {
        return password;
    }

    public UUID getId_utente() {
        return id_utente;
    }

     public void setId_utente(UUID id_utente) {
        this.id_utente = id_utente;
    }
    public String getEmail() {
        return email;
    }

}
