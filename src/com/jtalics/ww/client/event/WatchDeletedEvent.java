package com.jtalics.ww.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class WatchDeletedEvent extends GwtEvent<WatchDeletedEventHandler>{
  public static Type<WatchDeletedEventHandler> TYPE = new Type<WatchDeletedEventHandler>();
  
  @Override
  public Type<WatchDeletedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(WatchDeletedEventHandler handler) {
    handler.onWatchDeleted(this);
  }
}
