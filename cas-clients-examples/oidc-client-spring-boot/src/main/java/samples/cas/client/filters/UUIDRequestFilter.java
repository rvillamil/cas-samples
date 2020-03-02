package samples.cas.client.filters;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Set UUID on http response header and set UUID on log trace through 
 * the Mapped Diagnostic Context (MDC)
 * 
 * if the client making the request has not established a header with the UUID 
 * we generate it and set on http header response
 *  
 * @see https://logging.apache.org/log4j/log4j-2.2/manual/thread-context.html
 */
public class UUIDRequestFilter extends OncePerRequestFilter {

	/** The token name */
	private String tokenName;

	/**
	 * Instantiates a new UUIDRequestFilter filter.
	 *
	 * @param tokenName the name of request header and MDC token
	 */	
	public UUIDRequestFilter(String tokenName) {
		this.tokenName = tokenName;
	}

	@Override
	protected void doFilterInternal( HttpServletRequest request, 
									 HttpServletResponse response, 
									 FilterChain filterChain )
	
	        throws ServletException, IOException {

		final String uuid;
		try {
			if (!StringUtils.isEmpty(request.getHeader(tokenName))) {
				uuid = request.getHeader(tokenName);
			} else {
				uuid = UUID.randomUUID().toString();
			}

			MDC.put(tokenName, uuid);
			response.addHeader(tokenName, uuid);
			filterChain.doFilter(request, response);
		} finally {
			// Nota: Es importante eliminar los atributos después, ya que 
			// los servidores de aplicaciones reutilizan  hilos
			// podrían dar declaraciones de registro engañosas.
			MDC.remove(tokenName);
		}
	}
}
