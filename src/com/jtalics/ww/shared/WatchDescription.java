package com.jtalics.ww.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class WatchDescription implements Serializable {
  private String id;
  private String displayName;
  
  public WatchDescription() {
    new WatchDescription("0", "");
  }

  public WatchDescription(String id, String displayName) {
    this.id = id;
    this.displayName = displayName;
  }
  
  public String getId() { return id; }
  public void setId(String id) { this.id = id; }
  public String getDisplayName() { return displayName; }
  public void setDisplayName(String displayName) { this.displayName = displayName; } 
}
