package jsonjar.json_objects;

import jsonjar.error_handling.JsonReadException;

import static jsonjar.json_objects.JsonReadErrorConstants.JSON_READ_ERROR_UNEXPECTED_TYPE;

public interface Json {
    Object getValue();

    default JsonObject toJsonObject() {
        if (this instanceof JsonObject jsonObject) {
            return jsonObject;
        }
        throw new JsonReadException(JSON_READ_ERROR_UNEXPECTED_TYPE + this.getClass().getSimpleName());
    }

    default JsonArray toJsonArray() {
        if (this instanceof JsonArray jsonArray) {
            return jsonArray;
        }
        throw new JsonReadException(JSON_READ_ERROR_UNEXPECTED_TYPE + this.getClass().getSimpleName());
    }

    default JsonString toJsonString() {
        if (this instanceof JsonString jsonString) {
            return jsonString;
        }
        throw new JsonReadException(JSON_READ_ERROR_UNEXPECTED_TYPE + this.getClass().getSimpleName());
    }

    default JsonNumber toJsonNumber() {
        if (this instanceof JsonNumber jsonNumber) {
            return jsonNumber;
        }
        throw new JsonReadException(JSON_READ_ERROR_UNEXPECTED_TYPE + this.getClass().getSimpleName());
    }

    default JsonBoolean toJsonBoolean() {
        if (this instanceof JsonBoolean jsonBoolean) {
            return jsonBoolean;
        }
        throw new JsonReadException(JSON_READ_ERROR_UNEXPECTED_TYPE + this.getClass().getSimpleName());
    }

    default JsonNull toJsonNull() {
        if (this instanceof JsonNull n) {
            return n;
        }
        throw new JsonReadException(JSON_READ_ERROR_UNEXPECTED_TYPE + this.getClass().getSimpleName());
    }
}