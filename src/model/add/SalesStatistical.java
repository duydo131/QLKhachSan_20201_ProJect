package model.add;

public class SalesStatistical {
	private String time;
	private Integer amountCustomer;
	private Integer amountRoom;
	private Long totalMoney;
	
	public SalesStatistical() {
	}

	public SalesStatistical(String time, Integer amountCustomer, Integer amountRoom, Long totalMoney) {
		this.time = time;
		this.amountCustomer = amountCustomer;
		this.amountRoom = amountRoom;
		this.totalMoney = totalMoney;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getAmountCustomer() {
		return amountCustomer;
	}

	public void setAmountCustomer(Integer amountCustomer) {
		this.amountCustomer = amountCustomer;
	}

	public Integer getAmountRoom() {
		return amountRoom;
	}

	public void setAmountRoom(Integer amountRoom) {
		this.amountRoom = amountRoom;
	}

	public Long getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Long totalMoney) {
		this.totalMoney = totalMoney;
	}
}
