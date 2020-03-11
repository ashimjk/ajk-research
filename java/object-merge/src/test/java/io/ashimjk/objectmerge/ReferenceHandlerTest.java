package io.ashimjk.objectmerge;

import io.ashimjk.objectmerge.model.Beneficiary;
import io.ashimjk.objectmerge.model.ContactPerson;
import io.ashimjk.objectmerge.model.KeyManagement;
import io.ashimjk.objectmerge.model.ShareHolder;
import io.ashimjk.objectmerge.model.authorizedsignature.AuthorizedSignature;
import io.ashimjk.objectmerge.model.authorizedsignature.DelegatedPerson;
import io.ashimjk.objectmerge.model.correspondentbank.Account;
import io.ashimjk.objectmerge.model.correspondentbank.CorrespondentBank;
import io.ashimjk.objectmerge.model.deal.TypeOfDealing;
import io.ashimjk.objectmerge.model.shared.Address;
import io.ashimjk.objectmerge.model.shared.IdType;
import io.ashimjk.objectmerge.util.AssertList;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;

import static io.ashimjk.objectmerge.DataProvider.originalBeneficiary;
import static io.ashimjk.objectmerge.DataProvider.updatedBeneficiary;
import static java.util.Optional.ofNullable;
import static org.assertj.core.api.Assertions.assertThat;

class ReferenceHandlerTest extends AssertList {

    @Test
    void givenBeneficiary_whenInit_shouldSetReferences() {
        Beneficiary beneficiary = updatedBeneficiary();

        ReferenceHandler.init(beneficiary);

        assertList(beneficiary.getContactPersons(), ContactPerson::getReference);
        assertList(beneficiary.getKeyManagements(), KeyManagement::getReference);
        assertList(beneficiary.getShareHolders(), ShareHolder::getReference);
        assertList(beneficiary.getAddresses(), Address::getReference);
        assertList(beneficiary.getAuthorizedSignatories(), AuthorizedSignature::getReference);
        assertList(beneficiary.getAuthorizedSignatories(), AuthorizedSignature::getDelegatedPersons, DelegatedPerson::getReference);
        assertList(beneficiary.getCorrespondentBanks(), CorrespondentBank::getReference);
        assertList(beneficiary.getCorrespondentBanks(), CorrespondentBank::getAddress);
        assertList(beneficiary.getCorrespondentBanks(), CorrespondentBank::getAccounts, Account::getReference);
        assertList(beneficiary.getIdTypes(), IdType::getReference);
        assertList(beneficiary.getTypeOfDealings(), TypeOfDealing::getReference);
    }

    @Test
    void givenOriginalAndUpdated_whenExecuted_shouldUpdateReference() {
        Beneficiary original = originalBeneficiary();
        Beneficiary updated = updatedBeneficiary();

        ReferenceHandler.updateReferences(original, updated);

        assertOriginalAndUpdated(original.getContactPersons(), updated.getContactPersons(), ContactPerson::getReference);
        assertOriginalAndUpdated(original.getKeyManagements(), updated.getKeyManagements(), KeyManagement::getReference);
        assertOriginalAndUpdated(original.getShareHolders(), updated.getShareHolders(), ShareHolder::getReference);
        assertOriginalAndUpdated(original.getAddresses(), updated.getAddresses(), Address::getReference);

        assertOriginalAndUpdated(original.getAuthorizedSignatories(), updated.getAuthorizedSignatories(), AuthorizedSignature::getReference);
        assertCollectionOfCollectionObject(
                original.getAuthorizedSignatories(),
                updated.getAuthorizedSignatories(),
                AuthorizedSignature::getDelegatedPersons,
                DelegatedPerson::getReference
        );

        assertOriginalAndUpdated(original.getCorrespondentBanks(), updated.getCorrespondentBanks(), CorrespondentBank::getReference);
        assertCollectionOfCollectionObject(
                original.getCorrespondentBanks(),
                updated.getCorrespondentBanks(),
                CorrespondentBank::getAccounts,
                Account::getReference
        );
        assertOriginalAndUpdated(
                original.getCorrespondentBanks().get(0).getAccounts(),
                updated.getCorrespondentBanks().get(0).getAccounts(),
                Account::getReference
        );

        assertOriginalAndUpdated(original.getIdTypes(), updated.getIdTypes(), IdType::getReference);
        assertOriginalAndUpdated(original.getTypeOfDealings(), updated.getTypeOfDealings(), TypeOfDealing::getReference);
    }

    private <T, R> void assertList(List<T> objects,
                                   Function<T, List<R>> collectionFunction,
                                   Function<R, Object> getFunction) {
        objects.forEach(obj -> {

            ofNullable(collectionFunction.apply(obj))
                    .ifPresent(o -> assertList(o, getFunction));
        });
    }

    private <T> void assertList(List<T> objects, Function<T, Object> getFunction) {
        objects.forEach(obj -> {
            assertThat(getFunction.apply(obj)).isNotNull();
        });
    }

}
