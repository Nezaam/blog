package com.nezaam.blog.repository

import cats.Applicative
import cats.implicits._
import com.nezaam.blog.models.User
import org.joda.time.DateTime

trait Users[F[_]] {
  def get: F[User]
}

object Users {
  def apply[F[_]](implicit ev: Users[F]): Users[F] = ev

  def impl[F[_]: Applicative]: Users[F] = new Users[F] {
    override def get: F[User] = User(1, "Test user", "password", "email", DateTime.now()).pure[F]
  }
}
