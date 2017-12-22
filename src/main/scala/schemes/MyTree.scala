package schemes

import functor.Functor
object MyTree {

  sealed trait     MyBinaryTreeF[+A, +T]
  case object      Leaf                                       extends MyBinaryTreeF[Nothing, Nothing]
  final case class Node[+A, +T](left: T, value: A, right: T)  extends MyBinaryTreeF[A, T]


  implicit def functor[T]: Functor[MyBinaryTreeF[T, ?]] = new Functor[MyBinaryTreeF[T, ?]] {
    override def map[A, B](fa: MyBinaryTreeF[T, A])(f: A => B): MyBinaryTreeF[T, B] = fa match {
      case Leaf => Leaf
      case Node(left, value, right) => Node(f(left), value, f(right))
    }
  }

  type MyBinaryTree[A] = Fix[MyBinaryTreeF[A, ?]]

  object MyBinaryTree{
    def apply[A](f: MyBinaryTreeF[A, MyBinaryTree[A]]): MyBinaryTree[A] = ???
    def leaf[A]: MyBinaryTree[A] = apply(Leaf)
    def node[A](left: MyBinaryTree[A], value: A, right: MyBinaryTree[A]) : MyBinaryTree[A] = apply(Node(left, value, right))
  }

}


