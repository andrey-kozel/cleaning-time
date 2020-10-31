package dev.akozel.cleaningtime.core.communitymember.validation;

import dev.akozel.cleaningtime.core.communitymember.domain.CommunityMember;
import dev.akozel.cleaningtime.core.common.validation.RulesValidator;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CommunityMemberValidatorImpl implements CommunityMemberValidator {

    private final RulesValidator rulesValidator;

    @Inject
    public CommunityMemberValidatorImpl(RulesValidator rulesValidator) {
        this.rulesValidator = rulesValidator;
    }

    @Override
    public void validateCreate(CommunityMember communityMember) {
        rulesValidator.validate(communityMember);
    }

}
