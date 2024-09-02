package br.com.spedro.Meme.controller;

import br.com.spedro.Meme.dtos.MemeRequestDTO;
import br.com.spedro.Meme.dtos.MemeResponseDTO;
import br.com.spedro.Meme.entities.Meme;
import br.com.spedro.Meme.services.MemeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/meme")
public class MemeController {

    private final MemeService memeService;
    private static final Logger LOGGER = LoggerFactory.getLogger(MemeController.class);

    @Autowired
    public MemeController(MemeService memeService) {
        this.memeService = memeService;
    }

    @PostMapping
    public ResponseEntity<MemeResponseDTO> createMeme(@RequestBody @Valid MemeRequestDTO memeDTO) {
        LOGGER.info("Creating new Meme {}", memeDTO.getTitle());
        MemeResponseDTO response = memeService.createMeme(memeDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .build()
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping(value = "/daily-meme")
    public ResponseEntity<MemeResponseDTO> dailyMeme() {
        LOGGER.info("Getting daily meme...");
        MemeResponseDTO response = memeService.dailyMeme();

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/find-all")
    public ResponseEntity<Iterable<MemeResponseDTO>> findAllMemes() {
        LOGGER.info("Finding all memes...");
        List<MemeResponseDTO> memes = memeService.findAllMemes();

        return ResponseEntity.ok(memes);
    }

    @GetMapping(value = "/find-by-id/{id}")
    public ResponseEntity<MemeResponseDTO> findMemeById(@PathVariable(value = "id") String id) {
        LOGGER.info("Finding meme by id {}", id);
        MemeResponseDTO response = memeService.findMemeById(id);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteMeme(@PathVariable(value = "id") String id) {
        LOGGER.info("Deleting meme by id {}", id);
        memeService.deleteMeme(id);

        return ResponseEntity.ok("Meme deleted successfully");
    }

}
