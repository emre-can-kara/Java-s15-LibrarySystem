package Library.Entity;

public class Book {
    private Author Author;
    private Long bookID;
    private String edition;
    private boolean isBorrowed = false;
    private User currentHolder;
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public Book(Author author, Long bookID, String edition) {
        Author = author;
        this.bookID = bookID;
        this.edition = edition;
    }

    public Author getAuthor() {
        return Author;
    }

    public void setAuthor(Author author) {
        Author = author;
    }

    public Long getBookID() {
        return bookID;
    }

    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public User getCurrentHolder() {
        return currentHolder;
    }

    @Override
    public String toString() {
        return "Book{" +
                "Author=" + Author.getName() +
                ", bookID=" + bookID +
                ", edition='" + edition + '\'' +
                ", isBorrowed=" + isBorrowed +
                ", currentHolder=" + (currentHolder != null ? currentHolder.getName() : "Yok") +
                ", category='" + category + '\'' +
                '}';
    }


    public void setCurrentHolder(User currentHolder) {
        this.currentHolder = currentHolder;
    }
}
