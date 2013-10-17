package game

class Player(name: String, activeFlag: Boolean) {
    
  //TODO: Define a method for a player's turn
  
  //Define a method for a player's guess may be easier to return some kind of array or hash 
  def guess : Array[String] = {
    val guess = new Array[String](3)
    guess.foreach(yeild => guess.add => readLine("Enter one guess: "))
    println(guess.mkString(" "))
    return guess
  }
  
}