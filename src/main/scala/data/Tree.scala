package data

import applicative.Applicative
import traverse.Traverse

import scala.language.higherKinds


sealed trait Tree[+A] {
  def fold[B](z: B)(f: (B, A) => B): B = this match {
    case EmptyTree => z
    case Node(h, l) => {
      val next = f(z, h)
      l.foldLeft(next){ case (acc, v) => v.fold(acc)(f) }
    }
  }
}

case object EmptyTree extends Tree[Nothing]
final case class Node[+A](value: A, children: List[Tree[A]]) extends Tree[A]

object Tree {
  def apply[A](a: A): Tree[A] = Node(a, Nil)
  def empty[A]: Tree[A] = EmptyTree
}

object TreeInstances {
  implicit val traversableInstance = new Traverse[Tree] {
    override def traverse[G[_] : Applicative, A, B](fa: Tree[A])(f: A => G[B]): G[Tree[B]] = {
      val appInstance = implicitly[Applicative[G]]
      fa.fold(appInstance.pure(Tree.empty[B])){ case (acc: G[Tree[B]], v:A) => {
        val t: G[B] = f(v)
        appInstance.map2(acc, t) { case (ll, vv) => ll.fold(Tree.empty[B]){ case (tree, n) => ??? }}
      }}
    }
  }
}
