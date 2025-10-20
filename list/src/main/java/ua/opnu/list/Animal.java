package ua.opnu.list;

abstract class Animal {

    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Абстрактний метод, який має бути реалізований у класах-нащадках
    abstract String makeSound();
}