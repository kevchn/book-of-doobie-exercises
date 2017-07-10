import doobie.imports._
import cats._, cats.data._, cats.implicits._
import fs2.interop.cats._

object ForSQL extends App {
	val xa = DriverManagerTransactor[IOLite](
		"org.postgresql.Driver", "jdbc:postgresql:world", "postgres", ""
	)

	val prog =
		for {
			a <- sql"select 42".query[Int].unique
			b <- sql"select random()".query[Double].unique
		} yield (a, b)

	println(prog.transact(xa).unsafePerformIO)
}