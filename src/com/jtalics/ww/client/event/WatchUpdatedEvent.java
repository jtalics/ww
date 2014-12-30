package com.jtalics.ww.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.jtalics.ww.shared.Watch;

public class WatchUpdatedEvent extends GwtEvent<WatchUpdatedEventHandler>{
  public static Type<WatchUpdatedEventHandler> TYPE = new Type<WatchUpdatedEventHandler>();
  private final Watch updatedWatch;
  
  public WatchUpdatedEvent(Watch updatedContact) {
    this.updatedWatch = updatedContact;
  }
  
  public Watch getUpdatedWatch() { return updatedWatch; }
  

  @Override
  public Type<WatchUpdatedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(WatchUpdatedEventHandler handler) {
    handler.onWatchUpdated(this);
  }
}
