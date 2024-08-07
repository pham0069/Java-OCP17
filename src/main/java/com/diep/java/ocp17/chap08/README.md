## Chapter 8. Lambdas and Functional Interfaces

1. OO vs functional programing
* object state vs declaring what you want to do
2. Lambda expression
* block of code that can be passed around as an argument/variable
* work with interfaces that have exactly 1 abstract method (e.g. CheckIfHopper)
* example: a -> a.canHop() that is equivalent to CheckIfHopper object
* Java relies on context to interpret lambda
  * output type: boolean
  * input type: animal
* has 3 parts:
  * parameter names
  * arrow: separate parameter and body
  * body: return result
* valid syntax:
  * () -> true
  * x -> x.startsWith("test")
  * (String x) -> x.startsWith("test")
  * (x, y) -> { return x.length() > y.length() }
  * (String x, String y) -> x.length() > y.length()
  * s -> {}
* cannot assign lambda to var
``` 
var invalid = (Animal a) -> a.canHop; // DOES NOT COMPILE
```
neither lambda nor var has enough info to determine what type of functional interface should be used
