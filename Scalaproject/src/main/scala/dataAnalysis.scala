import org.json4s.scalap.scalasig.Flags
import org.apache.spark.mllib.stat.Statistics
import org.apache.spark.sql.SparkSession

class dataAnalysis extends App {

  val spark: SparkSession = SparkSession
    .builder()
    .appName("ProcessData")
    .master("local[*]")
    .getOrCreate()
  /**
   * Read and convert data.
   */
  val rddpre = data.preddata.rdd.map{row =>
    val one = row.getAs[Integer]("DBNOs")
    val two = row.getAs[Integer]("assists")
    val three = row.getAs[Integer]("boosts")
    val four = row.getAs[Float]("damageDealt")
    val five = row.getAs[Integer]("headshotKills")
    val six = row.getAs[Integer]("heals")
    val seven = row.getAs[Integer]("killPlace")
    val eight = row.getAs[Integer]("killPoint")
    val nine = row.getAs[Integer]("killStreaks")
    val ten = row.getAs[Integer]("kills")
    val eleven =row.getAs[Float]("longestKill")
    val twelve = row.getAs[Integer]("matchDuration")
    val onethree = row.getAs[Integer]("rankPoints")
    val onefour = row.getAs[Integer]("reives")
    val onefive = row.getAs[Float]("rideDistance")
    val onesix = row.getAs[Integer]("roadKills")
    val oneseven = row.getAs[Float]("swimDistance")
    val oneeight = row.getAs[Integer]("teamKills")
    val onenine = row.getAs[Integer]("vehicleDestroys")
    val twozero = row.getAs[Float]("walkDistance")
    val twoone = row.getAs[Integer]("weaponsAcquired")
    val twotwo = row.getAs[Integer]("winPoints")
    val twothree = row.getAs[Integer]("numGroups")
    val twofour = row.getAs[Float]("winPlacePerc")

    /**
     * Compute correlation
     */

    val corrMatrix = Statistics.corr(rddpre)
    corrMatrix.show()
  }
}
