package br.com.spedro.Category.utils;

import br.com.spedro.Category.dto.CategoryRequestDTO;
import br.com.spedro.Category.dto.CategoryResponseDTO;
import br.com.spedro.Category.entities.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryUtils {

    public static CategoryResponseDTO convertEntityToResponseDto(Category category) {
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setName(category.getName());
        categoryResponseDTO.setDescription(category.getDescription());
        categoryResponseDTO.setRegistrationDate(category.getRegistrationDate());
        return categoryResponseDTO;
    }

    public static Category convertResponseDtoToEntity(CategoryResponseDTO categoryResponseDto) {
        Category category = new Category();
        category.setName(categoryResponseDto.getName());
        category.setDescription(categoryResponseDto.getDescription());
        category.setRegistrationDate(categoryResponseDto.getRegistrationDate());
        return category;
    }

    public static CategoryRequestDTO convertEntityToRequestDto(Category category) {
        CategoryRequestDTO categoryRequestDTO = new CategoryRequestDTO();
        categoryRequestDTO.setName(category.getName());
        categoryRequestDTO.setDescription(category.getDescription());
        return categoryRequestDTO;
    }

    public static List<CategoryResponseDTO> convertEntityListToResponseDtoList(List<Category> categoryList) {
        List<CategoryResponseDTO> categoryResponseDtoList = new ArrayList<>();
        categoryList.forEach(category -> categoryResponseDtoList.add(convertEntityToResponseDto(category)));

        return categoryResponseDtoList;
    }

    public static Category convertRequestDtoToEntity(CategoryRequestDTO categoryRequestDTO) {
        Category category = new Category();
        category.setName(categoryRequestDTO.getName());
        category.setDescription(categoryRequestDTO.getDescription());
        return category;
    }

}
