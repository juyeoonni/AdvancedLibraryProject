package dto;

public class BookDTO {
	String bookID;
	String title;
	String publicationDate;
	String isAvailable;
	
	public BookDTO(String bookID, String title, String publicationDate, String isAvailable) {
		super();
		this.bookID = bookID;
		this.title = title;
		this.publicationDate = publicationDate;
		this.isAvailable = isAvailable;
	}
	public BookDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public String getBookID() {
		return bookID;
	}
	public void setBookID(String bookID) {
		this.bookID = bookID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}
	public String getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	@Override
	public String toString() {
		return "BookDTO [bookID=" + bookID + ", title=" + title + ", publicationDate=" + publicationDate
				+ ", isAvailable=" + isAvailable + "]";
	}
	
	
}