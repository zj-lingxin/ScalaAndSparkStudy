/*
null

Scala’s null is the same as in Java. Any reference type can be null, like Strings, Objects, or your own classes. Also just like Java, value types like Ints can’t be null.

Null

Null is a trait whose only instance is null. It is a subtype of all reference types, but not of value types. It purpose in existing is to make it so reference types can be assigned null and value types can’t.

Nothing

Nothing is a trait that is guaranteed to have zero instances. It is a subtype of all other types. It has two main reasons for existing: to provide a return type for methods that never return normally (i.e. a method that always throws an exception). The other reason is to provide a type for Nil (explained below).

Unit

Unit in Scala is the equivalent of void in Java. It’s used in a function’s signature when that function doesn’t return a value.

Nil

Nil is just an empty list, exactly like the result of List(). It is of type List[Nothing]. And since we know there are no instances of Nothing, we now have a list that is statically verifiable as empty. Nice to have.

An empty list can be represented by another nothing value: Nil

 */
List() == Nil

/*
None is the counterpart to Some, used when you’re using Scala’s Option class to help avoid null references.

None equals None:
 */
None == None

/*
None should be identical to None
 */
val a = None
val b = None
a eq b

/*
None can be converted to a String:
 */
None.toString == "None"

/*
None can be converted to an empty list
 */
val a2 = None
a2.toList == Nil

/*
None is considered empty
 */
None.isEmpty == true

/*
None can be cast Any, AnyRef or AnyVal
 */
None.asInstanceOf[Any]
None.asInstanceOf[AnyRef]
None.asInstanceOf[AnyVal]

/*
None can be used with Option instead of null references
 */
val optional: Option[String] = None
optional.isEmpty == true