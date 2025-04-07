import Library.Entity.Librarian;
import Library.Service.ScannerService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Librarian librarian = new Librarian("admin", "1234");
        ScannerService scannerService = new ScannerService(librarian);
        scannerService.start();
    }
}