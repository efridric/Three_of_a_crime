package game

import scala.collection.mutable._

case class Player(name: String, var activeFlag: Boolean, guess: ListBuffer[String], var guessFlag: Boolean) {
    
  //TODO: Define a method for a player's turn
  def startTurn = {
    println(name + " it is your turn!\nDo you want to guess who the perpetrators are (Yes/No)?: ")
    val answer = readLine()
    if(answer.toLowerCase() == "yes"){guessList}
  }
  
  //Define a method for a player's guess may be easier to return some kind of hash/map rather than array
  def guessList = {
    guess.clear()
    for( i <- 0 until 3 ){
      guess += "Eric"//promptForGuess(i)
    }
    guessFlag = true
  }
  
  //Returns the prompt for user guesses
  def promptForGuess(i: Int) : String = {
    val whichGuess = Array("first", "second", "third")
    return readLine("Enter your " + whichGuess(i) + " guess: ").toLowerCase()
  }
   
}