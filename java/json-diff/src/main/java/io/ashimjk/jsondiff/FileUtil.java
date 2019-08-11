package io.ashimjk.jsondiff;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.util.Objects.requireNonNull;

class FileUtil {

    @SneakyThrows
    public static String readString(String file) {
        try {
            String path = requireNonNull(FileUtil.class.getClassLoader().getResource(file)).getPath();
            return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    static LcSummary readJson(String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        TypeReference<LcSummary> ref = new TypeReference<LcSummary>() {
        };

        return mapper.readValue(readString(fileName), ref);
    }

}