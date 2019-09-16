package io.ashimjk.jsondiff;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.diff.JsonDiff;
import io.ashimjk.jsondiff.domain.LcSummary;
import io.ashimjk.jsondiff.util.FileUtil;
import io.ashimjk.jsondiff.util.JsonUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.ashimjk.jsondiff.util.JsonUtil.NEW_LC;
import static io.ashimjk.jsondiff.util.JsonUtil.OLD_LC;
import static io.ashimjk.jsondiff.util.JsonUtil.diff;
import static io.ashimjk.jsondiff.util.JsonUtil.summary;

class JsonPatchDiff {

    @Test
    void testJsonString() {
        String fileName = "lc_old.json";
        LcSummary lc = FileUtil.readJson(fileName);
        Assertions.assertNotNull(lc);
    }

    @Test
    @SneakyThrows
    void testJsonDiff() {
        JsonNode jsonNode = JsonDiff.asJson(JsonUtil.OLD_LC, NEW_LC);

        Assertions.assertNotNull(jsonNode);
        System.out.println(jsonNode);
    }

    @Test
    @SneakyThrows
    void testJsonRestore() {
        JsonPatch jsonPatch = JsonDiff.asJsonPatch(JsonUtil.OLD_LC, NEW_LC);

        JsonNode updatedNode = jsonPatch.apply(JsonUtil.OLD_LC);
        LcSummary updatedLc = summary(updatedNode);

        LcSummary lcNew = summary(NEW_LC);
        Assertions.assertEquals(lcNew, updatedLc);

        System.out.println(updatedLc);
    }

    @Test
    @SneakyThrows
    void testJsonRestore2() {
        JsonPatch jsonPatch = JsonPatch.fromJson(diff());

        JsonNode updatedNode = jsonPatch.apply(OLD_LC);
        LcSummary updatedLc = summary(updatedNode);

        LcSummary lcNew = summary(NEW_LC);
        Assertions.assertEquals(lcNew, updatedLc);

        System.out.println(updatedLc);
    }

}
