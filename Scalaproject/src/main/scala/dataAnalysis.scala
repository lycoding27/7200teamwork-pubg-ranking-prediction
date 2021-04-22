import org.apache.spark.mllib.linalg._
import org.apache.spark.mllib.stat.Statistics
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.monotonically_increasing_id
import org.apache.spark.sql.functions._
import data.spark.implicits._


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
    val four = row.getAs[Double]("damageDealt")
    val five = row.getAs[Integer]("headshotKills")
    val six = row.getAs[Integer]("heals")
    val seven = row.getAs[Integer]("killPlace")
    val eight = row.getAs[Integer]("killPoints")
    val nine = row.getAs[Integer]("killStreaks")
    val ten = row.getAs[Integer]("kills")
    val eleven =row.getAs[Double]("longestKill")
    val twelve = row.getAs[Integer]("matchDuration")
    val onethree = row.getAs[Integer]("rankPoints")
    val onefour = row.getAs[Integer]("revives")
    val onefive = row.getAs[Double]("rideDistance")
    val onesix = row.getAs[Integer]("roadKills")
    val oneseven = row.getAs[Double]("swimDistance")
    val oneeight = row.getAs[Integer]("teamKills")
    val onenine = row.getAs[Integer]("vehicleDestroys")
    val twozero = row.getAs[Double]("walkDistance")
    val twoone = row.getAs[Integer]("weaponsAcquired")
    val twotwo = row.getAs[Integer]("winPoints")
    val twothree = row.getAs[Integer]("numGroups")
    val twofour = row.getAs[Double]("winPlacePerc")
    Vectors.dense(one.toDouble,two.toDouble,three.toDouble,four,five.toDouble,six.toDouble,seven.toDouble,eight.toDouble,nine.toDouble,ten.toDouble,eleven,twelve.toDouble,onethree.toDouble,onefour.toDouble,onefive,onesix.toDouble,oneseven,oneeight.toDouble,onenine.toDouble,twozero,twoone.toDouble,twotwo.toDouble,twothree.toDouble,twofour)
  }


  /**
   * Compute correlation
   */

  val corrMatrix = Statistics.corr(rddpre)
  println(corrMatrix)

//  /**
//   * Convert correlation matrix to dataframe
//   */
//
//  val cols = (0 until corrMatrix.numCols)
//
//  val df = corrMatrix.transpose
//    .colIter.toSeq
//    .map(_.toArray)
//    .toDF("arr")
//
//  val cor = cols.foldLeft(df)((df, i) => df.withColumn("_" + (i+1), $"arr"(i)))
//    .drop("arr")
//    .withColumnRenamed("_1","DBNOs")
//    .withColumnRenamed("_2","assists")
//    .withColumnRenamed("_3","boosts")
//    .withColumnRenamed("_4","damageDealt")
//    .withColumnRenamed("_5","headshotKills")
//    .withColumnRenamed("_6","heals")
//    .withColumnRenamed("_7","killPlace")
//    .withColumnRenamed("_8","killPoints")
//    .withColumnRenamed("_9","killStreaks")
//    .withColumnRenamed("_10","kills")
//    .withColumnRenamed("_11","longestKill")
//    .withColumnRenamed("_12","matchDuration")
//    .withColumnRenamed("_13","rankPoints")
//    .withColumnRenamed("_14","revives")
//    .withColumnRenamed("_15","rideDistance")
//    .withColumnRenamed("_16","roadKills")
//    .withColumnRenamed("_17","swimDistance")
//    .withColumnRenamed("_18","teamKills")
//    .withColumnRenamed("_19","vehicleDestroys")
//    .withColumnRenamed("_20","walkDistance")
//    .withColumnRenamed("_21","weaponsAcquired")
//    .withColumnRenamed("_22","winPoints")
//    .withColumnRenamed("_23","numGroups")
//    .withColumnRenamed("_24","winPlacePerc")
//
//
//  def doubleToRound(df: DataFrame, roundCols: Array[String]): DataFrame =
//    roundCols.foldLeft(df)((acc, c) => acc.withColumn(c, round(col(c), 2)))
//
//  val doubleCor = doubleToRound(cor, Array("DBNOs", "assists", "boosts", "damageDealt", "headshotKills", "heals", "killPlace", "killPoints", "killStreaks", "kills", "longestKill", "matchDuration", "rankPoints", "revives", "rideDistance", "roadKills", "swimDistance", "teamKills", "vehicleDestroys", "walkDistance", "weaponsAcquired", "winPoints", "numGroups", "maxPlace", "winPlacePerc"))
//    .withColumn("id2", monotonically_increasing_id())
//
//  /**
//   * Add column names that match row name in correlation matrix
//   */
//  val colName = List("DBNOs", "assists", "boosts", "damageDealt", "headshotKills", "heals", "killPlace", "killPoints", "killStreaks", "kills", "longestKill", "matchDuration", "rankPoints", "revives", "rideDistance", "roadKills", "swimDistance", "teamKills", "vehicleDestroys", "walkDistance", "weaponsAcquired", "winPoints", "numGroups", "maxPlace", "winPlacePerc").toDF("Cor-Matrix")
//    .withColumn("id1", monotonically_increasing_id())
//
//  colName.join(doubleCor, colName("id1") === doubleCor("id2"), "inner")
//    .drop("id1")
//    .drop("id2")
//    .show(false)
//
//
}
