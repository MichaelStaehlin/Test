package client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Player implements Comparable<Player>,Serializable{

	//private String ipAddress;
	//private int port;
	private int gold;	
	private int playerID;
	private int numOfPlayers;
	private String playerName;
	ArrayList<Player> players;
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	ArrayList<Integer> muellerin;
	ArrayList<Integer> brauer;
	ArrayList<Integer> hexe;
	ArrayList<Integer> wache;
	ArrayList<Integer> soldat;
	ArrayList<Integer> wirt;
	ArrayList<Integer> adlige;
	ArrayList<Integer> lazarett;
	DeckOfCards deck;


	public Player(int playerIndex, String playerName){
		/* kann weggelassen werden, trotzdem noch nicht gel�scht
		 * this.ipAddress = ipAddress;
		this.port = port;
		*/
		this.playerID = playerIndex;
		this.playerName = playerName;
		this.gold = 0;
		
		
		this.muellerin = new ArrayList<Integer>();
		this.brauer = new ArrayList<Integer>();
		this.hexe = new ArrayList<Integer>();
		this.wache = new ArrayList<Integer>();
		this.soldat = new ArrayList<Integer>();
		this.wirt = new ArrayList<Integer>();
		this.adlige = new ArrayList<Integer>();
		this.lazarett = new ArrayList<Integer>();
		
		
		
	}
	
	public int getIndexByname(String pName)
    {
        for(Player p : players)
        {
            if(p.getPlayerName().equals(pName))
                return players.indexOf(p);
        }
        return -1;
    }
	
	public void setPlayerList(ArrayList<Player> players){
		this.players = players;
	}
	
	public void setNumOfPlayers(int numOfPlayers){
		this.numOfPlayers = numOfPlayers;
	}
	
	public void setDeckOfCards(DeckOfCards deck){
		this.deck = deck;
	}

	public int getGold(){
		return this.gold;
	}
	
	public DeckOfCards getDeck(int playerID){
		return players.get(playerID).deck;
	}
	
	public int getNumberOfMuellerin(int playerID){
		return players.get(playerID).muellerin.size();
	}
	
	public int getNumberOfBrauer(int playerID){
		return players.get(playerID).brauer.size();
	}
	
	public int getNumberOfHexe(int playerID){
		return players.get(playerID).hexe.size();
	}
	
	public int getNumberOfWachen(int playerID){
		return players.get(playerID).wache.size();
	}
	
	public int getNumberOfSoldat(int playerID){
		return players.get(playerID).soldat.size();
	}
	
	public int getNumberOfWirt(int playerID){
		return players.get(playerID).wirt.size();
	}
	
	public int getNumberOfAdlige(int playerID){
		return players.get(playerID).adlige.size();
	}
	
	public void addGold(int amount){
		this.gold += amount;
	}
	
	public void mePickCard(int index, int currentActivePlayer){
		int pickedCardOrdinal = getDeck(0).getCardOrdinalByIndex(index);
		
		if(pickedCardOrdinal == 0){
			players.get(currentActivePlayer).muellerin.add(0);
			players.get(currentActivePlayer).gold += players.get(currentActivePlayer).muellerin.size()*2;
		}

		if(pickedCardOrdinal == 1){
			players.get(currentActivePlayer).brauer.add(1);
			players.get(currentActivePlayer).gold += players.get(currentActivePlayer).brauer.size()*2;
		}

		if(pickedCardOrdinal == 2){
			players.get(currentActivePlayer).hexe.add(2);
			if(players.get(currentActivePlayer).muellerin.size()>=1 && players.get(currentActivePlayer).brauer.size()>=1 && players.get(currentActivePlayer).hexe.size()>=1){
				players.get(currentActivePlayer).gold +=gold += 2;
			}
			if(players.get(currentActivePlayer).muellerin.size()>=2 && players.get(currentActivePlayer).brauer.size()>=2 && players.get(currentActivePlayer).hexe.size()>=2){
				players.get(currentActivePlayer).gold += 4;
			}
			if(players.get(currentActivePlayer).muellerin.size()>=3 && players.get(currentActivePlayer).brauer.size()>=3 && players.get(currentActivePlayer).hexe.size()>=3){
				players.get(currentActivePlayer).gold += 6;
			}
			if(players.get(currentActivePlayer).muellerin.size()>=4 && players.get(currentActivePlayer).brauer.size()>=4 && players.get(currentActivePlayer).hexe.size()>=4){
				players.get(currentActivePlayer).gold += 8;
			}
			heal(currentActivePlayer);
		}
		
		if(pickedCardOrdinal == 3){
			players.get(currentActivePlayer).wache.add(3);
			if(players.get(currentActivePlayer).wache.size()>=1 && players.get(currentActivePlayer).soldat.size()>=1 && players.get(currentActivePlayer).wirt.size()>=1){
				players.get(currentActivePlayer).gold += 2;
			}
			if(players.get(currentActivePlayer).wache.size()>=2 && players.get(currentActivePlayer).soldat.size()>=2 && players.get(currentActivePlayer).wirt.size()>=2){
				players.get(currentActivePlayer).gold += 4;
			}
			if(players.get(currentActivePlayer).wache.size()>=3 && players.get(currentActivePlayer).soldat.size()>=3 && players.get(currentActivePlayer).wirt.size()>=3){
				players.get(currentActivePlayer).gold += 6;
			}
			if(players.get(currentActivePlayer).wache.size()>=4 && players.get(currentActivePlayer).soldat.size()>=4 && players.get(currentActivePlayer).wirt.size()>=4){
				players.get(currentActivePlayer).gold += 8;
			}
		}

		if(pickedCardOrdinal == 4){
			players.get(currentActivePlayer).soldat.add(4);
			players.get(currentActivePlayer).gold += players.get(currentActivePlayer).soldat.size()*3;
		}

		if(pickedCardOrdinal == 5){
			players.get(currentActivePlayer).wirt.add(5);
			players.get(currentActivePlayer).gold += players.get(currentActivePlayer).wirt.size()*4;
		}

		if(pickedCardOrdinal == 6){
			players.get(currentActivePlayer).adlige.add(6);
			players.get(currentActivePlayer).gold+= players.get(currentActivePlayer).adlige.size()*5;
		}
		
		//UPDATE GUI
		
		otherPickCard(index, currentActivePlayer);

	}
	
	public void otherPickCard(int index, int currentActivePlayer){
		int pickedCardOrdinal = getDeck(0).getCardOrdinalByIndex(index);
		players.get(playerID).deck.removeCardByIndex(index);
			for(Player p : players){
				if(pickedCardOrdinal == 1){
					if(this.muellerin.size()>=1){
						p.addGold(2);
					}
				} 
				if(pickedCardOrdinal == 4){
					attack(currentActivePlayer);
				}
				if(pickedCardOrdinal == 5){
					if(brauer.size()>=1){
						p.addGold(3);
					}
				}
			}
			
		//UPDATE GUI

	}
	
	public void attack(int currentActivePlayer){
		for(int i = 0; i < players.size(); i++){
			if(i != currentActivePlayer){
					if(players.get(i).getNumberOfWachen(i) < players.get(currentActivePlayer).getNumberOfSoldat(currentActivePlayer)){
						moveToLazarett(i);
					}
			}
		}

	}
	
	public void moveToLazarett(int playerID){
		if(players.get(playerID).getNumberOfMuellerin(playerID) > 0){
			players.get(playerID).muellerin.remove(0);
			players.get(playerID).lazarett.add(0);
		}else{
			if(players.get(playerID).getNumberOfBrauer(playerID) > 0){
				players.get(playerID).brauer.remove(0);
				players.get(playerID).lazarett.add(1);
			}else{
				if(players.get(playerID).getNumberOfHexe(playerID) > 0){
					players.get(playerID).hexe.remove(0);
					players.get(playerID).lazarett.add(2);
				}else{
					if(players.get(playerID).getNumberOfWachen(playerID) > 0){
						players.get(playerID).wache.remove(0);
						players.get(playerID).lazarett.add(3);
					}else{
						if(players.get(playerID).getNumberOfSoldat(playerID) > 0){
							players.get(playerID).soldat.remove(0);
							players.get(playerID).lazarett.add(4);
						}else{
							if(players.get(playerID).getNumberOfWirt(playerID) > 0){
								players.get(playerID).wirt.remove(0);
								players.get(playerID).lazarett.add(5);
							}else{
								if(players.get(playerID).getNumberOfAdlige(playerID) > 0){
									players.get(playerID).adlige.remove(0);
									players.get(playerID).lazarett.add(6);
								}
							}
						}
					}
				}
			}
		}
	}
	
	public void heal(int playerID){
		if(players.get(playerID).lazarett.size() > 0){
			int value = players.get(playerID).lazarett.get(0);
			switch (value){
				case 0:	players.get(playerID).muellerin.add(value);
						break;
				case 1:	players.get(playerID).brauer.add(value);
						break;
				case 2:	players.get(playerID).hexe.add(value);
						break;
				case 3:	players.get(playerID).wache.add(value);
						break;
				case 4:	players.get(playerID).soldat.add(value);
						break;
				case 5:	players.get(playerID).wirt.add(value);
						break;
				case 6:	players.get(playerID).adlige.add(value);
						break;
			}
			players.get(playerID).lazarett.remove(0);
		}
	}

	public void malusForLazarett(){
		for(Player p : players){
			if(p.lazarett.size() > 0){
				p.gold -= p.lazarett.size();
			}
		}
	}
	
	public void bonusDifferentCharacters(){
		for(Player p : players){
			int bonus = 0;
			
			if(p.muellerin.size()>0)
				bonus += 1;
			if(p.brauer.size()>0)
				bonus += 1;
			if(p.hexe.size()>0)
				bonus += 1;
			if(p.wache.size()>0)
				bonus += 1;
			if(p.soldat.size()>0)
				bonus += 1;
			if(p.wirt.size()>0)
				bonus += 1;
			if(p.adlige.size()>0)
				bonus += 1;
			
			p.addGold(bonus*bonus);
		}
	}
	
	public void bonusMostCharactersPerLocation(){
		ArrayList<Integer> idMostMuellerin = new ArrayList<Integer>();
		ArrayList<Integer> idMostBrauer = new ArrayList<Integer>();
		ArrayList<Integer> idMostHexe = new ArrayList<Integer>();
		ArrayList<Integer> idMostWache = new ArrayList<Integer>();
		ArrayList<Integer> idMostSoldat = new ArrayList<Integer>();
		ArrayList<Integer> idMostWirt = new ArrayList<Integer>();
		ArrayList<Integer> idMostAdlige = new ArrayList<Integer>();
		int mostMuellerin = -1;
		int mostBrauer = -1;
		int mostHexe = -1;
		int mostWache = -1;
		int mostSoldat = -1;
		int mostWirt = -1;
		int mostAdlige = -1;
		
		for(int i = 0; i < players.size(); i++){
			if(players.get(i).muellerin.size()>=mostMuellerin){
				//weiter unten einfuegen
				mostMuellerin = players.get(i).muellerin.size();
				if(players.get(i).muellerin.size()>mostMuellerin){
					idMostMuellerin.clear();
					idMostMuellerin.add(i);
				}else{
					idMostMuellerin.add(i);
				}
			}
		}
		
		for(int i = 0; i < players.size(); i++){
			if(players.get(i).brauer.size()>=mostBrauer){
				mostBrauer = players.get(i).brauer.size();
				if(players.get(i).brauer.size()>mostBrauer){
					idMostBrauer.clear();
					idMostBrauer.add(i);
				}else{
					idMostBrauer.add(i);
				}
			}
		}
		
		for(int i = 0; i < players.size(); i++){
			if(players.get(i).hexe.size()>=mostHexe){
				mostHexe = players.get(i).hexe.size();
				if(players.get(i).hexe.size()>mostHexe){
					idMostHexe.clear();
					idMostHexe.add(i);
				}else{
					idMostHexe.add(i);
				}
			}
		}
		
		for(int i = 0; i < players.size(); i++){
			if(players.get(i).wache.size()>=mostWache){
				mostWache = players.get(i).wache.size();
				if(players.get(i).wache.size()>mostWache){
					idMostWache.clear();
					idMostWache.add(i);
				}else{
					idMostWache.add(i);
				}
			}
		}
		
		for(int i = 0; i < players.size(); i++){
			if(players.get(i).soldat.size()>=mostSoldat){
				mostSoldat = players.get(i).soldat.size();
				if(players.get(i).soldat.size()>mostSoldat){
					idMostSoldat.clear();
					idMostSoldat.add(i);
				}else{
					idMostSoldat.add(i);
				}
			}
		}
		
		for(int i = 0; i < players.size(); i++){
			if(players.get(i).wirt.size()>=mostWirt){
				mostWirt = players.get(i).muellerin.size();
				if(players.get(i).wirt.size()>mostWirt){
					idMostWirt.clear();
					idMostWirt.add(i);
				}else{
					idMostWirt.add(i);
				}
			}
		}
		
		for(int i = 0; i < players.size(); i++){
			if(players.get(i).adlige.size()>=mostAdlige){
				mostAdlige = players.get(i).adlige.size();
				if(players.get(i).adlige.size()>mostAdlige){
					idMostAdlige.clear();
					idMostAdlige.add(i);
				}else{
					idMostAdlige.add(i);
				}
			}
		}
		
		for(int i : idMostMuellerin){
			players.get(i).addGold(10);
		}
		
		for(int i : idMostBrauer){
			players.get(i).addGold(11);
		}
		
		for(int i : idMostHexe){
			players.get(i).addGold(12);
		}
		
		for(int i : idMostWache){
			players.get(i).addGold(13);
		}
		
		for(int i : idMostSoldat){
			players.get(i).addGold(14);
		}
		
		for(int i : idMostWirt){
			players.get(i).addGold(15);
		}
		
		for(int i : idMostAdlige){
			players.get(i).addGold(16);
		}
	}
	
	public Player evaluateWinner(){
		//Copy actual list of Player to avoid problems with the GUI, which bases on list players
		ArrayList<Player> playersCopy = new ArrayList<Player>(players);
		Collections.sort(playersCopy);
		return playersCopy.get(0);
		//Liste playersCopy sollte nun angezeigt werden -> Damir/Suvi
		
	}
	public int compareTo(Player p)
    {
        return p.getGold() - this.getGold();
    }
	
	
	
}
