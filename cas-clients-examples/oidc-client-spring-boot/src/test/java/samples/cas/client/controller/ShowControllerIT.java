package samples.cas.client.controller;

import static org.junit.Assert.assertEquals;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * The Class ShowControllerIT.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // for restTemplate
public class ShowControllerIT {

	/** The template. */
	@Autowired
	private TestRestTemplate template;

	/** The port. */
	@LocalServerPort
	private int port;
	
	/** The url base. */
	private URL urlBase;
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		this.urlBase = new URL("http://localhost:" + port );
	}

    // ----------------------------- GET -------------------------------------

	/**
	 * Given not existing id when get then return json empty array.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void givenNotExistingId_whenGet_thenReturnJsonEmptyArray() throws Exception {
		
		// Given
		Long id = 14546L;
						
		// When
		String resourceUrl = this.urlBase +  "/shows/" + id;	
		ResponseEntity<String> response = template.getForEntity(resourceUrl, String.class);
		
		// then				
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());		
	}
	
	@Test
	public void givenExistingId_whenGet_thenReturnJsonArrayWithOneFilm() throws Exception {
		// Given
		Long id = 1L;
		
		// When
		String resourceUrl = this.urlBase +  "/shows/" + id;	
		ResponseEntity<String> response = template.getForEntity(resourceUrl, String.class);
		
		// then				
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}	   
	
	//@Test
	// FIXME: Implementar el test
	public void whenGetAllFilms_thenReturnJsonArrayWithAllFilms() throws Exception {
		// When
		//String resourceUrl = this.urlBase +  "/shows";	
		
		//ResponseEntity<ShowDto[]> response = template.getForEntity(resourceUrl, ShowDto[].class);
		
		// then				
		
		//assertEquals(HttpStatus.OK, response.getStatusCode());
	
	}
	
}
