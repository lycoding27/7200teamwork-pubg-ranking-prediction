import org.apache.spark.mllib.linalg.Vectors
import org.json4s.scalap.scalasig.Flags
import org.apache.spark.mllib.stat.Statistics
import org.apache.spark.sql.SparkSession

object dataAnalysis extends App {

  def toDoubleDynamic(x: Any) = x match {
    case s: String => s.toDouble
    case jn: java.lang.Number => jn.doubleValue()
    case _ => throw new ClassCastException("cannot cast to double")
  }

  /**
   * Read and convert data.
   */
  val rddpre = data.preddata.rdd.map{row =>
    val one = row.getAs[Integer]("DBNOs")
    val two = row.getAs[Integer]("assists")
    val three = row.getAs[Integer]("boosts")
    //val four = row.getAs[Float]("damageDealt")
    val five = row.getAs[Integer]("headshotKills")
    val six = row.getAs[Integer]("heals")
    val seven = row.getAs[Integer]("killPlace")
    val eight = row.getAs[Integer]("killPoints")
    val nine = row.getAs[Integer]("killStreaks")
    val ten = row.getAs[Integer]("kills")
    //val eleven =row.getAs[Float]("longestKill")
    val twelve = row.getAs[Integer]("matchDuration")
    val onethree = row.getAs[Integer]("rankPoints")
    val onefour = row.getAs[Integer]("revives")
    //val onefive = row.getAs[Float]("rideDistance")
    val onesix = row.getAs[Integer]("roadKills")
    //val oneseven = row.getAs[Float]("swimDistance")
    val oneeight = row.getAs[Integer]("teamKills")
    val onenine = row.getAs[Integer]("vehicleDestroys")
    //val twozero = row.getAs[Float]("walkDistance")
    val twoone = row.getAs[Integer]("weaponsAcquired")
    val twotwo = row.getAs[Integer]("winPoints")
    val twothree = row.getAs[Integer]("numGroups")
    //val twofour = row.getAs[Float]("winPlacePerc")
    Vectors.dense(one.toDouble,two.toDouble,three.toDouble,five.toDouble,six.toDouble,seven.toDouble,eight.toDouble,nine.toDouble,ten.toDouble,twelve.toDouble,onethree.toDouble,onefour.toDouble,onesix.toDouble,oneeight.toDouble,onenine.toDouble,twoone.toDouble,twotwo.toDouble,twothree.toDouble)
  }


  /**
   * Compute correlation
   */

  val corrMatrix = Statistics.corr(rddpre)
  println(corrMatrix)

}
