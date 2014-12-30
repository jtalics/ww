package com.jtalics.ww.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jtalics.ww.shared.City;
import com.jtalics.ww.shared.Watch;
import com.jtalics.ww.shared.WatchDescription;

/**
 * The async counterpart of <code>WatchesService</code>.
 */
public interface WatchesServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;

	void getWatchableCities(AsyncCallback<List<City>> callback);

	void getWatch(String id, AsyncCallback<Watch> asyncCallback);

	void updateWatch(Watch watch, AsyncCallback<Watch> asyncCallback);

	void getWatchDetails(AsyncCallback<List<WatchDescription>> asyncCallback);

	void deleteWatch(List<String> ids, AsyncCallback<List<WatchDescription>> asyncCallback);
}
