package com.nezaam.blog

import cats.effect.{ ExitCode, IO, IOApp }
import cats.implicits._

object Main extends IOApp {
  def run(args: List[String]): IO[ExitCode] =
    BlogServer.stream[IO].compile.drain.as(ExitCode.Success)
}