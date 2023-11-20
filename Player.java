package a12044041;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class Player implements Comparable<Player> {
	private String name;
	private Queue<VehicleCard> deck = new ArrayDeque<VehicleCard>();

	public Player(final String name) {
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException("Invalid player name.");
		
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
	public int getScore() {
		int sum = 0;
		for (VehicleCard v : deck) {
			sum += v.totalBonus();
		}
		return sum;
	}

	public void addCards(final Collection<VehicleCard> cards) {
		if(cards == null)
			throw new IllegalArgumentException("null cardS");
		else {
			for (VehicleCard v : cards) {
				if( v == null)
					throw new IllegalArgumentException("null card");
				else
					deck.add(v);
			}
		}
	}

	public void addCard(final VehicleCard card) {
		if(card == null)
			throw new IllegalArgumentException("null card");
		else
			deck.add(card);
	}

	public void clearDeck() {
		deck.clear();
	}

	public List<VehicleCard> getDeck() {
		List<VehicleCard> newList = new ArrayList<VehicleCard>(deck);
		return newList;

	}

	protected VehicleCard peekNextCard() {
		return deck.peek();
	}

	public VehicleCard playNextCard() {
		return deck.poll();
	}

	@Override
	public int compareTo(final Player other) {
		return other.name.compareToIgnoreCase(name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name.toLowerCase());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return Objects.equals(name.toLowerCase(), other.name.toLowerCase());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getName()).append("(").append(getScore()).append("):");
		for (VehicleCard v : deck) {
			sb.append("\n").append(v.toString());
		}
		return sb.toString();
	}

	public boolean challengePlayer(Player p) {
		if (p == null || this == p)
			throw new IllegalArgumentException("Find new players lmaoooo");
		
		List<VehicleCard> first = new ArrayList<VehicleCard>();
		List<VehicleCard> second = new ArrayList<VehicleCard>();
		
		
		
		
		while (this.peekNextCard() != null && p.peekNextCard() != null ) {
			first.add(this.playNextCard());
			second.add(p.playNextCard());
			
			if( first.get(first.size()-1).compareTo(second.get(second.size()-1)) > 0 ) {
				this.addCards(second);
				this.addCards(first);
				return true;
			} 
			else if (first.get(first.size()-1).compareTo(second.get(second.size()-1)) < 0) {
				p.addCards(first);
				p.addCards(second);
				 return false;
			}	
			
		}
		
		this.addCards(first);
		p.addCards(second);
		return false;		
		

	}

	public static Comparator<Player> compareByScore() {
		return new ComparatorScore();
	}

	public static Comparator<Player> compareByDeckSize() {
		return new ComparatorDeckSize();
	}

}

class ComparatorScore implements Comparator<Player>{

	@Override
	public int compare(Player o1, Player o2) {
		return Integer.valueOf(o1.getScore()).compareTo(o2.getScore());
	}
}

class ComparatorDeckSize implements Comparator<Player>{
	@Override
	public int compare(Player o1, Player o2) {
		return Integer.valueOf(o1.getDeck().size()).compareTo(o2.getDeck().size());
	}
}
/*
void cheat() {
		if(deck.isEmpty())
			throw new IllegalStateException("Error");
		VehicleCard copy = deck.element();
		deck.add(copy);
		hasCheated = true;
		
		
		
	}
	boolean hasCheated() {
		boolean res = hasCheated;
		return res;
	}
	
	public static SortedSet<VehicleCard> removeBelowAvg(Collection<VehicleCard> cards){
		if(cards == null || cards.isEmpty())
			throw new IllegalArgumentException("Error");
		Collection<VehicleCard> test = cards;
		Collection<VehicleCard> t = new ArrayList<VehicleCard>();
		
		double mittel= 0.;
		double sum = 0.;
		for(VehicleCard card : cards) {
			sum = sum + card.totalBonus();
		}
		mittel = sum/(cards.size());
		
		for(VehicleCard c : test) {
			if(c.totalBonus() < mittel)
				t.add(c);
		}
		test.removeAll(t);
		
		cards.removeAll(test);

				
		
		SortedSet<VehicleCard> res  = new TreeSet<VehicleCard>(test);
		return res;
	}
	*/
