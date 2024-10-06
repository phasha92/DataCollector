package org.example.pojo;

public record StationWithDate(String name, String date) {
    @Override
    public String toString() {
        return "Название станции: " + name + ", дата постройки: " + date;
    }
}
