package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.stage.Stage;

public class Client implements Serializable{

    private PrintWriter writer;
    private BufferedReader reader;
    private int id;
    private String name;
    ArrayList<Player> players;

    public void start(String ip, int port, String playerName) throws IOException, ClassNotFoundException {
    	
        System.out.println("Starting client");
        Socket socket = new Socket(ip, port);
        System.out.println("schritt2");

        writer = new PrintWriter(socket.getOutputStream(), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        name = playerName;
        writer.println(name);
        
        //Client wartet bis die ArrayList Player übermittelt wird
        
        ObjectInputStream objIn = new ObjectInputStream(socket.getInputStream());
        players = (ArrayList<Player>) objIn.readObject();
        //ArrayList<Client> clients = (ArrayList<Client>) objIn.readObject();
        //objIn.close();
        
        this.id = players.get(0).getIndexByname(name);
        System.out.println("My ID: "+id);
       
        
        while (!socket.isClosed()) {
            play();
        }

        System.out.println("Server socket closed");
    }

    private void play() throws IOException {
        System.out.println("Waiting for server input");
        String command = reader.readLine();

        //TODO make this more resilient
        if (command.equals("Your turn!")) {
            System.out.println("It is my turn!");
            
            //next line: send index of picked card
            writer.println("3");
        } else if(command.equals("Game Finished!")){
        	players.get(id).bonusDifferentCharacters();
        	players.get(id).bonusMostCharactersPerLocation();
        	System.out.println("Game Finished!");
        	System.out.println("Gold: "+players.get(id).getGold());
        	if(players.get(id).evaluateWinner().getPlayerName().equalsIgnoreCase(players.get(id).getPlayerName())){
        		System.out.println("You are the winner!");
        	} else{
        		System.out.println("Winner: "+players.get(id).evaluateWinner().getPlayerName());
        	}
        		
        }
        
        else {
        	String[] commands = command.split(",");
        	int pickedCard = Integer.parseInt(commands[0]);
        	int currentActivePlayer = Integer.parseInt(commands[1]);
        	//TODO MePickCard und OtherPickCard aufrufen
        	players.get(id).mePickCard(pickedCard, currentActivePlayer);
        	
            System.out.println("Board has been updated by server"+currentActivePlayer+pickedCard);
            //TODO update board
        }
    }

}