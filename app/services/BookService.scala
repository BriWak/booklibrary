package services

import models.Book

class BookService {
  def books = List(
    Book("Jurrasic Park", "Michael Crichton", "Science Fiction", 1784752223),
    Book("The Shining", "Stephen King", "Thriller", 1444720724),
    Book("The Dead Zone", "Stephen King", "Thriller", 1444708090))
}
