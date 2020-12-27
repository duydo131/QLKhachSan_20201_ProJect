package model;

import java.util.Date;

public class ChiTietThuePhong {
	private Long ID;
    private Long ID_P;
	private String TrangThai;
	private Long TienPhat;
	private String GhiChu;
	private Date NgayHenDi;
	private Date NgayDi;
	private Long TienCoc;
	private Long ID_NV_thanhToan;
	private Boolean active = true;
    
	public ChiTietThuePhong() {
	}

	public ChiTietThuePhong(Long iD, Long iD_P, String trangThai, Long tienPhat, String ghiChu,
			Date ngayHenDi, Date ngayDi, Long tienCoc, Long ID_NV_thanhToan) {
		ID = iD;
		ID_P = iD_P;
		TrangThai = trangThai;
		TienPhat = tienPhat;
		GhiChu = ghiChu;
		NgayHenDi = ngayHenDi;
		NgayDi = ngayDi;
		TienCoc = tienCoc;
		this.ID_NV_thanhToan = ID_NV_thanhToan;
	}

	public ChiTietThuePhong(Long iD, Long iD_P, String trangThai, Long tienPhat, String ghiChu,
			Date ngayHenDi, Date ngayDi, Long tienCoc, Boolean active, Long ID_NV_thanhToan) {
		ID = iD;
		ID_P = iD_P;
		TrangThai = trangThai;
		TienPhat = tienPhat;
		GhiChu = ghiChu;
		NgayHenDi = ngayHenDi;
		NgayDi = ngayDi;
		TienCoc = tienCoc;
		this.ID_NV_thanhToan = ID_NV_thanhToan;
		this.active = active;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public Long getID_P() {
		return ID_P;
	}

	public void setID_P(Long iD_P) {
		ID_P = iD_P;
	}

	public String getTrangThai() {
		return TrangThai;
	}

	public void setTrangThai(String trangThai) {
		TrangThai = trangThai;
	}

	public Long getTienPhat() {
		return TienPhat;
	}

	public void setTienPhat(Long tienPhat) {
		TienPhat = tienPhat;
	}

	public String getGhiChu() {
		return GhiChu;
	}

	public void setGhiChu(String ghiChu) {
		GhiChu = ghiChu;
	}

	public Date getNgayHenDi() {
		return NgayHenDi;
	}

	public void setNgayHenDi(Date ngayHenDi) {
		NgayHenDi = ngayHenDi;
	}

	public Date getNgayDi() {
		return NgayDi;
	}

	public void setNgayDi(Date ngayDi) {
		NgayDi = ngayDi;
	}

	public Long getTienCoc() {
		return TienCoc;
	}

	public void setTienCoc(Long tienCoc) {
		TienCoc = tienCoc;
	}

	public Long getID_NV_thanhToan() {
		return ID_NV_thanhToan;
	}

	public void setID_NV_thanhToan(Long iD_NV_thanhToan) {
		ID_NV_thanhToan = iD_NV_thanhToan;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
