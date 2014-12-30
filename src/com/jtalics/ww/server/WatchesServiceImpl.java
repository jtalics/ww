package com.jtalics.ww.server;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.jtalics.ww.client.WatchesService;
import com.jtalics.ww.shared.City;
import com.jtalics.ww.shared.FieldVerifier;
import com.jtalics.ww.shared.Watch;
import com.jtalics.ww.shared.WatchDescription;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class WatchesServiceImpl extends RemoteServiceServlet implements
		WatchesService {


	private final List<WatchDescription> watchDetails = new ArrayList<WatchDescription>();

	public WatchesServiceImpl() {
		watchDetails.add(new WatchDescription("11","111111"));
	}
	@Override
	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);
		return "Hello, " + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

	@Override
	public List<City> getWatchableCities() throws IllegalArgumentException {
		List<City> cities = new ArrayList<City>(NDFD_REST.cityToLatlon.keySet());
		Log.info("Getting cities.");
		return cities;
	}

	@Override
	public Watch updateWatch(Watch watch) throws Exception {
		EntityManager em=null;
		try {
			em = EMF.get().createEntityManager();
			em.persist(watch);
		}
		finally {
			if (em != null) {
				em.close();
			}
		}
		return watch;
    // Logger.getLogger("WWLOGGER").log(Level.ALL, "PUT WATCH");
		//throw new Exception("Implement me");
	}

	@Override
	public Watch getWatch(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WatchDescription> getWatchDetails() {
		return watchDetails ;
	}

	@Override
	public List<WatchDescription> deleteWatch(List<String> ids) {
		return watchDetails;
	}
}
