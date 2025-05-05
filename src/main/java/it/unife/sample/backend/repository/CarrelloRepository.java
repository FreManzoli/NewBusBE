package it.unife.sample.backend.repository;

import it.unife.sample.backend.model.Carrello;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarrelloRepository extends JpaRepository<Carrello, UUID> {
    @Query("SELECT c FROM Carrello c WHERE c.utente.id_utente = :idUtente")
    List<Carrello> findByUtenteId(@Param("idUtente") UUID idUtente);

    @Query("SELECT c FROM Carrello c WHERE c.utente.id_utente = :utenteId AND c.viaggio.id_viaggio = :viaggioId")
    Optional<Carrello> findByUtenteIdAndViaggioId(@Param("utenteId") UUID utenteId, @Param("viaggioId") UUID viaggioId);
    

   
}
