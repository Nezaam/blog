package com.nezaam.blog.models

import io.circe.{ Decoder, Encoder }
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

import scala.util.Try

trait DateJsonFormatter {
  val dateTimeFormat = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
  implicit val dateTimeFormatDecoder: Decoder[DateTime] =
    Decoder[String].emapTry(str => Try(DateTime.parse(str, dateTimeFormat)))

  implicit val jodaDateTimeEncoder: Encoder[DateTime] =
    Encoder[String].contramap(_.toString("yyyy-MM-dd'T'HH:mm:ss'Z'"))
}
