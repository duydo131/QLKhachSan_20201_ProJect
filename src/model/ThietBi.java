package model;

import model.add.Docx;

public class ThietBi extends Docx{
	private Integer stt;
	private Long ID; 
    private String TenTB;
    private Long Gia;
    private Boolean active = true;
    
	public ThietBi() {
	}

	public ThietBi(Long iD, String tenTB, Long gia) {
		ID = iD;
		TenTB = tenTB;
		Gia = gia;
	}

	public ThietBi(Long iD, String tenTB, Long gia, Boolean active) {
		ID = iD;
		TenTB = tenTB;
		Gia = gia;
		this.active = active;
	}

	public Integer getStt() {
		return stt;
	}

	public void setStt(Integer stt) {
		this.stt = stt;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getTenTB() {
		return TenTB;
	}

	public void setTenTB(String tenTB) {
		TenTB = tenTB;
	}

	public Long getGia() {
		return Gia;
	}

	public void setGia(Long gia) {
		Gia = gia;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	} 
}
