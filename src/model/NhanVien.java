package model;

import java.util.Date;

import model.add.Docx;

public class NhanVien extends Docx{
	private Integer stt;
	private Long ID;
	private String Ten;
	private Boolean GioiTinh = true;
	private String gt;
	private Date NgaySinh;
	private String ChuyenMon;
	private String CMND;
	private String DienThoai;
	private Boolean active = true;
	
	public NhanVien() {
	}

	public NhanVien(Long iD, String ten, Boolean gioiTinh, Date ngaySinh, String chuyenMon, String cMND,
			String dienThoai) {
		ID = iD;
		Ten = ten;
		GioiTinh = gioiTinh;
		this.gt = this.GioiTinh ? "Nam" : "Nữ";
		NgaySinh = ngaySinh;
		ChuyenMon = chuyenMon;
		CMND = cMND;
		DienThoai = dienThoai;
	}

	public NhanVien(Long iD, String ten, Boolean gioiTinh, Date ngaySinh, String chuyenMon, String cMND,
			String dienThoai, Boolean active) {
		ID = iD;
		Ten = ten;
		GioiTinh = gioiTinh;
		this.gt = this.GioiTinh ? "Nam" : "Nữ";
		NgaySinh = ngaySinh;
		ChuyenMon = chuyenMon;
		CMND = cMND;
		DienThoai = dienThoai;
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

	public String getTen() {
		return Ten;
	}

	public void setTen(String ten) {
		Ten = ten;
	}

	public Boolean getGioiTinh() {
		return GioiTinh;
	}

	public void setGioiTinh(Boolean gioiTinh) {
		GioiTinh = gioiTinh;
	}

	public String getGt() {
		return gt;
	}

	public void setGt(String gt) {
		this.gt = gt;
	}

	public Date getNgaySinh() {
		return NgaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		NgaySinh = ngaySinh;
	}

	public String getChuyenMon() {
		return ChuyenMon;
	}

	public void setChuyenMon(String chuyenMon) {
		ChuyenMon = chuyenMon;
	}

	public String getCMND() {
		return CMND;
	}

	public void setCMND(String cMND) {
		CMND = cMND;
	}

	public String getDienThoai() {
		return DienThoai;
	}

	public void setDienThoai(String dienThoai) {
		DienThoai = dienThoai;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
