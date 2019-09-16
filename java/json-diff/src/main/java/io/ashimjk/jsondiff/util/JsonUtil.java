package io.ashimjk.jsondiff.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.diff.JsonDiff;
import io.ashimjk.jsondiff.domain.LcSummary;
import lombok.SneakyThrows;

public class JsonUtil {

    public static ObjectMapper MAPPER = new ObjectMapper();
    public static JsonNode OLD_LC = oldNode();
    public static JsonNode NEW_LC = newNode();

    public static LcSummary summary(JsonNode jsonNode) {
        return MAPPER.convertValue(jsonNode, LcSummary.class);
    }

    public static JsonNode diff() {
        return diff(oldNode(), newNode());
    }

    @SneakyThrows
    public static JsonNode diff(JsonNode oldNode, JsonNode newNode) {

        JsonNode jsonNode = JsonDiff.asJson(oldNode, newNode);

        // convert string into JsonNode
        return MAPPER.readValue(jsonNode.toString(), JsonNode.class);
    }

    private static JsonNode oldNode() {
        return node("lc_old.json");
    }

    private static JsonNode newNode() {
        return node("lc_new.json");
    }

    @SneakyThrows
    public static JsonNode node(String file) {
        return MAPPER.readTree(FileUtil.readString(file));
    }
}
