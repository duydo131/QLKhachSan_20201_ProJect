package model.add;

public class DetailStatisticalModel {
	private Long idTP;
	private String customer;
	private String staff;
	private String room;
	private Long quantity;
	private Long total;
	
	public DetailStatisticalModel() {
	}

	public DetailStatisticalModel(Long idTP, String customer, String staff, String room, Long quantity, Long total) {
		this.idTP = idTP;
		this.customer = customer;
		this.staff = staff;
		this.room = room;
		this.quantity = quantity;
		this.total = total;
	}

	public Long getIdTP() {
		return idTP;
	}

	public void setIdTP(Long idTP) {
		this.idTP = idTP;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getStaff() {
		return staff;
	}

	public void setStaff(String staff) {
		this.staff = staff;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
}
