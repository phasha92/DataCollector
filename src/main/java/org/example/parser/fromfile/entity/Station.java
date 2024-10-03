package org.example.parser.fromfile.entity;

public record Station(String name, String depths) {
    @Override
    public String toString() {
        return "Название станции: " + name + ", глубина: " + depths;
    }
}
