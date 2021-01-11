package com.heftyb.supersupper.config;

import com.heftyb.supersupper.handlers.OAuth2LoginSuccessHandler;
import com.heftyb.supersupper.services.OidCUserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.AuthenticatedPrincipalOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;

@Configuration
@EnableWebSecurity
public class GoogleAuthSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private OidCUserServiceImp oAuth2UserService;

    @Autowired
    private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
            .antMatchers("/",
                "/h2-console/**",
                "/swagger-resources/**",
                "/swagger-resource/**",
                "/swagger-ui.html",
                "/v2/api-docs",
                "/webjars/**",
                "/createnewuser")
            .permitAll()
            .antMatchers("/users/**",
                "/useremails/**",
                "/oauth/revoke-token",
                "/logout",
                "/info")
            .authenticated()
            .and()
            .exceptionHandling()
            .and()
            .oauth2Login()
            .userInfoEndpoint()
            .oidcUserService(oAuth2UserService)
            .and()
            .successHandler(oAuth2LoginSuccessHandler);


        // process CORS annotations
        // http.cors();

        // disable the creation and use of Cross Site Request Forgery Tokens.
        // These tokens require coordination with the front end client that is beyond the scope of this class.
        // See https://www.yawintutor.com/how-to-enable-and-disable-csrf/ for more information
        http
            .csrf()
            .disable();

        // force a non-empty response body for 401's to make the response more browser friendly
        //        Okta.configureResourceServer401ResponseBody(http);

        // h2 console
        http.headers()
            .frameOptions()
            .disable();
    }


    @Bean
    public ClientRegistrationRepository clientRegistrationRepository()
    {
        return new InMemoryClientRegistrationRepository(this.googleClientRegistration());
    }

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService(ClientRegistrationRepository clientRegistrationRepository)
    {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository);
    }

    @Bean
    public OAuth2AuthorizedClientRepository authorizedClientRepository(OAuth2AuthorizedClientService authorizedClientService)
    {
        return new AuthenticatedPrincipalOAuth2AuthorizedClientRepository(authorizedClientService);
    }

    private ClientRegistration googleClientRegistration()
    {
        return CommonOAuth2Provider.GOOGLE.getBuilder("google")
            .clientId(System.getenv("GOOGID"))
            .clientSecret(System.getenv("GOOGSEC"))
            .scope("email",
                "profile",
                "openid"
            )
            .build();
    }
}