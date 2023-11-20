
import java.util.Set;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        // Create VehicleCard instances
        VehicleCard card1 = new VehicleCard("Tesla", VehicleCard.newMap(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0));
        VehicleCard card2 = new VehicleCard("Toyota", VehicleCard.newMap(2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0));

        // Print card details
        System.out.println("Card 1: " + card1);
        System.out.println("Card 2: " + card2);

        // Compare cards
        int result = card1.compareTo(card2);
        if (result > 0) {
            System.out.println("Card 1 is greater than Card 2");
        } else if (result < 0) {
            System.out.println("Card 2 is greater than Card 1");
        } else {
            System.out.println("Card 1 and Card 2 are equal");
        }

        // Create Player instances
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        // Add cards to players
        player1.addCard(card1);
        player2.addCard(card2);

        // Compare players
        int playerComparison = player1.compareTo(player2);
        if (playerComparison > 0) {
            System.out.println("Player 1 has a higher score than Player 2");
        } else if (playerComparison < 0) {
            System.out.println("Player 2 has a higher score than Player 1");
        } else {
            System.out.println("Player 1 and Player 2 have the same score");
        }
    }
}
