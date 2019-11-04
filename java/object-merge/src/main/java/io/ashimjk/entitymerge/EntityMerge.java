package io.ashimjk.entitymerge;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.ashimjk.entitymerge.request.BeneficiaryRequest;
import lombok.SneakyThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

class EntityMerge {

    @SneakyThrows
    private BeneficiaryRequest beneficiaryRequest() {
        String file = Objects.requireNonNull(EntityMerge.class.getClassLoader()
                .getResource("beneficiary.json"))
                .getFile();

        Path path = Paths.get(file);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(NON_EMPTY);
        mapper.setSerializationInclusion(NON_NULL);

        return mapper.readValue(path.toFile(), BeneficiaryRequest.class);
    }
}
