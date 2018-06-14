package views
import models.Book
import org.jsoup.Jsoup
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test.Helpers._
import play.api.test._

class BooksSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  val home = views.html.books(List(
    Book("Test Book", "Some Author", "Genre", 123456789),
    Book("Another Book", "Different Author", "Some Genre", 111111111)))

  val doc = Jsoup.parse(contentAsString(home)).body()

  "Books" should {

    "have an H1 title" in {
      doc.select("h1").text mustBe "Welcome to the library!"
    }

    "include an h2 heading" in {
      doc.select("h2").text mustBe "List of books available:"
    }

    "check that a book is on the index page" in {
      doc.getElementsByTag("li").text must include ("Test Book, Some Author, Genre, 123456789")
    }

    "check that a second book is on the index page" in {
     doc.getElementsByTag("li").text must include ("Another Book, Different Author, Some Genre, 111111111")
    }
    "check that the first book is a link" in {
      doc.getElementById("123456789").text mustBe "Test Book"
    }

    "check that the second book is a link" in {
      doc.getElementById("111111111").text mustBe "Another Book"
    }

    "link to a new unique page for each link" in {
      doc.getElementsByAttribute("href").toString must include ("bookdetails/123456789")
      doc.getElementsByAttribute("href").toString must include ("bookdetails/111111111")
    }
  }
}
