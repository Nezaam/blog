package com.nezaam.blog.models

import cats.Applicative
import cats.effect.Sync
import io.circe.{ Decoder, Encoder }
import io.circe.generic.semiauto._
import org.http4s.{ EntityDecoder, EntityEncoder }
import org.http4s.circe._
import org.joda.time.DateTime

final case class User(
  id: Long,
  name: String,
  password: String,
  email: String,
  dateTime: DateTime
)

object User extends DateJsonFormatter {
  implicit val userDecoder: Decoder[User] = deriveDecoder[User]
  implicit def userEntityDecoder[F[_]: Sync]: EntityDecoder[F, User] = jsonOf

  implicit val userEncoder: Encoder[User] = deriveEncoder[User]
  implicit def userEntityEncoder[F[_]: Applicative]: EntityEncoder[F, User] = jsonEncoderOf
}