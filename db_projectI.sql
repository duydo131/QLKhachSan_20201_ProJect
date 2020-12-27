use ProjectI_QLKhachSan;

Create table khachHang(
	ID bigint primary key auto_increment,
    TenKH nvarchar(255) not null,
    CMND char(30) not null,
    GioiTinh boolean not null,
    DiaChi nvarchar(255),
    DienThoai char(11),
    QuocTich nvarchar(50),
    NgaySinh Date,
    Nghe nvarchar(50),
    PhanLoaiKhachHang nvarchar(200),
	ToChuc nvarchar(200),
    active boolean default true
);

create table login(
	User char(30) not null,
    Password char(30) not null,
    ID_NV bigint not null
);

create table thietbi(
	ID bigint primary key auto_increment,
    TenTB nvarchar(50) not null,
    Gia bigint not null,
    active boolean default true
);

create table danhSachThietBi(
	ID_P bigint not null,
    ID_TB bigint not null,
    SoLuong bigint default 0
);

create table hoaDon(
	ID bigint primary key auto_increment,
    ID_TP bigint not null,
    NgayLap datetime not null,
    Thue bigint not null,
    PhiPhatSinh bigint default 0,
    active boolean default true
);

create table thuePhong(
	ID bigint primary key auto_increment,
	ID_KH bigint not null,
	NgayDangKi datetime not null,
	NgayDen datetime not null,
    ID_NV_choThue bigint,
    active boolean default true
);

create table phong(
	ID bigint primary key auto_increment,
    LoaiPhong char(5),
    GiaPhong bigint not null,
    TinhTrang int default 0,
    TenPhong nvarchar(255) not null
);

create table nhanVien(
	ID bigint primary key auto_increment,
    Ten nvarchar(50) not null,
    GioiTinh boolean not null,
    NgaySinh Date not null,
    ChuyenMon nvarchar(50),
    CMND char(30) not null,	
    DienThoai char(20),
    active boolean default true
);

create table chiTietThuePhong(
	ID bigint primary key auto_increment,
    ID_P bigint not null,
	TrangThai Nvarchar(255),
	TienPhat bigint,
    NgayDi date,
    NgayHenDi date,
    TienCoc bigint default 0,
	GhiChu nvarchar(255),
    ID_NV_thanhToan bigint,
    active boolean default true
);

create table connect(
	ID_TP bigint not null,
    ID_chiTiet bigint not null
); 

alter table thuePhong add constraint NVThue foreign key (ID_NV_choThue) references nhanVien(ID);
alter table chiTietThuePhong add constraint NVThanhToan foreign key (ID_NV_thanhToan) references nhanVien(ID);
alter table login add constraint login_NV foreign key (ID_NV) references nhanVien(ID);
alter table danhSachThietBi add constraint ds_TB foreign key (ID_TB) references thietbi(ID);
alter table danhSachThietBi add constraint ds_P foreign key (ID_P) references phong(ID);
alter table hoaDon add constraint hd_tp foreign key (ID_TP) references thuePhong(ID);
alter table thuePhong add constraint tp_KH foreign key (ID_KH) references khachHang(ID);
alter table chiTietThuePhong add constraint ct_p foreign key (ID_P) references phong(ID);
alter table connect add constraint cn_TP foreign key (ID_TP) references thuePhong(ID);
alter table connect add constraint cn_ct foreign key (ID_chiTiet) references chiTietThuePhong(ID);