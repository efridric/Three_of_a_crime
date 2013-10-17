package game

import scala.util.Random

object GameLogic {
  
	val players = new Array[Player](3)
	val criminals = new Array[Criminal](3)
	//TODO: Define a method for starting a round
	def startGame = {
	  initializePlayers
	  initializeCriminals
	  
	  //Begin the game
	}
  
	//This method creates three Players with user inputed names
	def initializePlayers = {
	  var i = 0
	  for(i <- players.indices){
	    var name = promptForPlayer(i)
	    players(i) = new Player(name,true)
	  }
	}
	
	//Prompts the player to input their name
	def promptForPlayer(i: Int) : String = {
	  val whichPlayer = Array("One, Two, Three")
      return readLine("Enter a name for " + whichPlayer(i) +": ")
	}
	
	//Creates seven Criminals. The Lists of names and status are randomly shuffled and used in the initialization of the Criminal
	def initializeCriminals = {
	  var i = 0
	  val name = Random.shuffle(List("Eric", "Tim", "Traivs", "Alex", "Sara", "Jack", "Jill"))
	  val status = Random.shuffle(List(true, true, true, false, false, false, false))
	  for(i <- criminals.indices){
	    criminals(i) = new Criminal(name(i), status(i))
	  }
	}
	
	//TODO: Define a method for choosing three criminals
	def getThreeCriminals = {
	  
	}
    //TODO: Define a method for handling player guesses
  
    //TODO: Define a method for losing
  
	//TODO: Define a method for winning
}