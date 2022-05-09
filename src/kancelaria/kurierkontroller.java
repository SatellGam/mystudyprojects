package kancelaria;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ResourceBundle;

import javax.print.DocFlavor.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class kurierkontroller {
	 	@FXML
	    private ResourceBundle resources;
	 	
	    @FXML
	    private TextField number_of_order;

	    @FXML
	    private TextField console;

	    @FXML
	    private TextField order_status;

	    @FXML
	    private TextField adress;

	    @FXML
	    private Button completed;

	    @FXML
	    private Button logout;
	    /** controller of the curier window*/
    @FXML
    void initialize() {
    	bike_kurier[] bikekuriers=new bike_kurier[100];
		try {
			FileInputStream fis = new FileInputStream("bikekuriers.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			bikekuriers=(bike_kurier[]) ois.readObject(); 
		}catch (ClassNotFoundException e){
            System.err.println("Класс не сущуствует: "+e);
        }catch (FileNotFoundException e){
            System.err.println("Файл для десериализации не существует: "+e);
        }catch (InvalidClassException e){
            System.err.println("не совпадение версий класов "+e);
        }catch (IOException e){
            System.err.println("Общая ошибка "+e);
        }
		autokurier[] autokuriers=new autokurier[100];
		try {
			FileInputStream fis = new FileInputStream("autokuriers.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			autokuriers=(autokurier[]) ois.readObject(); 
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
		int autodone=0;
		int bikedone=0;//using the 'in' indicator, determine which of the couriers has entered the system
		while(autokuriers[count]!=null) {
			if(autokuriers[count].in==1) {
				autokuriers[count].in=0;
				autodone=1;
				break;
			}
			count++;
		}
		if(autodone!=1) {
			count=0;
			while(bikekuriers[count]!=null) {
				if(bikekuriers[count].in==1) {
					bikedone=1;
					break;
				}
				count++;
			}
			bikekuriers[count].in=0;
		}
		try {
			FileOutputStream   fos = new FileOutputStream("bikekuriers.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(bikekuriers);
            oos.close();
		}catch (IOException e) {
            e.printStackTrace();
		}
		try {
			FileOutputStream   fos = new FileOutputStream("autokuriers.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(autokuriers);
            oos.close();
		}catch (IOException e) {
            e.printStackTrace();
		}
		objednavka[] orders = new objednavka[1000];
    	try {
			FileInputStream fis = new FileInputStream("orders.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			orders=(objednavka[]) ois.readObject(); 
		}catch (ClassNotFoundException e){
            System.err.println("Класс не сущуствует: "+e);
        }catch (FileNotFoundException e){
            System.err.println("Файл для десериализации не существует: "+e);
        }catch (InvalidClassException e){
            System.err.println("не совпадение версий класов "+e);
        }catch (IOException e){
            System.err.println("Общая ошибка "+e);
        }
    	int ordernumber=0;
		if(autodone==1) {
			ordernumber=autokuriers[count].ordernumber;
		}
		else if(bikedone==1) {
			ordernumber=bikekuriers[count].ordernumber;
		}
		final int or=ordernumber;
			number_of_order.setText(""+ordernumber);
			if(orders[ordernumber].getnumber_of_console()==0)
				console.setText("xbox series s");
			else if(orders[ordernumber].getnumber_of_console()==1)
				console.setText("xbox series x");
			else if(orders[ordernumber].getnumber_of_console()==2)
				console.setText("ps4");
			else if(orders[ordernumber].getnumber_of_console()==3)
				console.setText("ps5");
			order_status.setText("in progress");
			adress.setText(orders[ordernumber].getadress());
		/**logout button*/
		logout.setOnAction(event ->{
    		logout.getScene().getWindow().hide();
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(getClass().getResource("fxml1.fxml"));
    		try {
				loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		Parent root = loader.getRoot();
    		Stage stage=new Stage();
    		stage.setScene(new Scene(root));
    		stage.show();
    	});
		/**when you click on the 'completed' button, the order is marked as completed, and the courier as free*/
		completed.setOnAction(event ->{
			bike_kurier[] bikekuriers2=new bike_kurier[100];
			try {
				FileInputStream fis = new FileInputStream("bikekuriers.ser");
				ObjectInputStream ois = new ObjectInputStream(fis);
				bikekuriers2=(bike_kurier[]) ois.readObject(); 
			}catch (ClassNotFoundException e){
	            System.err.println("Класс не сущуствует: "+e);
	        }catch (FileNotFoundException e){
	            System.err.println("Файл для десериализации не существует: "+e);
	        }catch (InvalidClassException e){
	            System.err.println("не совпадение версий класов "+e);
	        }catch (IOException e){
	            System.err.println("Общая ошибка "+e);
	        }
			autokurier[] autokuriers2=new autokurier[100];
			try {
				FileInputStream fis = new FileInputStream("autokuriers.ser");
				ObjectInputStream ois = new ObjectInputStream(fis);
				autokuriers2=(autokurier[]) ois.readObject(); 
			}catch (ClassNotFoundException e){
	            System.err.println("Класс не сущуствует: "+e);
	        }catch (FileNotFoundException e){
	            System.err.println("Файл для десериализации не существует: "+e);
	        }catch (InvalidClassException e){
	            System.err.println("не совпадение версий класов "+e);
	        }catch (IOException e){
	            System.err.println("Общая ошибка "+e);
	        }
			int countcompl=0;
			while(autokuriers2[countcompl]!=null) {
				if(autokuriers2[countcompl].ordernumber==or) {
					autokuriers2[countcompl].is_free=true;
					try {
						FileOutputStream   fos = new FileOutputStream("autokuriers.ser");
			            ObjectOutputStream oos = new ObjectOutputStream(fos);
			            oos.writeObject(autokuriers2);
			            oos.close();
					}catch (IOException e) {
			            e.printStackTrace();
					}
					logout.getScene().getWindow().hide();
		    		FXMLLoader loader = new FXMLLoader();
		    		loader.setLocation(getClass().getResource("fxml1.fxml"));
		    		try {
						loader.load();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		Parent root = loader.getRoot();
		    		Stage stage=new Stage();
		    		stage.setScene(new Scene(root));
		    		stage.show();
				}
				countcompl++;
			}
			countcompl=0;
			while(bikekuriers2[countcompl]!=null) {
				if(bikekuriers2[countcompl].ordernumber==or) {
					bikekuriers2[countcompl].is_free=true;
					try {
						FileOutputStream   fos = new FileOutputStream("bikekuriers.ser");
			            ObjectOutputStream oos = new ObjectOutputStream(fos);
			            oos.writeObject(bikekuriers2);
			            oos.close();
					}catch (IOException e) {
			            e.printStackTrace();
					}
					logout.getScene().getWindow().hide();
		    		FXMLLoader loader = new FXMLLoader();
		    		loader.setLocation(getClass().getResource("fxml1.fxml"));
		    		try {
						loader.load();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		Parent root = loader.getRoot();
		    		Stage stage=new Stage();
		    		stage.setScene(new Scene(root));
		    		stage.show();
				}
				countcompl++;
			}
    	});
    }
}
