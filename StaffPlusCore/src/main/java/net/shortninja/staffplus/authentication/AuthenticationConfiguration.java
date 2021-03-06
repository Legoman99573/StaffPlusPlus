package net.shortninja.staffplus.authentication;

public class AuthenticationConfiguration {

    private AuthenticationProvider authenticationProvider;

    public AuthenticationConfiguration(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    public AuthenticationProvider getAuthenticationProvider() {
        return authenticationProvider;
    }
}
