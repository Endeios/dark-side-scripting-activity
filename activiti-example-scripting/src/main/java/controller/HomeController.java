package controller;

import java.util.List;

import org.activiti.engine.FormService;
import org.activiti.engine.form.FormProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.Document;
import exception.CannotFindProcess;
import service.BPMManagementService;
import service.DocumentService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	static Logger log = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private FormService formService;
	
	
	@Autowired
	private DocumentService documentService;
	
	@Autowired
	private BPMManagementService managementService;
	
	@RequestMapping("")
	public String index(Model model){
		List<Document> docs = documentService.findDocumentByActiveTrue();
		model.addAttribute(docs);
		model.addAttribute("docs",docs);
		log.info("Test");
		return "index";
	}
	
	@RequestMapping("/deploy-bar")
	public String deployBar(){
		managementService.deployBar();
		return "redirect:/";
	}
	
	@RequestMapping("new-process")
	public String newProcess(Model model) throws CannotFindProcess{
		String form = managementService.getStartForm();
		model.addAttribute("startForm", form);
		return "new-process";
	}

}
