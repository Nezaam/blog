package com.nezaam.blog.models

import cats.Applicative
import cats.effect.Sync
import io.circe.{ Decoder, Encoder }
import io.circe.generic.semiauto._
import org.http4s.{ EntityDecoder, EntityEncoder }
import org.http4s.circe._
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

import scala.util.Try

final case class Topic(
  title: String,
  author: String,
  createdDate: DateTime,
  modificationDate: Option[DateTime],
  text: String)

object Topic {
  val dateTimeFormat = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
  implicit val dateTimeFormatDecoder: Decoder[DateTime] =
    Decoder[String].emapTry(str => Try(DateTime.parse(str, dateTimeFormat)))

  implicit val jodaDateTimeEncoder: Encoder[DateTime] =
    Encoder[String].contramap(_.toString("yyyy-MM-dd'T'HH:mm:ss'Z'"))

  implicit val topicDecoder: Decoder[Topic] = deriveDecoder[Topic]
  implicit def topicEntityDecoder[F[_]: Sync]: EntityDecoder[F, Topic] = jsonOf

  implicit val topicEncoder: Encoder[Topic] = deriveEncoder[Topic]
  implicit def topicEntityEncoder[F[_]: Applicative]: EntityEncoder[F, Topic] = jsonEncoderOf
}
