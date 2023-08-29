package camilabarpp.todolistjava.controller;

import camilabarpp.todolistjava.model.category.response.CategoryResponse;
import camilabarpp.todolistjava.model.category.CategoryEntity;
import camilabarpp.todolistjava.model.category.request.CategoryRequest;
import camilabarpp.todolistjava.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static camilabarpp.todolistjava.model.category.mapper.CategoryMapper.toEntity;
import static camilabarpp.todolistjava.model.category.mapper.CategoryMapper.toResponse;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v2/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryServiceV2;

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
    public CategoryResponse save(@RequestBody CategoryRequest categoryEntity) {
        return toResponse(categoryServiceV2.save(toEntity(categoryEntity)));
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
