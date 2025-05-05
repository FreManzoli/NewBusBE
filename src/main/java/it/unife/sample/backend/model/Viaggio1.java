package it.unife.sample.backend.model;
 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import java.util.UUID;

@Data
@Entity
public class Viaggio1 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_viaggio;
    private String partenza;
    private String arrivo;
    private String ora_arrivo;
    private String ora_partenza;

    // Foreign key to another entity (e.g., "Autobus")
    @ManyToOne
    @JoinColumn(name = "targa", nullable = false)
    public Bus targa;

    // Foreign key to another entity (e.g., "Autista")
    @ManyToOne
    @JoinColumn(name = "classe", nullable = false)
    public Viaggio2 classe;

    public UUID getId_viaggio() {
        return id_viaggio;
    }

    public void setId_viaggio(UUID id_viaggio) {
        this.id_viaggio = id_viaggio;
    }

    public double getCosto() {
        return classe.getPrezzo();
    }

    
}
