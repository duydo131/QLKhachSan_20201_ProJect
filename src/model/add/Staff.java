package model.add;

import java.text.SimpleDateFormat;

import model.NhanVien;

public class Staff extends Docx{
	private Integer stt;
	private Long ID;
	private String Ten;
	private String GioiTinh;
	private String NgaySinh;
	private String ChuyenMon;
	private String CMND;
	private String DienThoai;
	
	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

	public Staff(NhanVien nv) {
		ID = nv.getID();
		Ten = nv.getTen();
		GioiTinh = nv.getGioiTinh() ? "Nam" : "Nữ";
		NgaySinh = format.format(nv.getNgaySinh());
		ChuyenMon = nv.getChuyenMon();
		CMND = nv.getCMND();
		DienThoai = nv.getDienThoai();
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

	public String getGioiTinh() {
		return GioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		GioiTinh = gioiTinh;
	}

	public String getNgaySinh() {
		return NgaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
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
}