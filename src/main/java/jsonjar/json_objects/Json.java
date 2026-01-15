package jsonjar.json_objects;

import jsonjar.error_handling.JsonReadException;

import static jsonjar.json_objects.JsonReadErrorConstants.JSON_READ_ERROR_UNEXPECTED_TYPE;

public interface Json {
    Object getValue();

    default JsonObject asJsonObject() {
        if (this instanceof JsonObject obj) {
            return obj;
        }
        throw new JsonReadException(JSON_READ_ERROR_UNEXPECTED_TYPE + this.getClass().getSimpleName());
    }

    default JsonArray asJsonArray() {
        if (this instanceof JsonArray arr) {
            return arr;
        }
        throw new JsonReadException(JSON_READ_ERROR_UNEXPECTED_TYPE + this.getClass().getSimpleName());
    }
}
