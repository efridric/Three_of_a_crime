/**
 *  Three of a crime is a simple logic game for up to 3 players. There are 7 different criminals.
 *  The computer will randomly choose three of these 7 criminals to be the perpetrators.
 *  At the start of each round the computer will provide the names of 3 criminals and state how many of those 3 if any are perpetrators.
 *  It is the job of the player to guess which 3 of the 7 criminals are the perpetrators, be careful though because if you guess wrong you are out. 
 * 
 */

package game

import scala.util.Random

object Main {
  
  def main(args: Array[String]) {
    //TODO: Should be able to call GameLogic.startGame
	  GameLogic.startGame
  }
}