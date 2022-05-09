package kancelaria;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sklad.game;
import sklad.ps4;
import sklad.ps5;
import sklad.xboxss;
import sklad.xboxsx;

public class orderlistcontrol {

	@FXML
    private TextField number_of_order;

    @FXML
    private TextField console;

    @FXML
    private TextField order_status;

    @FXML
    private TextField time;

    @FXML
    private TextField cislops5;

    @FXML
    private TextField cislops4;

    @FXML
    private TextField cisloxboxsx;

    @FXML
    private TextField cisloxboxss;

    @FXML
    private Button edit;

    @FXML
    private ChoiceBox<String> order_select;

    @FXML
    private Button new_game;

    @FXML
    private TextField gametextfield;

    @FXML
    private Button new_autokurier;

    @FXML
    private Button new_autokurier1;

    @FXML
    private TextField autokuriername;

    @FXML
    private TextField bikekuriername;

    @FXML
    private TextField autokurierphone;

    @FXML
    private TextField bikekurierphone;

    @FXML
    private Button getinfo;

    @FXML
    private TextField automodel;

    @FXML
    private TextField autonumber;

    @FXML
    private TextField autocolor;

    @FXML
    private Button logout;
    /**controller of admin window*/
    @FXML
    void initialize() {
    	xboxss seriess=new xboxss();
		try {
			FileInputStream fis = new FileInputStream("xboxss.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			seriess=(xboxss) ois.readObject(); 
			ois.close();
		}catch (ClassNotFoundException e){
	        System.err.println("Класс не сущуствует: "+e);
	    }catch (FileNotFoundException e){
	        System.err.println("Файл для десериализации не существует: "+e);
	    }catch (InvalidClassException e){
	        System.err.println("не совпадение версий класов "+e);
	    }catch (IOException e){
	        System.err.println("Общая ошибка "+e);
	    }
		xboxsx seriesx=new xboxsx();
		try {
			FileInputStream fis = new FileInputStream("xboxsx.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			seriesx=(xboxsx) ois.readObject(); 
		}catch (ClassNotFoundException e){
	        System.err.println("Класс не сущуствует: "+e);
	    }catch (FileNotFoundException e){
	        System.err.println("Файл для десериализации не существует: "+e);
	    }catch (InvalidClassException e){
	        System.err.println("не совпадение версий класов "+e);
	    }catch (IOException e){
	        System.err.println("Общая ошибка "+e);
	    }
		ps4 ps4c=new ps4();
		try {
			FileInputStream fis = new FileInputStream("ps4.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			ps4c=(ps4) ois.readObject(); 
			ois.close();
		}catch (ClassNotFoundException e){
	        System.err.println("Класс не сущуствует: "+e);
	    }catch (FileNotFoundException e){
	        System.err.println("Файл для десериализации не существует: "+e);
	    }catch (InvalidClassException e){
	        System.err.println("не совпадение версий класов "+e);
	    }catch (IOException e){
	        System.err.println("Общая ошибка "+e);
	    }
		ps5 ps5c=new ps5();
		try {
			FileInputStream fis = new FileInputStream("ps5.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			ps5c=(ps5) ois.readObject(); 
			ois.close();
		}catch (ClassNotFoundException e){
	        System.err.println("Класс не сущуствует: "+e);
	    }catch (FileNotFoundException e){
	        System.err.println("Файл для десериализации не существует: "+e);
	    }catch (InvalidClassException e){
	        System.err.println("не совпадение версий класов "+e);
	    }catch (IOException e){
	        System.err.println("Общая ошибка "+e);
	    }
		cislops5.setText(ps5c.getkol()+"");//gets information about the number of consoles available in warehouse
		cislops4.setText(ps4c.getkol()+"");
		cisloxboxss.setText(seriess.getkol()+"");
		cisloxboxsx.setText(seriesx.getkol()+"");
    	ObservableList<String> orderschoise = FXCollections.observableArrayList();
    	objednavka[] orders = new objednavka[1000];
    	try {
			FileInputStream fis = new FileInputStream("orders.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			orders=(objednavka[]) ois.readObject(); 
			ois.close();
		}catch (ClassNotFoundException e){
            System.err.println("Класс не сущуствует: "+e);
        }catch (FileNotFoundException e){
            System.err.println("Файл для десериализации не существует: "+e);
        }catch (InvalidClassException e){
            System.err.println("не совпадение версий класов "+e);
        }catch (IOException e){
            System.err.println("Общая ошибка "+e);
        }
    	int countorders=0;
    	while(orders[countorders]!=null) {
    	orderschoise.add(""+orders[countorders].getnumber());
    	countorders ++;
    	}
    	order_select.setItems(orderschoise);
    	/** button to get info about selected order*/
    	getinfo.setOnAction(event->{
    		objednavka[] orders2 = new objednavka[1000];
        	try {
    			FileInputStream fis = new FileInputStream("orders.ser");
    			ObjectInputStream ois = new ObjectInputStream(fis);
    			orders2=(objednavka[]) ois.readObject(); 
    			ois.close();
    		}catch (ClassNotFoundException e){
                System.err.println("Класс не сущуствует: "+e);
            }catch (FileNotFoundException e){
                System.err.println("Файл для десериализации не существует: "+e);
            }catch (InvalidClassException e){
                System.err.println("не совпадение версий класов "+e);
            }catch (IOException e){
                System.err.println("Общая ошибка "+e);
            }
    		int info=0;
    		while((orders2[info].getnumber()+"").equals(order_select.getValue())==false) {
        		info++;
        	}
        	number_of_order.setText(orders2[info].getnumber()+"");
        	if(orders2[info].getnumber_of_console()==0) {
        		console.setText("xbox series s");
        	}
        	if(orders2[info].getnumber_of_console()==1) {
            	console.setText("xbox series x");
        	}
        	if(orders2[info].getnumber_of_console()==2) {
            	console.setText("ps4");
        	}
        	if(orders2[info].getnumber_of_console()==3) {
            	console.setText("ps5");
        	}
        	time.setText(orders2[info].gettime()+" days");
    	});
    	/**button to create a new autocurier, and auto*/
    	new_autokurier.setOnAction(event->{
    		auto newauto = new auto(autonumber.getText(), autocolor.getText(), automodel.getText());
    		autokurier[] kuriers=new autokurier[100];
    		try {
    			FileInputStream fis = new FileInputStream("autokuriers.ser");
    			ObjectInputStream ois = new ObjectInputStream(fis);
    			kuriers=(autokurier[]) ois.readObject(); 
    			ois.close();
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
    		while(kuriers[count]!=null) {
    			count++;
    		}
    		kuriers[count]=new autokurier();
    		kuriers[count].setname(autokuriername.getText());
    		kuriers[count].setphone(autokurierphone.getText());
    		kuriers[count].setauto(newauto);
    		try {
				FileOutputStream   fos = new FileOutputStream("autokuriers.ser");
	            ObjectOutputStream oos = new ObjectOutputStream(fos);
	            oos.writeObject(kuriers);
	            oos.close();
			}catch (IOException e) {
                e.printStackTrace();
            }
    		
    	});
    	/**a button that allows you to edit the number of consoles in warehouse*/
    	edit.setOnAction(event->{
    		xboxss seriess2=new xboxss();
    		try {
    			FileInputStream fis = new FileInputStream("xboxss.ser");
    			ObjectInputStream ois = new ObjectInputStream(fis);
    			seriess2=(xboxss) ois.readObject(); 
    			ois.close();
    		}catch (ClassNotFoundException e){
    	        System.err.println("Класс не сущуствует: "+e);
    	    }catch (FileNotFoundException e){
    	        System.err.println("Файл для десериализации не существует: "+e);
    	    }catch (InvalidClassException e){
    	        System.err.println("не совпадение версий класов "+e);
    	    }catch (IOException e){
    	        System.err.println("Общая ошибка "+e);
    	    }
    		xboxsx seriesx2=new xboxsx();
    		try {
    			FileInputStream fis = new FileInputStream("xboxsx.ser");
    			ObjectInputStream ois = new ObjectInputStream(fis);
    			seriesx2=(xboxsx) ois.readObject(); 
    			ois.close();
    		}catch (ClassNotFoundException e){
    	        System.err.println("Класс не сущуствует: "+e);
    	    }catch (FileNotFoundException e){
    	        System.err.println("Файл для десериализации не существует: "+e);
    	    }catch (InvalidClassException e){
    	        System.err.println("не совпадение версий класов "+e);
    	    }catch (IOException e){
    	        System.err.println("Общая ошибка "+e);
    	    }
    		ps4 ps4c2=new ps4();
    		try {
    			FileInputStream fis = new FileInputStream("ps4.ser");
    			ObjectInputStream ois = new ObjectInputStream(fis);
    			ps4c2=(ps4) ois.readObject(); 
    			ois.close();
    		}catch (ClassNotFoundException e){
    	        System.err.println("Класс не сущуствует: "+e);
    	    }catch (FileNotFoundException e){
    	        System.err.println("Файл для десериализации не существует: "+e);
    	    }catch (InvalidClassException e){
    	        System.err.println("не совпадение версий класов "+e);
    	    }catch (IOException e){
    	        System.err.println("Общая ошибка "+e);
    	    }
    		ps5 ps5c2=new ps5();
    		try {
    			FileInputStream fis = new FileInputStream("ps5.ser");
    			ObjectInputStream ois = new ObjectInputStream(fis);
    			ps5c2=(ps5) ois.readObject(); 
    			ois.close();
    		}catch (ClassNotFoundException e){
    	        System.err.println("Класс не сущуствует: "+e);
    	    }catch (FileNotFoundException e){
    	        System.err.println("Файл для десериализации не существует: "+e);
    	    }catch (InvalidClassException e){
    	        System.err.println("не совпадение версий класов "+e);
    	    }catch (IOException e){
    	        System.err.println("Общая ошибка "+e);
    	    }
    		int ps4count = Integer.parseInt(cislops4.getText().trim());
    		int ps5count = Integer.parseInt(cislops5.getText().trim());
    		int xboxseriess = Integer.parseInt(cisloxboxss.getText().trim());
    		int xboxseriesx = Integer.parseInt(cisloxboxsx.getText().trim());
    		seriess2.setkol(xboxseriess);
    		seriesx2.setkol(xboxseriesx);
    		ps4c2.setkol(ps4count);
    		ps5c2.setkol(ps5count);
    		try {
    			FileOutputStream   fos = new FileOutputStream("ps5.ser");
    		    ObjectOutputStream oos = new ObjectOutputStream(fos);
    		    oos.writeObject(ps5c2);
    		    oos.close();
    			}catch (IOException e) {
    		        e.printStackTrace();
    		    }
    		try {
    			FileOutputStream   fos = new FileOutputStream("ps4.ser");
    		    ObjectOutputStream oos = new ObjectOutputStream(fos);
    		    oos.writeObject(ps4c2);
    		    oos.close();
    			}catch (IOException e) {
    		        e.printStackTrace();
    		    }
    		try {
    			FileOutputStream   fos = new FileOutputStream("xboxss.ser");
    		    ObjectOutputStream oos = new ObjectOutputStream(fos);
    		    oos.writeObject(seriess2);
    		    oos.close();
    			}catch (IOException e) {
    		        e.printStackTrace();
    		    }
    		try {
    			FileOutputStream   fos = new FileOutputStream("xboxsx.ser");
    		    ObjectOutputStream oos = new ObjectOutputStream(fos);
    		    oos.writeObject(seriesx2);
    		    oos.close();
    			}catch (IOException e) {
    		        e.printStackTrace();
    		    }
    	});
    	/**button to create a new bike kurier*/
    	new_autokurier1.setOnAction(event->{
    		bike_kurier[] kuriers=new bike_kurier[100];
    		try {
    			FileInputStream fis = new FileInputStream("bikekuriers.ser");
    			ObjectInputStream ois = new ObjectInputStream(fis);
    			kuriers=(bike_kurier[]) ois.readObject();
    			ois.close();
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
    		while(kuriers[count]!=null) {
    			count++;
    		}
    		kuriers[count]=new bike_kurier();
    		kuriers[count].setname(bikekuriername.getText());
    		kuriers[count].setphone(bikekurierphone.getText());
    		try {
				FileOutputStream   fos = new FileOutputStream("bikekuriers.ser");
	            ObjectOutputStream oos = new ObjectOutputStream(fos);
	            oos.writeObject(kuriers);
	            oos.close();
			}catch (IOException e) {
                e.printStackTrace();
            }
    		
    	});
    	/** button to create a new game*/
    	new_game.setOnAction(event->{
    		game[] Games=new game[100];
        	try {
    			FileInputStream fis = new FileInputStream("games.ser");
    			ObjectInputStream ois = new ObjectInputStream(fis);
    			Games=(game[]) ois.readObject(); 
    			ois.close();
    		}catch (ClassNotFoundException e){
                System.err.println("Класс не сущуствует: "+e);
            }catch (FileNotFoundException e){
                System.err.println("Файл для десериализации не существует: "+e);
            }catch (InvalidClassException e){
                System.err.println("не совпадение версий класов "+e);
            }catch (IOException e){
                System.err.println("Общая ошибка "+e);
            }
        	int gamecount=0;
        	while(Games[gamecount]!=null) {//finding free space
        	gamecount ++;
        	}
        	Games[gamecount]=new game();
        	Games[gamecount].setname(gametextfield.getText());
        	try {
				FileOutputStream   fos = new FileOutputStream("games.ser");
	            ObjectOutputStream oos = new ObjectOutputStream(fos);
	            oos.writeObject(Games);
	            oos.close();
			}catch (IOException e) {
                e.printStackTrace();
            }
    	});
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
    }
}
