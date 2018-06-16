package services
import connectors._
import models.Book


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
   val connector = new BookDetailsConnector
   connector.getDetails(isbn, "thumbnail")
  }

  def getDescription(isbn: String): String = {
    val connector = new BookDetailsConnector
    connector.getDetails(isbn, "description",
      "Sorry the description for this book is unavailable")
  }
}
