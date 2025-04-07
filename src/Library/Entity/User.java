package Library.Entity;

import java.util.*;

public class User extends Person {
    private final int BORROW_LIMIT = 5;
    private List<Book> borrowedBooks = new ArrayList<>();
    private List<Library.Entity.Invoice> invoices = new ArrayList<>();

    public User(String name) {
        super(name);
    }

    public boolean borrowBook(Book book) {
        if (borrowedBooks.size() >= BORROW_LIMIT || book.isBorrowed()) {
            return false;
        }
        borrowedBooks.add(book);
        book.setBorrowed(true);
        book.setCurrentHolder(this);
        Library.Entity.Invoice invoice = new Library.Entity.Invoice(this, book, true);
        invoices.add(invoice);
        return true;
    }

    public boolean returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.setBorrowed(false);
            book.setCurrentHolder(null);
            Library.Entity.Invoice invoice = new Library.Entity.Invoice(this, book, false);
            invoices.add(invoice);
            return true;
        }
        return false;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public List<Library.Entity.Invoice> getInvoices() {
        return invoices;
    }

    @Override
    public void whoAreYou(String name) {
        System.out.println("Ben kullanıcıyım: " + name);
    }

    @Override
    public String toString() {
        return "User{" +
                "BORROW_LIMIT=" + BORROW_LIMIT +
                ", borrowedBooks=" + borrowedBooks +
                ", invoices=" + invoices +
                '}';
    }
}
