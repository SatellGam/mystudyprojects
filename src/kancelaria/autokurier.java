package kancelaria;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
/**auto courier class for fast delivery, extends abstract class curier*/
public class autokurier extends kurier implements Serializable, Info {
	auto myauto;//agregation
	public void setauto(auto newauto) {
		myauto=newauto;
	}
	public String getinfo() {//interface override method
		return "your courier is "+getname()+" he will arrive in a " + myauto.getfarba()+ " " + myauto.getmodel() +" " + myauto.getnumber() +", ";
	}
	/**the method looks for a free auto courier and returns 1 if found*/
	public int getorder(int ordernum) {//trying to find a free curier to order
		autokurier[] kuriers=new autokurier[100];
		try {
			FileInputStream fis = new FileInputStream("autokuriers.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			kuriers=(autokurier[]) ois.readObject(); 
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
			System.out.println(kuriers[count].getname());
			if(kuriers[count].is_free==true) {
				kuriers[count].is_free=false;
				kuriers[count].ordernumber=ordernum;
				done=1;
				break;
			}
			if(kuriers[count+1]==null && done==0) {
				break;
			}
			count++;
		}
		try {
			FileOutputStream   fos = new FileOutputStream("autokuriers.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(kuriers);
            oos.close();
		}catch (IOException e) {
            e.printStackTrace();
		}
		return done;
	}
}
