// The next trait from the top in the collections hierarchy is Iterable. All methods in this trait are defined in
// terms of an abstract method, iterator, which yields the collection's elements one by one. The foreach method from
// trait Traversable is implemented in Iterable in terms of iterator. Here is the actual implementation:
// Quite a few subclasses of Iterable override this standard implementation of foreach in Iterable, because they can
// provide a more efficient implementation. Remember that foreach is the basis of the implementation of all operations
// in Traversable, so its performance matters.
// Some known iterators are Sets, Lists, Vectors, Stacks, and Streams. Iterator has two important methods: hasNext,
// which answers whether the iterator has another element available. next which will return the next element in
// the iterator.
val list = List(3, 5, 9, 11, 15, 19, 21)
val it = list.iterator
if (it.hasNext) {
  println(it.next())
}

//grouped will return an fixed sized Iterable chucks of an Iterable
val list2 = List(3, 5, 9, 11, 15, 19, 21, 24, 32)
val it2 = list grouped 3
it2.next() //List(3, 5, 9)
it2.next() //List(11, 15, 19)
//sliding will return an Iterable that shows a sliding window of an Iterable.
val list3 = List(3, 5, 9, 11, 15, 19, 21, 24, 32)
val it3 = list3 sliding 3
it3.next() //List(3, 5, 9)
it3.next() //List(5, 9, 11)
//sliding can take the size of the window as well the size of the step during each iteration
val list4 = List(3, 5, 9, 11, 15, 19, 21, 24, 32)
val it4 = list4 sliding(3, 2)
it4.next() //List(3, 5, 9)
it4.next() //List(9, 11, 15)
//takeRight is the opposite of 'take' in Traversable. It retrieves the last elements of an Iterable.
val list5 = List(3, 5, 9, 11, 15, 19, 21, 24, 32)
list5 takeRight 3 // List(21, 24, 32)

//dropRight will drop the number of elements from the right.
val list6 = List(3, 5, 9, 11, 15, 19, 21, 24, 32)
list6 dropRight 3 //List(3, 5, 9, 11, 15, 19)
//zip will stitch two iterables into an iterable of pairs of corresponding elements from both iterables.
//E.g. Iterable(x1, x2, x3) zip Iterable(y1, y2, y3) will return ((x1,y1), (x2, y2), (x3, y3)):

val xs = List(3, 5, 9)
val ys = List("Bob", "Ann", "Stella")
xs zip ys
//If two Iterables aren't the same size, then zip will only zip what can only be paired.
//E.g. Iterable(x1, x2, x3) zip Iterable(y1, y2) will return ((x1,y1), (x2, y2))
val xs2 = List(3, 5, 9)
val ys2 = List("Bob", "Ann")
xs2 zip ys2
//If two Iterables aren't the same size, then zipAll can provide fillers for what it couldn't find a complement for:
//E.g. Iterable(x1, x2, x3) zipAll (Iterable(y1, y2), x, y) will return ((x1,y1), (x2, y2, y))
val xs3 = List(3)
val ys3 = List("Bob", "Ann","Ani")
xs3 zipAll(ys3, -1, "?")
//zipWithIndex will zip an Iterable with it's integer index
val xs4 = List("Manny", "Moe", "Jack")
xs4.zipWithIndex
//sameElements will return true if the two iterables produce the same elements in the same order:
val xs5 = List("Manny", "Moe", "Jack")
val ys5 = List("Manny", "Moe", "Jack")
(xs5 sameElements ys5)

val xs6 = List("Manny", "Moe", "Jack")
val ys6 = List("Manny", "Jack", "Moe")
(xs6 sameElements ys6)

val xs7 = Set(3, 2, 1, 4, 5, 6, 7)
val ys7 = Set(7, 2, 1, 4, 5, 6, 3)
(xs7 sameElements ys7)

val xs8 = Set(1, 2, 3)
val ys8 = Set(3, 2, 1)
(xs8 sameElements ys8)