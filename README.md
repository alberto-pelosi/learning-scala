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
  * **Composition**: mapping with two functions f and g is the same as mapping with f and then mapping with g:
  
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

## Controvariant Functors
A Functor map method append a transformation to a chain.

A *Controvariant Functor* prepend a transformation to a chain, via the *contramap* method.

**F[B] contramap(A => B) F[A]**

An *Invariant Functor* implements a method called *imap* that is equivalent to a combination of a map and a contramap

**F[A] imap(A => B; B => A) F[B]**

If B is a subtype of A, we can always convert a B to an A.
Equivalently we could say that B is a subtype of A if exists a function A => B.
**So, a covariant functors capture this, if F is a covariant function, if we have a F[A] and a conversion B => A, we can convert to F[B]**

A **controvariant functor** captures the opposite case. 
**If F is a controvariant functor, if we have a F[A] and a conversion B => A we can convert to B.**

**Invariant functors** capture the case where **we can convert from F[A] to F[B] via a function A => V and viceversa via a function B => A.**


**Summary**

**Functors** represent **sequencing behaviours**.


* **Regular covariant Functors**, with their map method, represent the ability
  to apply func􀦞ons to a value in some context. Successive calls to
  map apply these func􀦞ons in sequence, each accep􀦞ng the result of its
  predecessor as a parameter.

* **Contravariant functors**, with their contramap method, represent the
 ability to “prepend” func􀦞ons to a func􀦞on-like context. Successive
 calls to contramap sequence these func􀦞ons in the opposite order to
 map.

* **Invariant functors**, with their imap method, represent bidirec􀦞onal
  transforma􀦞ons.
  
  
In brief, given a function on a category, A => B, a covariant functor maps F[A] => F[B].
Given a function on a category, B => A, a covariant functor maps F[A] => F[B]
Given a pair of functions on a category, A => B, B => A, an invariant functor maps F[A] => F[B]

# Monads

A **Monad** is anything with a constructor and a flatMap

In Cats:

```scala
import scala.language.higherKinds
trait Monad[F[_]] {
  def pure[A](value: A): F[A]
  def flatMap[A, B](value: F[A])(func: A => F[B]): F[B]
}
```

**Monad Laws**

* **Left identity**: calling pure and transforming the result with funct is the same as calling func:

```scala
pure(a).flatMap(func) == func(a)
```

* **Right identity**: passing pure to flatMap is the same as doing nothing

```scala
m.flatMap(pure) == m
```

* **Associativity**: flatMapping over two funcgtions f and g is the same as flatMapping over f and then flatMapping over g:

```scala
m.flatMap(f).flatMap(g) == m.flatMap(x => f(x).flatMap(g))
```

Every monad is also a functor, so:

```scala
import scala.language.higherKinds
trait Monad[F[_]] {
  def pure[A](value: A): F[A]
  def flatMap[A, B](value: F[A])(func: A => F[B]): F[B]
  def map[A, B](value: F[A])(func: A => B): F[B] = flatMap(value)(a => pure(func(a)))
}
```

**Eval: Eager vs Lazy vs Memoized**

**Eager computations** happen immediately, **Lazy computations** happen on access, **Memoized computations** are run once on first access, after wich the results are cached.

**val** is **eager**, **def** is **lazy**, **lazy val** is **lazy and memoized**

* Scala val = Cats Now (eager, memoized)
* Scala def = Cats Always (lazy, not memoized)
* Scala lazy val = Cats Later (lazy, memoized)

# Semigroupal

**Semigroupal** combines two context.

```scala
trait Semigroupal[F[_]] {
  def product[A, B](fa: F[A], fb: F[B]): F[(A, B)]
}
```
# Applicative
```scala
trait Apply[F[_]] extends Semigroupal[F] with Functor[F] {
  def ap[A, B](ff: F[A => B])(fa: F[A]): F[B]
  def product[A, B](fa: F[A], fb: F[B]): F[(A, B)] = ap(map(fa)(a => (b: B) => (a, b)))(fb)
}

trait Applicative[F[_]] extends Apply[F] {
  def pure[A](a: A): F[A]
}
```


Semigroupal and Applicative functors are usually used for validation rules




