import doobie.imports._
import cats._, cats.data._, cats.implicits._
import fs2.interop.cats._

object HelloDatabase extends App {

	// Set up ConnectionIO program and Transactor
	// Transactors know how to setup, hand out, and cleanup DB connections.
	// With that info, transactors can transform ConnectionIO to IOLite, a
	// Doobie simple datatype that has IO.
	val prog1 = 4.pure[ConnectionIO]
	val xa = DriverManagerTransactor[IOLite](
		"org.postgresql.Driver", "jdbc:postgresql:world", "postgres", ""
	)

	val task = prog1.transact(xa)	

	println(task.unsafePerformIO)
}