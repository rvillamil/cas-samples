package samples.saml.client.samlsecuredappone;

import static org.springframework.security.extensions.saml2.config.SAMLConfigurer.saml;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Value("${security.saml2.metadata-url}")
	String metadataUrl;

	@Value("${server.ssl.key-alias}")
	String keyAlias;

	@Value("${server.ssl.key-store-password}")
	String password;

	@Value("${server.port}")
	String port;

	@Value("${server.ssl.key-store}")
	String keyStoreFilePath;

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/saml*").permitAll()
		.anyRequest().authenticated()
		.and()
		.apply(saml())
		.serviceProvider()
		.keyStore()
		.storeFilePath(keyStoreFilePath)
		.password(password)
		.keyname(keyAlias)
		.keyPassword(password)
		.and()
		.protocol("https")
		.hostname(String.format("%s:%s", "samlclient2.company.com", port))
		.basePath("/")
		.and()
		.identityProvider()
		.metadataFilePath(metadataUrl);
	}
}
