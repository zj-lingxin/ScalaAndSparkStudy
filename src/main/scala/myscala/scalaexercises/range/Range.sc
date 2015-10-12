//Range are not inclusive at end of range:
val someNumbers = Range(0,5) //Range(0, 1, 2, 3, 4)
val second = someNumbers(1)
val last = someNumbers.last
//Range can specify a step for an increment:
val sn2 = Range(2, 10, 3) //Range(2, 5, 8)
//Range does not include the last item, even in a step increment:
val sn3 = Range(0, 10, 2) // Range(0, 2, 4, 6, 8)

//Range can specify to include the last value
val sn4 = Range(0, 10, 2).inclusive //Range(0, 2, 4, 6, 8, 10)

/*
A Range is an ordered sequence of integers that are equally spaced apart. For example, "1, 2, 3," is a range, as is
 "5, 8, 11, 14." To create a range in Scala, use the predefined methods to and by. 1 to 3 generates Range(1, 2, 3)
 and 5 to 14 by 3 generates Range(5, 8, 11, 14).
If you want to create a range that is exclusive of its upper limit, then use the convenience method until instead of
to: 1 until 3 generates Range(1, 2).
Ranges are represented in constant space, because they can be defined by just three numbers: their start, their end,
and the stepping value. Because of this representation, most operations on ranges are extremely fast.
 */
0 to 6 //Range(0, 1, 2, 3, 4, 5, 6)
0 to 6 by 2 //Range(0, 2, 4, 6)
0 until 6 //Range(0, 1, 2, 3, 4, 5)
0 until 6 by 2 //Range(0, 2, 4)

for (i <- 0 until 6) {
  print(i + " ")
}




