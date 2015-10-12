//When calling methods and functions, you can use the name of the variables expliclty in the call, like so:
def printName(first: String, last: String) = {
  println(first + " " + last)
}

printName("John", "Smith")
printName(first = "John", last = "Smith" )
printName(last = "Smith", first = "John")
