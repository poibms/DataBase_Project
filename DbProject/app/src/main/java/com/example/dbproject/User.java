package com.example.dbproject;

public class User {
    private String name;
    private int age;
    private boolean isDoctor;

    User(String name, int age, boolean isDoctor) {
        this.name = name;
        this.age = age;
        this.isDoctor = isDoctor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getIsDoctor() {
        return isDoctor;
    }

    public void setIdDoctor(boolean isDoctor) {
        this.isDoctor = isDoctor;
    }

    @Override
    public String toString() {
        return  (isDoctor ? "Доктор: " : "Пользователь: ") + "" + name;
    }
}
