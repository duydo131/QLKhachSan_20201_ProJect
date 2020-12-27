package generate;

import model.add.Docx;

public class InfoPay extends Docx{
	private Integer stt;
	private String ten;
	private Long gia;
	private String ngay;
	private Integer so;
	private Long tien;
	
	public InfoPay() {
	}

	public InfoPay(Integer stt, String ten, Long gia, String ngay, Integer so, Long tien) {
		this.stt = stt;
		this.ten = ten;
		this.gia = gia;
		this.ngay = ngay;
		this.so = so;
		this.tien = tien;
	}

	public Integer getStt() {
		return stt;
	}

	public void setStt(Integer stt) {
		this.stt = stt;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public Long getGia() {
		return gia;
	}

	public void setGia(Long gia) {
		this.gia = gia;
	}

	public String getNgay() {
		return ngay;
	}

	public void setNgay(String ngay) {
		this.ngay = ngay;
	}

	public Integer getSo() {
		return so;
	}

	public void setSo(Integer so) {
		this.so = so;
	}

	public Long getTien() {
		return tien;
	}

	public void setTien(Long tien) {
		this.tien = tien;
	}
}
