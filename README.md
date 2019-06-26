Notes about [Scala with Cats](https://underscore.io/books/scala-with-cats/)

# Monoids

* A **Monoid** is an algebric structure with a binary operation and an identity element

* In computer science, a Monoid for a Type A is:
  * a combine operation (A, A) => A, **associative**
  * an identity element, empty, of type A
  
* In cats Monoids are Type Classes:

```scala
trait Semigroup[A] {
	def combine(x: A, y: A): A
}
trait Monoid[A] extends Semigroup[A] {
	def empty: A
}
```

# Semigroups

* A **Semigroup** is just the combine part of a Monoid. A Semigroup is a Monoid without identity element

# Functors

* A **Functor** is an abstraction that allows to represent sequence of operations on contexts like List, Option, Future, etc...
* Functors on their own are not so useful, but special cases like **Monads** and Applicative Functors are commonly used in Cats
* A Functor is a class that encapsulates sequences computation. Formally, a functor is a type **F[A]** with an operation map with type *(A => B) => F[B]*

```scala
trait Functor[F[_]] {
	def map[A, B](fa: F[A])(f: A => B): F[B]
}

```
## Functor Laws

Functors guarantee the same semantic whether we sequence many small operations one by one, or combine them into a large, unique, function.
To ensure this, they follow this rules:

  * **Identity**: calling map with the identity function is the same as doing nothing
  
  ```scala
  fa.map(a => a) == fa
  ```
  * **Composition**: mapping with two functions f and g is the same as mapping with f and then mappung with g:
  
  ```scala
  fa.map(g(f(_))) == fa.map(f).map(g)
  ```
* Higher Kinds and Type Constructor

Kinds are types for types. They describe the number of "holes" in a type. **Regular Types** have no holes, and Type Constructor have holes we can fill to produce types.

For example List is a type constructor with one hole. If we fill it with Int, we have a regular type or List[A].

The trick is not to confuse type constructor with generic types. List is a type constructor, List[A] is a type.

```scala
List // type constructor, takes one parameter
List[A] // type, produced using a type parameter
```

In analogy, **Functions** are value constructor, they produce values when we supply parameters.

In scala **types constructors** are **defined** using **undescores**.

```scala
// Declare F using underscores:
def myMethod[F[_]] = {
// Reference F without underscores:
val functor = Functor.apply[F]
// ...
}
```

This is analogous to specifiyng a function's parameters in its definition and omitting them when referring to it:

```scala
// Declare f specifying parameters:
val f = (x: Int) => x * 2
// Reference f without parameters:
val f2 = f andThen f
```

**Higher Kinds** needs 

```scala
import scala.language.higherKinds
```

  
