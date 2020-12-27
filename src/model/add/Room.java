package model.add;

public class Room extends Docx{
	private String s[] = {"Trống", "Đã thuê", "Sửa chữa"};
	private Integer stt;
	private Long ID;
    private String LoaiPhong;
    private Long GiaPhong;
    private String TinhTrang;
    private String MoTa;
    
	public Room(Long iD, String loaiPhong, Long giaPhong, Integer tinhTrang) {
		ID = iD;
		LoaiPhong = loaiPhong;
		GiaPhong = giaPhong;
		TinhTrang = s[tinhTrang];
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

	public String getTinhTrang() {
		return TinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		TinhTrang = tinhTrang;
	}

	public String getMoTa() {
		return MoTa;
	}

	public void setMoTa(String moTa) {
		MoTa = moTa;
	}	
}
