package it.unife.sample.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.util.UUID;

@Data
@Entity
public class Viaggio2 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID classe;

    private double costo;

    public double getPrezzo() {
        return costo;
    }
}
