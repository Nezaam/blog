package com.nezaam.blog.models

import cats.Applicative
import cats.effect.Sync
import io.circe.{ Decoder, Encoder }
import io.circe.generic.semiauto._
import org.http4s.circe._
import org.http4s.{ EntityDecoder, EntityEncoder }
import org.joda.time.DateTime

final case class Comment(
  id: Long,
  dateTime: DateTime,
  modifiedDateTime: DateTime,
  name: String,
  text: String,
  post: Post
)

object Comment extends DateJsonFormatter {
  lazy implicit val commentDecoder: Decoder[Comment] = deriveDecoder[Comment]
  implicit def commentEntityDecoder[F[_]: Sync]: EntityDecoder[F, Comment] = jsonOf

  lazy implicit val commentEncoder: Encoder[Comment] = deriveEncoder[Comment]
  implicit def commentEntityEncoder[F[_]: Applicative]: EntityEncoder[F, Comment] = jsonEncoderOf
}
