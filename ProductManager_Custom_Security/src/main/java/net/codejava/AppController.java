package net.codejava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.List; 

@SessionAttributes({"currentUser"})

@Controller 
public class AppController {
	
	@Autowired 
	private ProductService service;   
	
	@RequestMapping("/") 
	public String viewHomePage(Model model) {
		List<Product> listProducts = service.listAll(); 
		model.addAttribute("listProducts", listProducts); // next bc of thymeleaf we make the index.html 
		
		return "index"; 
	}
	
	@RequestMapping("/new") 
	public String showNewProductForm(Model model) {
		Product product = new Product();
		model.addAttribute("product", product); 
		
		return "new_product";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST) 
	public String saveProduct(@ModelAttribute("product") Product product) {
		service.save(product);
		
		return "redirect:/"; 
		
	}
	
	@RequestMapping("/edit/{id}") 
	public ModelAndView showEditProductForm(@PathVariable(name = "id") Long id) {
		ModelAndView modelAndView =  new ModelAndView("edit_product");
		Product product = service.get(id); 
		modelAndView.addObject("product", product); 
		
		return modelAndView;
	}
	
	@RequestMapping("/delete/{id}") 
	public String deleteProduct(@PathVariable(name = "id") Long id) {
		service.delete(id);
		
		return "redirect:/"; 
	}
	
	@GetMapping("/403")
	public String error403() {		
		return "403"; 
	}
	
	@GetMapping("/login") 
	//@RequestMapping(value = "/login", method =RequestMethod.GET)
	public String loginPage() {
		return "login"; 
	}
	
	/* 
	@PostMapping("/login_success_handler") 
	//@RequestMapping(value = "/login_success_handler", method = RequestMethod.POST)
	public String loginSuccessHandler() {
    	System.out.println("Login success handler!"); 
		return "index";       	
	}

	@PostMapping("/login_failure_handler") 
	//@RequestMapping(value = "/login_failure_handler", method = RequestMethod.POST)
	public String loginFailureHandler() {
    	System.out.println("Login failure handler!");
		return "login_error";       	
	}
	*/ 
	
}
