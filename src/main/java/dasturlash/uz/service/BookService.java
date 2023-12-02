package dasturlash.uz.service;

import dasturlash.uz.container.ComponentContainer;
import dasturlash.uz.dto.Book;
import dasturlash.uz.dto.Category;

import java.time.LocalDateTime;
import java.util.List;

public class BookService {

    public void add(Book book) {
        ComponentContainer.categoryService.list();
        // check
        if (!isValid(book)) {
            return;
        }
        // category
        Category category = ComponentContainer.categoryRepository.getById(book.getCategoryId());
        if (category == null) {
            System.out.println("Category not found.");
            return;
        }
        book.setCreatedDate(LocalDateTime.now());
        book.setVisible(true);
        int effectedRow = ComponentContainer.bookRepository.save(book);
        if (effectedRow == 1) {
            System.out.println("Book saved.");
        } else {
            System.out.println("Book not saved.");
        }
    }

    public boolean isValid(Book book) {
        if (book.getTitle() == null || book.getTitle().isBlank() || book.getTitle().length() < 3) {
            System.out.println("Title required.");
            return false;
        }

        if (book.getAuthor() == null || book.getAuthor().isBlank() || book.getAuthor().length() < 3) {
            System.out.println("Author required.");
            return false;
        }

        if (book.getAvailableDay() == null || book.getAvailableDay() <= 0) {
            System.out.println("Available day required.");
            return false;
        }
        return true;
    }

    public void all() {
        List<Book> bookList = ComponentContainer.bookRepository.getAll();
        System.out.printf("-------------------------------------------------------------------%n");
        System.out.printf("                               Book list                        %n");
        System.out.printf("-------------------------------------------------------------------%n");
        System.out.printf("| %-4s | %-20s | %-15s | %-15s |%n", "Id", "Title", "Author", "Category name");
        System.out.printf("-------------------------------------------------------------------%n");
        bookList.forEach(book -> {
            System.out.printf("| %-4d | %-20s | %-15s | %-15s |%n", book.getId(), book.getTitle(), book.getAuthor(), book.getCategory().getName());
        });
        System.out.printf("-------------------------------------------------------------------%n");
    }

    public void search(String query) {
        List<Book> bookList = ComponentContainer.bookRepository.search(query);
        bookList.forEach(book -> {
            System.out.println(book.getId() + ", " + book.getTitle() + ", " + book.getAuthor() + ", " + book.getCategory().getName());
        });
    }

    public void delete(Integer bookId) {
        int effectedRows = ComponentContainer.bookRepository.delete(bookId);
        if (effectedRows == 1) {
            System.out.println("Book deleted");
        } else {
            System.out.println("Book not deleted");
        }
    }

    public void byCategoryId(Integer categoryId) {
        List<Book> bookList = ComponentContainer.bookRepository.getAllByCategoryId(categoryId);
        bookList.forEach(book -> {
            System.out.println(book.getId() + ", " + book.getTitle() + ", " + book.getAuthor() + ", " + book.getCategory().getName());
        });
    }
}
