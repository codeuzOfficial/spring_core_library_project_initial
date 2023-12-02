package dasturlash.uz.service;

import dasturlash.uz.container.ComponentContainer;
import dasturlash.uz.dto.Category;

import java.time.LocalDateTime;
import java.util.List;

public class CategoryService {

    public void create(Category category) {
        CategoryService categoryService = new CategoryService();

        if (!check(category)) {
            System.out.println("Mazgi Category nomida hatolik bor.");
            return;
        }
        Category exists = ComponentContainer.categoryRepository.getByName(category.getName());
        if (exists != null) {
            System.out.println("category name already exists");
            return;
        }

        category.setCreatedDate(LocalDateTime.now());
        category.setVisible(true);
        int effectedRows = ComponentContainer.categoryRepository.save(category);
        if (effectedRows != 0) {
            System.out.println("Category is created");
        }
    }

    public boolean check(Category category) {
        if (category.getName() == null || category.getName().isBlank() || category.getName().length() <= 3) {
            return false;
        }
        return true;
    }

    public void list() {
        List<Category> categoryList = ComponentContainer.categoryRepository.getAll();
        for (Category category : categoryList) {
            System.out.println(category.getId() + " " + category.getName() + " " + category.getCreatedDate());
        }
    }

    public void delete(Integer id) {
        int effectedRows = ComponentContainer.categoryRepository.deleteById(id);
        if (effectedRows != 0) {
            System.out.println("Category deleted");
        } else {
            System.out.println("Category not deleted");
        }
    }
}
