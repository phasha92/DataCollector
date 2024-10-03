package org.example.parser.fromweb.entity;

public record Station(String name, String lineNumber) {
    @Override
    public String toString() {
        return "Станция метро: " + name + " (Линия номер: " + lineNumber + ")";
    }
}