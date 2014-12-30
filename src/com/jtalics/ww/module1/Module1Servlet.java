package com.jtalics.ww.module1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.modules.ModulesService;
import com.google.appengine.api.modules.ModulesServiceFactory;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Module1Servlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<HTML><HEAD><TITLE>Got it</TITLE></HEAD><BODY>Got it</BODY></HTML>");
		System.out.println("got it");
		response.setStatus(200);
	}

	private void foobar() {
		ModulesService modulesApi = ModulesServiceFactory.getModulesService();

		// Get the module name handling the current request.
		String currentModuleName = modulesApi.getCurrentModule();
		// Get the instance handling the current request.
		String currentInstanceId = modulesApi.getCurrentInstanceId();
		try {
			URL url = new URL("http://" + modulesApi.getVersionHostname("my-backend-module", "v1") + "/fetch-stats");
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;

			while ((line = reader.readLine()) != null) {
				// Do something...
			}
			reader.close();

		}
		catch (MalformedURLException e) {
			// ...
		}
		catch (IOException e) {
			// ...
		}

	}
}
