package kancelaria;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class registercontrol {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button register;

    @FXML
    private TextField namefield;

    @FXML
    private TextField loginfield;

    @FXML
    private TextField phonefield;

    @FXML
    private TextField passwordfield;
    
    @FXML
    private Button go_back;
    /**controller of the registration field*/
    @FXML
    void initialize() {
    	register.setOnAction(event ->{	
    		int g=0;
    		while(g==0) {
    		int count=0;
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
    		while(Klient[count]!=null) {
    			if(loginfield.getText().equals(Klient[count].getLogin()))
    				g=1;
    			count++;
    		}
    		if(g==1)
    			break;
    		try {
    		Klient[count]=new klient();
    		FileOutputStream   fos = new FileOutputStream("users.ser");
    		Klient[count].setPhone(phonefield.getText());
    		Klient[count].setname(namefield.getText());
    		Klient[count].setPas(passwordfield.getText());
    		Klient[count].setLogin(loginfield.getText());
    		Klient[count].in=1;
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(Klient);
            oos.close();
    		}catch (IOException e) {
                e.printStackTrace();
            }
    		break;
    		}
    		register.getScene().getWindow().hide();
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
    	});
    	go_back.setOnAction(event ->{
    		go_back.getScene().getWindow().hide();
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
    }
}
