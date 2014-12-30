package com.jtalics.ww.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditWatchEvent extends GwtEvent<EditWatchEventHandler>{
  public static Type<EditWatchEventHandler> TYPE = new Type<EditWatchEventHandler>();
  private final String id;
  
  public EditWatchEvent(String id) {
    this.id = id;
  }
  
  public String getId() { return id; }
  
  @Override
  public Type<EditWatchEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditWatchEventHandler handler) {
    handler.onEditWatch(this);
  }
}
