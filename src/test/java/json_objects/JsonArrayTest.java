package json_objects;

import jsonjar.error_handling.JsonReadException;
import jsonjar.json_objects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class JsonArrayTest {
    private JsonArray jsonArray;

    @BeforeEach
    void setUp() {
        jsonArray = new JsonArray();
        jsonArray.addValue(new JsonString("Apple"));
        jsonArray.addValue(new JsonNumber(new BigDecimal("42"), "42"));
        jsonArray.addValue(new JsonBoolean(true));
        jsonArray.addValue(JsonNull.getInstance());

        JsonArray nestedArray = new JsonArray();
        nestedArray.addValue(new JsonString("nested1"));
        nestedArray.addValue(new JsonString("nested2"));
        jsonArray.addValue(nestedArray);

        JsonObject nestedObject = new JsonObject();
        nestedObject.addValue("key", new JsonString("value"));
        jsonArray.addValue(nestedObject);
    }

    @Test
    void shouldPrintCorrectlyWhenToStringIsCalled() {
        JsonArray array = new JsonArray();
        array.addValue(new JsonString("value1"));
        array.addValue(new JsonString("value2"));
        array.addValue(new JsonString("value3"));
        assertEquals("[\"value1\",\"value2\",\"value3\"]", array.toString());
    }

    @Test
    void shouldReturnValueOnGet() {
        Json result = jsonArray.get(0);
        assertNotNull(result);
        assertInstanceOf(JsonString.class, result);
    }

    @Test
    void shouldReturnNullForOutOfBoundsIndex() {
        Json result = jsonArray.get(99);
        assertNull(result);
    }

    @Test
    void shouldReturnNullForNegativeIndex() {
        Json result = jsonArray.get(-1);
        assertNull(result);
    }

    @Test
    void shouldReturnValueOnGetRequired() {
        Json result = jsonArray.getRequired(0);
        assertNotNull(result);
        assertInstanceOf(JsonString.class, result);
    }

    @Test
    void shouldThrowExceptionForOutOfBoundsIndex() {
        assertThrows(JsonReadException.class, () -> jsonArray.getRequired(99));
    }

    @Test
    void shouldThrowExceptionForNegativeIndex() {
        assertThrows(JsonReadException.class, () -> jsonArray.getRequired(-1));
    }

    @Test
    void shouldGetJsonString() {
        JsonString result = jsonArray.getAsJsonString(0);
        assertEquals("Apple", result.getValue());
    }

    @Test
    void shouldGetString() {
        String result = jsonArray.getAsString(0);
        assertEquals("Apple", result);
    }

    @Test
    void shouldThrowExceptionForWrongTypeString() {
        assertThrows(JsonReadException.class, () -> {
            jsonArray.getAsString(1); // Index 1 is a number
        });
    }

    @Test
    void shouldGetAsJsonNumber() {
        JsonNumber result = jsonArray.getAsJsonNumber(1);
        assertEquals(new BigDecimal("42"), result.getValue());
    }

    @Test
    void shouldGetAsBigDecimal() {
        BigDecimal result = jsonArray.getAsBigDecimal(1);
        assertEquals(new BigDecimal("42"), result);
    }

    @Test
    void shouldThrowExceptionForWrongTypeNumber() {
        assertThrows(JsonReadException.class, () -> {
            jsonArray.getAsJsonNumber(0); // Index 0 is a string
        });
    }

    @Test
    void shouldGetAsJsonBoolean() {
        JsonBoolean result = jsonArray.getAsJsonBoolean(2);
        assertTrue(result.getValue());
    }

    @Test
    void shouldGetAsBoolean() {
        boolean result = jsonArray.getAsBoolean(2);
        assertTrue(result);
    }

    @Test
    void shouldThrowExceptionForWrongTypeBoolean() {
        assertThrows(JsonReadException.class, () -> {
            jsonArray.getAsBoolean(0); // Index 0 is a string
        });
    }

    @Test
    void shouldGetAsJsonNull() {
        JsonNull result = jsonArray.getAsJsonNull(3);
        assertEquals(JsonNull.getInstance(), result);
    }

    @Test
    void shouldThrowExceptionForWrongTypeNull() {
        assertThrows(JsonReadException.class, () -> {
            jsonArray.getAsJsonNull(0); // Index 0 is a string
        });
    }

    @Test
    void shouldGetAsJsonArray() {
        JsonArray result = jsonArray.getAsJsonArray(4);
        assertEquals(2, result.getValue().size());
    }

    @Test
    void shouldThrowExceptionForWrongTypeArray() {
        assertThrows(JsonReadException.class, () -> {
            jsonArray.getAsJsonArray(0); // Index 0 is a string
        });
    }

    @Test
    void shouldGetJsonObject() {
        JsonObject result = jsonArray.getAsJsonObject(5);
        assertEquals("value", result.getAsString("key"));
    }

    @Test
    void shouldThrowExceptionForWrongTypeObject() {
        assertThrows(JsonReadException.class, () -> {
            jsonArray.getAsJsonObject(0); // Index 0 is a string
        });
    }

    @Test
    void shouldGetValue() {
        ArrayList<Json> result = jsonArray.getValue();
        assertEquals(6, result.size());
    }

    @Test
    void shouldBeIterable() {
        int count = 0;
        for (Json item : jsonArray) {
            assertNotNull(item);
            count++;
        }
        assertEquals(6, count);
    }

    @Test
    void shouldIterateWithIterator() {
        Iterator<Json> iterator = jsonArray.iterator();
        assertTrue(iterator.hasNext());
        assertInstanceOf(JsonString.class, iterator.next());
        assertTrue(iterator.hasNext());
        assertInstanceOf(JsonNumber.class, iterator.next());
    }

    @Test
    void shouldTestNestedAccess() {
        JsonArray nested = jsonArray.getAsJsonArray(4);
        String firstItem = nested.getAsString(0);
        assertEquals("nested1", firstItem);
    }

    @Test
    void shouldTestNestedObjectAccess() {
        JsonObject nested = jsonArray.getAsJsonObject(5);
        String value = nested.getAsString("key");
        assertEquals("value", value);
    }

    @Test
    void shouldReturnCorrectSize() {
        assertEquals(6, jsonArray.getValue().size());
    }

    @Test
    void shouldHandleEmptyArray() {
        JsonArray empty = new JsonArray();
        assertEquals(0, empty.getValue().size());
        assertEquals("[]", empty.toString());
        assertNull(empty.get(0));
    }

    @Test
    void shouldEqualIdenticalArray() {
        JsonArray array1 = new JsonArray();
        array1.addValue(new JsonString("test"));

        JsonArray array2 = new JsonArray();
        array2.addValue(new JsonString("test"));

        assertEquals(array1, array2);
    }

    @Test
    void shouldNotEqualDifferentArray() {
        JsonArray array1 = new JsonArray();
        array1.addValue(new JsonString("test1"));

        JsonArray array2 = new JsonArray();
        array2.addValue(new JsonString("test2"));

        assertNotEquals(array1, array2);
    }

    @Test
    void shouldHaveSameHashCodeForIdenticalArrays() {
        JsonArray array1 = new JsonArray();
        array1.addValue(new JsonString("test"));

        JsonArray array2 = new JsonArray();
        array2.addValue(new JsonString("test"));

        assertEquals(array1.hashCode(), array2.hashCode());
    }
}
