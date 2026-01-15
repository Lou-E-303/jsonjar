package jsonjar.json_objects;

public enum JsonReadingErrorConstants {
    JSON_READING_ERROR_TYPE_MISMATCH("Error: The requested value for given key is not of the requested type."),
    JSON_READING_ERROR_KEY_MISSING("Error: No entry for requested key "),
    JSON_READING_ERROR_INDEX_OUT_OF_BOUNDS("Error: Index out of bounds.");

    private final String message;

    JsonReadingErrorConstants(String message) {
        this.message = message;
    }

    String getMessage() {
        return message;
    }
}
