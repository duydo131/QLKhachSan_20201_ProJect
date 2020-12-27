package model;

import java.util.Date;

import model.add.Docx;

public class KhachHang extends Docx{
	private Integer stt;
    private Long maKH;
    private String tenKH;
    private String cmnd;
    private boolean gioiTinh = true;
    private String gt;
    private String diaChi;
    private String dienThoai;
    private String quocTich;
    private Date ngaySinh;
    private String ngheNghiep;
    private String phanLoaiKH;
    private String tenToChuc;
    private Boolean active = true;

    public KhachHang() {
    }

    public KhachHang(Long maKH, String tenKH, String cmnd, boolean gioiTinh, String diaChi, String dienThoai,
                     String quocTich, Date ngaySinh, String ngheNghiep, String phanLoaiKH, String tenToChuc) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.cmnd = cmnd;
        this.gioiTinh = gioiTinh;
        this.gt = this.gioiTinh ? "Nam" : "Nữ";
        this.diaChi = diaChi;
        this.dienThoai = dienThoai;
        this.quocTich = quocTich;
        this.ngaySinh = ngaySinh;
        this.ngheNghiep = ngheNghiep;
        this.phanLoaiKH = phanLoaiKH;
        this.tenToChuc = tenToChuc;
    }

    public KhachHang(Long maKH, String tenKH, String cmnd, boolean gioiTinh, String diaChi, String dienThoai,
			String quocTich, Date ngaySinh, String ngheNghiep, String phanLoaiKH, String tenToChuc, Boolean active) {
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.cmnd = cmnd;
		this.gioiTinh = gioiTinh;
        this.gt = this.gioiTinh ? "Nam" : "Nữ";
		this.diaChi = diaChi;
		this.dienThoai = dienThoai;
		this.quocTich = quocTich;
		this.ngaySinh = ngaySinh;
		this.ngheNghiep = ngheNghiep;
		this.phanLoaiKH = phanLoaiKH;
		this.tenToChuc = tenToChuc;
		this.active = active;
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

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getGt() {
		return gt;
	}

	public void setGt(String gt) {
		this.gt = gt;
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

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
