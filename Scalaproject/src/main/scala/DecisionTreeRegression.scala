import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.evaluation.RegressionEvaluator
import org.apache.spark.ml.feature.VectorIndexer
import org.apache.spark.ml.regression.DecisionTreeRegressionModel
import org.apache.spark.ml.regression.DecisionTreeRegressor

object DecisionTreeRegression  {
  // Train a DecisionTree model.
  val dt = new DecisionTreeRegressor()
    .setLabelCol("winPlacePerc")
    .setFeaturesCol("features")

  // Chain indexer and tree in a Pipeline.
  val pipeline = new Pipeline()
    .setStages(Array(data.assembler, dt))

  // Train model. This also runs the indexer.
  val model = pipeline.fit(data.train)

  // Make predictions.
  val predictions = model.transform(data.test)

  // Select example rows to display.
  predictions.select("prediction", "winPlacePerc", "features").show(5)

  // Select (prediction, true label) and compute test error.
  val evaluatorRMSE = new RegressionEvaluator()
    .setLabelCol("winPlacePerc")
    .setPredictionCol("prediction")
    .setMetricName("rmse")

  val evaluatorr2 = new RegressionEvaluator()
    .setLabelCol("winPlacePerc")
    .setPredictionCol("prediction")
    .setMetricName("r2")


  val rmse = evaluatorRMSE.evaluate(predictions)
  val r2 = evaluatorr2.evaluate(predictions)

  println(s"Root Mean Squared Error (RMSE) on test data = $rmse")
  println(s"R^2^ metric (r2) on test data = $r2")


//  val treeModel = model.stages(1).asInstanceOf[DecisionTreeRegressionModel]
//  println(s"Learned regression tree model:\n ${treeModel.toDebugString}")

}
