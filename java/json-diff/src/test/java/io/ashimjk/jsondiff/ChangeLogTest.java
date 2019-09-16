package io.ashimjk.jsondiff;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.diff.JsonDiff;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import static io.ashimjk.jsondiff.util.JsonUtil.MAPPER;
import static io.ashimjk.jsondiff.util.JsonUtil.NEW_LC;
import static io.ashimjk.jsondiff.util.JsonUtil.diff;
import static io.ashimjk.jsondiff.util.JsonUtil.node;

class ChangeLogTest {

    @Test
    @SneakyThrows
    void checkPropertyChanges() {
        JsonNode diff = diff();

        List<ChangeLog> changeLogs = Arrays.asList(MAPPER.readValue(diff.toString(), ChangeLog[].class));

        JsonNode expected = node("lc_diff.json");

        Assertions.assertEquals(expected.toString(), stringify(changeLogs));
    }

    @Test
    @SneakyThrows
    void checkObjectChanges() {
        JsonNode existing = node("lc_obj.json");
        JsonNode diff = JsonDiff.asJson(existing, NEW_LC);

        List<ChangeLog> changeLogs = Arrays.asList(MAPPER.readValue(diff.toString(), ChangeLog[].class));

        JsonNode expected = node("lc_obj_diff.json");

        Assertions.assertEquals(expected.toString(), stringify(changeLogs));
    }

    @Getter
    @Setter
    private static class ChangeLog implements Serializable {
        private String op;
        private String path;
        private Object value;
    }

    private String stringify(Object obj) {
        return new GsonBuilder().create().toJson(obj);
    }

}
