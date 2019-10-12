package io.ashimjk.jsondiff;

import com.fasterxml.jackson.databind.JsonNode;
import io.ashimjk.jsondiff.domain.ChangeLog;
import io.ashimjk.jsondiff.domain.ChangeLogString;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static io.ashimjk.jsondiff.util.JsonUtil.*;
import static java.util.Arrays.asList;

@RestController
public class Api {

    @GetMapping("/")
    @SneakyThrows
    public ResponseEntity<Object> object() {
        JsonNode existing = node("lc_obj.json");
        JsonNode diff = diff(existing, NEW_LC);

        return ResponseEntity.ok(MAPPER.readValue(diff.toString(), Object[].class));
    }

    @GetMapping("/changeLog")
    @SneakyThrows
    public ResponseEntity<List<ChangeLog>> changeLog() {
        JsonNode existing = node("lc_obj.json");
        JsonNode diff = diff(existing, NEW_LC);

        return ResponseEntity.ok(asList(MAPPER.readValue(diff.toString(), ChangeLog[].class)));
    }

    @GetMapping("/string")
    @SneakyThrows
    public ResponseEntity<Object> string() {
        JsonNode existing = node("lc_obj.json");
        JsonNode diff = diff(existing, NEW_LC);

        ChangeLogString[] changeLogs = MAPPER.readValue(diff.toString(), ChangeLogString[].class);

        return ResponseEntity.ok(changeLogs);
    }
}
