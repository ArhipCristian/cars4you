package entities;


public class Item {
	
	private int id,
		category;
	
	private String name,
		description,
                image;
	
	private double price;
	
	public Item() {
	}
	
	public Item(int id, int category, String name,
			String description ,double price, String image) {
		this.id = id;
		this.category = category;
		this.name = name;
		this.description = description;
		this.price = price;
                this.image = image;
	}	
	
	public int getId() {
		return id;
	}

	public int getCategory() {
		return category;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}        
        public String getImage() {
		return image;
	}

	public void setCategory(int category) {
		this.category = category;
	}
        public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(double price) {
		this.price = price;
	}
        
        public void setImage(String image) {
		this.image = image;
	}

}
