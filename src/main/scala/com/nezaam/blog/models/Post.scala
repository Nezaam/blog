package com.nezaam.blog.models

import cats.Applicative
import cats.effect.Sync
import io.circe.{ Decoder, Encoder }
import io.circe.generic.semiauto._
import org.http4s.{ EntityDecoder, EntityEncoder }
import org.http4s.circe._
import org.joda.time.DateTime

final case class Post(
  id: Long,
  title: String,
  author: String,
  dateTime: DateTime,
  modifiedDateTime: Option[DateTime],
  text: String,
  tags: Seq[Tag],
  comments: Seq[Comment]
)

object Post extends DateJsonFormatter {

  lazy implicit val postDecoder: Decoder[Post] = deriveDecoder[Post]
  implicit def postEntityDecoder[F[_]: Sync]: EntityDecoder[F, Post] = jsonOf

  lazy implicit val postEncoder: Encoder[Post] = deriveEncoder[Post]
  implicit def postEntityEncoder[F[_]: Applicative]: EntityEncoder[F, Post] = jsonEncoderOf
}
