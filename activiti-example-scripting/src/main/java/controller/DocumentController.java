package controller;

import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

@Controller
@RequestMapping("/documents")
public class DocumentController {
	@RequestMapping(value="",method=RequestMethod.POST)
	public String createDocument(ModelAndView modelAndView,HttpServletRequest request){
		String text = WebUtils.findParameterValue(request, "text");
		System.out.println(text+"<----------------------");
		return "redirect:/";
	}
}
