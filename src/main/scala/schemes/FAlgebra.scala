package schemes

import scala.language.higherKinds

object FAlgebra {
  type FAlgebra[F[_], A] = F[A] => A
  type FCoalgebra[F[_], A] = A => F[A]
}
