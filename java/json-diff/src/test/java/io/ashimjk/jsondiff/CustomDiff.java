package io.ashimjk.jsondiff;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.ashimjk.jsondiff.util.FileUtil;
import io.ashimjk.jsondiff.util.FlatMapUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

class CustomDiff {

    @Test
    @SneakyThrows
    void testCustomDiff_UsingJson() {
        String leftJson = FileUtil.readString("lc_old.json");
        String rightJson = FileUtil.readString("lc_new.json");

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String, Object>> type = new TypeReference<HashMap<String, Object>>() {
        };

        Map<String, Object> leftMap = mapper.readValue(leftJson, type);
        Map<String, Object> rightMap = mapper.readValue(rightJson, type);

        Map<String, Object> leftFlatMap = FlatMapUtil.flatten(leftMap);
        Map<String, Object> rightFlatMap = FlatMapUtil.flatten(rightMap);

        MapDifference<String, Object> difference = Maps.difference(leftFlatMap, rightFlatMap);

        System.out.println("Entries only on the left\n--------------------------");
        difference.entriesOnlyOnLeft().forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("\n\nEntries only on the right\n--------------------------");
        difference.entriesOnlyOnRight().forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("\n\nEntries differing\n--------------------------");
        difference.entriesDiffering().forEach((key, value) -> System.out.println(key + ": " + value));
    }

    @Test
    @SneakyThrows
    void testCustomDiff_UsingGson() {
        String leftJson = FileUtil.readString("lc_old.json");
        String rightJson = FileUtil.readString("lc_new.json");

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();

        Map<String, Object> leftMap = gson.fromJson(leftJson, type);
        Map<String, Object> rightMap = gson.fromJson(rightJson, type);

        Map<String, Object> leftFlatMap = FlatMapUtil.flatten(leftMap);
        Map<String, Object> rightFlatMap = FlatMapUtil.flatten(rightMap);

        MapDifference<String, Object> difference = Maps.difference(leftFlatMap, rightFlatMap);

        System.out.println("Entries only on the left\n--------------------------");
        difference.entriesOnlyOnLeft().forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("\n\nEntries only on the right\n--------------------------");
        difference.entriesOnlyOnRight().forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("\n\nEntries differing\n--------------------------");
        difference.entriesDiffering().forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
