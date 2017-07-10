import doobie.imports._
import cats._, cats.data._, cats.implicits._
import fs2.interop.cats._

object HelloSQL extends App {
	val xa = DriverManagerTransactor[IOLite](
		"org.postgresql.Driver", "jdbc:postgresql:world", "postgres", ""
	)

	val prog2 = sql"select 42".query[Int].unique
	val task2 = prog2.transact(xa)

	print(task2.unsafePerformIO)
}