package com.ssafy.ssamuso.security.user;

import com.ssafy.ssamuso.security.provider.OAuthUserInfo;
import com.ssafy.ssamuso.users.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class OAuth2UserImpl implements OAuth2User {

    private final User user;

    private final OAuthUserInfo oAuthUserInfo;

    @Override
    public Map<String, Object> getAttributes() {
        return oAuthUserInfo.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().getDescription()));
        return roles;
    }

    @Override
    public String getName() {
        return oAuthUserInfo.getProviderId();
    }

    public String getUsername() {
        return user.getUsername();
    }
}
