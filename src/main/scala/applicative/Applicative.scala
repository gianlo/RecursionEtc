package applicative

import scala.language.higherKinds

trait Applicative[F[_]] {
  def pure[A](a: A): F[A]
  def ap[A, B](fa: F[A])(f: F[A => B]): F[B]

  def map[A, B](fa: F[A])(f: A => B): F[B] = ap(fa)(pure(f))
  def product[A, B](fa: F[A], fb: F[B]): F[(A, B)] = ap(fa)(map(fb)(b => a => (a,b)))
  def map2[A, B, C](fa: F[A], fb: F[B])(f: ((A, B)) => C) : F[C] = ap(product(fa, fb))(pure(f))
}


object Applicative {
  implicit val optionInstance = new Applicative[Option] {
    override def pure[A](a: A): Option[A] = Option(a)

    override def ap[A, B](fa: Option[A])(f: Option[A => B]): Option[B] = for {
      a <- fa
      finner <- f
    } yield finner(a)
  }

  type Id[A] = A

  implicit val idInstance = new Applicative[Id] {
    override def pure[A](a: A): Id[A] = a
    override def ap[A, B](fa: Id[A])(f: Id[A => B]): Id[B] = f(fa)
  }

}