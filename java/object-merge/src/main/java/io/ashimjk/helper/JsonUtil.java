package io.ashimjk.helper;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static java.nio.file.Files.readAllLines;

@Slf4j
public final class JsonUtil {

    private static final Gson GSON = new Gson();

    public static <T> T readPropertyValue(String json, String propertyName) {
        return JsonPath.read(json, propertyName);
    }

    public static String getPropertyValueAsString(String json, String propertyName) {
        return JsonPath.read(json, propertyName).toString();
    }

    @SneakyThrows
    private static String readFileAsString(String filePath, Class aClass) {
        URL resourceUrl = aClass.getClassLoader().getResource(filePath);
        if (resourceUrl == null) {
            throw new IllegalArgumentException("cannot read file from path: " + filePath);
        }
        List<String> strings = readAllLines(Paths.get(resourceUrl.getPath()));
        return String.join("", strings);
    }

    public static Map<String, Object> readFileAsMap(String filePath, Class aClass) {
        return PayloadHelper.convertToMap(readFileAsString(filePath, aClass));
    }

    public static String toJson(Map<String, Object> payload) {
        return GSON.toJson(payload);
    }

    public static Gson gson() {
        return GSON;
    }
}
