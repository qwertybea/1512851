package ca.cours5b5.justinfofana.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

public class Jsonification {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();;

    public static Map<String, Object> enObjetJson(String json) {
        return (Map<String, Object>) gson.fromJson(json, Map.class);
    }

    public static String enChaine(Map<String, Object> objetJson) {
        return gson.toJson(objetJson);
    }

}
