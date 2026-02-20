package com.example.converters;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OidcConverter {

    @Value("${jwt.auth.converter.resource-id}")
    private String resourceId;

    public OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {

        final OidcUserService delegate = new OidcUserService();

        return userRequest -> {
            OidcUser oidcUser = delegate.loadUser(userRequest);

            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();

            // Default authorities
            mappedAuthorities.addAll(oidcUser.getAuthorities());

            // Extract resource_access roles
            Map<String, Object> resourceAccess =
                    oidcUser.getAttribute("resource_access");

            if (resourceAccess != null) {
                Map<String, Object> resource =
                        (Map<String, Object>) resourceAccess.get(resourceId);

                if (resource != null) {
                    Collection<String> roles =
                            (Collection<String>) resource.get("roles");

                    mappedAuthorities.addAll(
                            roles.stream()
                                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                                    .collect(Collectors.toSet())
                    );
                }
            }

            return new DefaultOidcUser(
                    mappedAuthorities,
                    oidcUser.getIdToken(),
                    oidcUser.getUserInfo()
            );
        };
    }

}
