package com.infotel.eshop.ui;

import java.text.MessageFormat;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Window {
	
	private final static Logger log = LogManager.getLogger(Window.class);
	
	protected String titleKey;
	//protected String title;

	public Window(String titleKey) {
		super();
		this.titleKey = titleKey;
		//this.title=getMessage(title);
	}
	
	// DESIGN PATTERN
	public void display(Object... params) {
		try {
			renderTitle();
			renderBody(params);
		} catch (Exception e) {
			log.error("Problème système", e);
			render("Problème système. Veuillez contacter l'administrateur");
			//e.printStackTrace();
		}
	}

	private void renderTitle() {
		render();
		render("*** "+ getMessage(titleKey) +" ***");
		render();
	}
	
	//on ajoute de l'intégrité dans le code
	protected abstract void renderBody(Object... params) throws Exception;

	protected String inputString(String title) {
			
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		render(title);
		return scanner.nextLine();
	}
	
	protected int inputInt(String title) {
		return Integer.parseInt(inputString(title));
	}

	protected void render() {
		System.out.println();
	}
	
	protected void render(String text) {
		//System.out.println(text);
		render(text, true);
	}
	
	protected void render(String text, boolean newLine) {
		if(newLine) {
			System.out.println(text);
		} else {
			System.out.print(text);
		}
	}
	
	protected void navigate(String target, Object... params) {
		Router.Instance.navigate(target, params);
	}
	
	protected <T> T submit(Callable<T> callable) throws Exception{
		Future<T> future = SessionContext.Instance.executor.submit(callable);
		
		if (log.isDebugEnabled()) {
			log.debug("Exécution tâche longue");
		}
		
		render("Attente traitement", false);
		while (!future.isDone()) { // isDone renvoit toujours false. On ne sort jamais de la boucle
			render(".", false);
			Thread.sleep(300);
		}
		render();
		return future.get();
	}
	
	protected String getMessage(String key, Object... params) {
		try {
			String pattern = SessionContext.Instance.getBundle().getString(key);
			return MessageFormat.format(pattern, params);
		} catch (Exception e) {
			log.warn("Clé de traduction n'est pas trouvé : " + key);
			return "???" + key + "???";
		}
	}
	public <T> T getBean(Class<T> clazz) {
		return SessionContext.Instance.getBean(clazz);
	}
}