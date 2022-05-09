package kancelaria;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sklad.complectation;
import sklad.game;
import sklad.gameException;
import sklad.ps4;
import sklad.ps5;
import sklad.xboxss;
import sklad.xboxsx;

public class controlzakaz {

	@FXML
    private ChoiceBox<String> consolechoisebox;

    @FXML
    private Label namelabel;

    @FXML
    private Button letsplay;

    @FXML
    private ChoiceBox<String> timechoisebox;

    @FXML
    private ChoiceBox<String> game_selecter;

    @FXML
    private ChoiceBox<String> game_selecter1;

    @FXML
    private ChoiceBox<String> game_selecter2;

    @FXML
    private ChoiceBox<String> game_selecter11;

    @FXML
    private ChoiceBox<String> game_selecter21;

    @FXML
    private ChoiceBox<String> game_selecter111;

    @FXML
    private ChoiceBox<String> orderschoise;

    @FXML
    private ChoiceBox<String> deliverytype;

    @FXML
    private TextField console_field;

    @FXML
    private TextField time_field;

    @FXML
    private Button getinfo;
    
    @FXML
    private TextField infocurier;
    
    @FXML
    private Button logout;
    /**controller of user window*/
    @FXML
    void initialize() {
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
    	klient[] Klient2=new klient[1000];
		try {
			FileInputStream fis = new FileInputStream("users.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Klient2=(klient[]) ois.readObject();
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
    	int count =0;//actual user
		while(Klient2[count]!=null) {//using the 'in' variable, determines which user is currently logged in
			if(Klient2[count].in==1) {
				Klient2[count].in=0;
				try {
				FileOutputStream   fos = new FileOutputStream("users.ser");
	            ObjectOutputStream oos = new ObjectOutputStream(fos);
	            oos.writeObject(Klient2);
	            oos.close();
			}catch (IOException e) {
                e.printStackTrace();
            }
				break;
			}
			count++;
		}
    	ObservableList<String> consoles = FXCollections.observableArrayList("ps4", "ps5", "xbox series S", "xbox series X");
    	ObservableList<String> times = FXCollections.observableArrayList("1 day", "2 days", "3 days", "5 days", "1 week", "1 month");
    	ObservableList<String> games = FXCollections.observableArrayList();
    	ObservableList<String> delivery = FXCollections.observableArrayList("fast","regular");
    	ObservableList<String> orderschoise2 = FXCollections.observableArrayList();
    	final int countfinal=count;
    	int countorders=0;
    	while(orders2[countorders]!=null) {
    		if(orders2[countorders].getusername().equals(Klient2[countfinal].getLogin())) {
    		String str = Integer.toString(orders2[countorders].getnumber());
        	orderschoise2.add(str);}
        	countorders ++;
        	}
    	int gamecount=0;
    	while(Games[gamecount]!=null) {
    	games.add(Games[gamecount].getname());
    	gamecount ++;
    	}
    	consolechoisebox.setItems(consoles);
    	timechoisebox.setItems(times);
    	deliverytype.setItems(delivery);
    	consolechoisebox.setValue("ps4");
    	timechoisebox.setValue("1 day");
    	game_selecter.setItems(games);
    	game_selecter1.setItems(games);
    	game_selecter2.setItems(games);
    	game_selecter11.setItems(games);
    	game_selecter21.setItems(games);
    	game_selecter111.setItems(games);
		System.out.println("1");
    	orderschoise.setItems(orderschoise2);
    	/**Getinfo button displays data about the selected order */
    	getinfo.setOnAction(event->{
    		objednavka[] orders3 = new objednavka[1000];
        	try {
    			FileInputStream fis = new FileInputStream("orders.ser");
    			ObjectInputStream ois = new ObjectInputStream(fis);
    			orders3=(objednavka[]) ois.readObject(); 
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
    		int countorders2=0;
        	while(orders3[countorders2]!=null) {
        		if(orderschoise.getValue().equals(orders3[countorders2].getnumber()+"")) {
        			if(orders3[countorders2].getnumber_of_console()==0)
                		console_field.setText("xbox series s");
                	if(orders3[countorders2].getnumber_of_console()==1)
                    	console_field.setText("xbox series x");
                	if(orders3[countorders2].getnumber_of_console()==2)
                    	console_field.setText("ps4");
                	if(orders3[countorders2].getnumber_of_console()==3)
                    	console_field.setText("ps5");
                	time_field.setText(orders3[countorders2].gettime()+" days");
                	break;
        		}
            	countorders2 ++;
            	}
    	});
    	/**lets play button creates a new order if there are free consoles*/
		letsplay.setOnAction(event ->{
			new Thread(() -> {
			klient[] Klient=new klient[1000];
	    	objednavka[] orders = new objednavka[1000]; 
			try {
				FileInputStream fis = new FileInputStream("users.ser");
				ObjectInputStream ois = new ObjectInputStream(fis);
				Klient=(klient[]) ois.readObject(); 
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
			int count2=0;//trying to find free space for order
			while(orders[count2]!=null) {
				count2++;
			}
			boolean freeconsole=true;
			orders[count2]=new objednavka();
			int model=0;
			String info2=null;
    		if(consolechoisebox.getValue().equals("ps5")) {//trying to find a free ps5 on the warehouse
    			ps5 play5 = new ps5();
    			//if the required console is not in stock, the order cannot be completed
    			try {
					play5.getconsole();
				} catch (gameException e) {
					// TODO Auto-generated catch block
					freeconsole=false;
					e.printStackTrace();
				}
    			model=3;
    			complectation complect = new complectation("dualshock 5","ps5 hd camera");
				play5.setnewcomplect(complect);
				info2 = ("your ps5 comes with "+play5.getinfo());
    		}
    		if(consolechoisebox.getValue().equals("ps4")) {//trying to find a free ps4 on the warehouse
    			ps4 play4 = new ps4();
    			//if the required console is not in stock, the order cannot be completed
    			try {
					play4.getconsole();
				} catch (gameException e) {
					// TODO Auto-generated catch block
					freeconsole=false;
					e.printStackTrace();
				}
    			model=2;
    			complectation complect = new complectation("dualshock 4 v2","ps move");
				play4.setnewcomplect(complect);
				info2 =  ("your ps4 comes with "+play4.getinfo());
    		}
    		if(consolechoisebox.getValue().equals("xbox series S")) {//trying to find a free Xbox Series S on the warehouse
    			xboxss xboxs=new xboxss();
    			//if the required console is not in stock, the order cannot be completed
    			try {
					xboxs.getconsole();
				} catch (gameException e) {
					// TODO Auto-generated catch block
					freeconsole=false;
					e.printStackTrace();
				}
    			model=0;
    			complectation complect = new complectation("xbox gamepad","kinnect");
				xboxs.setnewcomplect(complect);
				info2 = ("your xbox series S comes with "+ xboxs.getinfo());
    		}
    		if(consolechoisebox.getValue().equals("xbox series X")) {//trying to find a free Xbox Series X on the warehouse
    			xboxsx xboxx = new xboxsx();
    			//if the required console is not in stock, the order cannot be completed
    			try {
					xboxx.getconsole();
				} catch (gameException e) {
					// TODO Auto-generated catch block
					freeconsole=false;
					e.printStackTrace();
				}
    			model=1;
    			complectation complect = new complectation("xbox gamepad","kinnect");
				xboxx.setnewcomplect(complect);
				info2 =  ("your xbox series X comes with "+xboxx.getinfo());
    		}
    		int days=0;
    		if(timechoisebox.getValue().equals("1 day")) {
    			days=1;
    		}
    		if(timechoisebox.getValue().equals("2 days")) {
    			days=2;
    		}
    		if(timechoisebox.getValue().equals("3 days")) {
    			days=3;
    		}
    		if(timechoisebox.getValue().equals("5 days")) {
    			days=5;
    		}
    		if(timechoisebox.getValue().equals("1 week")) {
    			days=7;
    		}
    		if(timechoisebox.getValue().equals("1 month")) {
    			days=30;
    		}
    		orders[count2].setusername(Klient[countfinal].getLogin());
    		orders[count2].setnumber_of_console(model);
    		orders[count2].setadress(Klient[countfinal].getadress());
    		orders[count2].settime(days);
    		orders[count2].status=0;
    		orders[count2].setnumber(count2);
    		if(freeconsole==true) {
    		if(deliverytype.getValue().equals("fast")) {
    			//search for a free auto courier, in case of his absence: wait 1 minute, and one more attempt to search
    			final String info=info2;
    			autokurier auto=new autokurier();
    			int number=count2;
    			new Thread(() -> {
    			int try1= auto.getorder(number);
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
    			for(int i=0; i<100; i++) {
    				if(kuriers[i]==null) {
    					infocurier.setText("we don't have free curiers now, your order will be delivered later");
    					break;
    				}
    				if(kuriers[i].getordernum()==number) {
    					infocurier.setText(kuriers[i].getinfo()+" "+info);
    					break;
    				}
    			}
    			if(try1==0) {
    				while(try1==0) {
    					try1= auto.getorder(number);
    					try {
							Thread.sleep(60000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
    				}
    			}
    			}).start();
    		}
    		if(deliverytype.getValue().equals("regular")) {
    			//search for a free bike courier, in case of his absence: wait 1 minute, and one more attempt to search
    			final String info=info2;
    			bike_kurier bike=new bike_kurier();
    			int number=count2;
    			new Thread(() -> {
    			bike.getorder(number);
    			int try1= bike.getorder(number);
    			try {
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
    			while(try1==0) {
    				try1=bike.getorder(number);
    			}
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
    			for(int i=0; i<100; i++) {
    				if(kuriers[i]==null) {
    					infocurier.setText("we don't have free curiers now, your order will be delivered later");
    					break;
    				}
    				if(kuriers[i].getordernum()==number) {
    					infocurier.setText(kuriers[i].getinfo()+" " +info);
    					break;
    				}
    			}
    			if(try1==0) {
    				while(try1==0) {
    					try1= bike.getorder(number);
    					try {
							Thread.sleep(60000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
    				}
    			}
    			}).start();
    		}
    		try {
				FileOutputStream   fos = new FileOutputStream("orders.ser");
	            ObjectOutputStream oos = new ObjectOutputStream(fos);
	            oos.writeObject(orders);
	            oos.close();
			}catch (IOException e) {
                e.printStackTrace();
            }}
    		else if(freeconsole==false) {
    			infocurier.setText("we don't have this console, please try again later");
    			orders[count2]=null;
    		}
			}).start();
    		});
		/**logout button */
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
