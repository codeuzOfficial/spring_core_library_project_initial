package dasturlash.uz.controller;

import dasturlash.uz.container.ComponentContainer;
import dasturlash.uz.dto.Book;
import dasturlash.uz.util.ScannerUtil;

import java.time.LocalDate;

public class BookController {

    public void start() {
        boolean loop = true;
        while (loop) {
            showMenu();
            int action = ScannerUtil.getAction();
            switch (action) {
                case 1:
                    list();
                    break;
                case 2:
                    search();
                    break;
                case 3:
                    add();
                    break;
                case 4:
                    delete();
                    break;
                case 5:
                    ComponentContainer.studentBookService.booksOnHand();
                    break;
                case 6:
                    bookHistory();
                    break;
                case 7:
                    ComponentContainer.studentBookService.bestBooks();
                    break;
                case 0:
                    loop = false;
                    break;
                default:
                    System.out.println("Mazgi bu hatoku.");
            }
        }
    }


    public void showMenu() {
        System.out.println("*** Book ***");
        System.out.println("1. Book list");
        System.out.println("2. Search");
        System.out.println("3. Add book");
        System.out.println("4. Remove book");
        System.out.println("5. Book on hand");
        System.out.println("6. Book history");
        System.out.println("7. Best books:");
        System.out.println("0. Exits");
    }


    public void add() {
        System.out.print("Enter title: ");
        String title = ComponentContainer.scannerText.next();

        System.out.print("Enter author: ");
        String author = ComponentContainer.scannerText.next();

        System.out.print("Enter category id: ");
        Integer categoryId = ComponentContainer.scannerNumber.nextInt();

        System.out.print("Enter available day: ");
        Integer availableDay = ComponentContainer.scannerNumber.nextInt();

        System.out.print("Enter publishDate (yyyy-MM-dd): "); // 2023-07-15
        String publishDate = ComponentContainer.scannerText.next();

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setCategoryId(categoryId);
        book.setPublishDate(LocalDate.parse(publishDate));
        book.setAvailableDay(availableDay);

        ComponentContainer.bookService.add(book);
    }

    public void list() {
        System.out.println("--- Book list ---");
        ComponentContainer.bookService.all();
    }

    public void search() {
        System.out.print("Enter query:");
        String query = ComponentContainer.scannerText.next();
        ComponentContainer.bookService.search(query);
    }

    public void delete() {
        System.out.print("Enter id:");
        Integer bookId = ComponentContainer.scannerNumber.nextInt();
        ComponentContainer.bookService.delete(bookId);
    }

    private void bookHistory() {
        System.out.print("Enter book Id:");
        Integer bookId = ComponentContainer.scannerNumber.nextInt();
        ComponentContainer.studentBookService.bookHistory(bookId);
    }
}
