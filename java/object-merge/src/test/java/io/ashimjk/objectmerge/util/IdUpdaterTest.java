package io.ashimjk.objectmerge.util;

import io.ashimjk.objectmerge.model.Beneficiary;
import io.ashimjk.objectmerge.model.ContactPerson;
import io.ashimjk.objectmerge.model.authorizedsignature.AuthorizedSignature;
import io.ashimjk.objectmerge.model.authorizedsignature.DelegatedPerson;
import io.ashimjk.objectmerge.model.correspondentbank.CorrespondentBank;
import io.ashimjk.objectmerge.model.deal.TypeOfDealing;
import org.junit.jupiter.api.Test;

import static io.ashimjk.objectmerge.DataProvider.originalBeneficiary;
import static io.ashimjk.objectmerge.DataProvider.updatedBeneficiary;

class IdUpdaterTest extends AssertList {

    @Test
    void givenOldAndNewBeneficiary_whenMerged_thenReturnBeneficiaryWithExistingId() {
        Beneficiary original = originalBeneficiary();
        Beneficiary updated = updatedBeneficiary();

        IdUpdater.execute(original, updated);

        assertWithDefaultValueNull(original.getContactPersons(), updated.getContactPersons(), ContactPerson::getId);
        assertWithDefaultValueNull(original.getAuthorizedSignatories(), updated.getAuthorizedSignatories(), AuthorizedSignature::getId);
        assertCollectionWithDefaultValueNull(
                original.getAuthorizedSignatories(),
                updated.getAuthorizedSignatories(),
                AuthorizedSignature::getDelegatedPersons,
                DelegatedPerson::getId
        );
        assertWithDefaultValueNull(original.getCorrespondentBanks(), updated.getCorrespondentBanks(), CorrespondentBank::getId);
        assertWithDefaultValueNull(original.getTypeOfDealings(), updated.getTypeOfDealings(), TypeOfDealing::getId);
    }

}
