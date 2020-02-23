package dev.akozel.cleaningtime.core.community.validation;

import dev.akozel.cleaningtime.core.community.domain.Community;
import dev.akozel.cleaningtime.core.validation.RulesValidator;

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

    private RulesValidator rulesValidator;

    @Inject
    public CommunityValidatorImpl(RulesValidator rulesValidator) {
        this.rulesValidator = rulesValidator;
    }

    @Override
    public void validateGet(Integer communityId) {
        if (communityId == null) {
            rulesValidator.raiseError("Community id should be present", "communityId", null);
        }
    }

    @Override
    public void validateCreate(Community community) {
        rulesValidator.validate(community);
    }

    @Override
    public void validateUpdate(Integer communityId, Community community) {
        if (communityId == null) {
            rulesValidator.raiseError("Community id should be present", "communityId", null);
        }
        rulesValidator.validate(community);
    }
}
