package ua.opnu.list;

class Dog extends Animal {

    public Dog(String name) {
        super(name);
    }

    @Override
    String makeSound() {
        return "GAVVV!";
    }
}