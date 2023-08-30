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
import java.util.Optional;

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

    @Test
    @DisplayName("Deve encontrar uma categoria pelo id")
    void findById_shouldFindCategoryById() {
        CategoryEntity category = new CategoryEntity(1L, "Estudos");

        when(repository.findById(1L)).thenReturn(Optional.of(category));

        CategoryEntity result = service.findById(1L);

        assertEquals(category, result);
    }

    @Test
    @DisplayName("Deve lançar NotFoundException quando não encontrar a categoria pelo id")
    void findById_shouldThrowNotFoundException() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.findById(1L));
    }

    @Test
    @DisplayName("Deve atualizar uma categoria")
    void update_shouldUpdateCategory() {
        CategoryEntity category = new CategoryEntity(1L, "Estudos");

        when(repository.findById(1L)).thenReturn(Optional.of(category));
        when(repository.save(category)).thenReturn(category);

        Optional<CategoryEntity> result = service.update(1L, category);

        assertEquals(category, result.get());
    }

    @Test
    @DisplayName("Deve lançar NotFoundException quando não encontrar a categoria pelo id para atualizar")
    void update_shouldThrowNotFoundException() {
        CategoryEntity category = new CategoryEntity(1L, "Estudos");

        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.update(1L, category));
    }

    @Test
    @DisplayName("Deve deletar uma categoria pelo id")
    void deleteById_shouldDeleteCategoryById() {
        CategoryEntity category = new CategoryEntity(1L, "Estudos");

        when(repository.save(category)).thenReturn(category);
        when(repository.findById(1L)).thenReturn(Optional.of(category));

        service.deleteById(1L);

        verify(repository, times(1)).deleteById(1L);
        assertEquals(0, repository.findAll().size());
    }

    @Test
    @DisplayName("Deve deletar todas as categorias")
    void deleteAll_shouldDeleteAllCategories() {
        doNothing().when(repository).deleteAll();

        service.deleteAll();

        verify(repository, times(1)).deleteAll();
    }
}