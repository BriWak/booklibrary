package views

import models.Book
import org.jsoup.Jsoup
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.Helpers._
import play.api.test.Injecting

class BookDetailsSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  val home = views.html.bookdetails(Book("Test Book", "Some Author", "Genre", 123456789))

  val doc = Jsoup.parse(contentAsString(home)).body()

  "BookDetails" should {

    "have an H1 title" in {
      doc.select("h1").text mustBe "Test Book"
    }
  }
}
