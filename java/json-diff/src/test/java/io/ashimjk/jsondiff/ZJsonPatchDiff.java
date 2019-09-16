package io.ashimjk.jsondiff;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flipkart.zjsonpatch.DiffFlags;
import com.flipkart.zjsonpatch.JsonDiff;
import com.flipkart.zjsonpatch.JsonPatch;
import io.ashimjk.jsondiff.domain.LcSummary;
import io.ashimjk.jsondiff.util.FileUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;

class ZJsonPatchDiff {

    private ObjectMapper mapper = new ObjectMapper();
    private EnumSet<DiffFlags> flags = DiffFlags.dontNormalizeOpIntoMoveAndCopy().clone();

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

        JsonNode jsonNode = JsonDiff.asJson(oldNode, newNode, flags);

        Assertions.assertNotNull(jsonNode);
        System.out.println(jsonNode);
    }

    @Test
    @SneakyThrows
    void testJsonRestore() {
        JsonNode oldNode = mapper.readTree(FileUtil.readString("lc_old.json"));
        JsonNode newNode = mapper.readTree(FileUtil.readString("lc_new.json"));

        JsonNode jsonNode = JsonDiff.asJson(oldNode, newNode, flags);

        JsonPatch.applyInPlace(jsonNode, oldNode);
        LcSummary updatedLc = mapper.convertValue(oldNode, LcSummary.class);

        LcSummary lcNew = mapper.convertValue(newNode, LcSummary.class);
        Assertions.assertEquals(lcNew, updatedLc);

        System.out.println(updatedLc);
    }

}
