@Grapes( 
    @Grab(group='org.jruby', module='jruby', version='9.0.1.0') 
)

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import javax.script.SimpleScriptContext;

ScriptEngineManager sem = new ScriptEngineManager();
engine = sem.getEngineByName("jruby");


Bindings bindings = new SimpleBindings();

def cli = new CliBuilder(usage:'jgroovy <ruby_script>')

def options = cli.parse(args)
println "Arguments: "+options.arguments()
if(options==null || options.arguments().size != 1){
    cli.usage()
    System.exit(1)
}

//arg 0
script = new FileReader(options.arguments()[0]);
engine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);
try{
	engine.eval(script);
}catch (Throwable e){
	e.printStackTrace()
} finally{
	println "Bindings:\n $bindings "
}
