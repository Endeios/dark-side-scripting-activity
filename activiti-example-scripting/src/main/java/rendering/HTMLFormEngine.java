package rendering;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.impl.form.FormEngine;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class HTMLFormEngine implements FormEngine {
	
	private static String MY_NAME = "html";
	private static Logger log = LoggerFactory.getLogger(HTMLFormEngine.class);
	 

	@Override
	public String getName() {
		return MY_NAME;
	}

	@Override
	public Object renderStartForm(StartFormData startForm) {
		List<FormProperty> props = startForm.getFormProperties();
		log.debug("Rendering "+startForm);
		StringBuffer sb = renderListProperties(props);
		log.debug(startForm+" render is "+sb);
		return sb.toString();
	}

	private StringBuffer renderListProperties(List<FormProperty> props) {
		StringBuffer sb = new StringBuffer();
		for(FormProperty prop:props){
			sb.append("<p>");
			log.debug("rendering form property for: "+prop+" having type name "+prop.getType().getName());
			switch (prop.getType().getName()) {
			case "string":
				sb.append(String.format("<label for=\"%s\" >%s</label>",prop.getId(),prop.getName()));
				sb.append(String.format("<input type=\"text\" id=\"%s\" name=\"%s\" />",prop.getId(),prop.getId()));
				break;
			case "int":
				sb.append(String.format("<label for=\"%s\" >%s</label>",prop.getId(),prop.getName()));
				sb.append(String.format("<input type=\"text\" id=\"%s\" name=\"%s\" />",prop.getId(),prop.getId()));
				break;
			case "long":
				sb.append(String.format("<label for=\"%s\" >%s</label>",prop.getId(),prop.getName()));
				sb.append(String.format("<input type=\"text\" id=\"%s\" name=\"%s\" />",prop.getId(),prop.getId()));
				break;
			case "enum":
				Map<String,String> options =(Map<String,String>) prop.getType().getInformation("values");
				sb.append(String.format("<label for=\"%s\" >%s</label>",prop.getId(),prop.getName()));
				sb.append(String.format("<select  id=\"%s\" name=\"%s\" >",prop.getId(),prop.getId()));
				Set<String> keys = options.keySet();
				for (String key : keys) {
					sb.append(String.format("<option value=\"%s\">%s</option>",key,options.get(key)));
				}
				sb.append("</select>");
				break;
			case "date":
				sb.append(String.format("<label for=\"%s\" >%s</label>",prop.getId(),prop.getName()));
				sb.append(String.format("<input type=\"text\" id=\"%s\" name=\"%s\" />",prop.getId(),prop.getId()));
				break;
			case "boolean":
				sb.append(String.format("<label for=\"%s\" >%s</label>",prop.getId(),prop.getName()));
				sb.append(String.format("<input type=\"checkbox\" id=\"%s\" name=\"%s\" />",prop.getId(),prop.getId()));
				break;
			default:
				log.warn("Could not render "+prop.getId());
				sb.append(String.format("<h1>Error formatting %s</h1>",prop.getId()));
				break;
			}
			sb.append("<p>");
		}
		return sb;
	}

	@Override
	public Object renderTaskForm(TaskFormData taskForm) {
		List<FormProperty> props = taskForm.getFormProperties();
		StringBuffer sb = renderListProperties(props);
		return sb.toString();
	}

}
