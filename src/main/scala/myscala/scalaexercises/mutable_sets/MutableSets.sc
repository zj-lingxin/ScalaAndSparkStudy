//Mutable sets can be created easily:
import scala.collection.mutable
var mySet = mutable.Set("Michigan", "Ohio", "Wisconsin", "Iowa")
mySet.size
mySet += "Oregon"
mySet contains "Oregon"

//Mutable sets can have elements removed:
mySet = mutable.Set("Michigan", "Ohio", "Wisconsin", "Iowa")
mySet -= "Ohio"
mySet contains "Ohio"

//Mutable sets can have tuples of elements removed:
mySet = mutable.Set("Michigan", "Ohio", "Wisconsin", "Iowa")
mySet -= ("Iowa", "Ohio")
mySet contains "Ohio"

//Mutable sets can have tuples of elements added
mySet = mutable.Set("Michigan", "Wisconsin")
mySet += ("Iowa", "Ohio")
mySet contains "Ohio"

//Mutable sets can have Lists of elements added:
mySet = mutable.Set("Michigan", "Wisconsin")
mySet ++= List("Iowa", "Ohio")
mySet contains "Ohio"
mySet.size

//Mutable sets can have Lists of elements removed:
mySet = mutable.Set("Michigan", "Ohio", "Wisconsin", "Iowa")
mySet --= List("Iowa", "Ohio")
mySet contains "Ohio"

//Mutable sets can be cleared:
mySet = mutable.Set("Michigan", "Ohio", "Wisconsin", "Iowa")
mySet.clear() // Convention is to use parens if possible when method called changes state
mySet contains "Ohio"
mySet.size

