package controllers

import com.google.inject.Inject
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}
import services.BookService

class BookController @Inject()(bookService: BookService)(cc: ControllerComponents) extends AbstractController(cc) {

  def bookList() = Action {
    val books = bookService.books

      Ok(views.html.books(books))
  }

  def bookDetails(isbn: String) = Action {
    val book = bookService.book(isbn)

    Ok(views.html.bookdetails(isbn))
  }
}
