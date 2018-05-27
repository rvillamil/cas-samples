package samples.cas.client;

import javax.servlet.http.HttpSessionEvent;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.validation.Cas30ServiceTicketValidator;
import org.jasig.cas.client.validation.TicketValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.EventListener;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@SpringBootApplication
public class CasSecuredAppApplicationOne {

    private static final String SERVICE_NAME = "https://localhost:9001/login/cas";
    private static final String CAS_SERVER_URL = "https://casdev.company.com:8443/cas";
    private static final String CAS_SERVER_URL_LOGIN = CAS_SERVER_URL + "/login";
    private static final String CAS_SERVER_URL_LOGOUT = CAS_SERVER_URL + "/logout";

    public static void main(String[] args) {
        SpringApplication.run(CasSecuredAppApplicationOne.class, args);
    }

    @Bean
    public ServiceProperties serviceProperties() {
        final ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setService(SERVICE_NAME);
        serviceProperties.setSendRenew(false);
        return serviceProperties;
    }

    @Bean
    @Primary
    public AuthenticationEntryPoint authenticationEntryPoint(ServiceProperties sP) {
        final CasAuthenticationEntryPoint entryPoint = new CasAuthenticationEntryPoint();
        entryPoint.setLoginUrl(CAS_SERVER_URL_LOGIN);
        entryPoint.setServiceProperties(sP);
        return entryPoint;
    }

    @Bean
    public TicketValidator ticketValidator() {
        return new Cas30ServiceTicketValidator(CAS_SERVER_URL);
    }

    @Bean
    public CasAuthenticationProvider casAuthenticationProvider() {
        final CasAuthenticationProvider provider = new CasAuthenticationProvider();
        provider.setServiceProperties(this.serviceProperties());
        provider.setTicketValidator(this.ticketValidator());

        provider.setUserDetailsService(
                s -> new User("casuser", "Mellon", true, true, true, true,
                        AuthorityUtils.createAuthorityList("ROLE_ADMIN")));
        provider.setKey("CAS_PROVIDER_LOCALHOST_9001");

        return provider;
    }


    @Bean
    public SecurityContextLogoutHandler securityContextLogoutHandler() {
        return new SecurityContextLogoutHandler();
    }

    @Bean
    public LogoutFilter logoutFilter() {
        final LogoutFilter logoutFilter = new LogoutFilter(
                CAS_SERVER_URL_LOGOUT, this.securityContextLogoutHandler());
        logoutFilter.setFilterProcessesUrl("/logout/cas");
        return logoutFilter;
    }

    @Bean
    public SingleSignOutFilter singleSignOutFilter() {
        final SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
        singleSignOutFilter.setCasServerUrlPrefix(CAS_SERVER_URL);
        singleSignOutFilter.setIgnoreInitConfiguration(true);
        return singleSignOutFilter;
    }

    @EventListener
    public SingleSignOutHttpSessionListener singleSignOutHttpSessionListener(HttpSessionEvent event) {
        return new SingleSignOutHttpSessionListener();
    }
}
