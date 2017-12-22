package schemes

object CataApp {

  import MyTree._
  import FAlgebra._
  import MyBinaryTree._
  val alg: FAlgebra[MyBinaryTreeF[Int, ?], Int] = {
    case Leaf => 0
    case Node(left, value, right) => left + value + right
  }

  def main(args: Array[String]): Unit = {

    val tree: MyBinaryTree[Int] = node(node(node(leaf, 2, node(leaf, 1, leaf)), -3, leaf), 2, leaf)


    val sumOfNodeValues = Catamorphism.cata[MyBinaryTreeF[Int, ?], Int](alg)(tree)


    println(tree)
    println(sumOfNodeValues)

    assert(sumOfNodeValues == 2)
  }
}
