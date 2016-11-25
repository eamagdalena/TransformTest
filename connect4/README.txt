 How to play: 
 
 - Make REST calls with a browser:
 
 Create game:
  http://localhost:8080/connect4/game/create
 Join game:  
  http://localhost:8080/connect4/game/{gameid}/join
 Check game status: 
 http://localhost:8080/connect4/game/{gameid}/status
 Play your turn:
  http://localhost:8080/connect4/game/player/{playerid}/dropOnColumn/{column}
 
 OR
 - Write a test with the game you want to play following the existing ones on Connect4ResourceTest as example
   
 What it contains:
 
 * A JAX-RS Webservice with 4 methods
 * A Game model and logic (model package)
 * A Service layer
 * A "persistence" DAO layer 
 * Grizzly for ease of use (Main.java)