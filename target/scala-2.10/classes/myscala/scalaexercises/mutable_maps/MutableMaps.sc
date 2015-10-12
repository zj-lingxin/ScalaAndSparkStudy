//Mutable maps can be created easily:
import scala.collection.mutable
var myMap = mutable.Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa")
myMap.size
myMap += "OR" -> "Oregon"
myMap contains "OR"

//Mutable maps can have elements removed:
myMap -= "OR"
myMap contains "OR"

//Mutable maps can have tuples of elements added:
myMap = mutable.Map("MI" -> "Michigan", "WI" -> "Wisconsin")
myMap += ("IA" -> "Iowa", "OH" -> "Ohio")
myMap contains "OH"
myMap.size

//Mutable maps can have Lists of elements added:
myMap = mutable.Map("MI" -> "Michigan", "WI" -> "Wisconsin")
myMap ++= List("IA" -> "Iowa", "OH" -> "Ohio")
myMap contains "OH"

//Mutable maps can have Lists of elements removed:
myMap = mutable.Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa")
myMap --= List("IA", "OH")
myMap contains "OH"

//Mutable maps can be cleared:
myMap = mutable.Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa")
myMap.clear() // Convention is to use parens if possible when method called changes state
myMap contains "OH"





