---
id: getting-started
title: "Getting Started with Quill"
sidebar_label: "Getting Started"
---

> ### [Scastie](https://scastie.scala-lang.org/) is a great tool to try out Quill without having to prepare a local environment. It works with [mirror contexts](contexts.md#mirror-context), see [this](https://scastie.scala-lang.org/QwOewNEiR3mFlKIM7v900A) snippet as an example.

Quill has integrations with many libraries. If you are using a regular RDBMS e.g. PostgreSQL
and want to use Quill to query it with an asychronous, non-blocking, reactive application, the easiest way to get
started is by using an awesome library called ZIO.

A simple ZIO + Quill application looks like this:
```scala
case class Person(name: String, age: Int)

class DataService(quill: Quill.Postgres[SnakeCase]) {
  import quill._
  def getPeople: ZIO[Any, SQLException, List[Person]] = run(query[Person])
}
object DataService {
  def getPeople: ZIO[DataService, SQLException, List[Person]] =
    ZIO.serviceWithZIO[DataService](_.getPeople)

  val live = ZLayer.fromFunction(new DataService(_))
}
object Main extends ZIOAppDefault {
  override def run = {
    DataService.getPeople
      .provide(
        DataService.live,
        Quill.Postgres.fromNamingStrategy(SnakeCase),
        Quill.DataSource.fromPrefix("myDatabaseConfig")
      )
      .debug("Results")
      .exitCode
  }
}
```

Add the following to build.sbt:

```scala
libraryDependencies ++= Seq(
  "io.getquill"          %% "quill-jdbc-zio" % "@VERSION@",
  "org.postgresql"       %  "postgresql"     % "42.3.1"
)
```

You can find this code (with some more examples) complete with a docker-provided Postgres database [here](https://github.com/deusaquilus/zio-quill-gettingstarted).
A veriety of other examples using Quill with ZIO are available in the [examples](https://github.com/zio/zio-quill/tree/master/quill-jdbc-zio/src/test/scala/io/getquill/examples) folder.

## Choosing a Module

Choose the quill module that works for you!

* If you are starting from scratch with a regular RDBMS try using the `quill-jdbc-zio` module as shown above.
* If you are developing a legacy Java project and don't want/need reactive, use `quill-jdbc`.
* If you are developing a project with Cats and/or Monix, try `quill-jdbc-monix`.
* If you like to "live dangerously" and want to try a socket-level async library, try `quill-jasync-postgres`
  or `quill-jasync-mysql`.
* If you are using Cassandra, Spark, or OrientDB, try the corresponding modules for each of them.
