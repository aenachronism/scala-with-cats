import scala.sys.process._

name              in ThisBuild := "scala-with-cats"
organization      in ThisBuild := "io.underscore"
version           in ThisBuild := "0.0.1"

scalaVersion      in ThisBuild := "2.13.1"

useSuperShell     in ThisBuild := false
logLevel          in Global    := Level.Warn

enablePlugins(MdocPlugin)
mdocIn  := sourceDirectory.value / "pages"
mdocOut := target.value          / "pages"

scalacOptions ++= Seq(
//   "-deprecation",
//   "-encoding", "UTF-8",
//   "-unchecked",
//   "-feature",
//   "-Xlint",
//   "-Xfatal-warnings",
//   "-Ywarn-dead-code",
  "-Yrepl-class-based"
)

// resolvers ++= Seq(Resolver.sonatypeRepo("snapshots"))

libraryDependencies ++= Seq("org.typelevel" %% "cats-core" % "2.0.0")

addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full)

lazy val pdf  = taskKey[Unit]("Build the PDF version of the book")
lazy val html = taskKey[Unit]("Build the HTML version of the book")
lazy val epub = taskKey[Unit]("Build the ePub version of the book")
lazy val json = taskKey[Unit]("Build the Pandoc JSON AST of the book")
lazy val all  = taskKey[Unit]("Build all versions of the book")

pdf  := { mdoc.toTask("").value ; "grunt pdf"  ! }
html := { mdoc.toTask("").value ; "grunt html" ! }
epub := { mdoc.toTask("").value ; "grunt epub" ! }
json := { mdoc.toTask("").value ; "grunt json" ! }
all  := { pdf.value ; html.value ; epub.value ; json.value }
