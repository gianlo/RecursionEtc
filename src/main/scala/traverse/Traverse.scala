package traverse

import applicative.Applicative
import applicative.Applicative.Id

import scala.language.higherKinds

trait Traverse[F[_]] {
    def traverse[G[_] : Applicative, A, B](fa: F[A])(f: A => G[B]): G[F[B]]
    def sequence[G[_] : Applicative, A](fga: F[G[A]]): G[F[A]] = traverse(fga)(identity)

    def map[A, B](fa: F[A])(f: A => B): F[B] = traverse(fa)(x => implicitly[Applicative[Id]].pure(f(x)))
}

object Traverse{
  implicit val listTraverse = new Traverse[List] {
    override def traverse[G[_] : Applicative, A, B](fa: List[A])(f: A => G[B]): G[List[B]] = {
      val appInstance = implicitly[Applicative[G]]
      fa.foldLeft(appInstance.pure(List.empty[B]))((acc: G[List[B]], v: A) => {
        val t: G[B] = f(v)
        appInstance.map2(acc, t){ case (ll, vv) => ll :+ vv }
      })
    }
  }
}
