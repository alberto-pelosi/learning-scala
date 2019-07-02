def show[A](list: List[A]): String = list.foldLeft("nil")((accum, item)=> s"$item then $accum")

show(Nil)

show(List(1, 2, 3))



List(1, 2, 3).foldLeft(0)(_ - _)
List(1, 2, 3).foldRight(0)(_ - _)
//3 - 0 = 3: 2-3 = -1; 1- (-1) = 2

