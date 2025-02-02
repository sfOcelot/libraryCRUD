package bookworm;

public class Books {
	private int ID;
	private String title;
	private String genre;
	private String editorial;
	private int price;
	private String ISBN;
	private String cover;
	private String editions;
	
	public Books() {
		
	}
	
	public Books(int ID, String title, String genre, String editorial, int price, String ISBN, String cover) {
		this.ID = ID;
		this.title = title;
		this.genre = genre;
		this.editorial = editorial;
		this.price = price;
		this.ISBN = ISBN;
		this.cover = cover;
	}
	
	public Books(int ID, String title, String genre, String editorial, int price, String ISBN, String editions, String cover) {
		this.ID = ID;
		this.title = title;
		this.genre = genre;
		this.editorial = editorial;
		this.price = price;
		this.ISBN = ISBN;
		this.editions = editions;
		this.cover = cover;
	}
	
	public Books(String title, String genre, String editorial, int price, String ISBN, String editions, String cover) {
		this.title = title;
		this.genre = genre;
		this.editorial = editorial;
		this.price = price;
		this.ISBN = ISBN;
		this.editions = editions;
		this.cover = cover;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getEditorial() {
		return editorial;
	}
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getEditions() {
		return editions;
	}

	public void setEditions(String editions) {
		this.editions = editions;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
}
