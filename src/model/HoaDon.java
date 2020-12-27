package model;

import java.util.Date;

public class HoaDon {
	private Long ID;
	private Long ID_TP;
	private Date NgayLap;
	private Long Thue;
	private Long PhiPhatSinh = 0L;
	private Boolean active = true;
	
	public HoaDon() {
	}

	public HoaDon(Long iD, Long iD_TP, Date ngayLap, Long thue, Long phiPhatSinh) {
		ID = iD;
		ID_TP = iD_TP;
		NgayLap = ngayLap;
		Thue = thue;
		PhiPhatSinh = phiPhatSinh;
	}
	
	public HoaDon(Long iD, Long iD_TP, Date ngayLap, Long thue, Long phiPhatSinh, Boolean active) {
		ID = iD;
		ID_TP = iD_TP;
		NgayLap = ngayLap;
		Thue = thue;
		PhiPhatSinh = phiPhatSinh;
		this.active = active;
	}
	
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public Long getID_TP() {
		return ID_TP;
	}

	public void setID_TP(Long iD_TP) {
		ID_TP = iD_TP;
	}

	public Date getNgayLap() {
		return NgayLap;
	}

	public void setNgayLap(Date ngayLap) {
		NgayLap = ngayLap;
	}

	public Long getThue() {
		return Thue;
	}

	public void setThue(Long thue) {
		Thue = thue;
	}

	public Long getPhiPhatSinh() {
		return PhiPhatSinh;
	}

	public void setPhiPhatSinh(Long phiPhatSinh) {
		PhiPhatSinh = phiPhatSinh;
	}
}
