import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.evaluation.RegressionEvaluator
import org.apache.spark.ml.feature.VectorIndexer
import org.apache.spark.ml.regression.{RandomForestRegressionModel, RandomForestRegressor}
object data extends App {
  val spark: SparkSession = SparkSession
    .builder()
    .appName("ProcessData")
    .master("local[*]")
    .getOrCreate()

  val data: DataFrame = spark.read.format("csv")
    .option("header", "true")
    .option("inferSchema", "true")
    .load("src/main/Resources/data.csv")

    //data.show()
  //try to find the abnormal data
  //find the abnormal kills
  data.createOrReplaceTempView("pubg")
  val abnormalkills = spark.sql("select * from pubg where kills < 20")
  abnormalkills.show();
  //find the abnormal ridedistance
  abnormalkills.createOrReplaceTempView("kills")
  val abnormalrideDistance = spark.sql("select * from kills where rideDistance < 20000")
  abnormalrideDistance.show();

  abnormalrideDistance.createOrReplaceTempView("ride")
  val abnormalwalkDistance = spark.sql("select * from ride where walkDistance < 10000")
  abnormalwalkDistance.show();

  abnormalwalkDistance.createOrReplaceTempView("walk")
  val abnormalswimDistance = spark.sql("select * from walk where swimDistance < 10000")
  abnormalswimDistance.show();

  abnormalswimDistance.createOrReplaceTempView("swim")
  val abnormalweapinAcquired = spark.sql("select * from swim where weaponsAcquired < 80")
  abnormalweapinAcquired.show();

  abnormalweapinAcquired.createOrReplaceTempView("weapon")
  val finaldata = spark.sql("select * from weapon where heals < 40")
  finaldata.show();






  //println(spark.sql("select * from pubg where matchType IN ('squad-fpp', 'squad')").count())
  // Split the data into training and test sets (30% held out for testing).
}
