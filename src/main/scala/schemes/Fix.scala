package schemes

import scala.language.higherKinds

final case class Fix[F[_]](unFix: F[Fix[F]])
