package com.jtalics.ww.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.jtalics.ww.client.event.AddWatchEvent;
import com.jtalics.ww.client.event.AddWatchEventHandler;
import com.jtalics.ww.client.event.EditWatchCancelledEvent;
import com.jtalics.ww.client.event.EditWatchCancelledEventHandler;
import com.jtalics.ww.client.event.EditWatchEvent;
import com.jtalics.ww.client.event.EditWatchEventHandler;
import com.jtalics.ww.client.event.WatchUpdatedEvent;
import com.jtalics.ww.client.event.WatchUpdatedEventHandler;
import com.jtalics.ww.client.presenter.EditWatchPresenter;
import com.jtalics.ww.client.presenter.Presenter;
import com.jtalics.ww.client.presenter.WatchesPresenter;

import com.jtalics.ww.client.view.*;

public class AppController implements Presenter, ValueChangeHandler<String> {
  private final HandlerManager eventBus;
  private final WatchesServiceAsync rpcService; 
  private HasWidgets container;
  
  public AppController(WatchesServiceAsync rpcService, HandlerManager eventBus) {
    this.eventBus = eventBus;
    this.rpcService = rpcService;
    bind();
  }
  
  private void bind() {
    History.addValueChangeHandler(this);

    eventBus.addHandler(AddWatchEvent.TYPE,
        new AddWatchEventHandler() {
          @Override
					public void onAddWatch(AddWatchEvent event) {
            doAddNewWatch();
          }
        });  

    eventBus.addHandler(EditWatchEvent.TYPE,
        new EditWatchEventHandler() {
          @Override
					public void onEditWatch(EditWatchEvent event) {
            doEditWatch(event.getId());
          }
        });  

    eventBus.addHandler(EditWatchCancelledEvent.TYPE,
        new EditWatchCancelledEventHandler() {
          @Override
					public void onEditWatchCancelled(EditWatchCancelledEvent event) {
            doEditWatchCancelled();
          }
        });  

    eventBus.addHandler(WatchUpdatedEvent.TYPE,
        new WatchUpdatedEventHandler() {
          @Override
					public void onWatchUpdated(WatchUpdatedEvent event) {
            doWatchUpdated();
          }
        });  
  }
  
  private void doAddNewWatch() {
    History.newItem("add");
  }
  
  private void doEditWatch(String id) {
    History.newItem("edit", false);
    Presenter presenter = new EditWatchPresenter(rpcService, eventBus, new EditWatchView(), id);
    presenter.go(container);
  }
  
  private void doEditWatchCancelled() {
    History.newItem("list");
  }
  
  private void doWatchUpdated() {
    History.newItem("list");
  }
  
  @Override
	public void go(final HasWidgets container) {
    this.container = container;
    
    if ("".equals(History.getToken())) {
      History.newItem("list");
    }
    else {
      History.fireCurrentHistoryState();
    }
  }

  @Override
	public void onValueChange(ValueChangeEvent<String> event) {
    String token = event.getValue();
    
    if (token != null) {
      Presenter presenter = null;

      if (token.equals("list")) {
        presenter = new WatchesPresenter(rpcService, eventBus, new WatchesView());
      }
      else if (token.equals("add")) {
        presenter = new EditWatchPresenter(rpcService, eventBus, new EditWatchView());
      }
      else if (token.equals("edit")) {
        presenter = new EditWatchPresenter(rpcService, eventBus, new EditWatchView());
      }
      
      if (presenter != null) {
        presenter.go(container);
      }
    }
  } 
}
