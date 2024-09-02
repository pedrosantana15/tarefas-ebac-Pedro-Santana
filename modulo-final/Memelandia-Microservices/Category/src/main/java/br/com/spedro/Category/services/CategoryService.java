package br.com.spedro.Category.services;

import br.com.spedro.Category.dto.CategoryRequestDTO;
import br.com.spedro.Category.dto.CategoryResponseDTO;
import br.com.spedro.Category.entities.Category;
import br.com.spedro.Category.repositories.ICategoryRepository;
import br.com.spedro.Category.utils.CategoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Component
public class CategoryService {

    private final ICategoryRepository categoryRepository;

    @Autowired
    public CategoryService(ICategoryRepository categoryService) {
        this.categoryRepository = categoryService;
    }

    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryDto) {
        Category category = CategoryUtils.convertRequestDtoToEntity(categoryDto);
        category.setRegistrationDate(Instant.now());
        categoryRepository.insert(category);

        return CategoryUtils.convertEntityToResponseDto(category);
    }

    public CategoryResponseDTO findCategoryById(String id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        return CategoryUtils.convertEntityToResponseDto(category);
    }

    public List<CategoryResponseDTO> findAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return CategoryUtils.convertEntityListToResponseDtoList(categories);
    }

    public Boolean isRegistered(String id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.isPresent();
    }

}
