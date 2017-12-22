package schemes

import scala.language.higherKinds

object FAlgebra {
  type FAlgebra[F[_], A] = F[A] => A
}
