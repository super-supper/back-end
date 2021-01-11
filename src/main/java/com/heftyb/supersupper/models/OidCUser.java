package com.heftyb.supersupper.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.Collection;
import java.util.Map;

public class OidCUser implements OidcUser
{
    private OidcUser oidcUser;

    public OidCUser(OidcUser oidcUser)
    {
        this.oidcUser = oidcUser;
    }

    @Override
    public Map<String, Object> getClaims()
    {
        return oidcUser.getClaims();
    }

    @Override
    public OidcUserInfo getUserInfo()
    {
        return oidcUser.getUserInfo();
    }

    @Override
    public OidcIdToken getIdToken()
    {
        return oidcUser.getIdToken();
    }

    @Override
    public Map<String, Object> getAttributes()
    {
        return oidcUser.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return oidcUser.getAuthorities();
    }

    @Override
    public String getName()
    {
        return oidcUser.getName();
    }

    public String getFullName()
    {
        return oidcUser.getFullName();
    }

    public String getPicture()
    {
        return oidcUser.getPicture();
    }
}
