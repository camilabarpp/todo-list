package camilabarpp.todolistjava.repository;

import camilabarpp.todolistjava.model.category.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    @Query("SELECT t FROM CategoryEntity t WHERE t.categoryName LIKE %?1%")
    CategoryEntity findAllByCategoryNameContains(String categoryName);

}
