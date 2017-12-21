package schemes

object CataApp {

  import BinaryTrees._

  def main(args: Array[String]): Unit = {
    val tree: MyBinaryTree[Int] = MyBinaryTree.node[Int](
      MyBinaryTree.node[Int](
        MyBinaryTree.node[Int](MyBinaryTree.leaf, 2, MyBinaryTree.node[Int](MyBinaryTree.leaf, 1, MyBinaryTree.leaf)), -3, MyBinaryTree.leaf))

    val alg: FAlgebra.FAlgebra[MyBinaryTreeF[Int, ?], Int] = {
      case Leaf => 0
      case Node(left, value, right) => left + value + right
    }

    val ciccio: Int = Catamorphism.cata(alg)(tree)

  }

}
