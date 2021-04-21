import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.evaluation.RegressionEvaluator
import org.apache.spark.ml.feature.VectorIndexer
import org.apache.spark.ml.regression.{RandomForestRegressionModel, RandomForestRegressor}

/**
 * This object is Random Forest model.
 */
object RandomForest {

  // Train a RandomForest model.
  val rf = new RandomForestRegressor()
    .setLabelCol("label")
    .setFeaturesCol("indexedFeatures")

  // Chain indexer and forest in a Pipeline.
  val pipeline = new Pipeline()
    .setStages(Array(featureIndexer, rf))

  // Train model. This also runs the indexer.
  val model = pipeline.fit(trainingData)

  // Make predictions.
  val predictions = model.transform(testData)

  // Select example rows to display.
  predictions.select("prediction", "label", "features").show(5)

  // Select (prediction, true label) and compute test error.
  val evaluator = new RegressionEvaluator()
    .setLabelCol("label")
    .setPredictionCol("prediction")
    .setMetricName("rmse")
  val rmse = evaluator.evaluate(predictions)
  println(s"Root Mean Squared Error (RMSE) on test data = $rmse")

  val rfModel = model.stages(1).asInstanceOf[RandomForestRegressionModel]
  println(s"Learned regression forest model:\n ${rfModel.toDebugString}")
}