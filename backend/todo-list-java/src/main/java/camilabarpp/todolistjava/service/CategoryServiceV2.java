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
public class CategoryServiceV2 {

    private final CategoryRepository categoryRepository;

    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }

    public List<CategoryEntity> findByCategory(String categoryName) {
        return categoryRepository.findAllByCategoryNameContains(categoryName);
    }

    public CategoryEntity save(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    public CategoryEntity findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Categoria com id: " + id + " não encontrads!"));    }

    public Optional<CategoryEntity> update(CategoryEntity categoryEntity) {
        return Optional.ofNullable(categoryRepository.findById(categoryEntity.getCategoryId())
                .map(categoryEntity1 -> {
                    categoryEntity1.setCategoryName(categoryEntity.getCategoryName());
                    return categoryRepository.save(categoryEntity1);
                })
                .orElseThrow(() ->
                        new NotFoundException("Categoria " + categoryEntity.getCategoryName() + " não encontrads!")));
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    public void deleteAll() {
        categoryRepository.deleteAll();
    }
}
