package dev.akozel.cleaningtime.core.community.validation;

import dev.akozel.cleaningtime.core.community.domain.Community;
import dev.akozel.cleaningtime.core.common.validation.RulesValidator;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * CommunityValidatorImpl.
 * <p>
 * Date: 23/02/2020
 *
 * @author Andrey Kozel
 */
@Named
public class CommunityValidatorImpl implements CommunityValidator {

    private final RulesValidator rulesValidator;

    @Inject
    public CommunityValidatorImpl(RulesValidator rulesValidator) {
        this.rulesValidator = rulesValidator;
    }

    @Override
    public void validateGet(Long communityId) {
        validCommunityId(communityId);
    }

    @Override
    public void validateCreate(Community community) {
        rulesValidator.validate(community);
    }

    @Override
    public void validateUpdate(Long communityId, Community community) {
        validCommunityId(communityId);
        rulesValidator.validate(community);
    }

    @Override
    public void validateDelete(Long communityId) {
        validCommunityId(communityId);
    }

    private void validCommunityId(Long communityId) {
        if (communityId == null) {
            rulesValidator.raiseError("Community id should be present", "communityId", null);
        }
    }

}
