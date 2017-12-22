package schemes

object AnaApp {

  import FAlgebra._
  import MyTree._

  def main(args: Array[String]): Unit = {

    val coalg: FCoalgebra[MyBinaryTreeF[Int, ?], List[Int]] = {
      case p :: tail if p >= 0 => Node(Nil, p, tail)
      case n :: tail if n < 0 => Node(tail, n , Nil)
      case Nil => Leaf
    }

    val values = List(-1, 5, 1,-2, -3, 1)

    val tree: MyBinaryTree[Int] = Anamorphism.ana(coalg)(values)
    println(tree)

    val sumOfNodeValues = Catamorphism.cata[MyBinaryTreeF[Int, ?], Int](CataApp.alg)(tree)
    println(sumOfNodeValues)

    assert(sumOfNodeValues == values.sum)

  }
}
