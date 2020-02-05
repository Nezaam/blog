package com.nezaam.blog

import cats.effect.Sync
import cats.implicits._
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl

object BlogRoutes {

  def topicRoutes[F[_]: Sync](T: Topics[F]): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F] {}
    import dsl._
    HttpRoutes.of[F] {
      case GET -> Root / "topic" ⇒
        for {
          topic ← T.get
          resp ← Ok(topic)
        } yield resp
    }
  }
}