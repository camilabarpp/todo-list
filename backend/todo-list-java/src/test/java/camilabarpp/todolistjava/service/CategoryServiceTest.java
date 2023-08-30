package camilabarpp.todolistjava.service;

import camilabarpp.todolistjava.exception.NotFoundException;
import camilabarpp.todolistjava.model.category.CategoryEntity;
import camilabarpp.todolistjava.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class CategoryServiceTest {

    @InjectMocks
    private CategoryService service;

    @Mock
    private CategoryRepository repository;

    @Test
    @DisplayName("Devolve uma lista de categorias")
    void findAll_shouldReturnListOfCategories() {
        List<CategoryEntity> categoryList = List.of(
                new CategoryEntity(1L, "Estudos"),
                new CategoryEntity(2L, "Trabalho")
        );

        when(repository.findAll()).thenReturn(categoryList);

        List<CategoryEntity> result = service.findAll();

        assertEquals(categoryList, result);
    }

    @Test
    @DisplayName("Devolve uma categoria pelo nome")
    void findByCategory_shouldReturnCategoryByName() {
        CategoryEntity category = new CategoryEntity(1L, "Estudos");

        when(repository.findAllByCategoryNameContains("Estudos")).thenReturn(category);

        CategoryEntity result = service.findByCategory("Estudos");

        assertEquals(category, result);
    }

    @Test
    @DisplayName("Deve lançar NotFoundException quando não encontrar a categoria pelo nome")
    void findByCategory_shouldThrowNotFoundException() {
        when(repository.findAllByCategoryNameContains("Estudos")).thenReturn(null);

        assertThrows(NotFoundException.class, () -> service.findByCategory("Estudos"));
    }

    @Test
    @DisplayName("Deve salvar uma categoria")
    void save_shouldSaveCategory() {
        CategoryEntity category = new CategoryEntity(1L, "Estudos");

        when(repository.save(category)).thenReturn(category);

        CategoryEntity result = service.save(category);

        assertEquals(category, result);
    }
}