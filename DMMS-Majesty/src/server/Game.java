package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import client.Player;

public class Game {

    private final List<Client> clients;
    private final int rounds;
    private int roundCounter;
    private int currentActivePlayer;
    private ArrayList<Player> players;


    public Game(List<Client> clients, ArrayList<Player> players) {
        this.clients = clients;
        this.rounds = clients.size()*12;
        this.roundCounter=0;
        this.currentActivePlayer=0;
        this.players = players;
    }

    public void start() {
        try {
        	startImpl();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void startImpl() throws IOException {
    	//DeckOfCards deck = new DeckOfCards(clients.size());
    	
        for(int i = 0;i<clients.size();i++){
        	clients.get(i).getObjectStream().writeObject(players);
        	//clients.get(i).getObjectStream().writeObject(clients);
        }
    	
        while (!gameFinished()) {
        	if(currentActivePlayer>=clients.size()){
        		currentActivePlayer = 0;
        	}
        	processInput(clients.get(currentActivePlayer)); //TODO decide which client is next
            currentActivePlayer++;
            roundCounter++;
        }
        for (Client client : clients){
        	client.getWriter().println("Game Finished!");
        }
        for (Client client : clients) {
            client.close();
        }
    }


	private void processInput(Client client) throws IOException {
        System.out.println("Waiting for input of client " + client);
        client.getWriter().println("Your turn!");

        String command = client.getReader().readLine();
        System.out.println("command = " + command);
        int cardIndex = Integer.parseInt(command);
        updateClients(cardIndex);
        //TODO update board according to given command
    }

    public void updateClients(int cardIndex) throws IOException {
        System.out.println("Sending index of picked Card to all clients");
        
        String stringCardIndex = Integer.toString(cardIndex);
        String stringActivePlayer = Integer.toString(currentActivePlayer);
        
        for (Client client : clients) {
            client.getWriter().println(stringCardIndex+","+stringActivePlayer); //TODO send new board state to all clients
        }
    }

    private boolean gameFinished() {
    	if(this.roundCounter<=this.rounds-1)
    		return false;
    	else return true;//TODO implement game ending condition
    }

}