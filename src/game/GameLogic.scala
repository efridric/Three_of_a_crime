package game

import scala.util.Random
import scala.collection.mutable._

object GameLogic {
	
	val players = new Array[Player](3)
	val criminals = new Array[Criminal](7)
	val currentCriminals = new ListBuffer[String]()
	val perps = new ListBuffer[String]()
	var perpCount = 0
	var playersLeft = 3
	var winner = false

	//TODO: Define a method for starting the game
	def startGame = {
	  initializePlayers
	  initializeCriminals
	  //For debugging
	  println("the perps(for testing) "+perps.mkString(" "))
	  startRound(0)
	}
  
	//TODO: Define a method for starting a round
	def startRound(turnCount: Int) : Int = {
	  val newCount = turnCount + 1
	  
	  if(!winner && playersLeft != 0){
		  //New series of turns
		  if(turnCount % 3 == 0){
		    newCurrentCriminals
		  }
		  
		  println(displayCurrentCriminals)
		  if(isEligible(turnCount)) {
		    players(turnCount%3).startTurn
		    
			  if(checkForGuess(turnCount)){
			    println(validateGuess(turnCount))
			  }
		  }
		  startRound(newCount)
		
	  }else{
	    if(!winner){
	      println("No one guessed correctly, You all lose =[\nGAME OVER!")
	    }
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
	def isEligible(turnCount: Int): Boolean = {
	  	  if(players(turnCount%3).activeFlag){
		  return true
	  }
	  	  return false
	  
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
	  val name = Random.shuffle(List("Eric", "Tim", "Travis", "Alex", "Sara", "Jack", "Jill"))
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
	  if(lowerCaseSort(guess) == lowerCaseSort(perps)){
	    return winner(turnCount)
	  } else {
	    return loser(turnCount)
	  }
	}
    //a method for losing
	def loser(turnCount: Int) : String = {
	  players(turnCount%3).activeFlag = false
	  playersLeft -= 1
	  return players(turnCount%3).name.toString() + " Your guess was incorrect you lose =[" 
	}
  
	//a method for winning
	def winner(turnCount: Int) : String = {
	  winner = true
	  return players(turnCount%3).name.toString() + " Your guess was correct you win!"
	}
	
	def lowerCaseSort(list : ListBuffer[String]): ListBuffer[String] = {
      list.map(m => m.toLowerCase()).sorted    
    }
}