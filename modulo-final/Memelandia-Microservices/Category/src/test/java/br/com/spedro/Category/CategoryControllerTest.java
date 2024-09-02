package br.com.spedro.Category;

import br.com.spedro.Category.controller.CategoryController;
import br.com.spedro.Category.dto.CategoryRequestDTO;
import br.com.spedro.Category.dto.CategoryResponseDTO;
import br.com.spedro.Category.entities.Category;
import br.com.spedro.Category.services.CategoryService;
import br.com.spedro.Category.utils.CategoryUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    public CategoryRequestDTO buildCategory() {
        CategoryRequestDTO category = new CategoryRequestDTO();
        category.setName("Comedy");
        category.setDescription("The best comedy memes");

        return category;
    }

    /**
     * The method "createMeme" uses URI Servlet and requires an HTTP context. We can't simulate this context in a
     *     unit test. That's why this method can't work.
     * @deprecated
     */
//    @Test
//    public void testCreateCategory_HappyPath() {
//        CategoryRequestDTO categoryDto = buildCategory();
//        Category category = CategoryUtils.convertRequestDtoToEntity(categoryDto);
//
//        Mockito.when(categoryService.createCategory(categoryDto))
//                .thenReturn(CategoryUtils.convertEntityToResponseDto(category));
//
//        ResponseEntity<CategoryResponseDTO> createdCategory = categoryController.createCategory(categoryDto);
//
//        assertNotNull(createdCategory);
//        assertEquals(category.getName(), createdCategory.getBody());
//        assertEquals(category.getDescription(), createdCategory.getBody().getDescription());
//    }

    @Test
    public void testFindCategoryById_HappyPath() {
        CategoryRequestDTO categoryDto = buildCategory();
        Category category = CategoryUtils.convertRequestDtoToEntity(categoryDto);

        Mockito.when(categoryService.findCategoryById(category.getId()))
                .thenReturn(CategoryUtils.convertEntityToResponseDto(category));

        ResponseEntity<CategoryResponseDTO> foundCategory = categoryController.findCategoryById(category.getId());

        assertNotNull(foundCategory);
        assertEquals(category.getName(), foundCategory.getBody().getName());
        assertEquals(category.getDescription(), foundCategory.getBody().getDescription());
    }

    @Test
    public void testFindCategoryById_InvalidId() {
        Mockito.when(categoryService.findCategoryById("A1")).thenReturn(null);

        ResponseEntity<CategoryResponseDTO> foundCategory = categoryController.findCategoryById("A1");

        assertNull(foundCategory.getBody());
    }

    @Test
    public void testFindAllCategories_HappyPath() {
        CategoryRequestDTO category1 = buildCategory();
        CategoryRequestDTO category2 = buildCategory();

        List<Category> categories = new ArrayList<>();
        categories.add(CategoryUtils.convertRequestDtoToEntity(category1));
        categories.add(CategoryUtils.convertRequestDtoToEntity(category2));

        Mockito.when(categoryService.findAllCategories())
                .thenReturn(CategoryUtils.convertEntityListToResponseDtoList(categories));

        ResponseEntity<List<CategoryResponseDTO>> list = categoryController.findAllCategories();

        assertNotNull(list);
        assertEquals(categories.size(), list.getBody().size());
    }

    @Test
    public void testIsCategoryRegistered_HappyPath() {
        CategoryRequestDTO categoryDto = buildCategory();
        Category category = CategoryUtils.convertRequestDtoToEntity(categoryDto);

        Mockito.when(categoryService.isRegistered(category.getId())).thenReturn(true);

        ResponseEntity<Boolean> isRegistered = categoryController.isRegistered(category.getId());

        assertNotNull(isRegistered.getBody());
        assertTrue(isRegistered.getBody());
    }

    @Test
    public void testIsCategoryRegistered_NotRegistered() {
        CategoryRequestDTO categoryDto = buildCategory();
        Category category = CategoryUtils.convertRequestDtoToEntity(categoryDto);

        Mockito.when(categoryService.isRegistered(category.getId())).thenReturn(false);

        ResponseEntity<Boolean> isRegistered = categoryController.isRegistered(category.getId());

        assertNotNull(isRegistered.getBody());
        assertFalse(isRegistered.getBody());
    }

}
