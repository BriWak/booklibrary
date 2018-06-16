package connectors
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest

class BookDetailsConnectorSpec  extends PlaySpec with GuiceOneAppPerTest {

  class FakeBookDetailsConnector extends Connector {
    override def getDetails(isbn: String, category: String, notFoundMessage: String): String = "Test Result"
  }

  ".getDetails" should {

    "return a string containing the value of a requested category when given an ISBN and category" in {
      val connector = new FakeBookDetailsConnector
      connector.getDetails("123456789", "Test Category") mustBe "Test Result"
    }
  }

}
