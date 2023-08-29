package camilabarpp.todolistjava.service;

import camilabarpp.todolistjava.exception.NotFoundException;
import camilabarpp.todolistjava.model.category.CategoryEntity;
import camilabarpp.todolistjava.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }

    public CategoryEntity findByCategory(String categoryName) {
        var category = categoryRepository.findAllByCategoryNameContains(categoryName);

        if (category == null) {
            throw new NotFoundException("Categoria '" + categoryName + "' não encontrada!");
        } else {
            return category;
        }
    }

    public CategoryEntity save(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    public CategoryEntity findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Categoria com id: '" + id + "' não encontrado!"));
    }

    public Optional<CategoryEntity> update(Long id, CategoryEntity categoryEntity) {
        return Optional.ofNullable(categoryRepository.findById(id)
                .map(categoryEntity1 -> {
                    categoryEntity1.setCategoryName(categoryEntity.getCategoryName());
                    return categoryRepository.save(categoryEntity1);
                })
                .orElseThrow(() ->
                        new NotFoundException("Categoria com id: '" + id + "' não encontrado!")));
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    public void deleteAll() {
        categoryRepository.deleteAll();
    }
}
