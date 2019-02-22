package com.tahir.authorization.server.authorizationserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    static final String APPNAME = "ras";
    static final String CLIEN_ID = APPNAME + "-client";
    static final String CLIENT_SECRET = APPNAME + "-secret";
    //	static final String CLIENT_SECRET ="$2a$04$e/c1/RfsWuThaWFCrcCuJeoyvwCV0URN/6Pn9ZFlrtIWaU/vj/BfG";
    static final String GRANT_TYPE_PASSWORD = "password";
    static final String CLIENT_CREDENTIALS = "client_credentials";
    static final String AUTHORIZATION_CODE = "authorization_code";
    static final String REFRESH_TOKEN = "refresh_token";
    static final String IMPLICIT = "implicit";
    static final String SCOPE_READ = "read";
    static final String SCOPE_WRITE = "write";
    static final String TRUST = "trust";
    static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1 * 60 * 60;
    static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6 * 60 * 60;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("as466gf");
        return converter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {

        configurer

//				This is For Testing ClientAuthorizationServerSecurityConfigurer
                .inMemory()
                .withClient(APPNAME + "-angular-id")
                .secret(passwordEncoder.encode(APPNAME + "-angular-secret"))
                .authorizedGrantTypes(CLIENT_CREDENTIALS)
                .scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
                .refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS)

                .and()

                .withClient(APPNAME + "-android-id")
                .secret(passwordEncoder.encode(APPNAME + "-android-secret"))
                .authorizedGrantTypes(CLIENT_CREDENTIALS)
                .scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
                .refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS)

                .and()

                .withClient(APPNAME + "-ios-id")
                .secret(passwordEncoder.encode(APPNAME + "-ios-secret"))
                .authorizedGrantTypes(CLIENT_CREDENTIALS)
                .scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
                .refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS)

                .and()

                .withClient(APPNAME + "-crown-job-id")
                .secret(passwordEncoder.encode(APPNAME + "-crown-job-secret"))
                .authorizedGrantTypes(CLIENT_CREDENTIALS)
                .scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
                .refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS)


                .and()

                .withClient(APPNAME + "-secure-redirect")
                .authorizedGrantTypes("authorization_code")
                .authorities("ROLE_CLIENT")
                .scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
                .resourceIds(APPNAME + "-resource-redirect")
                .redirectUris("http://anywhere?key=value")

                .and()

                .withClient(CLIEN_ID)
                .secret(passwordEncoder.encode(CLIENT_SECRET))
                .authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT)
                .scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
                .refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS)

                .and()

                .withClient("my-trusted-client")
                .authorizedGrantTypes("password",
                        "refresh_token", "implicit", "client_credentials", "authorization_code")
                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
                .scopes("read", "write", "trust")
                .accessTokenValiditySeconds(60)
                .redirectUris("http://localhost:8081/test.html")
                .resourceIds("resource")
                .secret("mysecret");

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
//                .pathMapping("/oauth/authorize", "/api/auth/v1/oauth/authorize")// here
//                .pathMapping("/oauth/check_token", "/api/auth/v1/oauth/check_token")// here
//                .pathMapping("/oauth/confirm_access", "/api/auth/v1/oauth/confirm_access")// here
//                .pathMapping("/oauth/error", "/api/auth/v1/oauth/error")// here
//                .pathMapping("/oauth/token", "/api/auth/v1/oauth/token")// here
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .accessTokenConverter(accessTokenConverter())
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer)
            throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()");
    }
}

