package schemes

import functor.Functor

import scala.language.higherKinds

object Catamorphism {

  import FAlgebra._

  def cata[F[_], A](alg: FAlgebra[F, A])(data: Fix[F])(implicit ff: Functor[F]): A =
    alg(ff.map(data.unFix)(xx => cata(alg)(xx)))

}
