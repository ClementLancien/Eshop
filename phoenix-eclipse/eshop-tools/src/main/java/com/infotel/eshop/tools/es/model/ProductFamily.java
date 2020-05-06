package com.infotel.eshop.tools.es.model;

import java.util.List;

public class ProductFamily {
    private int id;
    private String name;

    private List<ProductFamily> subFamilies;
    
    public ProductFamily clone(boolean withSubFamilies) {
    	ProductFamily f = new ProductFamily();
    	f.setId(id);
    	f.setName(name);
    	if (withSubFamilies) {
    		f.setSubFamilies(subFamilies);
    	}
    	return f;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductFamily> getSubFamilies() {
        return subFamilies;
    }

    public void setSubFamilies(List<ProductFamily> subFamilies) {
        this.subFamilies = subFamilies;
    }
}
