package kancelaria;

import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerAut {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField LoginField;

    @FXML
    private PasswordField PassField;

    @FXML
    private Button SignInButton;

    @FXML
    private Button SignUpButton;

    /** authorization controller*/
    @FXML
    void initialize() {
    	/**sign in button tries to find a user with the specified username and password, for admin window:username and password is "admin"
    	 * for curier window username is a curier name, password is the curier phone number
    	 */
    	SignInButton.setOnAction(event ->{
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
    		while(autokuriers[count]!=null) {
    			//if the specified username and password match the name and number of the courier, enter the courier window
    			if(autokuriers[count].getname().equals(LoginField.getText().trim())&& autokuriers[count].getphone().equals(PassField.getText().trim())&&autokuriers[count].is_free==false) {
    				autokuriers[count].in=1;
    				try {
    		    		FileOutputStream   fos = new FileOutputStream("autokuriers.ser");
    		            ObjectOutputStream oos = new ObjectOutputStream(fos);
    		            oos.writeObject(autokuriers);
    		            oos.close();
    		    		}catch (IOException e) {
    		                e.printStackTrace();
    		            }
    		    		SignInButton.getScene().getWindow().hide();
    		    		FXMLLoader loader = new FXMLLoader();
    		    		loader.setLocation(getClass().getResource("kurier.fxml"));
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
    		    		break;
    			}
    			count++;
    		}
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
    		count=0;
    		//if the specified username and password match the name and number of the courier, enter the courier window
    		while(bikekuriers[count]!=null) {
    			if(bikekuriers[count].getname().equals(LoginField.getText().trim())&& bikekuriers[count].getphone().equals(PassField.getText().trim())&&bikekuriers[count].is_free==false) {
    				bikekuriers[count].in=1;
    				try {
    		    		FileOutputStream   fos = new FileOutputStream("bikekuriers.ser");
    		            ObjectOutputStream oos = new ObjectOutputStream(fos);
    		            oos.writeObject(bikekuriers);
    		            oos.close();
    		    		}catch (IOException e) {
    		                e.printStackTrace();
    		            }
    		    		SignInButton.getScene().getWindow().hide();
    		    		FXMLLoader loader = new FXMLLoader();
    		    		loader.setLocation(getClass().getResource("kurier.fxml"));
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
    		    		break;
    			}
    			count++;
    		}
    		String admin="admin";
    		//if the specified username and password is "admin" enter the admin window
    		if(LoginField.getText().trim().equals(admin) && PassField.getText().trim().equals(admin)) {
			SignInButton.getScene().getWindow().hide();
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(getClass().getResource("admin.fxml"));
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
    		klient[] Klient=new klient[1000];
    		try {
    			FileInputStream fis = new FileInputStream("users.ser");
    			ObjectInputStream ois = new ObjectInputStream(fis);
    			Klient=(klient[]) ois.readObject(); 
    		}catch (ClassNotFoundException e){
                System.err.println("Класс не сущуствует: "+e);
            }catch (FileNotFoundException e){
                System.err.println("Файл для десериализации не существует: "+e);
            }catch (InvalidClassException e){
                System.err.println("не совпадение версий класов "+e);
            }catch (IOException e){
                System.err.println("Общая ошибка "+e);
            }
    		count=0;
    		while(Klient[count]!=null) {
    			//if the specified username and password match the name and password of user, enter the user window
    			if(Klient[count].getLogin().equals(LoginField.getText().trim())&& Klient[count].getPas().equals(PassField.getText().trim())) {
    				Klient[count].in=1;
    				try {
    		    		FileOutputStream   fos = new FileOutputStream("users.ser");
    		            ObjectOutputStream oos = new ObjectOutputStream(fos);
    		            oos.writeObject(Klient);
    		            oos.close();
    		    		}catch (IOException e) {
    		                e.printStackTrace();
    		            }
    		    		SignInButton.getScene().getWindow().hide();
    		    		FXMLLoader loader = new FXMLLoader();
    		    		loader.setLocation(getClass().getResource("zakaz.fxml"));
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
    		    		break;
    			}
    			count++;
    		}
    	});
    	/**button that opens the registration window*/
    	SignUpButton.setOnAction(event ->{
    		SignUpButton.getScene().getWindow().hide();
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(getClass().getResource("registerfield.fxml"));
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
    }
}