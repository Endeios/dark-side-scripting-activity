@Grapes([
	@Grab(group='org.jruby', module='jruby', version='9.0.1.0'),
    @Grab(group='org.python', module='jython', version='2.7.1b1'),
    @Grab(group='log4j', module='log4j', version='1.2.17')
])

import org.apache.log4j.*
import javax.script.*
/*
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
*/

log = Logger.getLogger(this.class)
log.level = Level.DEBUG

sem = new ScriptEngineManager();
log.debug("Initilizing scripting engine manager ")
def get_engine_name_by_filename(filename){
	log.debug("Resolving scripting engine for ${filename}")
	def resolver = [".js":"js",".rb":"jruby",".py":"jython"]
	
	def keys = resolver.keySet()
	
	log.debug("Registered keys are ${keys}")
	
	for (key in keys){
		log.debug("Comparing  ${filename} and ${key}")
		if(filename.endsWith(key))
			return sem.getEngineByName(resolver.get(key)); 
	}
	
	log.warn("Returning default js engine")
	return sem.getEngineByName("js");
	
}

Bindings bindings = new SimpleBindings();

def cli = new CliBuilder(usage:'runScript <script>')

def options = cli.parse(args)
log.debug("Arguments: "+options.arguments())
if(options==null || options.arguments().size != 1){
    cli.usage()
    System.exit(1)
}

script_file = new File (options.arguments()[0])

if(!script_file.exists()){
	log.fatal("the script file to run must exsists");
	cli.usage()
    System.exit(1)
}



log.info("Opening reader for ${script_file}")
script = new FileReader(script_file);
engine = get_engine_name_by_filename(script_file.name)
log.debug("Engine is ${engine}")
engine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);
try{
	log.debug("Starting evaluation of script")
	engine.eval(script);
	log.debug("Script evaluation terminated")
}catch (Throwable e){
	e.printStackTrace()
	log.error("Script evaluation terminated with error: ${e}")
} finally{
	log.info("Bindings in exit:\n $bindings")
}
