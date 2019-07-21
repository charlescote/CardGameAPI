This is a simple RESTful web service that can be used for a basic game of cards, using the Spring Boot library for Java.

The code can be found in the /src/main/java/CardGameAPI directory. Since this was built using Apache Maven, the jar can be built by typing the commands

  mvn compile

and
  
  mvn package

at the root level of the directory, provided that the Maven binaries are included in the system's PATH environment variables.


A pre-built jar has been included in the respository. Simply run it by typing

  java -jar target/card-game-api-1.0.0.jar


The service should be running on http://localhost:8080.



Create a new game:
  http://localhost:8080/api/game/new

Delete the game:
  http://localhost:8080/api/game/delete


Add a player:
  http://localhost:8080/api/game/players/{id}/add

Remove a player:
  http://localhost:8080/api/game/players/{id}/remove

Create a deck of 52 unique cards:
  http://localhost:8080/api/game/deck/create

Add one of the decks to the main game deck (shoe):
  http://localhost:8080/api/game/gamedeck/add

Shuffle the main game deck:
  http://localhost:8080/api/game/gamedeck/shuffle

List the counts of each individual cards in the main game deck:
  http://localhost:8080/api/game/gamedeck/undealt/cards

List the counts of cards per suit in the main game deck:
  http://localhost:8080/api/game/gamedeck/undealt/suits

Deal cards to a player from the main game deck:
  http://localhost:8080/api/game/players/{id}/deal?numCards={num}

List the cards in a player's hand:
  http://localhost:8080/api/game/players/{id}/hand

List the players along with their score:
  http://localhost:8080/api/game/players/score
  


Enjoy!

~Charles Cote