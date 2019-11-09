package io.ashimjk.entitymerge;

import io.ashimjk.entitymerge.domain.Beneficiary;
import io.ashimjk.entitymerge.request.BeneficiaryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface Transformer {

    @Mapping(target = "reference")
    Beneficiary beneficiary(BeneficiaryRequest request);

}
