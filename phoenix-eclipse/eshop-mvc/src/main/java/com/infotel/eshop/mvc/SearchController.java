package com.infotel.eshop.mvc;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.infotel.eshop.model.Product;
import com.infotel.eshop.model.ProductFamily;
import com.infotel.eshop.mvc.form.SearchForm;
import com.infotel.eshop.service.CatalogService;

@Controller @RequestMapping("/search")
public class SearchController {

	private final static Logger log = LogManager.getLogger(SearchController.class);
	
	@Autowired
	private CatalogService service;
	
	@ModelAttribute
	public SearchForm setupForm() {
		return new SearchForm();
	}
	
	@ModelAttribute(name="families") //http://localhost:8080/eshop-mvc/search?families
	public List<ProductFamily> setupFamilies() throws Exception {
		return service.findAllFamilies();
	}
	
	@GetMapping
	public String showForm() {
		return "search";
	}
	
//	@PostMapping // on aurait pu utiliser @RequestParam mais on n'utilise pas le formulaire
//	public String search(@ModelAttribute SearchForm searchForm, Model model) {
//		List<Product> products = null;
//		
//		try {
//			products = service.findProductByCriteria(searchForm.getKeyword(), searchForm.getFamilyId());
//			if (log.isDebugEnabled()) {
//				log.debug("search products result size : " + products.size());
//			}
//			model.addAttribute("products", products);
//		} catch (EShopException e) {
//			log.error("Echec lecture des produits", e);
//		}
//		return "search";
//	}
	
	@GetMapping(params = {"keyword", "familyId"})
	public String search(@ModelAttribute SearchForm form, Model model) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Recherche : " + form);
		}
		List<Product> products = service.findProductByCriteria(form.getKeyword(), form.getFamilyId());
		model.addAttribute("products", products);

		return "search";
	}
//	@PostMapping
//	public String searchProducts(@ModelAttribute SearchForm searchForm,
//										Model model) {
//		List<Product> products = null;
//		
//		try {
//			products = service.findProductByCriteria(searchForm.getKeyword(), searchForm.getFamilyId());
//			if (log.isDebugEnabled()) {
//				log.debug("search products result size : " + products.size());
//			}
//			model.addAttribute("products", products);
//		} catch (EShopException e) {
//			log.error("Echec lecture des produits", e);
//		}
//		return "search";
//	}
}
