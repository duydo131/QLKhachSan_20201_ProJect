package model.add;

import java.text.SimpleDateFormat;

import model.KhachHang;

public class Customer extends Docx{
	private Integer stt;
	private Long maKH;
    private String tenKH;
    private String cmnd;
    private String gioiTinh;
    private String diaChi;
    private String dienThoai;
    private String quocTich;
    private String ngaySinh;
    private String ngheNghiep;
    private String phanLoaiKH;
    private String tenToChuc;

    private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    
    public Customer(KhachHang kh) {
        this.maKH = kh.getMaKH();
        this.tenKH = kh.getTenKH();
        this.cmnd = kh.getCmnd();
        this.gioiTinh = kh.isGioiTinh() ? "Nam" : "Nữ";
        this.diaChi = kh.getDiaChi();
        this.dienThoai = kh.getDienThoai();
        this.quocTich = kh.getQuocTich();
        this.ngaySinh = format.format(kh.getNgaySinh());
        this.ngheNghiep = kh.getNgheNghiep();
        this.phanLoaiKH = kh.getPhanLoaiKH();
        this.tenToChuc = kh.getTenToChuc();
    }

	public String getGioiTinh() {
		return gioiTinh;
	}

	public Integer getStt() {
		return stt;
	}

	public void setStt(Integer stt) {
		this.stt = stt;
	}

	public Long getMaKH() {
        return maKH;
    }

    public void setMaKH(Long maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getQuocTich() {
        return quocTich;
    }

    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getNgheNghiep() {
        return ngheNghiep;
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }

    public String getPhanLoaiKH() {
        return phanLoaiKH;
    }

    public void setPhanLoaiKH(String phanLoaiKH) {
        this.phanLoaiKH = phanLoaiKH;
    }

    public String getTenToChuc() {
        return tenToChuc;
    }

    public void setTenToChuc(String tenToChuc) {
        this.tenToChuc = tenToChuc;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }
}
