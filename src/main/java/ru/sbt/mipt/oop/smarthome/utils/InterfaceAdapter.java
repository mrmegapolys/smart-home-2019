package ru.sbt.mipt.oop.smarthome.utils;

import com.google.gson.*;

import java.lang.reflect.Type;

public class InterfaceAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {

    @Override
    public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject wrapper = new JsonObject();
        wrapper.addProperty("type", src.getClass().getName());
        return wrapper;
    }

    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        JsonObject wrapper = (JsonObject) json;
        JsonElement typeName = get(wrapper, "type");
        Type type = typeForName(typeName);
        return context.deserialize(json, type);
    }

    private JsonElement get(JsonObject wrapper, String memberName) {
        JsonElement elem = wrapper.get(memberName);
        if (elem == null) throw new JsonParseException(
                "No '" + memberName + "' member found in what was expected to be an interface wrapper"
        );
        return elem;
    }

    private Type typeForName(JsonElement typeElem) {
        try {
            return Class.forName(typeElem.getAsString());
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e);
        }
    }
}
