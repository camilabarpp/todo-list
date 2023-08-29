package camilabarpp.todolistjava.model.category.mapper;

import camilabarpp.todolistjava.model.category.request.CategoryRequest;
import camilabarpp.todolistjava.model.category.CategoryEntity;
import camilabarpp.todolistjava.model.category.response.CategoryResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public static CategoryResponse toResponse(CategoryEntity categoryEntity) {
        return CategoryResponse.builder()
                .categoryId(categoryEntity.getCategoryId())
                .categoryName(categoryEntity.getCategoryName())
                .build();
    }

    public static CategoryEntity toEntity(CategoryRequest categoryRequest) {
        return CategoryEntity.builder()
                .categoryName(categoryRequest.getCategoryName())
                .build();
    }

    public static CategoryRequest toRequest(CategoryEntity categoryEntity) {
        return CategoryRequest.builder()
                .categoryName(categoryEntity.getCategoryName())
                .build();
    }
}
