package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import client.DeckOfCards;
import client.Player;


public class Server {

    private static final int PORT = 8888;
    private static int NUM_CLIENTS; 
    
    private ServerSocket server;
    private final List<Client> clients = new ArrayList<>();
    private final ArrayList<Player> players = new ArrayList<>();
    private int indexCounter=0;

    public void start() throws IOException {
        System.out.println("Starting server on port " + PORT);
        server = new ServerSocket(PORT);
        String ip = InetAddress.getLocalHost().getHostAddress();
        System.out.println("Server IP-Address: "+ip);
        
      //Input dialog with a text field
        String input=null;
        while(input==null){
        	input = JOptionPane.showInputDialog(null,"Wie viele Spieler?","Grösse des Spiels",JOptionPane.QUESTION_MESSAGE);
        	if(input!=null){
        		try{
        			NUM_CLIENTS = Integer.parseInt(input);
        			if(NUM_CLIENTS < 2 || NUM_CLIENTS > 4)
        				input = null;
        		} catch (NumberFormatException e){
        			input=null;
        		}
        	}
        	
        }
       

        while (clients.size() < NUM_CLIENTS) {
            System.out.println("Waiting for client to connect...");
            Socket clientSocket = server.accept();
            System.out.println("Client from " + clientSocket.getInetAddress().getHostName() + ":" + clientSocket.getPort() + " connected");
            clients.add(new Client(clientSocket));
            System.out.println("Waiting for Client Name...");
            String name = clients.get(indexCounter).getReader().readLine();
            System.out.println(name+" joined!");
            Player p = new Player(indexCounter,name);
            players.add(p);
            indexCounter++;
        }
        DeckOfCards deck = new DeckOfCards(players.size());
        for(Player p : players){
        	p.setDeckOfCards(deck);
        	p.setNumOfPlayers(players.size());
        	p.setPlayerList(players);
        }
        System.out.println(NUM_CLIENTS + " connected, starting game");
        Game g = new Game(clients,players);
        g.start();
    }

}