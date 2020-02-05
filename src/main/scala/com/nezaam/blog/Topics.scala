package com.nezaam.blog

import cats.Applicative
import cats.implicits._
import com.nezaam.blog.models.Topic
import org.joda.time.DateTime

trait Topics[F[_]] {
  def get: F[Topic]
}

object Topics {
  def apply[F[_]](implicit ev: Topics[F]): Topics[F] = ev

  def impl[F[_]: Applicative]: Topics[F] = new Topics[F] {
    def get: F[Topic] = Topic("Some title", "Nezaam", DateTime.now(), None, "Some text").pure[F]
  }
}
