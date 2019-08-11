package io.ashimjk.jsondiff;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.diff.JsonDiff;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JsonPatchDiff {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void testJsonString() {
        String fileName = "lc_old.json";
        LcSummary lc = FileUtil.readJson(fileName);
        Assertions.assertNotNull(lc);
    }

    @Test
    @SneakyThrows
    void testJsonDiff() {
        JsonNode oldNode = mapper.readTree(FileUtil.readString("lc_old.json"));
        JsonNode newNode = mapper.readTree(FileUtil.readString("lc_new.json"));

        JsonNode jsonNode = JsonDiff.asJson(oldNode, newNode);

        Assertions.assertNotNull(jsonNode);
        System.out.println(jsonNode);
    }

    @Test
    @SneakyThrows
    void testJsonRestore() {
        JsonNode oldNode = mapper.readTree(FileUtil.readString("lc_old.json"));
        JsonNode newNode = mapper.readTree(FileUtil.readString("lc_new.json"));

        JsonPatch jsonPatch = JsonDiff.asJsonPatch(oldNode, newNode);

        JsonNode updatedNode = jsonPatch.apply(oldNode);
        LcSummary updatedLc = mapper.convertValue(updatedNode, LcSummary.class);

        LcSummary lcNew = mapper.convertValue(newNode, LcSummary.class);
        Assertions.assertEquals(lcNew, updatedLc);

        System.out.println(updatedLc);
    }

}
