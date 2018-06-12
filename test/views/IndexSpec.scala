package views

import controllers.HomeController
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._
import org.jsoup.Jsoup
import play.api.mvc.Result

import scala.concurrent.Future

class IndexSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  val controller = new HomeController(stubControllerComponents())
  val home = controller.index().apply(FakeRequest(GET, "/"))
  val doc = Jsoup.parse(contentAsString(home)).body()

  "Index" should {

    "have an H1 title" in {
      doc.select("h1").text mustBe "Welcome to the library!"
    }

    "include an h2 heading" in {
      doc.select("h2").text mustBe "List of books available:"
    }

    "check that a book is on the index page" in {
      contentAsString(home) must include ("Jurrasic Park, Michael Crichton, Science Fiction, 1784752223")
    }

    "check that a second book is on the index page" in {
      contentAsString(home) must include ("The Shining, Stephen King, Thiller, 1444720724")
    }


  }
}
