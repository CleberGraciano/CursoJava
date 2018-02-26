package br.pro.delfino.drogaria.util;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

//http://localhost:8080/Drogaria/rest
@ApplicationPath("rest")
public class DrogariaResouceConfig extends ResourceConfig {
	

	
	public DrogariaResouceConfig () {
	
		
		packages("br.pro.delfino.drogaria.service");
	
	};
	
	

}
