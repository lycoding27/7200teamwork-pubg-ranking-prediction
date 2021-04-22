import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.evaluation.RegressionEvaluator
import org.apache.spark.ml.feature.{StandardScaler, StandardScalerModel, VectorAssembler, VectorIndexer}
import org.apache.spark.ml.regression.{RandomForestRegressionModel, RandomForestRegressor}
object data{
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
  /**
   * try to find the abnormal data
   * and clean the data
   */

  //find the abnormal kills
  data.createOrReplaceTempView("pubg")
  val abnormalkills = spark.sql("select * from pubg where kills < 20")
  //abnormalkills.show();
  //find the abnormal ridedistance
  abnormalkills.createOrReplaceTempView("kills")
  val abnormalrideDistance = spark.sql("select * from kills where rideDistance < 20000")
 // abnormalrideDistance.show();

  abnormalrideDistance.createOrReplaceTempView("ride")
  val abnormalwalkDistance = spark.sql("select * from ride where walkDistance < 10000")
 // abnormalwalkDistance.show();

  abnormalwalkDistance.createOrReplaceTempView("walk")
  val abnormalswimDistance = spark.sql("select * from walk where swimDistance < 10000")
//  abnormalswimDistance.show();

  abnormalswimDistance.createOrReplaceTempView("swim")
  val abnormalweapinAcquired = spark.sql("select * from swim where weaponsAcquired < 80")
//  abnormalweapinAcquired.show();

  abnormalweapinAcquired.createOrReplaceTempView("weapon")
  val finaldata = spark.sql("select * from weapon where heals < 40")
//  finaldata.show();


  /**
   * get the data for trainning
   */
  finaldata.createOrReplaceTempView("finaldata")
  val preddata : DataFrame = spark.sql("select DBNOs, assists, boosts, damageDealt, headshotKills, heals, killPlace, killPoints, killStreaks, kills, longestKill, matchDuration, rankPoints, revives, rideDistance, swimDistance, walkDistance, weaponsAcquired, winPoints, numGroups, maxPlace, winPlacePerc from finaldata")
  val Array(train_valid, test) = preddata.randomSplit(Array(0.9, 0.1), seed = 1111L)
  val Array(train, valid) = train_valid.randomSplit(Array(0.7, 0.3), seed = 222L)

//  test.show()
  //print(test.count())


  /**
  * select features from dataset
  * */
  val assembler: VectorAssembler = new VectorAssembler()
    .setInputCols(Array("DBNOs", "assists", "boosts", "damageDealt", "headshotKills", "heals", "killPlace", "killPoints", "killStreaks", "kills", "longestKill", "matchDuration", "rankPoints", "revives", "rideDistance","swimDistance","walkDistance", "weaponsAcquired", "winPoints", "numGroups", "maxPlace" ))
    .setOutputCol("features")

  val pTrain: DataFrame = assembler.transform(train)
  val pValid: DataFrame = assembler.transform(valid)
  val pTest: DataFrame = assembler.transform(test)


  /**
   * standardize features
   */
  val scaler: StandardScaler = new StandardScaler()
    .setInputCol(assembler.getOutputCol)
    .setOutputCol("scaledFeatures")
    .setWithStd(true)
    .setWithMean(true)

  val scalerM: StandardScalerModel = scaler.fit(pTrain)
  val sTrain: DataFrame = scalerM.transform(pTrain)
  val sValid: DataFrame = scalerM.transform(pValid)
  val sTest: DataFrame = scalerM.transform(pTest)

  //println(spark.sql("select * from pubg where matchType IN ('squad-fpp', 'squad')").count())
  // Split the data into training and test sets (30% held out for testing).
}
