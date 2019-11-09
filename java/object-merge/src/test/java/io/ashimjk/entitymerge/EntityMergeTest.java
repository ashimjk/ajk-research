package io.ashimjk.entitymerge;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.ashimjk.entitymerge.domain.Beneficiary;
import io.ashimjk.entitymerge.request.AddressRequest;
import io.ashimjk.entitymerge.request.BeneficiaryRequest;
import io.ashimjk.helper.PayloadHelper;
import io.ashimjk.merge.ModelMerger;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

class EntityMergeTest {

    private Transformer transformer = new TransformerImpl();

    @Test
    void testMerge() {
        BeneficiaryRequest request = beneficiaryRequest();
        Beneficiary beneficiary = transformer.beneficiary(request);

        BeneficiaryRequest modifiedRequest = new BeneficiaryRequest();
        modifiedRequest.setFullName("ashim khakda");
        AddressRequest address = new AddressRequest();
        address.setAddressName("ktm");
        address.setCity("ktm");
        address.setCountry("nepal");
        modifiedRequest.setAddresses(Collections.singletonList(address));

        Beneficiary modifiedBeneficiary = transformer.beneficiary(modifiedRequest);

//        mergeUsingObject(beneficiary, modifiedBeneficiary);

//        mergeUsingMap(beneficiary, modifiedBeneficiary);

        mergeUsingModelMapper(beneficiary, modifiedBeneficiary);
    }

    private void mergeUsingModelMapper(Beneficiary beneficiary, Beneficiary modifiedBeneficiary) {
        Beneficiary response = ModelMerger.merge(beneficiary, modifiedBeneficiary);
        System.out.println(EntityMerge.json(response));
    }

    private void mergeUsingObject(Beneficiary beneficiary, Beneficiary modifiedBeneficiary) {
        Beneficiary response = EntityMerge.applyUpdates(beneficiary, modifiedBeneficiary);

        System.out.println(EntityMerge.json(response));
    }

    /**
     * If json structure is different then it won't merge
     *
     * @param beneficiary         source
     * @param modifiedBeneficiary updates
     */
    private void mergeUsingMap(Beneficiary beneficiary, Beneficiary modifiedBeneficiary) {
        Map<String, Object> original = PayloadHelper.convertToMap(beneficiary);
        Map<String, Object> updated = PayloadHelper.convertToMap(modifiedBeneficiary);

        Map<String, Object> response = EntityMerge.applyUpdates(original, updated);

        System.out.println(EntityMerge.json(response));
        System.out.println(EntityMerge.json(PayloadHelper.convertToObject(response, Beneficiary.class)));
    }

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