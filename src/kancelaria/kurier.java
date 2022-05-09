package kancelaria;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.Serializable;
	/** abstract class curier*/
	abstract public class kurier implements Serializable, Info {
		private String name;
		int in=0;//indicator that the user is logged in
		private String phone;
		public int ordernumber;//number of order lol
		public boolean is_free=true;
		/**name getter*/
		public String getname() {
			return name;
		}
		/**name setter*/
		public void setname(String name) {
			this.name=name;
		}
		/**phone getter*/
		public String getphone() {
			return phone;
		}
		/**phone setter*/
		public void setphone(String phone) {
			this.phone=phone;
		}
		/**order number setter*/
		public void setordernum(int ordernum) {
			this.ordernumber=ordernum;
		}
		/**order number getter*/
		public int getordernum() {
			return ordernumber;
		}
	}
