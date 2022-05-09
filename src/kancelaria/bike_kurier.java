package kancelaria;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
/**bike courier class for regular delivery, extends abstract class curier*/

public class bike_kurier extends kurier implements Serializable, Info{
	public String getinfo() {//interface override method
		return "Your order will be delivered by our best courier:"+getname();
	}
	/**the method looks for a free bike courier and returns 1 if found*/
	public int getorder(int ordernum) {
		bike_kurier[] kuriers=new bike_kurier[100];
		try {
			FileInputStream fis = new FileInputStream("bikekuriers.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			kuriers=(bike_kurier[]) ois.readObject(); 
		}catch (ClassNotFoundException e){
            System.err.println("Класс не сущуствует: "+e);
        }catch (FileNotFoundException e){
            System.err.println("Файл для десериализации не существует: "+e);
        }catch (InvalidClassException e){
            System.err.println("не совпадение версий класов "+e);
        }catch (IOException e){
            System.err.println("Общая ошибка "+e);
        }
		int count=0;
		int done=0;
		while(kuriers[count]!=null){
			if(kuriers[count].is_free==true) {
				kuriers[count].is_free=false;
				kuriers[count].ordernumber=ordernum;
				done++;
				break;
			}
			if(kuriers[count+1]==null && done==0) {
				break;
			}
			count++;
		}
		try {
			FileOutputStream   fos = new FileOutputStream("bikekuriers.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(kuriers);
            oos.close();
		}catch (IOException e) {
            e.printStackTrace();
		}
		return done;
	}
}
