package jsonjar.json_objects;

public enum JsonReadErrorConstants {
    JSON_READ_ERROR_TYPE_MISMATCH("Error: The requested value for given key is not of the requested type."),
    JSON_READ_ERROR_KEY_MISSING("Error: No entry for requested key "),
    JSON_READ_ERROR_INDEX_OUT_OF_BOUNDS("Error: Index out of bounds."),
    JSON_READ_ERROR_UNEXPECTED_TYPE("Error: Unexpected type ");

    private final String message;

    JsonReadErrorConstants(String message) {
        this.message = message;
    }

    String getMessage() {
        return message;
    }
}
