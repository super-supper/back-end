package com.lambdaschool.oktafoundation.config;

//import com.google.api.client.googleapis.GoogleUtils;
//import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
    @Bean
    // see https://www.devglan.com/spring-security/spring-boot-jwt-auth
    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception
    {
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
//        http.authorizeRequests()
//            .antMatchers("/",
//                "/h2-console/**",
//                "/swagger-resources/**",
//                "/swagger-resource/**",
//                "/swagger-ui.html",
//                "/v2/api-docs",
//                "/webjars/**",
//                "/createnewuser")
//            .permitAll()
//            .antMatchers(HttpMethod.POST,
//                "/users/**")
//            .hasAnyRole("ADMIN")
//            .antMatchers(HttpMethod.DELETE,
//                "/users/**")
//            .hasAnyRole("ADMIN")
//            .antMatchers(HttpMethod.PUT,
//                "/users/**")
//            .hasAnyRole("ADMIN")
//            .antMatchers("/users/**",
//                "/useremails/**",
//                "/oauth/revoke-token",
//                "/logout")
//            .anyRequest()
//
//            .authenticated()
//            .antMatchers("/roles/**")
//            .hasAnyRole("ADMIN")
//            .and()
//            .exceptionHandling()
//            .and()
//            .oauth2Login();
//            .and()
//            .oauth2ResourceServer()
//            .and()
//            .oauth2Login()
//            .jwt();



        http.authorizeRequests()
//            .antMatchers("/",
//                "/h2-console/**",
//                "/swagger-resources/**",
//                "/swagger-resource/**",
//                "/swagger-ui.html",
//                "/v2/api-docs",
//                "/webjars/**",
//                "/createnewuser")
//            .permitAll();
            .anyRequest().authenticated()
//            .and()
//            .oauth2ResourceServer().and()
//            .oauth2Client()
            .and()
//            .formLogin()

            .oauth2Login();
//            .loginPage("/urlToLogin")


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
                "openid",
                "https://www.googleapis.com/auth/user.addresses.read",
                "https://www.googleapis.com/auth/user.phonenumbers.read",
                "https://www.googleapis.com/auth/user.birthday.read",
                "https://www.googleapis.com/auth/user.emails.read")
            .build();
    }
}