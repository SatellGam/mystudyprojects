package kancelaria;

import java.io.Serializable;

/**class auto for auto curiers*/
public class auto implements Serializable{
	private String number;//number model and color of our auto
	private String farba;
	private String model;
	/** constructor, create a new auto object*/
	public auto(String number, String farba, String model) {
		this.number=number;
		this.farba=farba;
		this.model=model;
	}
	public String getnumber() {
		return number;
	}
	public void setnumber(String number) {
		this.number=number;
	}
	public String getmodel() {
		return model;
	}
	public void setmodel(String model) {
		this.model=model;
	}
	public String getfarba() {
		return farba;
	}
	public void setfarba(String farba) {
		this.farba=farba;
	}
}
