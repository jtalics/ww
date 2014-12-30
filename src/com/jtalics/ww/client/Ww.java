package com.jtalics.ww.client;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Ww implements EntryPoint {
	/**
	 * This field gets compiled out when <code>log_level=OFF</code>, or any
	 * <code>log_level</code> higher than <code>DEBUG</code>.
	 */
	private long startTimeMillis;

	/**
	 * Note, we defer all application initialization code to
	 * {@link #onModuleLoad2()} so that the UncaughtExceptionHandler can catch
	 * any unexpected exceptions.
	 */
	@Override
	public void onModuleLoad() {
		//http://code.google.com/p/gwt-log/wiki/GettingStarted#Working_examples
		/*
		 * Install an UncaughtExceptionHandler which will produce
		 * <code>FATAL</code> log messages
		 */
		Log.setUncaughtExceptionHandler();
		Log.setCurrentLogLevel(Log.LOG_LEVEL_DEBUG);

		// use deferred command to catch initialization exceptions in
		// onModuleLoad2
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				onModuleLoad2();
			}
		});
	}

	/**
	 * Deferred initialization method, used by {@link #onModuleLoad()}.
	 */
	private void onModuleLoad2() {
		/*
		 * Use a <code>if (Log.isDebugEnabled()) {...}</code> guard to ensure
		 * that <code>System.currentTimeMillis()</code> is compiled out when
		 * <code>log_level=OFF</code>, or any <code>log_level</code> higher than
		 * <code>DEBUG</code>.
		 */
		if (Log.isDebugEnabled()) {
			startTimeMillis = System.currentTimeMillis();
		}

		/*
		 * No guards necessary. Code will be compiled out when
		 * <code>log_level=OFF</code>
		 */
		//Log.debug("This is a 'DEBUG' test message");
		//Log.info("This is a 'INFO' test message");
		//Log.warn("This is a 'WARN' test message");
		//Log.error("This is a 'ERROR' test message");
		//Log.fatal("This is a 'FATAL' test message");

		// Log.fatal("This is what an exception might look like",new RuntimeException("2 + 2 = 5"));

		// Log.debug("foo.bar.baz", "Using logging categories", (Exception) null);
    WatchesServiceAsync rpcService = GWT.create(WatchesService.class);
    HandlerManager eventBus = new HandlerManager(null);
    AppController appViewer = new AppController(rpcService, eventBus);
    appViewer.go(RootPanel.get());

		/*
		 * Again, we need a guard here, otherwise <code>log_level=OFF</code>
		 * would still produce the following useless JavaScript: <pre> var
		 * durationSeconds, endTimeMillis; endTimeMillis =
		 * currentTimeMillis_0(); durationSeconds = (endTimeMillis -
		 * this$static.startTimeMillis) / 1000.0; </pre>
		 */
		if (Log.isDebugEnabled()) {
			long endTimeMillis = System.currentTimeMillis();
			float durationSeconds = (endTimeMillis - startTimeMillis) / 1000F;
			Log.debug("Duration: " + durationSeconds + " seconds");
		}
	}

	/**
	 * This is the entry point method.
	 * @wbp.parser.entryPoint
	 */
	public void onModuleLoad3() {
		RootPanel.get().add(new WatchCreate());
	}
}
