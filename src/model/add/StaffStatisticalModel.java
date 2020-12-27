package model.add;

public class StaffStatisticalModel extends Docx{
	private Integer stt;
	private Long id;
	private String name;
	private Integer quantityLease;
	private Integer quantityPay;
	
	public StaffStatisticalModel() {
	}

	public StaffStatisticalModel(Long id, String name, Integer quantityLease, Integer quantityPay) {
		this.id = id;
		this.name = name;
		this.quantityLease = quantityLease;
		this.quantityPay = quantityPay;
	}

	public Integer getStt() {
		return stt;
	}

	public void setStt(Integer stt) {
		this.stt = stt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQuantityLease() {
		return quantityLease;
	}

	public void setQuantityLease(Integer quantityLease) {
		this.quantityLease = quantityLease;
	}

	public Integer getQuantityPay() {
		return quantityPay;
	}

	public void setQuantityPay(Integer quantityPay) {
		this.quantityPay = quantityPay;
	}
}
