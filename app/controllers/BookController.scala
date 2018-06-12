package controllers

import com.google.inject.Inject
import play.api.mvc.{AbstractController, Action, Controller, ControllerComponents}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.BookService

import scala.concurrent.Future

class BookController @Inject()(theBooks: BookService)(cc: ControllerComponents) extends AbstractController(cc) {

  def bookList() = Action {
    val books = theBooks.books

      Ok(views.html.books(books))

  }
}
