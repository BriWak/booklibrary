package views

import org.jsoup.Jsoup
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.test.Helpers._
import play.api.test._

class BookSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  val application = new GuiceApplicationBuilder().build
  val request = FakeRequest(GET, "/books").withHeaders("Host" -> "localhost")
  val home = route(application, request).get

  val doc = Jsoup.parse(contentAsString(home)).body()

  "Books" should {

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
