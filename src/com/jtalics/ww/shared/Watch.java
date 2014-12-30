package com.jtalics.ww.shared;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Watch implements Serializable {

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmailAddress() {
		return email;
	}

	public void setEmailAddress(String email) {
		this.email = email;
	}

	public Integer getLoInteger() {
		return loInteger;
	}

	public void setLoInteger(Integer loInteger) {
		this.loInteger = loInteger;
	}

	public Integer getHiInteger() {
		return hiInteger;
	}

	public void setHiInteger(Integer hiInteger) {
		this.hiInteger = hiInteger;
	}

	public Float getLoFloat() {
		return loFloat;
	}

	public void setLoFloat(Float loFloat) {
		this.loFloat = loFloat;
	}

	public Float getHiFloat() {
		return hiFloat;
	}

	public void setHiFloat(Float hiFloat) {
		this.hiFloat = hiFloat;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

		@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    long id;
    @Basic
    String email;
    @Basic
    Integer loInteger, hiInteger;
    @Basic
    Float loFloat,hiFloat;
    @Basic
    String symbol;
    
  	public WatchDescription getLightWeightContact() {
  	  return new WatchDescription("id", "watch description");
  	}

		public String getFirstName() {
			return "Firsty";
		}

		public String getLastName() {
			// TODO Auto-generated method stub
			return "Lasty";
		}

		public void setFirstName(String value) {
			// TODO Auto-generated method stub
			
		}

		public void setLastName(String value) {
			// TODO Auto-generated method stub
			
		}

}
