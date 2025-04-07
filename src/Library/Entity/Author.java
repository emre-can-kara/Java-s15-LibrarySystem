package Library.Entity;

public class Author extends Person{


    public Author(String name) {
        super(name);
    }

    @Override
    public void whoAreYou(String name) {
        System.out.println("Benim adim: " + name);
    }

    @Override
    public String toString() {
        return "Author{name='" + getName() + "'}";
    }

}
