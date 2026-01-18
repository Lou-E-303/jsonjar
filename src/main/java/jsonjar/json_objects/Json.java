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

    default JsonString asJsonString() {
        if (this instanceof JsonString str) {
            return str;
        }
        throw new JsonReadException(JSON_READ_ERROR_UNEXPECTED_TYPE + this.getClass().getSimpleName());
    }

    default JsonNumber asJsonNumber() {
        if (this instanceof JsonNumber num) {
            return num;
        }
        throw new JsonReadException(JSON_READ_ERROR_UNEXPECTED_TYPE + this.getClass().getSimpleName());
    }

    default JsonBoolean asJsonBoolean() {
        if (this instanceof JsonBoolean bool) {
            return bool;
        }
        throw new JsonReadException(JSON_READ_ERROR_UNEXPECTED_TYPE + this.getClass().getSimpleName());
    }

    default JsonNull asJsonNull() {
        if (this instanceof JsonNull n) {
            return n;
        }
        throw new JsonReadException(JSON_READ_ERROR_UNEXPECTED_TYPE + this.getClass().getSimpleName());
    }
}