package samples.cas.client.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import samples.cas.client.TestUtil;
import samples.cas.client.model.Film;
import samples.cas.client.model.ShowDto;
import samples.cas.client.service.FilmCreator;
import samples.cas.client.service.FilmSearcher;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * To test the Controllers, we can use @WebMvc Test. It will auto-configure the Spring MVC infrastructure for our unit tests.
 * In most of the cases, @WebMvcTest will be limited to bootstrap a single controller. It is used along with @MockBean to
 * provide mock implementations for required dependencies.
 *
 * @author XXXX
 * @see https://spring.io/guides/gs/spring-boot/#_add_unit_tests
 */
@RunWith(SpringRunner.class)
//@WebMvcTest(ShowController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShowControllerTest {

    /** The mvc. */
    @Autowired
    private MockMvc mvc;
    /** The service. */
    @MockBean
    private FilmSearcher 			filmSearcher;
    @MockBean
    private FilmCreator 			filmCreator;
    
    // ----------------------------- GET -------------------------------------
	/**
	 * Given existing id when get then return json array with one film.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void givenExistingId_whenGet_thenReturnJsonArrayWithOneFilm() throws Exception {
		// Given
		Long supermanId = 1L;
		
		// When
		Optional<Film> film = Optional.of(Film.builder()			
			.title("Superman")
			.year("1977")
			.imdbScore("10")
			.build());
        when(this.filmSearcher.search(supermanId)).thenReturn(film);

        // then
		mvc.perform(MockMvcRequestBuilders
				.get("/shows/{id}", supermanId)
				.accept(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk())
        		.andExpect(MockMvcResultMatchers.jsonPath("$.year").value("1977"))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.imdbScore").value("10"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Superman"));
	}
	
	/**
	 * Given not existing id when get then return json empty array.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void givenNotExistingId_whenGet_thenReturnJsonEmptyArray() throws Exception {
		// Given
		Long filmId = 2L;	
		
		// When
	    when(this.filmSearcher.search(filmId)).thenReturn( Optional.empty());

        // then
		mvc.perform(MockMvcRequestBuilders
				.get("/shows/{id}", filmId)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$").doesNotExist())
        		.andExpect(status().is4xxClientError() );
	}
	

    @Test
	public void whenGetAllFilms_thenReturnJsonArrayWithAllFilms() throws Exception {
		// Given
		
		// When
		Film filmOne = Film.builder()			
			.title("Superman")
			.year("1977")
			.imdbScore("10")
            .build();
            
        Film filmTwo = Film.builder()		
			.title("Supergirl")
			.year("1977")
			.imdbScore("10")
            .build();
        
        List<Film> films =  new LinkedList<>();      
        films.add(filmOne);
        films.add(filmTwo);
        
        when(this.filmSearcher.searchAll()).thenReturn(films);

        // then
		mvc.perform(MockMvcRequestBuilders
				.get("/shows")
				.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                 .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                 .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", is("Superman")))
                 .andExpect(MockMvcResultMatchers.jsonPath("$[1].title", is("Supergirl")));
	}
    // ----------------------------- POST -------------------------------------
	@Test
	public void givenJSON_withShowObject_whenCreateNewFilm_then_return2xxSuccessfull() throws Exception {
		// Given
		ShowDto showDto = new ShowDto();
		showDto.setTitle("test");
		showDto.setYear("2013");
		showDto.setImdbScore("3.4");				
		byte[] showJson = TestUtil.toJson(showDto);
		
		
		// When - Then
    	this.mvc.perform  ( post("/shows/" )   		
                .contentType(MediaType.APPLICATION_JSON)
                .content(showJson))
    			.andDo(print())
    			.andExpect(status().is2xxSuccessful());
    	verify(filmCreator).createFilm(any(Film.class));
	}
	
}



