<h1 align="center"><b>JsonJar</b></h1>
<p align="center">
<img src="src/resources/json-jar.png" alt="JsonJar" style="width: 50%;" />
<h3 align="center">A JSON parser implementation for Java</h3>

[![](https://jitpack.io/v/Lou-E-303/jsonjar.svg?refresh=true)](https://jitpack.io/#Lou-E-303/jsonjar)

This JSON parser implementation uses a finite state transition parsing model. It can parse JSON data from both string and file input into hierarchical `Json` objects, and also provides functionality to pretty-print these objects with proper indentation.

# Usage & Features
```java
JsonParser parser = new JsonParser();
```

```java
// Parse JSON from a string
String jsonString = "{\"name\":\"Alice\",\"age\":30,\"active\":true}";
Json result = parser.parseFromString(jsonString);
```
```java
// Parse JSON from a file
File jsonFile = new File("data.json");
Json result = parser.parseFromFile(jsonFile);
```

```java
// Access collections easily
JsonObject obj = result.asJsonObject();
JsonArray arr = result.asJsonArray();
```
```java
// Access values easily
String name = obj.getAsString("name");
BigDecimal age = obj.getAsBigDecimal("age");
boolean active = obj.getAsBoolean("active");
```
```java
// Access nested objects
String city = obj.getAsJsonObject("address").getAsString("city");
```
```java
// Work with arrays
JsonArray scores = obj.getAsJsonArray("scores");
for (Json score : scores) {
    System.out.println(((JsonNumber) score).getValue());
}
```
```java
// Or access array elements directly
BigDecimal firstScore = scores.getAsBigDecimal(0);
BigDecimal secondScore = scores.getAsBigDecimal(1);
```

```java
// Pretty print parsed JSON
JsonPrettyPrinter printer = new JsonPrettyPrinter();
String formatted = printer.getFormattedJsonString(result, 0);
System.out.println(formatted);
```
# How it works

- [JsonLexer](https://github.com/Lou-E-303/json-parser/blob/master/src/main/java/jsonparser/lexing_parsing/JsonLexer.java)
- [JsonParser](https://github.com/Lou-E-303/json-parser/blob/master/src/main/java/jsonparser/lexing_parsing/JsonParser.java)
- [JsonFiniteStateMachine](https://github.com/Lou-E-303/json-parser/blob/master/src/main/java/jsonparser/state_management/JsonFiniteStateMachine.java)

are the three key components to understanding how the parsing works.

From there, you can see the main logical flow of the parser, which parses lexical tokens using a finite state machine implementation:

1. The lexer breaks the input string down into lexical tokens
2. The tokens are then processed by the parser according to formal JSON grammar rules
3. The finite state machine manages the parse state transitions.

Everything else in this library is utility built to support these three components.

# Run

See JitPack instructions [here](https://jitpack.io/#Lou-E-303/jsonjar).

# Links

Link to challenge [here](https://codingchallenges.fyi/challenges/challenge-json-parser/) with thanks to John Crickett.