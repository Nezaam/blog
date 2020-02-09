package com.nezaam.blog

import cats.implicits._
import cats.effect.{ ConcurrentEffect, ContextShift, Timer }
import com.nezaam.blog
import com.nezaam.blog.routes.{ Comments, Posts }
import fs2.Stream
import org.http4s.implicits._
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.server.middleware.Logger

object BlogServer {

  def stream[F[_]: ConcurrentEffect](implicit T: Timer[F], C: ContextShift[F]): Stream[F, Nothing] = {
    val postAlg = Posts.impl[F]
    val commentAlg = Comments.impl[F]

    val httpApp = (
      BlogRoutes.postRoutes[F](postAlg) <+>
      BlogRoutes.commentsRoutes[F](commentAlg)
    ).orNotFound

    val finalHttpApp = Logger.httpApp(logHeaders = true, logBody = true)(httpApp)
    for {
      exitCode ← BlazeServerBuilder[F]
        .bindHttp(8080, "0.0.0.0")
        .withHttpApp(finalHttpApp)
        .serve
    } yield exitCode
  }.drain
}