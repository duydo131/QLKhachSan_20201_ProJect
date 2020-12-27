package model;

public class Connect {
	private Long ID_TP;
    private Long ID_chiTiet;
    
	public Connect() {
	}
	
	public Connect(Long iD_TP, Long iD_chiTiet) {
		ID_TP = iD_TP;
		ID_chiTiet = iD_chiTiet;
	}

	public Long getID_TP() {
		return ID_TP;
	}

	public void setID_TP(Long iD_TP) {
		ID_TP = iD_TP;
	}

	public Long getID_chiTiet() {
		return ID_chiTiet;
	}

	public void setID_chiTiet(Long iD_chiTiet) {
		ID_chiTiet = iD_chiTiet;
	}
}
