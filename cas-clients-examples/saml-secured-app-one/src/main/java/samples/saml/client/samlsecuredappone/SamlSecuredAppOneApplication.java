package samples.saml.client.samlsecuredappone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @see https://developer.okta.com/blog/2017/03/16/spring-boot-saml
 *
 * @author Rodrigo Villamil Pérez
 */
@SpringBootApplication
public class SamlSecuredAppOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(SamlSecuredAppOneApplication.class, args);
	}
}
