package com.jtalics.ww.client.event;


import com.google.gwt.event.shared.GwtEvent;

public class AddWatchEvent extends GwtEvent<AddWatchEventHandler> {
  public static Type<AddWatchEventHandler> TYPE = new Type<AddWatchEventHandler>();
  
  @Override
  public Type<AddWatchEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(AddWatchEventHandler handler) {
    handler.onAddWatch(this);
  }
}
