package Library.Entity;

import java.time.LocalDateTime;

public class Invoice {
    private User user;
    private Book book;
    private boolean isBorrowing;
    private LocalDateTime timestamp;
    private double amount;

    public Invoice(User user, Book book, boolean isBorrowing) {
        this.user = user;
        this.book = book;
        this.isBorrowing = isBorrowing;
        this.timestamp = LocalDateTime.now();
        this.amount = isBorrowing ? 10.0 : -10.0;
    }

    @Override
    public String toString() {
        return (isBorrowing ? "Ödünç: " : "İade: ") +
                book.getBookID() + " | Kullanıcı: " + user.getName() +
                " | Zaman: " + timestamp + " | Tutar: " + amount + " TL";
    }


}
