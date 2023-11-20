# pr2_java
Project Overview

This Java project, named TopTrumps, implements a card game involving vehicle statistics. The goal is to create a card game where players compare different categories of vehicle attributes to determine the winner of each round.
Project Structure

The project is organized into the following classes:

    VehicleCard
        Represents a card with vehicle statistics.
        Contains attributes such as name, categories (e.g., economy, cylinders), and methods to compare cards and calculate total bonuses.

    VehicleCard.Category
        Enumeration representing different categories for vehicle attributes (e.g., Economy, Cylinders).
        Each category has methods to check if it is inverted, calculate the bonus, and convert to a string.

    FoilVehicleCard
        Extends VehicleCard to represent special foil cards.
        Includes additional special categories and methods to calculate total bonus with special categories.

    Player
        Represents a player in the card game.
        Manages a deck of VehicleCards, calculates scores, and implements methods for playing cards and challenging other players.

Base Implementation (Basis)

The base implementation includes the classes mentioned above, along with essential methods for card comparison, player actions, and card bonuses. The main class (Main.java) is used to test the basic functionality of the implemented classes.
