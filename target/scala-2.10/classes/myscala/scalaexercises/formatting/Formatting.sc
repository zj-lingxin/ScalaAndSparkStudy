//String can be placed in format:
val s = "Hello World"
"Application %s".format(s)

//Character Literals can be an a single character:
val a = 'a'
val b = 'B'
//format(a) is a string format, meaning the "%c".format(x)
//will return the string representation of the char.
"Application %c".format(a)
"Application %c".format(b)

//Character Literals can be an escape sequence, including octal or hexidecimal:
val c = '\u0061' //unicode for a
val d = '\141' //octal for a
val e = '\"'
val f = '\\'
"%c".format(c)
"%c".format(d)
"%c".format(e)
"%c".format(f)

//Formatting can also include numbers:
val j = 190
"%d bottles of beer on the wall" format j - 100

//Formatting can be used for any number of items, like a string and a number:
val k = "vodka"
"%d bottles of %s on the wall".format(j - 100, k)
