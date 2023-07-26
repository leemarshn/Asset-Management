package com.lenhac.deprakt.services;


import com.lenhac.deprakt.models.UserSessionInfo;
import com.lenhac.deprakt.repositories.UserSessionInfoRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import org.springframework.stereotype.Service;

@Service
public class UserSessionService {

    private final UserSessionInfoRepository userSessionInfoRepository;

    @Autowired
    public UserSessionService(UserSessionInfoRepository userSessionInfoRepository) {
        this.userSessionInfoRepository = userSessionInfoRepository;
    }

    public UserSessionInfo storeSession(HttpServletRequest request) {
        try {
            UserSessionInfo session = new UserSessionInfo();
            // Get the authenticated user's details
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
                WebAuthenticationDetails authDetails = (WebAuthenticationDetails) authentication.getDetails();

                String username = userDetails.getUsername();
                String roleName = null;

                // Find the role authority (it starts with "ROLE_")
                for (GrantedAuthority authority : userDetails.getAuthorities()) {
                    if (authority.getAuthority().startsWith("ROLE_")) {
                        roleName = authority.getAuthority().substring("ROLE_".length());
                        break;
                    }
                }

                session.setSessionId(authDetails.getSessionId());
                session.setIpAddress(authDetails.getRemoteAddress());
                session.setUsername(username);
                session.setRoleName(roleName);
                session.setUserAgent(request.getHeader("User-Agent"));

                return userSessionInfoRepository.save(session);
            } else {
                // Authentication details not available or not of the expected type
                // You can handle this case as needed, such as logging an error or returning null
                return null;
            }
        } catch (Exception e) {
            // Handle any other exceptions that may occur during the process
            e.printStackTrace();
            return null;
        }

    }
}

