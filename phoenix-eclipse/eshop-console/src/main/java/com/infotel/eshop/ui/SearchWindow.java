package com.infotel.eshop.ui;

import java.util.List;

import com.infotel.eshop.model.Product;
import com.infotel.eshop.model.ProductFamily;
import com.infotel.eshop.service.CatalogService;

public class SearchWindow extends Window {
	
	private List<Product> products;
	//private boolean done;
	
	public SearchWindow() {
		super("search.title");
	}

	@Override
	protected void renderBody(Object... params) throws Exception {
		if (products != null && !products.isEmpty()) {
			renderResults();
		}
		
		String keyword = inputString(getMessage("search.input.keyword"));
		
		//CatalogService service = new CatalogServiceImpl();
		CatalogService service = getBean(CatalogService.class);
		//CatalogService service = context.getBean(CatalogService.class);
		List<ProductFamily> families = service.findAllFamilies();
		render();
		render(getMessage("search.render.familiesList"));
		render(getMessage("search.render.familyByDefault"));
		for (int i = 0; i < families.size(); i++) {
			render(getMessage("search.render.families", (i+1),  families.get(i).getName()));
//			render("[" + (i+1) + "] " + families.get(i).getName());
		}
		render();
		int familyIndex = inputInt(getMessage("search.input.family")) - 1;
		ProductFamily family = (familyIndex == -1)?null : families.get(familyIndex);
		
		//Remplacer ce code par l'ExecutorService
		/* Version 1
		Thread longProcessing  = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					products = service.findProductByCriteria(keyword);
					done = true;
					onResult();
				} catch (EShopException e) {}
				System.out.println("Traitement long");
			}
		}, "MonTraitement"); // Nom du thread
		longProcessing.start();
		
		render("Attente traitement", false);
		while (!done) {
			render(".", false);
			Thread.sleep(300);
		}*/
		
		/* Version 2
		SessionContext.Instance.executor.submit(new Runnable() {
			@Override
			public void run() {
				try {
					products = service.findProductByCriteria(keyword);
					done = true;
					onResult();
				} catch (EShopException e) {}
				System.out.println("Traitement long");
			}
		});
		
		render("Attente traitement", false);
		while (!done) {
			render(".", false);
			Thread.sleep(300);
		}*/
		
		/*
		// Version 3
		Future future = SessionContext.Instance.executor.submit(new Runnable() {
			@Override
			public void run() {
				try {
					products = service.findProductByCriteria(keyword);
					onResult();
				} catch (EShopException e) {}
				System.out.println("Traitement long");
			}
		});
		render("Attente traitement", false);
		while (!future.isDone()) { // isDone renvoie toujours false. On ne sort jamais de la boucle
			render(".", false);
			Thread.sleep(300);
		}*/
		
		/*
		// Version 4
		Future<List<Product>> future = SessionContext.Instance.executor.submit(new Callable<List<Product>>() {
			@Override
			public List<Product> call() throws Exception {
				return service.findProductByCriteria(keyword);
			}
		});
		
		render("Attente traitement", false);
		while (!future.isDone()) { // isDone renvoit toujours false. On ne sort jamais de la boucle
			render(".", false);
			Thread.sleep(300);
		}
		
		products = future.get();
		
		onResult();
		*/
		
		/*
		// Version 5
		Future<List<Product>> future = SessionContext.Instance.executor.submit(() -> service.findProductByCriteria(keyword));
		
		render("Attente traitement", false);
		while (!future.isDone()) { // isDone renvoit toujours false. On ne sort jamais de la boucle
			render(".", false);
			Thread.sleep(300);
		}
		
		products = future.get();
		
		onResult();
		*/
		
		
		// Version 6
		int familyId = (family== null) ? -1 : family.getId();
		//if  (family != null) familyId = family.getId();
		products = submit(() -> service.findProductByCriteria(keyword, familyId));
		onResult();
	}
	
	/*
	// version 6
	private List<Product> submit(Callable<List<Product>> callable) throws Exception{
		Future<List<Product>> future = SessionContext.Instance.executor.submit(callable);
		
		render("Attente traitement", false);
		while (!future.isDone()) { // isDone renvoit toujours false. On ne sort jamais de la boucle
			render(".", false);
			Thread.sleep(300);
			}
		return future.get();
	}
	*/
	
	/*
	// version 7
	private <T> T submit(Callable<T> callable) throws Exception{
		Future<T> future = SessionContext.Instance.executor.submit(callable);
		
		render("Attente traitement", false);
		while (!future.isDone()) { // isDone renvoit toujours false. On ne sort jamais de la boucle
			render(".", false);
			Thread.sleep(300);
		}
		return future.get();
	}
	*/
	
	// version 8 : on met la methode submit dans la classe window
	private void renderResults() {
		render(getMessage("search.render.title.results"));
		for (int i = 0; i < products.size(); i++) {
			Product product = products.get(i);
			render(getMessage("search.render.results", (i+1), product.getName()));
			//render("[" + (i + 1) + "] " + product.getName());
		}
	}

	private void onResult() {
		render();
		if(products.isEmpty()) {
			render(getMessage("search.warning.noresult"));
		} else {
			renderResults();
		}
		
		// sélection d'un produit
		render();
		int choice = inputInt(getMessage("search.input.index.product"));
		int productId = products.get(choice - 1).getId();
		
		navigate(UiConstants.DETAIL, productId);
	}
}