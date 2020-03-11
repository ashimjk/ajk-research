package io.ashimjk.objectmerge.util;

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
import io.ashimjk.objectmerge.util.MergeUtil.UpdateOrSetValue;
import lombok.experimental.UtilityClass;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static io.ashimjk.objectmerge.util.DomainAsserts.isBlank;
import static io.ashimjk.objectmerge.util.ReferenceInitializer.setCollectionOfReferences;
import static io.ashimjk.objectmerge.util.ReferenceInitializer.setReferences;

@UtilityClass
public class ReferenceHandler {

    public static void init(Beneficiary beneficiary) {
        setReferences(beneficiary.getContactPersons(), ContactPerson::setReference);
        setReferences(beneficiary.getKeyManagements(), KeyManagement::setReference);
        setReferences(beneficiary.getShareHolders(), ShareHolder::setReference);
        setReferences(beneficiary.getAddresses(), Address::setReference);

        setReferences(beneficiary.getAuthorizedSignatories(), AuthorizedSignature::setReference);
        setCollectionOfReferences(beneficiary.getAuthorizedSignatories(), AuthorizedSignature::getDelegatedPersons, DelegatedPerson::setReference);

        setReferences(beneficiary.getCorrespondentBanks(), CorrespondentBank::setReference);
        setReferences(beneficiary.getCorrespondentBanks(), (correspondentBank, s) -> correspondentBank.getAddress().setReference(s));
        setCollectionOfReferences(beneficiary.getCorrespondentBanks(), CorrespondentBank::getAccounts, Account::setReference);

        setReferences(beneficiary.getIdTypes(), IdType::setReference);
        setReferences(beneficiary.getTypeOfDealings(), TypeOfDealing::setReference);
    }

    public static void updateReferences(Beneficiary updated) {
        updateReferencesIfAvailable(updated.getContactPersons(), ContactPerson::getReference, ContactPerson::setReference);
        updateReferencesIfAvailable(updated.getKeyManagements(), KeyManagement::getReference, KeyManagement::setReference);
        updateReferencesIfAvailable(updated.getShareHolders(), ShareHolder::getReference, ShareHolder::setReference);
        updateReferencesIfAvailable(updated.getAddresses(), Address::getReference, Address::setReference);

        safeList(updated.getAuthorizedSignatories()).forEach(as -> {
            updateReferenceIfEmpty(as::getReference, as::setReference);
            updateReferencesIfAvailable(as.getDelegatedPersons(), DelegatedPerson::getReference, DelegatedPerson::setReference);
        });

        safeList(updated.getCorrespondentBanks()).forEach(cb -> {
            updateReferenceIfEmpty(cb::getReference, cb::setReference);
            updateReferencesIfAvailable(cb.getAccounts(), Account::getReference, Account::setReference);
            safeObject(cb.getAddress(), ad -> updateReferenceIfEmpty(ad::getReference, ad::setReference));
        });

        updateReferencesIfAvailable(updated.getIdTypes(), IdType::getReference, IdType::setReference);
        updateReferencesIfAvailable(updated.getTypeOfDealings(), TypeOfDealing::getReference, TypeOfDealing::setReference);
    }

    public static <T> List<T> safeList(List<T> objects) {
        return Optional.ofNullable(objects)
                .orElseGet(Collections::emptyList);
    }

    public static <T> void safeObject(T object, Consumer<T> consumer) {
        Optional.ofNullable(object).ifPresent(consumer);
    }

    private static <T> void updateReferencesIfAvailable(List<T> objects, Function<T, String> getFunction, BiConsumer<T, String> setConsumer) {
        Optional.ofNullable(objects)
                .orElseGet(Collections::emptyList)
                .forEach(vl -> updateReferenceIfEmpty(() -> getFunction.apply(vl), s -> setConsumer.accept(vl, s)));
    }

    public static void updateReferenceIfEmpty(Supplier<String> getSupplier, Consumer<String> consumer) {
        if (isBlank(getSupplier.get())) {
            consumer.accept(defaultReference());
        }
    }

    public static void updateReferences(Beneficiary original, Beneficiary updated) {

        UpdateOrSetValue.mergeValue(
                original.getContactPersons(),
                updated.getContactPersons(),
                ContactPerson::getReference,
                ContactPerson::setReference,
                ReferenceHandler::defaultReference
        );

        UpdateOrSetValue.mergeValue(
                original.getKeyManagements(),
                updated.getKeyManagements(),
                KeyManagement::getReference,
                KeyManagement::setReference,
                ReferenceHandler::defaultReference
        );

        UpdateOrSetValue.mergeValue(
                original.getShareHolders(),
                updated.getShareHolders(),
                ShareHolder::getReference,
                ShareHolder::setReference,
                ReferenceHandler::defaultReference
        );

        UpdateOrSetValue.mergeValue(
                original.getAddresses(),
                updated.getAddresses(),
                Address::getReference,
                Address::setReference,
                ReferenceHandler::defaultReference
        );

        UpdateOrSetValue.mergeValue(
                original.getAuthorizedSignatories(),
                updated.getAuthorizedSignatories(),
                AuthorizedSignature::getReference,
                AuthorizedSignature::setReference,
                ReferenceHandler::defaultReference
        );

        UpdateOrSetValue.mergeCollection(
                original.getAuthorizedSignatories(),
                updated.getAuthorizedSignatories(),
                AuthorizedSignature::getDelegatedPersons,
                DelegatedPerson::getReference,
                DelegatedPerson::setReference,
                ReferenceHandler::defaultReference
        );

        UpdateOrSetValue.mergeValue(
                original.getCorrespondentBanks(),
                updated.getCorrespondentBanks(),
                CorrespondentBank::getReference,
                CorrespondentBank::setReference,
                ReferenceHandler::defaultReference
        );

        UpdateOrSetValue.mergeCollection(
                original.getCorrespondentBanks(),
                updated.getCorrespondentBanks(),
                CorrespondentBank::getAccounts,
                Account::getReference,
                Account::setReference,
                ReferenceHandler::defaultReference
        );

        UpdateOrSetValue.mergeValue(
                original.getIdTypes(),
                updated.getIdTypes(),
                IdType::getReference,
                IdType::setReference,
                ReferenceHandler::defaultReference
        );

        UpdateOrSetValue.mergeValue(
                original.getTypeOfDealings(),
                updated.getTypeOfDealings(),
                TypeOfDealing::getReference,
                TypeOfDealing::setReference,
                ReferenceHandler::defaultReference
        );
    }

    public static String defaultReference() {
        return UUID.randomUUID().toString();
    }

}
