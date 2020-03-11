package io.ashimjk.objectmerge.util;

import io.ashimjk.objectmerge.model.Beneficiary;
import io.ashimjk.objectmerge.model.authorizedsignature.AuthorizedSignature;
import io.ashimjk.objectmerge.model.authorizedsignature.DelegatedPerson;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static io.ashimjk.objectmerge.DataProvider.*;
import static org.assertj.core.api.Assertions.assertThat;

class MergeUtilTest extends AssertList {

    @Test
    void whenNewHasMoreItems_whenRefUpdate_thenInsertNewRef() {
        List<AuthorizedSignature> originalAuth = originalBeneficiary().getAuthorizedSignatories();
        Beneficiary original = new Beneficiary();
        original.setAuthorizedSignatories(originalAuth);

        List<AuthorizedSignature> updatedAuth = updatedBeneficiary().getAuthorizedSignatories();
        Beneficiary updated = new Beneficiary();
        updated.setAuthorizedSignatories(updatedAuth);

        runUpdateReferenceWithDefaultValue(original, updated);

        assertThat(originalAuth).hasSize(1);
        assertThat(updatedAuth).hasSize(2);

        assertOriginalAndUpdated(original.getAuthorizedSignatories(), updated.getAuthorizedSignatories(), AuthorizedSignature::getReference);
        assertCollectionOfCollectionObject(
                original.getAuthorizedSignatories(),
                updated.getAuthorizedSignatories(),
                AuthorizedSignature::getDelegatedPersons,
                DelegatedPerson::getReference
        );
    }

    @Test
    void whenOriginalHasMoreItems_whenRefUpdate_thenUpdateRef() {
        List<AuthorizedSignature> originalAuth = new ArrayList<>(originalBeneficiary().getAuthorizedSignatories());
        originalAuth.add(authorizedSignature());
        Beneficiary original = new Beneficiary();
        original.setAuthorizedSignatories(originalAuth);

        List<AuthorizedSignature> updatedAuth = new ArrayList<>();
        updatedAuth.add(authorizedSignature());
        Beneficiary updated = new Beneficiary();
        updated.setAuthorizedSignatories(updatedAuth);

        runUpdateReferenceWithDefaultValue(original, updated);

        assertThat(originalAuth).hasSize(2);
        assertThat(updatedAuth).hasSize(1);

        assertOriginalAndUpdated(original.getAuthorizedSignatories(), updated.getAuthorizedSignatories(), AuthorizedSignature::getReference);
        assertCollectionOfCollectionObject(
                original.getAuthorizedSignatories(),
                updated.getAuthorizedSignatories(),
                AuthorizedSignature::getDelegatedPersons,
                DelegatedPerson::getReference
        );
    }

    @Test
    void whenBothHasEqualItems_whenRefUpdate_thenUpdateRef() {
        List<AuthorizedSignature> originalAuth = originalBeneficiary().getAuthorizedSignatories();
        Beneficiary original = new Beneficiary();
        original.setAuthorizedSignatories(originalAuth);

        List<AuthorizedSignature> updatedAuth = new ArrayList<>();
        updatedAuth.add(authorizedSignature());
        Beneficiary updated = new Beneficiary();
        updated.setAuthorizedSignatories(updatedAuth);

        runUpdateReferenceWithoutDefaultValue(original, updated);

        assertThat(originalAuth).hasSize(1);
        assertThat(updatedAuth).hasSize(1);

        assertWithDefaultValueNull(original.getAuthorizedSignatories(), updated.getAuthorizedSignatories(),
                AuthorizedSignature::getId);
        assertCollectionWithDefaultValueNull(
                original.getAuthorizedSignatories(),
                updated.getAuthorizedSignatories(),
                AuthorizedSignature::getDelegatedPersons,
                DelegatedPerson::getId
        );
    }

    @Test
    void whenOriginalHasNotItems_whenRefUpdate_thenSetReference() {
        Beneficiary original = new Beneficiary();
        original.setAuthorizedSignatories(null);

        List<AuthorizedSignature> updatedAuth = new ArrayList<>();
        updatedAuth.add(authorizedSignature());
        Beneficiary updated = new Beneficiary();
        updated.setAuthorizedSignatories(updatedAuth);

        runUpdateReferenceWithoutDefaultValue(original, updated);

        assertThat(original.getAuthorizedSignatories()).isNull();
        assertThat(updatedAuth).hasSize(1);

        assertWithDefaultValueNull(original.getAuthorizedSignatories(), updated.getAuthorizedSignatories(),
                AuthorizedSignature::getId);
        assertCollectionWithDefaultValueNull(
                original.getAuthorizedSignatories(),
                updated.getAuthorizedSignatories(),
                AuthorizedSignature::getDelegatedPersons,
                DelegatedPerson::getId
        );
    }

    @Test
    void whenNewHasNoItems_whenRefUpdate_thenNoUpdateReference() {
        List<AuthorizedSignature> originalAuth = originalBeneficiary().getAuthorizedSignatories();
        Beneficiary original = new Beneficiary();
        original.setAuthorizedSignatories(originalAuth);

        Beneficiary updated = new Beneficiary();
        updated.setAuthorizedSignatories(null);

        runUpdateReferenceWithoutDefaultValue(original, updated);

        assertThat(originalAuth).hasSize(1);
        assertThat(updated.getAuthorizedSignatories()).isNull();

        assertOriginalAndUpdated(original.getAuthorizedSignatories(), updated.getAuthorizedSignatories(),
                AuthorizedSignature::getId);
        assertCollectionOfCollectionObject(
                original.getAuthorizedSignatories(),
                updated.getAuthorizedSignatories(),
                AuthorizedSignature::getDelegatedPersons,
                DelegatedPerson::getId
        );
    }

    private void runUpdateReferenceWithoutDefaultValue(Beneficiary original, Beneficiary updated) {
        MergeUtil.UpdateValue.mergeValue(
                original.getAuthorizedSignatories(),
                updated.getAuthorizedSignatories(),
                AuthorizedSignature::getId,
                AuthorizedSignature::setId
        );

        MergeUtil.UpdateValue.mergeCollection(
                original.getAuthorizedSignatories(),
                updated.getAuthorizedSignatories(),
                AuthorizedSignature::getDelegatedPersons,
                DelegatedPerson::getId,
                DelegatedPerson::setId
        );
    }

    private void runUpdateReferenceWithDefaultValue(Beneficiary original, Beneficiary updated) {
        MergeUtil.UpdateOrSetValue.mergeValue(
                original.getAuthorizedSignatories(),
                updated.getAuthorizedSignatories(),
                AuthorizedSignature::getReference,
                AuthorizedSignature::setReference,
                () -> UUID.randomUUID().toString()
        );

        MergeUtil.UpdateOrSetValue.mergeCollection(
                original.getAuthorizedSignatories(),
                updated.getAuthorizedSignatories(),
                AuthorizedSignature::getDelegatedPersons,
                DelegatedPerson::getReference,
                DelegatedPerson::setReference,
                () -> UUID.randomUUID().toString()
        );
    }

}
