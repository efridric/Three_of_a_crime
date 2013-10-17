package game

import scala.util.Random
import scala.collection.mutable._

object GameLogic {
	
	val winner = false
	val players = new Array[Player](3)
	val criminals = new Array[Criminal](7)
	val currentCriminals = new ListBuffer[String]()
	val perps = new ListBuffer[String]()
	var perpCount = 0

	//TODO: Define a method for starting the game
	def startGame = {
	  initializePlayers
	  initializeCriminals
	  startRound(0)
	}
  
	//TODO: Define a method for starting a round
	def startRound(turnCount: Int) : Int = {
	  val newCount = turnCount + 1
	  
	  if(!winner){
		  //New series of turns
		  if(turnCount % 3 == 0){
		    newCurrentCriminals
		  }
		  
		  println(displayCurrentCriminals)
		  startTurn(turnCount)
		  if(checkForGuess(turnCount)){
		    validateGuess(turnCount)
		  }
		  startRound(newCount)
		
	  }else{
	    exit(0)
	  }
		  //Start a players turn
		  //while winner is false
		  //check guess flag
		  //evaluate guess
		  //go to next player
	  
	  return newCount
	}
	
	//TODO: Start the players turn
	def startTurn(turnCount: Int)  = {
	  	  if(players(turnCount%3).activeFlag){
		  players(turnCount%3).startTurn
	  }
	  
	}
	//This method creates three Players with user inputed names
	def initializePlayers = {
	  for(i <- players.indices){
	    var name = promptForPlayer(i)
	    players(i) = new Player(name,true,new ListBuffer,false)
	  }
	}
	
	//Prompts the player to input their name
	def promptForPlayer(i: Int) : String = {
	  val whichPlayer = Array("One", "Two", "Three")
      return readLine("Enter a name for Player " + whichPlayer(i) +": ")
	}
	
	//Creates seven Criminals. The Lists of names and status are randomly shuffled and used in the initialization of the Criminal
	def initializeCriminals = {
	  val name = Random.shuffle(List("Eric", "Tim", "Traivs", "Alex", "Sara", "Jack", "Jill"))
	  val status = Random.shuffle(List(true, true, true, false, false, false, false))
	  for(i <- criminals.indices){
	    criminals(i) = new Criminal(name(i), status(i))
	    if(status(i)){
	      addToPerpList(name(i))
	    }
	  }
	}
	
	//Keeps track of the perpetrators
	def addToPerpList(name: String) = {
	  perps += name
	}
	
	//Randomly selects three criminals to display
	def newCurrentCriminals = {
	  perpCount = 0
	  currentCriminals.clear()
	  val indexList = Random.shuffle((0 to 6).toList)
	  for(i <- 0 until 3) {
	    currentCriminals += criminals(indexList(i)).name
	    if(criminals(indexList(i)).status)
	      perpCount += 1
	  }
	}
	
	//Displays the current criminals and shows how many are perpetrators
	def displayCurrentCriminals : String = {
	  return "Out of the criminals "+currentCriminals.mkString(", ")+" "+perpCount+" (is/are) perpetrators"
	}
	

    //Checks if the player made a guess or not
	def checkForGuess(turnCount: Int) : Boolean = {
	  return players(turnCount%3).guessFlag
	}
	
	def validateGuess(turnCount: Int) : String = {
	  val guess = players(turnCount%3).guess
	  if(guess.contains(currentCriminals)){
	    return winner(turnCount)
	  } else {
	    return loser(turnCount)
	  }
	}
    //a method for losingr
	def loser(turnCount: Int) : String = {
	  players(turnCount%3).activeFlag = false
	  return players(turnCount%3).name.toString() + " Your guess was incorrect you lose =[" 
	}
  
	//a method for winning
	def winner(turnCount: Int) : String = {
	  return players(turnCount%3).name.toString() + " Your guess was correct you win!"
	}
}