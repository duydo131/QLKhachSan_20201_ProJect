package model.add;

public class DeviceRoom{
	private String tenTB;
	private Long soLuong;
	private Long gia;
	
	public DeviceRoom() {
	}

	public DeviceRoom(String tenTB, Long soLuong, Long gia) {
		this.tenTB = tenTB;
		this.soLuong = soLuong;
		this.gia = gia;
	}

	public String getTenTB() {
		return tenTB;
	}

	public void setTenTB(String tenTB) {
		this.tenTB = tenTB;
	}

	public Long getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(Long soLuong) {
		this.soLuong = soLuong;
	}

	public Long getGia() {
		return gia;
	}

	public void setGia(Long gia) {
		this.gia = gia;
	}
}
