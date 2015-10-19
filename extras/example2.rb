$some_var = "some value"
some_other_var = "some other value"

def myFunction(param) 
	print("Hey "+param+", i have "+$some_var+"\n")
	print("I also have "+some_other_var+"\n")
end


myClosure = Proc.new { |param|
	print("Hey "+param+", i have "+$some_var+"\n")
	print("I also have "+some_other_var+"\n")
}

param = "User"

def main
	some_other_var = "some other value"
	myClosure.call(param)
	print("=================\n")
	myFunction(param)
end

main