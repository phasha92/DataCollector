package org.example.parser.fromweb.entity;

public record Line (String name, String number){
    @Override
    public String toString() {
        return "Линия метро: " + name + " (Номер: " + number + ")";
    }
}