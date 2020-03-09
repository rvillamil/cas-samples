package samples.cas.client;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import lombok.extern.log4j.Log4j2;
import samples.cas.client.filters.HttpFilterConstants;
import samples.cas.client.filters.UUIDRequestFilter;

/**
 * The Class ApplicationRunner.
 */
@SpringBootApplication
@EnableAutoConfiguration
@Log4j2
public class ApplicationRunner {

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRunner.class, args);

        StringBuilder message = new StringBuilder("Project 'billboard' running with events!!");

        try {
            InetAddress ip = InetAddress.getLocalHost();
            message.append(" from host: " + ip);
        } catch (UnknownHostException e) {
            log.error(e);
        }
        log.info(message.toString());
    }

    // --------------------------- Configuration -------------------------------    
    @Bean
    public FilterRegistrationBean<UUIDRequestFilter> servletRegistrationBean() {

        final FilterRegistrationBean<UUIDRequestFilter> registrationBean = new FilterRegistrationBean<>();
        final UUIDRequestFilter uuidRequestFilter = new UUIDRequestFilter(HttpFilterConstants.DEFAULT_UUID_HEADER_NAME);

        registrationBean.setFilter(uuidRequestFilter);
        registrationBean.setOrder(Integer.MAX_VALUE);

        return registrationBean;
    }    
}
