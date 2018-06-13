package controllers

import com.google.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}
import services.BookService

class BookController @Inject()(bookService: BookService)(cc: ControllerComponents) extends AbstractController(cc) {

  def bookList() = Action {
    val books = bookService.books

      Ok(views.html.books(books))

  }
}
