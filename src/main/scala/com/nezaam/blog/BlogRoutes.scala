package com.nezaam.blog

import cats.effect.Sync
import cats.implicits._
import com.nezaam.blog.routes.{ Comments, Posts }
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl

object BlogRoutes {

  def postRoutes[F[_]: Sync](T: Posts[F]): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F] {}
    import dsl._
    HttpRoutes.of[F] {
      case GET -> Root / "post" ⇒
        for {
          topic ← T.get
          resp ← Ok(topic)
        } yield resp
    }
  }

  def commentsRoutes[F[_]: Sync](T: Comments[F]): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F] {}
    import dsl._
    HttpRoutes.of[F] {
      case GET -> Root / "comments" ⇒
        for {
          comment ← T.get
          resp ← Ok(comment)
        } yield resp
    }
  }
}