package jsonjar.json_objects;

import jsonjar.error_handling.JsonReadException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

import static jsonjar.json_objects.JsonReadErrorConstants.JSON_READ_ERROR_INDEX_OUT_OF_BOUNDS;
import static jsonjar.json_objects.JsonReadErrorConstants.JSON_READ_ERROR_TYPE_MISMATCH;

public class JsonArray implements Json, Iterable<Json> {
    private final ArrayList<Json> values;

    public JsonArray() {
        this.values = new ArrayList<>();
    }

    public void addValue(Json value) {
        this.values.add(value);
    }

    public Json get(int index) {
        if (index < 0 || index >= values.size()) {
            return null;
        }
        return values.get(index);
    }

    public Json getRequired(int index) {
        if (index < 0 || index >= values.size()) {
            throw new JsonReadException(JSON_READ_ERROR_INDEX_OUT_OF_BOUNDS.getMessage() + " Index: " + index);
        }
        return values.get(index);
    }

    public JsonString getAsJsonString(int index) {
        if (getRequired(index) instanceof JsonString jsonString) {
            return jsonString;
        }
        throw new JsonReadException(JSON_READ_ERROR_TYPE_MISMATCH.getMessage() + " Index: " + index + " is not a JsonString.");
    }

    public String getAsString(int index) {
        if (getRequired(index) instanceof JsonString jsonString) {
            return jsonString.getValue();
        }
        throw new JsonReadException(JSON_READ_ERROR_TYPE_MISMATCH.getMessage() + " Index: " + index + " is not a String.");
    }

    public JsonNumber getAsJsonNumber(int index) {
        if (getRequired(index) instanceof JsonNumber jsonNumber) {
            return jsonNumber;
        }
        throw new JsonReadException(JSON_READ_ERROR_TYPE_MISMATCH.getMessage() + " Index: " + index + " is not a JsonNumber.");
    }

    public BigDecimal getAsBigDecimal(int index) {
        if (getRequired(index) instanceof JsonNumber jsonNumber) {
            return jsonNumber.getValue();
        }
        throw new JsonReadException(JSON_READ_ERROR_TYPE_MISMATCH.getMessage() + " Index: " + index + " is not a BigDecimal.");
    }

    public JsonBoolean getAsJsonBoolean(int index) {
        if (getRequired(index) instanceof JsonBoolean jsonBoolean) {
            return jsonBoolean;
        }
        throw new JsonReadException(JSON_READ_ERROR_TYPE_MISMATCH.getMessage() + " Index: " + index + " is not a JsonBoolean.");
    }

    public boolean getAsBoolean(int index) {
        if (getRequired(index) instanceof JsonBoolean jsonBoolean) {
            return jsonBoolean.getValue();
        }
        throw new JsonReadException(JSON_READ_ERROR_TYPE_MISMATCH.getMessage() + " Index: " + index + " is not a Boolean.");
    }

    public JsonArray getAsJsonArray(int index) {
        if (getRequired(index) instanceof JsonArray jsonArray) {
            return jsonArray;
        }
        throw new JsonReadException(JSON_READ_ERROR_TYPE_MISMATCH.getMessage() + " Index: " + index + " is not a JsonArray.");
    }

    public JsonObject getAsJsonObject(int index) {
        if (getRequired(index) instanceof JsonObject jsonObject) {
            return jsonObject;
        }
        throw new JsonReadException(JSON_READ_ERROR_TYPE_MISMATCH.getMessage() + " Index: " + index + " is not a JsonObject.");
    }

    public JsonNull getAsJsonNull(int index) {
        if (getRequired(index) instanceof JsonNull jsonNull) {
            return jsonNull;
        }
        throw new JsonReadException(JSON_READ_ERROR_TYPE_MISMATCH.getMessage() + " Index: " + index + " is not a JsonNull.");
    }

    @Override
    public Iterator<Json> iterator() {
        return values.iterator();
    }

    @Override
    public ArrayList<Json> getValue() {
        return new ArrayList<>(values);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof JsonArray other) {
            return this.values.equals(other.values);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return values.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (Json value : values) {
            sb.append(value.toString());
            sb.append(",");
        }

        if (!values.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
        }

        sb.append("]");
        return sb.toString();
    }
}