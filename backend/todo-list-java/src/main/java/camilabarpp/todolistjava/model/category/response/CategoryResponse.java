package camilabarpp.todolistjava.model.category.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CategoryResponse {
    private Long categoryId;
    private String categoryName;
}
