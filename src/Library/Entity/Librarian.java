package Library.Entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Librarian {
    private String name;
    private String password;
    private Boolean hasAccess = true;
    private Set<Book> bookCollection = new HashSet<>();
    private String category;



    public Librarian(String name, String password) {
        this.name = name;
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getHasAccess() {
        return hasAccess;
    }

    public void setHasAccess(Boolean hasAccess) {
        this.hasAccess = hasAccess;
    }

    public Set<Book> addBook(Book book) {
        bookCollection.add(book);
        return bookCollection;
    }

    public Set<Book> getBookCollection() {
        return bookCollection;
    }

    public boolean removeBook(Long bookID) {
        Book bookToRemove = null;

        for (Book book : bookCollection) {
            if (book.getBookID().equals(bookID)) {
                bookToRemove = book;
                break;
            }
        }

        if (bookToRemove != null) {
            bookCollection.remove(bookToRemove);
            return true;
        } else {
            return false;
        }
    }

    public Book findBookById(Long id) {
        return bookCollection.stream()
                .filter(book -> book.getBookID().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Book> findBooksByAuthor(String authorName) {
        return bookCollection.stream()
                .filter(book -> book.getAuthor().getName().equalsIgnoreCase(authorName))
                .toList();
    }

    public List<Book> findBooksByCategory(String category) {
        return bookCollection.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    public boolean updateBook(Long id, String newEdition) {
        Book book = findBookById(id);
        if (book != null) {
            book.setEdition(newEdition);
            return true;
        }
        return false;
    }

    public boolean borrowBook(Long bookID, String userName) {
        Book book = findBookById(bookID);

        if (book == null || book.isBorrowed()) {
            return false;
        }

        User user = new User(userName);
        if (user.getBorrowedBooks().size() >= 5) {
            return false;
        }

        book.setBorrowed(true);
        book.setCurrentHolder(user);
        user.getBorrowedBooks().add(book);

        Library.Entity.Invoice invoice = new Library.Entity.Invoice(user, book, true);
        System.out.println("Fatura: " + invoice);
        return true;
    }


    public boolean returnBook(Long bookID, String userName) {
        Book book = findBookById(bookID);

        if (book == null || !book.isBorrowed()) {
            return false;
        }

        User currentUser = book.getCurrentHolder();
        if (currentUser == null || !currentUser.getName().equalsIgnoreCase(userName)) {
            return false;
        }

        book.setBorrowed(false);
        book.setCurrentHolder(null);
        currentUser.getBorrowedBooks().remove(book);

        Library.Entity.Invoice invoice = new Library.Entity.Invoice(currentUser, book, false);
        System.out.println("Fatura: " + invoice);
        return true;
    }


    @Override
    public String toString() {
        return "Librarian{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", hasAccess=" + hasAccess +
                ", bookCollection=" + bookCollection +
                ", category='" + category + '\'' +
                '}';
    }
}
