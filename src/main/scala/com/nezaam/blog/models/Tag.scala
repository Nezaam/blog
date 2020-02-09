package com.nezaam.blog.models

import cats.Applicative
import cats.effect.Sync
import io.circe.{ Decoder, Encoder }
import io.circe.generic.semiauto._
import org.http4s.circe._
import org.http4s.{ EntityDecoder, EntityEncoder }

final case class Tag(
  id: Long,
  name: String,
  posts: Seq[Post]
)

object Tag {
  lazy implicit val tagDecoder: Decoder[Tag] = deriveDecoder[Tag]
  implicit def tagEntityDecoder[F[_]: Sync]: EntityDecoder[F, Tag] = jsonOf

  lazy implicit val tagEncoder: Encoder[Tag] = deriveEncoder[Tag]
  implicit def tagEntityEncoder[F[_]: Applicative]: EntityEncoder[F, Tag] = jsonEncoderOf
}
