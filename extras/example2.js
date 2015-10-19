some_var = "some value"


param = "User"

function main(){
	var some_other_var = "some other value"
	myClosure(param)
	print("=================")
	myFunction(param)	
}
function myFunction(param){
	print("Hey "+param+", i have "+some_var)
	print("I also have "+some_other_var)
}

//joking, no such thing as difference in functions and closures in javascript....
// it is just to show parallelism
var myClosure = function(param){
	print("Hey "+param+", i have "+some_var)
	print("I also have "+some_other_var)
}

main()
