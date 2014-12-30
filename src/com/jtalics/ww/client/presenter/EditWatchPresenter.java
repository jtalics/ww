package com.jtalics.ww.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback; 
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.Window;
import com.jtalics.ww.client.WatchesServiceAsync;
import com.jtalics.ww.client.event.EditWatchCancelledEvent;
import com.jtalics.ww.client.event.WatchUpdatedEvent;
import com.jtalics.ww.shared.Watch;

public class EditWatchPresenter implements Presenter{  
  public interface Display {
    HasClickHandlers getSaveButton();
    HasClickHandlers getCancelButton();
    HasValue<String> getFirstName();
    HasValue<String> getLastName();
    HasValue<String> getEmailAddress();
    Widget asWidget();
  }
  
  private Watch watch;
  private final WatchesServiceAsync rpcService;
  private final HandlerManager eventBus;
  private final Display display;
  
  public EditWatchPresenter(WatchesServiceAsync rpcService, HandlerManager eventBus, Display display) {
    this.rpcService = rpcService;
    this.eventBus = eventBus;
    this.watch = new Watch();
    this.display = display;
    bind();
  }
  
  public EditWatchPresenter(WatchesServiceAsync rpcService, HandlerManager eventBus, Display display, String id) {
    this.rpcService = rpcService;
    this.eventBus = eventBus;
    this.display = display;
    bind();
    
    rpcService.getWatch(id, new AsyncCallback<Watch>() {
      @Override
			public void onSuccess(Watch result) {
        watch = result;
        EditWatchPresenter.this.display.getFirstName().setValue(watch.getFirstName());
        EditWatchPresenter.this.display.getLastName().setValue(watch.getLastName());
        EditWatchPresenter.this.display.getEmailAddress().setValue(watch.getEmailAddress());
      }
      
      @Override
			public void onFailure(Throwable caught) {
        Window.alert("Error retrieving Watch");
      }
    });
    
  }
  
  public void bind() {
    this.display.getSaveButton().addClickHandler(new ClickHandler() {   
      @Override
			public void onClick(ClickEvent event) {
        doSave();
      }
    });

    this.display.getCancelButton().addClickHandler(new ClickHandler() {   
      @Override
			public void onClick(ClickEvent event) {
        eventBus.fireEvent(new EditWatchCancelledEvent());
      }
    });
  }

  @Override
	public void go(final HasWidgets container) {
    container.clear();
    container.add(display.asWidget());
  }

  private void doSave() {
    watch.setFirstName(display.getFirstName().getValue());
    watch.setLastName(display.getLastName().getValue());
    watch.setEmailAddress(display.getEmailAddress().getValue());
    
    rpcService.updateWatch(watch, new AsyncCallback<Watch>() {
        @Override
				public void onSuccess(Watch result) {
          eventBus.fireEvent(new WatchUpdatedEvent(result));
        }
        @Override
				public void onFailure(Throwable caught) {
          Window.alert("Error updating Watch");
        }
    });
  }
  
}
