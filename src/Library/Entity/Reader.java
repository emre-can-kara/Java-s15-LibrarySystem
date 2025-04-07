package Library.Entity;

public class Reader extends Person{
    public Reader(String name) {
        super(name);
    }

    @Override
    public void whoAreYou(String name) {
        System.out.println("Benim adim:" + name);
    }

    @Override
    public String toString() {
        return "Reader{}";
    }
}
