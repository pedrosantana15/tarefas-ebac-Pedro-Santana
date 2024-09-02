package br.com.spedro.Category.controller;

import br.com.spedro.Category.dto.CategoryRequestDTO;
import br.com.spedro.Category.dto.CategoryResponseDTO;
import br.com.spedro.Category.entities.Category;
import br.com.spedro.Category.services.CategoryService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {

    private final CategoryService categoryService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody @Valid CategoryRequestDTO categoryDto) {
        LOGGER.info("Creating new Category: {}", categoryDto.getName());

        CategoryResponseDTO response = categoryService.createCategory(categoryDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .build()
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping(value = "/find-by-id/{id}")
    public ResponseEntity<CategoryResponseDTO> findCategoryById(@PathVariable(value = "id") String id) {
        LOGGER.info("Finding Category by id: {}", id);
        CategoryResponseDTO response = categoryService.findCategoryById(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/find-all")
    public ResponseEntity<List<CategoryResponseDTO>> findAllCategories() {
        LOGGER.info("Finding all Categories...");
        List<CategoryResponseDTO> response = categoryService.findAllCategories();

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/is-registered/{id}")
    public ResponseEntity<Boolean> isRegistered(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok(categoryService.isRegistered(id));
    }

}
