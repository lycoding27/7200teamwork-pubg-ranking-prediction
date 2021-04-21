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
  val abnormalkills = spark.sql("select * from pubg where kills > 20000")
  abnormalkills.show();
  //find the abnormal ridedistance
  data.createOrReplaceTempView("pubg")
  val abnormalrideDistance = spark.sql("select * from pubg where rideDistance > 20000")
  abnormalrideDistance.show();

  data.createOrReplaceTempView("pubg")
  val abnormalwalkDistance = spark.sql("select * from pubg where walkDistance > 10000")
  abnormalwalkDistance.show();

  data.createOrReplaceTempView("pubg")
  val abnormalswimDistance = spark.sql("select * from pubg where swimDistance > 10000")
  abnormalswimDistance.show();

  data.createOrReplaceTempView("pubg")
  val abnormalweapinAcquired = spark.sql("select * from pubg where weaponsAcquired > 80")
  abnormalweapinAcquired.show();

  data.createOrReplaceTempView("pubg")
  val abnormalheals = spark.sql("select * from pubg where heals > 40")
  abnormalheals.show();





  //println(spark.sql("select * from pubg where matchType IN ('squad-fpp', 'squad')").count())
  // Split the data into training and test sets (30% held out for testing).
}
