package connectors

import play.api.libs.json.{JsValue, Json}

trait Connector {

  def getDetails(isbn: String, category: String, notFoundMessage: String = ""): String
}

class BookDetailsConnector extends Connector {

  def getDetails(isbn: String, category: String, notFoundMessage: String = ""): String = {
    val url = s"https://www.googleapis.com/books/v1/volumes?q=isbn:$isbn"
    val result: JsValue = Json.parse(scala.io.Source.fromURL(url).mkString)
    result \\ category match {
      case List() => notFoundMessage
      case _ => (result \\ category).head.as[String]
    }

  }
}
