import java.util.Date
import scala.util.control.Breaks.{break, breakable}

object Main  extends App{

  //val bestmodel = modelSelect.bestmodel
  /**
   * user system
   * input system
   */
  breakable{
    while(true){
      println("enter 0 to exit, enter 1 to predict")
      val flag = scala.io.StdIn.readInt()
      if(flag == 1){
        println("please enter the data")
        println("DBNOs(Number of enemies knocked down by the player):")
        val DBNOs = scala.io.StdIn.readInt()

        println("Assists:")
        val assists = scala.io.StdIn.readInt()

        println("Boosts:")
        val boosts = scala.io.StdIn.readInt()

        println("DamageDealt:")
        val damageDealt = scala.io.StdIn.readInt()

        println("HeadshotKills:")
        val headshotKills = scala.io.StdIn.readInt()

        println("Heals:")
        val heals = scala.io.StdIn.readInt()

        println("KillPlace:")
        val killPlace = scala.io.StdIn.readInt()

        println("KillPoints:")
        val killPoints = scala.io.StdIn.readInt()

        println("KillStreaks:")
        val killStreaks = scala.io.StdIn.readInt()

        println("Kills:")
        val kills = scala.io.StdIn.readInt()

        println("LongestKill:")
        val longestKill = scala.io.StdIn.readInt()

        println("MatchDuration:")
        val matchDuration = scala.io.StdIn.readInt()

        println("RankPoints(ELO ranking):")
        val rankPoints = scala.io.StdIn.readInt()

        println("Revives(Times of rescue teammates):")
        val revives = scala.io.StdIn.readInt()

        println("RideDistance:")
        val rideDistance = scala.io.StdIn.readDouble()

        println("RoadKills:")
        val roadKills = scala.io.StdIn.readInt()

        println("SwimDistance:")
        val swimDistance = scala.io.StdIn.readDouble()

        println("TeamKills(Times of teammates killed):")
        val teamKills = scala.io.StdIn.readInt()

        println("VehicleDestroys:")
        val vehicleDestroys = scala.io.StdIn.readInt()

        println("WalkDistance:")
        val walkDistance = scala.io.StdIn.readDouble()

        println("WeaponsAcquired:")
        val weaponsAcquired = scala.io.StdIn.readInt()

        println("WinPoints:")
        val winPoints = scala.io.StdIn.readInt()

        println("NumGroups:")
        val numGroups = scala.io.StdIn.readInt()

        println("MaxPlace(Worst ranking of all players in this round):")
        val maxPlace = scala.io.StdIn.readInt()

        println("WinPlacePerc:")
        val winPlacePerc = scala.io.StdIn.readDouble()

        /**
         * get start time
         */
        val start = new Date()

        /**
         * convert data into required dataframe
         */
        val userData = data.spark.createDataFrame(Seq(
          (DBNOs, assists, boosts, damageDealt, headshotKills, heals, killPlace, killPoints, killStreaks, kills, longestKill, matchDuration, rankPoints, revives, rideDistance,swimDistance,walkDistance, weaponsAcquired, winPoints, numGroups, maxPlace, winPlacePerc)
        )).toDF("DBNOs", "assists", "boosts", "damageDealt", "headshotKills", "heals", "killPlace", "killPoints", "killStreaks", "kills", "longestKill", "matchDuration", "rankPoints", "revives", "rideDistance", "swimDistance","walkDistance", "weaponsAcquired", "winPoints", "numGroups", "maxPlace", "winPlacePerc")


        /**
         * prediction
         */
        //to be ....
        val end = new Date()

        /**
         * show the result
         */

        println("Running time for prediction:" + ((end.getTime - start.getTime).toDouble / 1000))
        //to be ....
      }else{
        break
      }
    }
  }
}
