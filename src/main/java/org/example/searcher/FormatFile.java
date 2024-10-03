package org.example.searcher;

public enum FormatFile {

    JSON,
    CSV;

    private final String format = ".".concat(this.name().toLowerCase());

    public String getFormat() {
        return format;
    }
}
