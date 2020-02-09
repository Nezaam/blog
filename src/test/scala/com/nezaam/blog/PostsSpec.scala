package com.nezaam.blog

import cats.effect.IO
import com.nezaam.blog.routes.Posts
import org.http4s._
import org.http4s.implicits._
import org.specs2.matcher._

class PostsSpec extends org.specs2.mutable.Specification with JsonMatchers {

  "Topic" >> {
    "return 200" >> {
      uriReturns200()
    }
    "return json with filed \"title\":\"Some title\"" >> {
      uriReturnsTopic()
    }
  }

  private[this] val retTopics: Response[IO] = {
    val getHW = Request[IO](Method.GET, uri"/topic")
    val topics = Posts.impl[IO]
    BlogRoutes.postRoutes(topics).orNotFound(getHW).unsafeRunSync()
  }

  private[this] def uriReturns200(): MatchResult[Status] =
    retTopics.status must beEqualTo(Status.Ok)

  private[this] def uriReturnsTopic(): MatchResult[String] =
    retTopics.as[String].unsafeRunSync() must / ("title" → "Some title")
}