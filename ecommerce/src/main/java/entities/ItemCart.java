package entities;

import java.io.Serializable;

public class ItemCart extends Item implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int qty;
	
	public ItemCart(){}
	
	public ItemCart(Item item){
		setId(item.getId());
		setCategory(item.getCategory());		
		setName(item.getName());
		setDescription(item.getDescription());		
		setImage(item.getImage());
		setPrice(item.getPrice());		
		qty = 0;
	}	
	
	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
}
