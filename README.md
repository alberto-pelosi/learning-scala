Notes about [Scala with Cats](https://underscore.io/books/scala-with-cats/)

# Monoids

* A **Monoid** is an algebric structure with a binary operation and an identity element

* In computer science, a Monoid for a Type A is:
  * a combine operation (A, A) => A, **associative**
  * an identity element, empty, of type A
  
* In cats Monoids are Type Classes:

`
trait Semigroup[A] {
	def combine(x: A, y: A): A
}
trait Monoid[A] extends Semigroup[A] {
	def empty: A
}
`

# Semigroups

* A **Semigroup** is just the combine part of a Monoid. A Semigroup is a Monoid without identity element

# Functors

* A **Functor** is an abstraction that allows to represent sequence of operations on contexts like List, Option, Future, etc...
* Functors on their own are not so useful, but special cases like **Monads** and Applicative Functors are commonly used in Cats
