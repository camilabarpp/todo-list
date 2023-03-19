package camilabarpp.todolistjava.controller;

import camilabarpp.todolistjava.model.category.CategoryEntity;
import camilabarpp.todolistjava.model.task.TaskEntity;
import camilabarpp.todolistjava.service.CategoryServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<CategoryEntity> findByCategory(@RequestParam String categoryName) {
        return categoryServiceV2.findByCategory(categoryName);
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
