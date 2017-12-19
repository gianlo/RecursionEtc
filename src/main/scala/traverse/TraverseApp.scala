package traverse

import data.Node

object TraverseApp {
  import Traverse._

  val listTraverse = implicitly[Traverse[List]]

  def main(args: Array[String]): Unit = {
    val ll = List(1, 2, -3)
    val res = listTraverse.traverse(ll)(n => if (n > 2) None else Some(n))
    println(res)

    val gll = ll.map(Option(_)) :+ None
    val res2 = listTraverse.sequence(gll)
    println(res2)

    val res3 = listTraverse.traverse(ll)(x => List(x - 1, x + 1))
    println(res3)

    val tree = Node(1, List(Node(10, List(Node(100, Nil))), Node(20, Nil)))
    assert(tree.fold(0)(_ + _) == 131)
    assert(tree.fold(1)(_ * _) == 20000)
  }


}
