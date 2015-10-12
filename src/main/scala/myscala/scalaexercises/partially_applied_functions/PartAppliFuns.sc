//A partially applied function is a function that you do not apply any or all the arguments, creating another function.
// This partially applied function doesn't apply any arguments.
def sum(a: Int, b: Int, c: Int) = a + b + c
val sum3 = sum _
sum3(1, 9, 7)
sum(1, 9, 7)

//Partially applied functions can replace any number of arguments:
val sumC = sum(1, 9, _:Int) //注意：一定要加 :Int,否则运行时报错
sumC(7)

