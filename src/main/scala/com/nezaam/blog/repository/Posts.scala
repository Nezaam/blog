package com.nezaam.blog.repository

import cats.Applicative
import cats.implicits._
import com.nezaam.blog.models.Post
import org.joda.time.DateTime

trait Posts[F[_]] {
  def get: F[Post]
}

object Posts {
  def apply[F[_]](implicit ev: Posts[F]): Posts[F] = ev

  def impl[F[_]: Applicative]: Posts[F] = new Posts[F] {
    def get: F[Post] = Post(0, "Some title", "Nezaam", DateTime.now(), None, "Some text", List(), List()).pure[F]
  }
}
