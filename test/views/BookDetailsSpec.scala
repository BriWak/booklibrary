package views

import models.Book
import org.jsoup.Jsoup
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test.Helpers._
import play.api.test.Injecting

class BookDetailsSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  val home = views.html.bookdetails(Book("Test Book", "Some Author", "Genre", "123456789"),"CoverURL","Test description")

  val doc = Jsoup.parse(contentAsString(home)).body()

  "BookDetails" should {

    "have an H1 title" in {
      doc.select("h1").text mustBe "Test Book"
    }

    "have author details shown on the page" in {
      doc.getElementsByClass("author").text mustBe "Some Author"
    }

    "have genre shown on the page" in {
      doc.select("p").text must include ("Genre")
    }

    "have isbn shown on the page" in {
      doc.select("p").text must include ("123456789")
    }

    "show an image of the book cover" in {
      doc.select("img").attr("src") mustBe "CoverURL"
    }

    "have a book description shown on the page" in {
      doc.select("p").text must include ("Test description")
    }
  }
}

