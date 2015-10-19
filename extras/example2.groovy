
some_var = "some value"

def myFunction(param){
    println("Hey $param, i have $some_var")
    println("I also have $some_other_var")
}

myClosure = {
    param->
    println("Hey $param, i have $some_var")
    println("I also have $some_other_var")
}

param = "User"
def main(){
	def some_other_var = "some other value"
	myClosure(param)
	println("=================")
	myFunction(param)
}

main()
 