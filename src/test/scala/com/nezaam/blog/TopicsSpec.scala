package com.nezaam.blog

import cats.effect.IO
import org.http4s._
import org.http4s.implicits._
import org.specs2.matcher._

class TopicsSpec extends org.specs2.mutable.Specification with JsonMatchers {

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
    val topics = Topics.impl[IO]
    BlogRoutes.topicRoutes(topics).orNotFound(getHW).unsafeRunSync()
  }

  private[this] def uriReturns200(): MatchResult[Status] =
    retTopics.status must beEqualTo(Status.Ok)

  private[this] def uriReturnsTopic(): MatchResult[String] =
    retTopics.as[String].unsafeRunSync() must / ("title" → "Some title")
}