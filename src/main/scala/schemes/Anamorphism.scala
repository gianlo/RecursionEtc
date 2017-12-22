package schemes

import functor.Functor

object Anamorphism {

  import FAlgebra._

  def ana[F[_], A](coalg: FCoalgebra[F, A])(init: A)(implicit ff: Functor[F]): Fix[F] =
    Fix[F](ff.map(coalg(init))(xx => ana(coalg)(xx)))

}
