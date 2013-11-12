/**
 * 	Author: Eric Fridrich
 *  Three of a crime is a simple logic game for up to 3 players. There are 7 different criminals.
 *  The computer will randomly choose three of these 7 criminals to be the perpetrators.
 *  At the start of each round the computer will provide the names of 3 criminals and state how many of those 3 if any are perpetrators.
 *  It is the job of the player to guess which 3 of the 7 criminals are the perpetrators, be careful though because if you guess wrong you are out. 
 * 
 */

import scala.util.Random
import scala.collection.mutable._

case class Criminal(name: String, status:Boolean) //Class for criminals
case class Player(name: String, var activeFlag: Boolean, guess: ListBuffer[String], var guessFlag: Boolean) {
    
  //Function for a player's turn
  def startTurn = {
    println(name + " it is your turn!\nDo you want to guess who the perpetrators are (Yes/No)?: ")
    val answer = readLine()
    if(answer.toLowerCase() == "yes"){initiateGuess}
  }
  
  //Function for a player's guess
  def initiateGuess = {
    guess.clear()
    for( i <- 0 until 3 ){
      guess += promptForGuess(i)
    }
    guessFlag = true
  }
  
  //Returns the prompt for user guesses
  def promptForGuess(i: Int) : String = {
    val whichGuess = Array("first", "second", "third")
    return readLine("Enter your " + whichGuess(i) + " guess: ").toLowerCase()
  }
}

//Starts the game
object Main {
  def main(args: Array[String]) {
	ThreeOfACrime.startGame
  }
}

object ThreeOfACrime {

	val players = new Array[Player](3)
	val criminals = new Array[Criminal](7)
	val currentCriminals = new ListBuffer[String]()
	val perps = new ListBuffer[String]()
	var perpCount = 0
	var playersLeft = 3
	var winner = false

	//Sets up the game
	def startGame = {
	  initializePlayers
	  initializeCriminals
	  //For debugging
	  //println("the perps(for testing) "+perps.mkString(" "))
	  startRound(0)
	}
  
	//Starts the round
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
	  return newCount
	}
	
	//Starts a players turn
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
	
	//Checks if a player guessed correctly or not
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
	
	//converts a list to lowercase and then sorts it
	def lowerCaseSort(list : ListBuffer[String]): ListBuffer[String] = {
      list.map(m => m.toLowerCase()).sorted    
    }
}