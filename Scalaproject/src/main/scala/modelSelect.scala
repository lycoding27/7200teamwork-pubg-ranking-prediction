object modelSelect {
  /**
   * create modelmap
   * try to find best model
   */
  val modelmap = Map("DecisionTreeRegression" -> DecisionTreeRegression.r2, "GradientboostedTreeRegression" -> GradientboostedTreeRegression.r2, "LinearRegression" -> LinearRegression.trainingSummary.r2, "RandomForest" -> RandomForest.r2)

  val bestModel : String = modelmap.maxBy(_._2)match{
    case (k, v) => k
  }

  def find(key: String) = key match{
    case "DecisionTreeRegression" => DecisionTreeRegression.model
    case "GradientboostedTreeRegression" => GradientboostedTreeRegression.model
    case "LinearRegression" => LinearRegression.lrModel
    case "RandomForest" => RandomForest.model
  }

  val bestmodel = find(bestModel)


}
