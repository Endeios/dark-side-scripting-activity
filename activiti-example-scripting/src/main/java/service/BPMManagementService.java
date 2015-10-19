package service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import exception.CannotFindProcess;

public class BPMManagementService {
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private FormService formService;
	
	private String workFlowName;
	private String barPostion;
	private static Logger log = LoggerFactory.getLogger(BPMManagementService.class);
	
	public BPMManagementService(String workFlowName, String barPostion) {
		super();
		this.workFlowName = workFlowName;
		this.barPostion = barPostion;
	}
	
	public void deployBar(){
		ArrayList<String> bars = getBars();
		List<Deployment> justDeployed = new ArrayList<Deployment>();
		log.debug("Bars to deploy:" + bars);
		for (String bar : bars) {
			try {
				ZipInputStream inputStream = new ZipInputStream(new FileInputStream(bar));
				justDeployed.add(repositoryService.createDeployment().addZipInputStream(inputStream).deploy());

			} catch (FileNotFoundException e) {
				log.error("FileNotFoundException", e);
			}
		}
		List<String> messages = new ArrayList<String>();
		for (Deployment d : justDeployed) {
			messages.add("Just deployed " + d.getName() + " on " + d.getDeploymentTime() + "(" + d.getId() + ")");
		}
	}

	private ArrayList<String> getBars() {
		ArrayList<String> bars = new ArrayList<String>();
		try {
			log.debug("Exploring folder "+barPostion+" for bars");
			DirectoryStream<Path> filez = Files.newDirectoryStream(Paths.get(barPostion), "*.bar");
			for (Path path2 : filez) {
				log.debug("Found "+path2.toFile().getAbsolutePath());
				bars.add(path2.toFile().getAbsolutePath());
			}
		} catch (IOException e) {
			log.error("IOException", e);
		}
		return bars;
	}
	
	/**
	 * Gets the start command values
     * 
	 * @param processDefinition
	 * @return
	 * @throws CannotFindProcess
	 */
	private String getStartForm(String processDefinition) throws CannotFindProcess
	  {
	    ProcessDefinition a = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinition).latestVersion().singleResult();
	    if(a == null)
	    {
	    	throw new CannotFindProcess("Process: "+processDefinition+" not found");	
	    }
	    log.trace("Working with process " + a);
//	    StartFormData sfd = formService.getStartFormData(a.getId());
//	    List<FormProperty> props = sfd.getFormProperties();
//	    return props;
		return (String) formService.getRenderedStartForm(a.getId(),"html");
	  }
	  
	  public String getStartForm() throws CannotFindProcess{
		  return getStartForm(workFlowName);
	  }
	  
}
