package kancelaria;

import java.io.Serializable;

public class objednavka implements Serializable{
private static final long serialVersionUID = 1L;
private String username;
	/**username setter */
	public void setusername(String username){
		this.username=username;
	}
	/**username getter */
	public String getusername() {
		return username;
	}
private int number_of_console;//0-xboxss, 1-xboxsx, 2-ps4, 3-ps5
	/**number of console setter */
	public void setnumber_of_console(int consolenumber){
		number_of_console=consolenumber;
	}
	/**number of console getter */
	public int getnumber_of_console() {
		return number_of_console;
	}
private String adress;
	/**adress setter */
	public void setadress(String adress){
		this.adress=adress;
	}
	/**adress getter */
	public String getadress() {
		return adress;
	}
private int time;
	/**order time setter */
	public void settime(int time){
		this.time=time;
	}
	/**order time getter */
	public int gettime() {
		return time;
	}
	public int status; 
private int number;
	/**number of order setter */
	public void setnumber(int number){
		this.number=number;
	}
	/**number of order getter */
	public int getnumber() {
		return number;
	}
	
}
