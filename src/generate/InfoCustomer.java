package generate;

public class InfoCustomer {
	private String name;
	private String phone;
	private String address;
	private Long total;
	
	public InfoCustomer() {
	}

	public InfoCustomer(String name, String phone, String address, Long total) {
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.total = total;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
}
