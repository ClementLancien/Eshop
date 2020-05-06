package com.infotel.eshop.dao;

import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.ItemCounter;

public interface ItemCounterDao {
	void create(ItemCounter counter) throws EShopException;
	
	/**
	 * Mettre à jour uniquement le champ <code>newtvalue</code>
	 * @param counter le counter à incrémenter
	 * @throws EShopException Quand il ya un problème BDD
	 */
	void update(ItemCounter counter) throws EShopException;
	
	ItemCounter findByID(String item, String subset) throws EShopException;
}
