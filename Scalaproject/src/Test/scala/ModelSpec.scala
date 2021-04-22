import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class ModelSpec extends AnyFlatSpec with Matchers {
  "RF Model predictions" should "Greater than 0" in {
    val RF = RandomForest.rmse
    RF should be >= 0.0

  }
  

  "GTR Model predictions" should "Greater than 0" in {
    val GTR = GradientboostedTreeRegression.rmse
    GTR should be >= 0.0

  }


  "DTR Model predictions" should "greater than 0" in {
    val DTR = DecisionTreeRegression.rmse
    DTR should be >= 0.0
  }






}