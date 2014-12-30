package com.jtalics.ww.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditWatchCancelledEvent extends GwtEvent<EditWatchCancelledEventHandler>{
  public static Type<EditWatchCancelledEventHandler> TYPE = new Type<EditWatchCancelledEventHandler>();
  
  @Override
  public Type<EditWatchCancelledEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditWatchCancelledEventHandler handler) {
    handler.onEditWatchCancelled(this);
  }
}
