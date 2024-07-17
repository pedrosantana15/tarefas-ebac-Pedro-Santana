package br.com.spedro.AnimalService.repository;

import br.com.spedro.AnimalService.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAnimalRepository extends JpaRepository<Animal, Integer> {

    @Query("SELECT a FROM Animal a WHERE a.adoptionDate IS NULL ORDER BY a.entryDate ASC")
    List<Animal> findNotAdopted();

    @Query("SELECT a FROM Animal a WHERE a.adoptionDate IS NOT NULL")
    List<Animal> findAdopted();

    @Query("SELECT COUNT(a) FROM Animal a WHERE a.receiverName = :name")
    Integer animalsAdoptedByWorker(@Param(value = "name") String name);
}
