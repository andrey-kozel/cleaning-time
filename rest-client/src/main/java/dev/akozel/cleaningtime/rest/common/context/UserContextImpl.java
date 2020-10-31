package dev.akozel.cleaningtime.rest.common.context;

import dev.akozel.cleaningtime.core.common.context.UserContext;
import dev.akozel.cleaningtime.rest.auth.model.ApplicationUserDetails;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserContextImpl implements UserContext {

    @Override
    public Long getUserId() {
        SecurityContext context = SecurityContextHolder.getContext();
        ApplicationUserDetails user = (ApplicationUserDetails) context.getAuthentication().getDetails();
        return user.getId();
    }
}
