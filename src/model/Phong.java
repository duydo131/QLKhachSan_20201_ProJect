package model;

public class Phong {
	private Long ID;
    private String LoaiPhong;
    private Long GiaPhong;
    private Integer TinhTrang = 0;
    private String TenPhong;
    
	public Phong() {
	}

	public Phong(Long iD, String loaiPhong, Long giaPhong, Integer tinhTrang, String tenPhong) {
		ID = iD;
		LoaiPhong = loaiPhong;
		GiaPhong = giaPhong;
		TinhTrang = tinhTrang;
		TenPhong = tenPhong;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getLoaiPhong() {
		return LoaiPhong;
	}

	public void setLoaiPhong(String loaiPhong) {
		LoaiPhong = loaiPhong;
	}

	public Long getGiaPhong() {
		return GiaPhong;
	}

	public void setGiaPhong(Long giaPhong) {
		GiaPhong = giaPhong;
	}

	public Integer getTinhTrang() {
		return TinhTrang;
	}

	public void setTinhTrang(Integer tinhTrang) {
		TinhTrang = tinhTrang;
	}

	public String getTenPhong() {
		return TenPhong;
	}

	public void setTenPhong(String tenPhong) {
		TenPhong = tenPhong;
	}
}
