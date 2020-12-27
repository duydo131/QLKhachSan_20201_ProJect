package model;

import java.util.Date;

public class ThuePhong {
	private Long ID;
	private Long ID_KH;
	private Date NgayDangKi;
	private Date NgayDen;
	private Long ID_NV_choThue;
	private Boolean active = true;

	public ThuePhong() {
	}
	
	public ThuePhong(Long iD, Long iD_KH, Date ngayDangKi, Date ngayDen, Long ID_NV_choThue) {
		ID = iD;
		ID_KH = iD_KH;
		NgayDangKi = ngayDangKi;
		NgayDen = ngayDen;
		this.ID_NV_choThue = ID_NV_choThue;
	}

	public ThuePhong(Long iD, Long iD_KH, Date ngayDangKi, Date ngayDen, Boolean active, Long ID_NV_choThue) {
		ID = iD;
		ID_KH = iD_KH;
		NgayDangKi = ngayDangKi;
		NgayDen = ngayDen;
		this.active = active;
		this.ID_NV_choThue = ID_NV_choThue;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public Long getID_KH() {
		return ID_KH;
	}

	public void setID_KH(Long iD_KH) {
		ID_KH = iD_KH;
	}

	public Date getNgayDangKi() {
		return NgayDangKi;
	}

	public void setNgayDangKi(Date ngayDangKi) {
		NgayDangKi = ngayDangKi;
	}

	public Date getNgayDen() {
		return NgayDen;
	}

	public void setNgayDen(Date ngayDen) {
		NgayDen = ngayDen;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Long getID_NV_choThue() {
		return ID_NV_choThue;
	}

	public void setID_NV_choThue(Long iD_NV_choThue) {
		ID_NV_choThue = iD_NV_choThue;
	}
}
