package camilabarpp.todolistjava.stub;

import camilabarpp.todolistjava.model.category.CategoryEntity;

public class CategoryStubs {

    public static CategoryEntity createCategory() {
        return CategoryEntity.builder()
                .categoryId(1L)
                .categoryName("Estudos")
                .build();
    }
}
