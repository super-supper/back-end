package com.heftyb.supersupper.handlers;

import com.heftyb.supersupper.models.OidCUser;
import com.heftyb.supersupper.repository.UserRepository;
import com.heftyb.supersupper.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler
{
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userrepo;

    public OAuth2LoginSuccessHandler()
    {
        super();
    }

    public OAuth2LoginSuccessHandler(String defaultTargetUrl)
    {
        super(defaultTargetUrl);
    }

    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException
    {
        OidCUser oAuth2User = (OidCUser) authentication.getPrincipal();

        if (userrepo.findByUsername(oAuth2User.getName()) == null)
        {
            userService.saveOidcUser(oAuth2User);
        }

        super.onAuthenticationSuccess(request,
            response,
            authentication);
    }
}
