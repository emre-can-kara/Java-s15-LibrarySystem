package Library.Service;

import Library.Entity.Author;
import Library.Entity.Book;
import Library.Entity.Librarian;

import java.util.List;
import java.util.Scanner;

public class ScannerService {

    private final Librarian librarian;
    private final Scanner scanner;

    public ScannerService(Librarian librarian) {
        this.librarian = librarian;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1 - Kitap Ekle");
            System.out.println("2 - Tüm Kitapları Listele");
            System.out.println("3 - Kitabı Sil");
            System.out.println("4 - Kitap Güncelle (edition)");
            System.out.println("5 - ID ile Kitap Ara");
            System.out.println("6 - Yazarına Göre Kitapları Listele");
            System.out.println("7 - Kategorisine Göre Kitapları Listele");
            System.out.println("8 - Kitap Ödünç Al");
            System.out.println("9 - Kitap İade Et");

            System.out.println("0 - Çıkış");
            System.out.print("Seçiminizi yapın: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Dummy read

            switch (choice) {
                case 1 -> addBook();
                case 2 -> listBooks();
                case 3 -> removeBook();
                case 4 -> updateBook();
                case 5 -> searchBookById();
                case 6 -> listBooksByAuthor();
                case 7 -> listBooksByCategory();
                case 8 -> borrowBook();
                case 9 -> returnBook();

                case 0 -> {
                    System.out.println("Çıkış yapılıyor...");
                    return;
                }
                default -> System.out.println("Geçersiz seçim! Tekrar deneyin.");
            }
        }
    }

    private void addBook() {
        System.out.print("Yazar Adı: ");
        String authorName = scanner.nextLine();

        System.out.print("Kitap ID: ");
        Long bookID = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Edition: ");
        String edition = scanner.nextLine();

        System.out.print("Kategori: ");
        String category = scanner.nextLine();

        Author author = new Author(authorName);
        Book book = new Book(author, bookID, edition);
        book.setCategory(category);

        librarian.addBook(book);
        System.out.println("Kitap eklendi!");
    }

    private void listBooks() {
        System.out.println("\n--- Tüm Kitaplar ---");
        for (Book book : librarian.getBookCollection()) {
            System.out.println(book);
        }
    }

    private void removeBook() {
        System.out.print("Silmek istediğiniz kitabın ID'sini girin: ");
        Long bookID = scanner.nextLong();
        scanner.nextLine();

        boolean isRemoved = librarian.removeBook(bookID);
        System.out.println(isRemoved ? "Kitap silindi!" : "Kitap bulunamadı!");
    }

    private void updateBook() {
        System.out.print("Güncellemek istediğiniz kitabın ID'si: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Yeni edition: ");
        String newEdition = scanner.nextLine();

        boolean updated = librarian.updateBook(id, newEdition);
        System.out.println(updated ? "Kitap güncellendi!" : "Kitap bulunamadı!");
    }

    private void searchBookById() {
        System.out.print("Aranacak kitap ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Book book = librarian.findBookById(id);
        if (book != null) {
            System.out.println("Bulunan Kitap: " + book);
        } else {
            System.out.println("Kitap bulunamadı!");
        }
    }

    private void listBooksByAuthor() {
        System.out.print("Yazar adı girin: ");
        String authorName = scanner.nextLine();

        List<Book> books = librarian.findBooksByAuthor(authorName);
        if (books.isEmpty()) {
            System.out.println("Bu yazara ait kitap bulunamadı.");
        } else {
            System.out.println("Yazara ait kitaplar:");
            books.forEach(System.out::println);
        }
    }

    private void listBooksByCategory() {
        System.out.print("Kategori girin: ");
        String category = scanner.nextLine();

        List<Book> books = librarian.findBooksByCategory(category);
        if (books.isEmpty()) {
            System.out.println("Bu kategoride kitap yok.");
        } else {
            System.out.println("Kategoriye ait kitaplar:");
            books.forEach(System.out::println);
        }
    }
    private void borrowBook() {
        System.out.print("Kullanıcı adı: ");
        String userName = scanner.nextLine();

        System.out.print("Alınacak kitap ID: ");
        Long bookID = scanner.nextLong();
        scanner.nextLine();

        boolean success = librarian.borrowBook(bookID, userName);

        if (success) {
            System.out.println("Kitap başarıyla ödünç alındı!");
        } else {
            System.out.println("Kitap alınamadı. Ya mevcut değil, ya da zaten ödünç alınmış ya da limit dolu.");
        }
    }

    private void returnBook() {
        System.out.print("İade eden kullanıcı adı: ");
        String userName = scanner.nextLine();

        System.out.print("İade edilecek kitap ID: ");
        Long bookID = scanner.nextLong();
        scanner.nextLine();

        boolean success = librarian.returnBook(bookID, userName);

        if (success) {
            System.out.println("Kitap başarıyla iade edildi!");
        } else {
            System.out.println("İade işlemi başarısız. Belki kitap sizde değildir?");
        }
    }


    @Override
    public String toString() {
        return "ScannerService{" +
                "librarian=" + librarian +
                ", scanner=" + scanner +
                '}';
    }
}
