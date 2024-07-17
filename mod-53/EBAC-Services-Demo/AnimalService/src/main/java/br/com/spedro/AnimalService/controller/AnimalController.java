package br.com.spedro.AnimalService.controller;

import br.com.spedro.AnimalService.entity.Animal;
import br.com.spedro.AnimalService.repository.IAnimalRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/animals")
public class AnimalController {

    private IAnimalRepository animalRepository;

    public AnimalController(IAnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @GetMapping
    private List<Animal> findAll() {
        return animalRepository.findAll();
    }

    @PostMapping
    private Animal create(@RequestBody Animal animal) {
        return animalRepository.save(animal);
    }

    @GetMapping(value = "/not-adopted")
    private List<Animal> findNotAdopted() {
        return animalRepository.findNotAdopted();
    }

    @GetMapping(value = "/adopted")
    private List<Animal> findAdopted() {
        return animalRepository.findAdopted();
    }

    @GetMapping(value = "/adopted-by/{receiverName}")
    private Integer animalsAdoptedByWorker(@PathVariable("receiverName") String receiverName) {
        return animalRepository.animalsAdoptedByWorker(receiverName);
    }

}
