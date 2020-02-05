val Http4sVersion = "0.20.17"
val CirceVersion = "0.11.1"
val Specs2Version = "4.8.2"
val LogbackVersion = "1.2.3"

lazy val root = (project in file("."))
  .settings(
    organization := "com.nezaam",
    name := "blog",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.12.10",
    libraryDependencies ++= Seq(
      "org.http4s"      %% "http4s-blaze-server" % Http4sVersion,
      "org.http4s"      %% "http4s-circe"        % Http4sVersion,
      "org.http4s"      %% "http4s-dsl"          % Http4sVersion,
      "io.circe"        %% "circe-generic"       % CirceVersion,
      "org.specs2"      %% "specs2-core"         % Specs2Version % "test",
      "org.specs2"      %% "specs2-matcher-extra"         % Specs2Version % "test",
      "ch.qos.logback"  %  "logback-classic"     % LogbackVersion,
      "joda-time"       %  "joda-time"           % "2.10",
      "org.joda"        %  "joda-convert"        % "2.1.1"
    ),
    addCompilerPlugin("org.typelevel" %% "kind-projector"     % "0.10.3"),
    addCompilerPlugin("com.olegpy"    %% "better-monadic-for" % "0.3.0")
  )

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-language:higherKinds",
  "-language:postfixOps",
  "-feature",
  "-Ypartial-unification",
  "-Xfatal-warnings",
)

import scalariform.formatter.preferences._

scalariformPreferences := scalariformPreferences.value
  .setPreference(DoubleIndentConstructorArguments, false)
  .setPreference(DanglingCloseParenthesis, Force)
  .setPreference(MultilineScaladocCommentsStartOnFirstLine, true)
  .setPreference(PlaceScaladocAsterisksBeneathSecondAsterisk, true)
  .setPreference(RewriteArrowSymbols, false)
  .setPreference(SpacesWithinPatternBinders, true)
  .setPreference(SpacesAroundMultiImports, true)
  .setPreference(FormatXml, false)
  .setPreference(IndentPackageBlocks, true)
  .setPreference(IndentLocalDefs, true)
