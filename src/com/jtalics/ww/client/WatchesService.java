package com.jtalics.ww.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.jtalics.ww.shared.City;
import com.jtalics.ww.shared.Watch;
import com.jtalics.ww.shared.WatchDescription;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface WatchesService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;
	List<City> getWatchableCities() throws IllegalArgumentException;
	Watch updateWatch(Watch watch) throws Exception;
	Watch getWatch(String id);
	List<WatchDescription> getWatchDetails();
  List<WatchDescription> deleteWatch(List<String> ids);
}
