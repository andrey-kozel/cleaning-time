package dev.akozel.cleaningtime.core.communityinvitation.service;

import dev.akozel.cleaningtime.core.communityinvitation.domain.CommunityInvitation;
import dev.akozel.cleaningtime.core.communityinvitation.domain.CommunityInvitationStatus;
import dev.akozel.cleaningtime.core.communityinvitation.repository.CommunityInvitationRepository;
import dev.akozel.cleaningtime.core.communityinvitation.validation.CommunityInvitationValidator;
import dev.akozel.cleaningtime.core.user.domain.ApplicationUser;
import dev.akozel.cleaningtime.core.user.service.ApplicationUserService;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CommunityInvitationServiceImpl implements CommunityInvitationService {

    private final CommunityInvitationRepository invitationRepository;
    private final ApplicationUserService userService;
    private final CommunityInvitationValidator invitationValidator;

    @Inject
    public CommunityInvitationServiceImpl(CommunityInvitationRepository invitationRepository,
                                          ApplicationUserService userService,
                                          CommunityInvitationValidator invitationValidator) {
        this.invitationRepository = invitationRepository;
        this.userService = userService;
        this.invitationValidator = invitationValidator;
    }

    @Override
    public Long inviteUser(Long communityId, String userEmail) {
        invitationValidator.validateSave(communityId, userEmail);
        ApplicationUser userToBeInvited = userService.getByEmail(userEmail);
        CommunityInvitation previousInvitation =
                invitationRepository.findByCommunityAndUser(communityId, userToBeInvited.getId());
        if (previousInvitation == null) {
            CommunityInvitation invitation = CommunityInvitation.builder()
                    .userId(userToBeInvited.getId())
                    .communityId(communityId)
                    .status(CommunityInvitationStatus.SUBMITTED)
                    .build();
            return invitationRepository.save(invitation);
        } else {
            invitationRepository.update(previousInvitation.getId(), CommunityInvitationStatus.RESUBMITTED);
            return previousInvitation.getId();
        }
    }

    @Override
    public Long respondForInvitation(Long invitationId, CommunityInvitationStatus status) {
        invitationValidator.validateRespond(invitationId, status);
        invitationRepository.update(invitationId, status);
        return invitationId;
    }
}
