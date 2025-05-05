package it.unife.sample.backend.repository;

import it.unife.sample.backend.model.Viaggio1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface Viaggio1Repository extends JpaRepository<Viaggio1, UUID> {

    List<Viaggio1> findByPartenzaAndArrivo(String partenza, String arrivo); //questa chiamata effettua la seguente query sql: SELECT * FROM viaggio1 WHERE partenza = ? AND arrivo = ?;
    List<Viaggio1> findByPartenza(String partenza); //questa chiamata effettua la seguente query sql: SELECT * FROM viaggio1 WHERE partenza = ?;

    @Query("SELECT v.arrivo, b.targa, b.lunghezza, b.larghezza " +
       "FROM Viaggio1 v JOIN v.targa b " +
       "WHERE v.partenza = :partenza AND v.arrivo = :arrivo")
    List<Object[]> findCustomByPartenzaAndArrivo(@Param("partenza") String partenza,@Param("arrivo") String arrivo);

    @Query("SELECT v FROM Viaggio1 v JOIN v.classe c WHERE v.partenza = :partenza AND v.arrivo = :arrivo AND c.costo BETWEEN :min AND :max")
    List<Viaggio1> findByCosto(@Param("min") double min, @Param("max") double max, @Param("partenza") String partenza,@Param("arrivo") String arrivo);

    @Query("SELECT v FROM Viaggio1 v JOIN v.classe c WHERE v.partenza = :partenza AND c.costo BETWEEN :min AND :max")
    List<Viaggio1> findByCosto1(@Param("min") double min, @Param("max") double max, @Param("partenza") String partenza);

    @Query("SELECT v FROM Viaggio1 v WHERE  v.partenza = :partenza AND v.arrivo = :arrivo AND v.ora_partenza >= :min ")
    List<Viaggio1> findByOrario(@Param("min") String min, @Param("partenza") String partenza, @Param("arrivo") String arrivo);

    @Query("SELECT v FROM Viaggio1 v WHERE  v.partenza = :partenza AND v.ora_partenza >= :min ")
    List<Viaggio1> findByOrario1(@Param("min") String min, @Param("partenza") String partenza);
}