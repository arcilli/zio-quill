resolvers += Classpaths.sbtPluginReleases

resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

addDependencyTreePlugin

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.9.2")

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "5.2.4")

addSbtPlugin("com.github.sbt" % "sbt-release" % "1.1.0")

addSbtPlugin("com.jsuereth" % "sbt-pgp" % "2.0.1")

addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "3.9.14")

addSbtPlugin("com.typesafe" % "sbt-mima-plugin" % "0.8.1")

addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.8.3")

addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.9.0")

addSbtPlugin("org.portable-scala" % "sbt-scalajs-crossproject" % "1.2.0")

addSbtPlugin("com.etsy" % "sbt-compile-quick-plugin" % "1.4.0")

addSbtPlugin("dev.zio" % "zio-sbt-website" % "0.0.0+80-e5b408eb-SNAPSHOT")

resolvers += Resolver.sonatypeRepo("public")
