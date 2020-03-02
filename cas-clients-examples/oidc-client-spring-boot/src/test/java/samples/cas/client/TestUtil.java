package samples.cas.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Spring boot Application for spring boot testing support.
 */
public class TestUtil {  
   
    /**
     * To json.
     *
     * @param obj the obj
     * @return the byte[]
     * @throws JsonProcessingException the json processing exception
     */
    public static byte[] toJson(Object obj) throws JsonProcessingException {
    	final ObjectMapper map = new ObjectMapper();
    	return map.writeValueAsString(obj).getBytes();
    }
}


