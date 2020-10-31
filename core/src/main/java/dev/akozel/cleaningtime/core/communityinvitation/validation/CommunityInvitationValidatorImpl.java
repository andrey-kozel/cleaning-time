package dev.akozel.cleaningtime.core.communityinvitation.validation;

import dev.akozel.cleaningtime.core.community.domain.Community;
import dev.akozel.cleaningtime.core.community.service.CommunityService;
import dev.akozel.cleaningtime.core.communityinvitation.domain.CommunityInvitation;
import dev.akozel.cleaningtime.core.communityinvitation.domain.CommunityInvitationStatus;
import dev.akozel.cleaningtime.core.communityinvitation.repository.CommunityInvitationRepository;
import dev.akozel.cleaningtime.core.user.domain.ApplicationUser;
import dev.akozel.cleaningtime.core.user.service.ApplicationUserService;
import dev.akozel.cleaningtime.core.validation.RulesValidator;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CommunityInvitationValidatorImpl implements CommunityInvitationValidator {

    private final CommunityInvitationRepository invitationRepository;
    private final ApplicationUserService userService;
    private final CommunityService communityService;
    private final RulesValidator rulesValidator;

    @Inject
    public CommunityInvitationValidatorImpl(CommunityInvitationRepository invitationRepository,
                                            ApplicationUserService userService,
                                            CommunityService communityService,
                                            RulesValidator rulesValidator) {
        this.invitationRepository = invitationRepository;
        this.userService = userService;
        this.communityService = communityService;
        this.rulesValidator = rulesValidator;
    }

    @Override
    public void validateSave(Long communityId, String userEmail) {
        if (communityId == null) {
            rulesValidator.raiseError("Community id should be present", "communityId", null);
        }

        Community community = communityService.get(communityId);
        if (community == null) {
            rulesValidator.raiseError("Community should be present", "community", null);
        }

        if (userEmail == null) {
            rulesValidator.raiseError("User email should be present", "userEmail", null);
        }

        ApplicationUser user = userService.getByEmail(userEmail);
        if (user == null) {
            rulesValidator.raiseError("User should be present", "user", null);
        }

    }

    @Override
    public void validateRespond(Long invitationId, CommunityInvitationStatus status) {
        if (invitationId == null) {
            rulesValidator.raiseError("Invitation id should be present", "invitationId", null);
        }

        CommunityInvitation invitation = invitationRepository.get(invitationId);
        if (invitation == null) {
            rulesValidator.raiseError("Invitation should be present", "invitation", null);
        }

        if (status == null || status == CommunityInvitationStatus.SUBMITTED) {
            rulesValidator.raiseError("Status should be CONFIRMED, REJECTED, or RESUBMITTED", "status", null);
        }
    }
}
