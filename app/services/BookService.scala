package services

import models.Book
import play.api.libs.json.{JsValue, Json}


class BookService {

  def books = List(
    Book("Jurrasic Park", "Michael Crichton", "Science Fiction", "0307763056"),
    Book("The Shining", "Stephen King", "Thriller", "1848940998"),
    Book("The Dead Zone", "Stephen King", "Thriller", "1444708090")
  )

  def book(isbn: String) : Book = {
    books.filter( b => b.isbn == isbn).head
  }

  def getCover(isbn: String): String = {
    val url = s"https://www.googleapis.com/books/v1/volumes?q=isbn:$isbn"
    val result: JsValue = Json.parse(scala.io.Source.fromURL(url).mkString)
    result \\ "thumbnail" match {
      case List() => ""
      case _ => (result \\ "thumbnail").head.as[String]
    }

  }
}
