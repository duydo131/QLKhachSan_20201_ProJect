package model.add;

import java.util.Date;

public class Info {
	private Long idTP;
	private Long ID_KH;
	private Long ID_NV1;
	private String customer;
	private Long ID_NV2;
	private Long idRoom;
	private String room;
	private Date ngayDen;
	private Date ngayDi;
	private Long tienCoc;
	private Long tienPhat;
	private Long total;
	private Long gia;

	public Info() {
	}
	
	public Long getGia() {
		return gia;
	}

	public void setGia(Long gia) {
		this.gia = gia;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal() {
		long d = 24 * 60 * 60 * 1000;
		this.total = gia * (ngayDi.getTime() - ngayDen.getTime()) / d + tienPhat;
	}
	
	public Long getIdTP() {
		return idTP;
	}

	public void setIdTP(Long idTP) {
		this.idTP = idTP;
	}

	public Long getID_KH() {
		return ID_KH;
	}

	public void setID_KH(Long iD_KH) {
		ID_KH = iD_KH;
	}

	public Long getID_NV1() {
		return ID_NV1;
	}

	public void setID_NV1(Long iD_NV1) {
		ID_NV1 = iD_NV1;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Long getID_NV2() {
		return ID_NV2;
	}

	public void setID_NV2(Long iD_NV2) {
		ID_NV2 = iD_NV2;
	}

	public Long getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(Long idRoom) {
		this.idRoom = idRoom;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public Date getNgayDen() {
		return ngayDen;
	}

	public void setNgayDen(Date ngayDen) {
		this.ngayDen = ngayDen;
	}

	public Date getNgayDi() {
		return ngayDi;
	}

	public void setNgayDi(Date ngayDi) {
		this.ngayDi = ngayDi;
	}

	public Long getTienCoc() {
		return tienCoc;
	}

	public void setTienCoc(Long tienCoc) {
		this.tienCoc = tienCoc;
	}

	public Long getTienPhat() {
		return tienPhat;
	}

	public void setTienPhat(Long tienPhat) {
		this.tienPhat = tienPhat;
	}
}
