package com.cts.fse.eauction.seller.constants;

public enum ProductCategory {

	//. Painting b. Sculptor c. Ornament
	
	PAINTING("Painting"),
	SCULPTOR("Sculptor"),
	ORNAMENT("Ornament");
	
	String categroyName;
	
	ProductCategory(String categoryName){
		this.categroyName = categoryName;
	}	
	
	public String getCategroyName() {
		return categroyName;
	}
	public void setCategroyName(String categroyName) {
		this.categroyName = categroyName;
	}
	
}
