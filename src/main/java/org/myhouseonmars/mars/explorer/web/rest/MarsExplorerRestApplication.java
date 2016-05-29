/**
 * 
 */
package org.myhouseonmars.mars.explorer.web.rest;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Registra os componentes de uma aplicacao JAX-RS. Necessario para
 * integrar o JAX-RS com o Spring.
 * 
 * @author Marco Pessoa
 *
 */
@ApplicationPath("/api")
public class MarsExplorerRestApplication extends ResourceConfig {

	public MarsExplorerRestApplication() {
		register(MissionRESTService.class);
		register(MultiPartFeature.class);

	}
	
}
