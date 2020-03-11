package io.ashimjk.objectmerge.util;

import io.ashimjk.objectmerge.model.Beneficiary;
import io.ashimjk.objectmerge.model.ContactPerson;
import io.ashimjk.objectmerge.model.authorizedsignature.AuthorizedSignature;
import io.ashimjk.objectmerge.model.authorizedsignature.DelegatedPerson;
import io.ashimjk.objectmerge.model.correspondentbank.CorrespondentBank;
import io.ashimjk.objectmerge.model.deal.TypeOfDealing;

public final class IdUpdater {

    public static void execute(Beneficiary original, Beneficiary updated) {

        MergeUtil.UpdateValue.mergeValue(
                original.getContactPersons(),
                updated.getContactPersons(),
                ContactPerson::getId,
                ContactPerson::setId
        );

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

        MergeUtil.UpdateValue.mergeValue(
                original.getCorrespondentBanks(),
                updated.getCorrespondentBanks(),
                CorrespondentBank::getId,
                CorrespondentBank::setId
        );

        MergeUtil.UpdateValue.mergeValue(
                original.getTypeOfDealings(),
                updated.getTypeOfDealings(),
                TypeOfDealing::getId,
                TypeOfDealing::setId
        );
    }

}
