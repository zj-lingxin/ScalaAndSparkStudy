/**
 * Sets
Sets are Iterables that contain no duplicate elements. The operations on sets are summarized in the following table
for general sets and in the table after that for mutable sets. They fall into the following categories:
Tests contains, apply, subsetOf. The contains method asks whether a set contains a given element. The apply method
for a set is the same as contains, so set(elem) is the same as set contains elem. That means sets can also be used
as test functions that return true for the elements they contain.
Additions + and ++, which add one or more elements to a set, yielding a new set.
Removals -, --, which remove one or more elements from a set, yielding a new set.
Set operations for union, intersection, and set difference. Each of these operations exists in two forms: alphabetic
and symbolic. The alphabetic versions are intersect, union, and diff, whereas the symbolic versions are &, |, and &~.
In fact, the ++ that Set inherits from Traversable can be seen as yet another alias of union or |, except that ++ takes
a Traversable argument whereas union and | take sets.
 */
//Sets can be created easily:
val s = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
s.size

//Sets contain distinct values:
val s1 = Set("Michigan", "Ohio", "Wisconsin", "Iowa", "Michigan")

//Sets can be added to easily:
val s2 = s + "Lucius"

//Sets may be of mixed type:
val s3 = Set("Michigan", "Ohio", "Wisconsin", "Iowa", 12)

//Sets can be checked for member existence:
s3("Michigan")
s3("Jim")

//Set elements can be removed easily:
val s4 = s - "Iowa"
s.contains("Iowa")
s4.contains("Iowa")

//Set elements can be removed in multiple:
val s5 = s -- List("Michigan", "Ohio")

//Set elements can be removed with a tuple:
val s6 = s - ("Michigan", "Ohio")

//Attempted removal of nonexistent elements from a set is handled gracefully:
val s7 = s - "Lucius"

//Sets can be iterated easily:
val s8 = Set(0, 2, 4)
var sum = 0
for (e <- s8) {
  sum += e
}
sum

//Two sets can be intersected easily:
val s9 = Set(0, 1, 3, 5)
s8.intersect(s9)

//Two sets can be joined as their union easily:
s8.union(s9)

//A set is either a subset of another set or it isn't:
val s10 = Set(1, 5)
val s11 = Set(0, 1, 3, 5)
val s12 = Set(-1,1)
s10.subsetOf(s9)
s11.subsetOf(s9)
s12.subsetOf(s9)

//The difference between two sets can be obtained easily:
s10.diff(s9)
s11.diff(s9)
s12.diff(s9)

//Set equivalency is independent of order:
val s13 = Set(5, 1)
s10.equals(s13)


