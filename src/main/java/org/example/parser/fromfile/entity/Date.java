package org.example.parser.fromfile.entity;

public record Date(String name, String date) {
    @Override
    public String toString() {
        return "Название станции: " + name +
                ", дата постройки: " + date;
    }
}
