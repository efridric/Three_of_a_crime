package game

class Player(name: String, activeFlag: Boolean) {
    
  //TODO: Define a method for a player's turn
  def startTurn = {
    println(name + "it is your turn!\nDo you want to guess who the perpetrators are (Yes/No): ?")
    val answer = readLine()
    if(answer.toLowerCase() == "yes"){guess}
  }
  
  //Define a method for a player's guess may be easier to return some kind of hash/map rather than array
  def guess : Array[String] = {
	var i = 0
    val guess = new Array[String](3)
    for( i <- guess.indices ){
      guess(i) = promptForGuess(i)
    }
    return guess
  }
  
  //Returns the prompt for user guesses
  def promptForGuess(i: Int) : String = {
    val whichGuess = Array("first", "second", "third")
    return readLine("Enter your " + whichGuess(i) + " guess: ")
  }
   
}