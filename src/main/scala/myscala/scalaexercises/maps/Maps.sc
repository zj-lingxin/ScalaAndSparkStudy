/**
A Map is an Iterable consisting of pairs of keys and values (also named mappings or associations).
Scala’s Predef class offers an implicit conversion that lets you write key -> value as an alternate
syntax for the pair (key, value). For instance Map("x" -> 24, "y" -> 25, "z" -> 26) means exactly the
same as Map(("x", 24), ("y", 25), ("z", 26)), but reads better.
The fundamental operations on maps are similar to those on sets. They are summarized in the following
table and fall into the following categories:
1、Lookup operations apply, get, getOrElse, contains, and isDefinedAt. These turn maps into partial
  functions from keys to values. The fundamental lookup method for a map is: def get(key): Option[Value].
  The operation "m get key" tests whether the map contains an association for the given key. If so, it
  returns the associated value in a Some. If no key is defined in the map, get returns None. Maps also
  define an apply method that returns the value associated with a given key directly, without wrapping it
  in an Option. If the key is not defined in the map, an exception is raised.
2、Additions and updates +, ++, updated, which let you add new bindings to a map or change existing bindings.
3、Removals -, --, which remove bindings from a map.
4、Subcollection producers keys, keySet, keysIterator, values, valuesIterator, which return a map’s keys
  and values separately in various forms.
5、Transformations filterKeys and mapValues, which produce a new map by filtering and transforming bindings
  of an existing map.
 **/
//Maps can be created easily:
val nameAgeMap = Map("lingxin" -> 23, "张三" -> 24, "王五" -> 16)

//Maps contain distinct pairings:
val nameAgeMap2 = Map("lingxin" -> 23, "张三" -> 23, "王五" -> 16, "lingxin" -> 24)

//Maps can be added to easily:
val newMap = nameAgeMap + ("xiaocai" -> 11)

//Map values can be iterated(重复的值会被过滤掉):
val mapValues = nameAgeMap2.values
val mapKeys = nameAgeMap2.keys

//Maps may be accessed:
newMap("张三")

//Map keys may be of mixed type:
val m = Map(135757 -> "lingxin", "3304XX" -> "lingxin")
m(135757)
m("3304XX")

//Map elements can be removed easily:
val m2 = m - 135757
m.contains(135757)
m2.contains(135757)

//Accessing a map by key results in an exception if key is not found:
//m2(135758)
//Map elements can be removed in multiple:
val m3 = Map("lingxin" -> 23, "张三" -> 24, "王五" -> 16, "朱六" -> 55)
val m4 = m3 -- List("lingxin", "张三")
m3
m4

//Map elements can be removed with a tuple:
val m5 = m3 - ("lingxin", "张三")

//Attempted removal of nonexistent elements from a map is handled gracefully:
m5 - "王六"

//Map equivalency is independent of order:
val myMap1 = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa")
val myMap2 = Map("WI" -> "Wisconsin", "MI" -> "Michigan", "IA" -> "Iowa", "OH" -> "Ohio")
myMap1 equals myMap2



