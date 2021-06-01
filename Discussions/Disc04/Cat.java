// https://fa20.datastructur.es/materials/discussion/disc04.pdf

public class Cat extends Animal {

    public Cat(String name, int age) {
        super(name, age);
        noise = "Meow!";
    }

    @Override
    public void greet() {
        System.out.println("Cat " + name + " says: " + makeNoise());
    }

    public static void main(String[] args) {
        Cat c = new Cat("Jeremy", 6);
        c.greet();
    }
}
