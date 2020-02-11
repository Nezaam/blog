package com.nezaam.blog.repository

import cats.Applicative
import cats.implicits._
import com.nezaam.blog.models.{ Comment, Post }
import org.joda.time.DateTime

trait Comments[F[_]] {
  def get: F[Comment]
}

object Comments {
  def apply[F[_]](implicit ev: Comments[F]): Comments[F] = ev

  def impl[F[_]: Applicative]: Comments[F] = new Comments[F] {
    override def get: F[Comment] = Comment(1, DateTime.now(), DateTime.now(), "", "", Post(0, "Some title", "Nezaam", DateTime.now(), None, "Some text", List(), List())).pure[F]
  }
}
