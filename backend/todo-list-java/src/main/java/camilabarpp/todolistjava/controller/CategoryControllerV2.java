package camilabarpp.todolistjava.controller;

import camilabarpp.todolistjava.model.category.CategoryEntity;
import camilabarpp.todolistjava.service.CategoryServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v2/categories")
@RequiredArgsConstructor
public class CategoryControllerV2 {

    private final CategoryServiceV2 categoryServiceV2;

    @GetMapping
    public List<CategoryEntity> findAll() {
        return categoryServiceV2.findAll();
    }
    @GetMapping(params = "categoryName")
    public CategoryEntity findByCategory(@RequestParam String categoryName) {
        return categoryServiceV2.findByCategory(categoryName);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public CategoryEntity save(@RequestBody CategoryEntity categoryEntity) {
        return categoryServiceV2.save(categoryEntity);
    }

    @PutMapping("/{id}")
    public Optional<CategoryEntity> update(@PathVariable Long id, @RequestBody CategoryEntity categoryEntity) {
        return categoryServiceV2.update(id, categoryEntity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id) {
        categoryServiceV2.delete(id);
    }

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    public void deleteAll() {
        categoryServiceV2.deleteAll();
    }
}
