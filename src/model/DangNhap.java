package model;

public class DangNhap {
    private String username;
    private String password;
    private Long maNV;

    public DangNhap() {
    }
    
    public DangNhap(String username, String password, Long maNV) {
		this.username = username;
		this.password = password;
		this.maNV = maNV;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getMaNV() {
        return maNV;
    }

    public void setMaNV(Long maNV) {
        this.maNV = maNV;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
