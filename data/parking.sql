USE [PARK]
GO
/****** Object:  Table [dbo].[KhuGuiXe]    Script Date: 24-04-2024 20:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhuGuiXe](
	[MaKhu] [char](4) NOT NULL,
	[TenKhu] [nvarchar](50) NOT NULL,
	[DiaChi] [nvarchar](255) NULL,
	[SoLuongChoDau] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaKhu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiXe]    Script Date: 24-04-2024 20:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiXe](
	[MaLoaiXe] [varchar](5) NOT NULL,
	[TenLoaiXe] [nvarchar](30) NULL,
	[MaKhu] [char](4) NULL,
	[MaLoaiGia] [varchar](6) NULL,
 CONSTRAINT [PK_LoaiXe] PRIMARY KEY CLUSTERED 
(
	[MaLoaiXe] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TheXeCuDan]    Script Date: 24-04-2024 20:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TheXeCuDan](
	[MaThe] [char](7) NOT NULL,
	[ThoiGianDangKy] [datetime] NULL,
	[MaCuDan] [char](6) NULL,
	[MaLoaiXe] [varchar](5) NULL,
 CONSTRAINT [PK_TheXeCuDan] PRIMARY KEY CLUSTERED 
(
	[MaThe] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GiaoDich]    Script Date: 24-04-2024 20:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GiaoDich](
	[MaGD] [int] IDENTITY(1,1) NOT NULL,
	[ThoiGianVao] [datetime] NULL,
	[ThoiGianRa] [datetime] NULL,
	[SoNgayGui] [int] NULL,
	[BienSoXe] [char](13) NULL,
	[PhiGuiXe] [float] NOT NULL,
	[TrangThai] [bit] NOT NULL,
	[Hinh] [nvarchar](50) NULL,
	[MaNV] [int] NULL,
	[CuDanID] [char](7) NULL,
	[is_Delete] [int] NOT NULL,
	[KhachVangLaiID] [char](7) NULL,
 CONSTRAINT [PK_GiaoDich] PRIMARY KEY CLUSTERED 
(
	[MaGD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[View_LuongXeCuDanGuiNam]    Script Date: 24-04-2024 20:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[View_LuongXeCuDanGuiNam]
AS
	SELECT  YEAR(ThoiGianVao) Nam, MONTH(ThoiGianVao) THANG, DAY(ThoiGianVao) NGAY, x.MaKhu
	FROM GiaoDich g JOIN TheXeCuDan t ON g.CuDanID = t.MaThe
					JOIN LoaiXe x ON t.MaLoaiXe = x.MaLoaiXe
					JOIN KhuGuiXe k ON k.MaKhu = x.MaKhu
	GROUP BY  YEAR(ThoiGianVao), x.MaKhu, MONTH(ThoiGianVao), DAY(ThoiGianVao)
GO
/****** Object:  View [dbo].[View_LuongXeCuDanGui]    Script Date: 24-04-2024 20:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   VIEW [dbo].[View_LuongXeCuDanGui]
AS
	SELECT  YEAR(ThoiGianVao) Nam, MONTH(ThoiGianVao) THANG, DAY(ThoiGianVao) NGAY, x.MaKhu
	FROM GiaoDich g JOIN TheXeCuDan t ON g.CuDanID = t.MaThe
					JOIN LoaiXe x ON t.MaLoaiXe = x.MaLoaiXe
					JOIN KhuGuiXe k ON k.MaKhu = x.MaKhu
	GROUP BY  YEAR(ThoiGianVao), x.MaKhu, MONTH(ThoiGianVao), DAY(ThoiGianVao)
GO
/****** Object:  Table [dbo].[TheXeKhachVangLai]    Script Date: 24-04-2024 20:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TheXeKhachVangLai](
	[MaThe] [char](7) NOT NULL,
	[MaLoaiXe] [varchar](5) NULL,
 CONSTRAINT [PK_TheXeKhachVangLai] PRIMARY KEY CLUSTERED 
(
	[MaThe] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[View_LuongXeKhachVangLaiGui]    Script Date: 24-04-2024 20:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   VIEW [dbo].[View_LuongXeKhachVangLaiGui]
AS
	SELECT  YEAR(ThoiGianVao) Nam, MONTH(ThoiGianVao) THANG, DAY(ThoiGianVao) NGAY, x.MaKhu
	FROM GiaoDich g JOIN TheXeKhachVangLai t ON g.KhachVangLaiID = t.MaThe
					JOIN LoaiXe x ON t.MaLoaiXe = x.MaLoaiXe
					JOIN KhuGuiXe k ON k.MaKhu = x.MaKhu
	GROUP BY  YEAR(ThoiGianVao), x.MaKhu, MONTH(ThoiGianVao), DAY(ThoiGianVao)
GO
/****** Object:  View [dbo].[View_ThoiGianCuDanGui]    Script Date: 24-04-2024 20:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   VIEW [dbo].[View_ThoiGianCuDanGui]
AS
	SELECT  YEAR(ThoiGianVao) Nam, MONTH(ThoiGianVao) Thang, DAY(ThoiGianVao) Ngay, x.MaKhu
	FROM GiaoDich g JOIN TheXeCuDan t ON g.CuDanID = t.MaThe
					JOIN LoaiXe x ON t.MaLoaiXe = x.MaLoaiXe
					JOIN KhuGuiXe k ON k.MaKhu = x.MaKhu
	GROUP BY  YEAR(ThoiGianVao), x.MaKhu, MONTH(ThoiGianVao), DAY(ThoiGianVao)
GO
/****** Object:  View [dbo].[View_ThoiGianKhachVangLaiGui]    Script Date: 24-04-2024 20:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- View luong xe khách vãng lai gửi theo năm
CREATE   VIEW [dbo].[View_ThoiGianKhachVangLaiGui]
AS
	SELECT  YEAR(ThoiGianVao) Nam, MONTH(ThoiGianVao) Thang, DAY(ThoiGianVao) Ngay, x.MaKhu
	FROM GiaoDich g JOIN TheXeKhachVangLai t ON g.KhachVangLaiID = t.MaThe
					JOIN LoaiXe x ON t.MaLoaiXe = x.MaLoaiXe
					JOIN KhuGuiXe k ON k.MaKhu = x.MaKhu
	GROUP BY  YEAR(ThoiGianVao), x.MaKhu, MONTH(ThoiGianVao), DAY(ThoiGianVao)
GO
/****** Object:  Table [dbo].[BangGia]    Script Date: 24-04-2024 20:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BangGia](
	[MaLoaiGia] [varchar](6) NOT NULL,
	[ThoiGian] [nvarchar](20) NOT NULL,
	[Gia] [float] NOT NULL,
 CONSTRAINT [PK_BangGia] PRIMARY KEY CLUSTERED 
(
	[MaLoaiGia] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CuDan]    Script Date: 24-04-2024 20:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CuDan](
	[MaCuDan] [char](6) NOT NULL,
	[Ho] [nvarchar](30) NOT NULL,
	[Ten] [nvarchar](30) NOT NULL,
	[GioiTinh] [int] NULL,
	[SDT] [char](11) NULL,
	[Email] [varchar](50) NULL,
	[is_Delete] [int] NOT NULL,
 CONSTRAINT [PK_CuDan] PRIMARY KEY CLUSTERED 
(
	[MaCuDan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 24-04-2024 20:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[MaNV] [int] IDENTITY(1,1) NOT NULL,
	[Ho] [nvarchar](30) NOT NULL,
	[Ten] [nvarchar](30) NOT NULL,
	[TenDangNhap] [varchar](50) NOT NULL,
	[MatKhau] [varchar](50) NOT NULL,
	[Email] [varchar](50) NOT NULL,
	[SDT] [char](10) NULL,
	[GioiTinh] [int] NULL,
	[DiaChi] [nvarchar](255) NULL,
	[VaiTro] [bit] NULL,
	[TrangThai] [int] NULL,
	[is_Delete] [int] NOT NULL,
	[HinhAnh] [nvarchar](50) NULL,
 CONSTRAINT [PK_NhanVien] PRIMARY KEY CLUSTERED 
(
	[MaNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Remember]    Script Date: 24-04-2024 20:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Remember](
	[userName] [varchar](50) NOT NULL,
	[pass] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[userName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[BangGia] ([MaLoaiGia], [ThoiGian], [Gia]) VALUES (N'GOTO01', N'Ngày', 20000)
INSERT [dbo].[BangGia] ([MaLoaiGia], [ThoiGian], [Gia]) VALUES (N'GOTO02', N'Tuần', 100000)
INSERT [dbo].[BangGia] ([MaLoaiGia], [ThoiGian], [Gia]) VALUES (N'GOTO03', N'Tháng', 500000)
INSERT [dbo].[BangGia] ([MaLoaiGia], [ThoiGian], [Gia]) VALUES (N'GOTO04', N'Năm', 5000000)
INSERT [dbo].[BangGia] ([MaLoaiGia], [ThoiGian], [Gia]) VALUES (N'GXD01', N'Ngày', 3000)
INSERT [dbo].[BangGia] ([MaLoaiGia], [ThoiGian], [Gia]) VALUES (N'GXD02', N'Tuần', 18000)
INSERT [dbo].[BangGia] ([MaLoaiGia], [ThoiGian], [Gia]) VALUES (N'GXD03', N'Tháng', 60000)
INSERT [dbo].[BangGia] ([MaLoaiGia], [ThoiGian], [Gia]) VALUES (N'GXD04', N'Năm', 600000)
INSERT [dbo].[BangGia] ([MaLoaiGia], [ThoiGian], [Gia]) VALUES (N'GXM01', N'Ngày', 5000)
INSERT [dbo].[BangGia] ([MaLoaiGia], [ThoiGian], [Gia]) VALUES (N'GXM02', N'Tuần', 30000)
INSERT [dbo].[BangGia] ([MaLoaiGia], [ThoiGian], [Gia]) VALUES (N'GXM03', N'Tháng', 80000)
INSERT [dbo].[BangGia] ([MaLoaiGia], [ThoiGian], [Gia]) VALUES (N'GXM04', N'Năm', 800000)
INSERT [dbo].[BangGia] ([MaLoaiGia], [ThoiGian], [Gia]) VALUES (N'XDD02', N'Tuần', 24000)
GO
INSERT [dbo].[CuDan] ([MaCuDan], [Ho], [Ten], [GioiTinh], [SDT], [Email], [is_Delete]) VALUES (N'CD0001', N'Nguyễn', N'Hạnh', 0, N'0324278678 ', N'hanh@gmail.com', 0)
INSERT [dbo].[CuDan] ([MaCuDan], [Ho], [Ten], [GioiTinh], [SDT], [Email], [is_Delete]) VALUES (N'CD0002', N'Trần văn', N'Cường', 1, N'0356982304 ', N'vancuong@gmail.com', 0)
INSERT [dbo].[CuDan] ([MaCuDan], [Ho], [Ten], [GioiTinh], [SDT], [Email], [is_Delete]) VALUES (N'CD0003', N'Nguyễn Văn', N'Hạnh', 1, N'0976283953 ', N'vanhanh@gmail.com', 0)
INSERT [dbo].[CuDan] ([MaCuDan], [Ho], [Ten], [GioiTinh], [SDT], [Email], [is_Delete]) VALUES (N'CD0004', N'ĐỖ VĂN', N'MINH', 2, N'0968095685 ', N'dominh@gmail.com', 0)
INSERT [dbo].[CuDan] ([MaCuDan], [Ho], [Ten], [GioiTinh], [SDT], [Email], [is_Delete]) VALUES (N'CD0005', N'NGUYỄN TẤN', N'HIẾU', 1, N'0927594734 ', N'tanhieu@gmail.com', 0)
INSERT [dbo].[CuDan] ([MaCuDan], [Ho], [Ten], [GioiTinh], [SDT], [Email], [is_Delete]) VALUES (N'CD0006', N'TRẦN VĂN', N'NAM', 1, N'0924774498 ', N'namtran@gmail.com', 0)
INSERT [dbo].[CuDan] ([MaCuDan], [Ho], [Ten], [GioiTinh], [SDT], [Email], [is_Delete]) VALUES (N'CD0007', N'NGUYỄN HỮU', N'TRÍ', 1, N'0946984711 ', N'huutri@gmail.com', 0)
INSERT [dbo].[CuDan] ([MaCuDan], [Ho], [Ten], [GioiTinh], [SDT], [Email], [is_Delete]) VALUES (N'CD0008', N'NGUYỄN HOÀNG THIÊN', N'PHƯỚC', 1, N'0912499836 ', N'hoangphuoc@gmail.com', 0)
INSERT [dbo].[CuDan] ([MaCuDan], [Ho], [Ten], [GioiTinh], [SDT], [Email], [is_Delete]) VALUES (N'CD0009', N'HỒ HỮU', N'HẬU', 1, N'0924984876 ', N'hohuuhau@gmail.com', 0)
INSERT [dbo].[CuDan] ([MaCuDan], [Ho], [Ten], [GioiTinh], [SDT], [Email], [is_Delete]) VALUES (N'CD0010', N'PHAN TẤN', N'VIỆT', 2, N'0924832716 ', N'phantanviet@gmail.com', 0)
INSERT [dbo].[CuDan] ([MaCuDan], [Ho], [Ten], [GioiTinh], [SDT], [Email], [is_Delete]) VALUES (N'CD0011', N'LÊ THÀNH', N'PHƯƠNG', 1, N'0922948096 ', N'lethanhphuong@gmail.com', 0)
INSERT [dbo].[CuDan] ([MaCuDan], [Ho], [Ten], [GioiTinh], [SDT], [Email], [is_Delete]) VALUES (N'CD0012', N'ĐẶNG BẢO', N'VIỆT', 1, N'0917749344 ', N'dangbaoviet@gmail.com', 0)
INSERT [dbo].[CuDan] ([MaCuDan], [Ho], [Ten], [GioiTinh], [SDT], [Email], [is_Delete]) VALUES (N'CD0013', N'TRẦN', N'NHẠT', 1, N'0946219377 ', N'nhat@gmail.com', 0)
INSERT [dbo].[CuDan] ([MaCuDan], [Ho], [Ten], [GioiTinh], [SDT], [Email], [is_Delete]) VALUES (N'CD0014', N'TRƯƠNG THÀNH', N'ĐẠT', 1, N'0991509408 ', N'truongdat@gmail.com', 0)
INSERT [dbo].[CuDan] ([MaCuDan], [Ho], [Ten], [GioiTinh], [SDT], [Email], [is_Delete]) VALUES (N'CD0015', N'TÔ VĂN', N'NĂNG', 2, N'0915134551 ', N'vannang@gmail.com', 0)
INSERT [dbo].[CuDan] ([MaCuDan], [Ho], [Ten], [GioiTinh], [SDT], [Email], [is_Delete]) VALUES (N'CD0016', N'NGUYỄN NHẬT', N'VĨNH', 1, N'0917886371 ', N'nhatvinh@gmail.com', 0)
INSERT [dbo].[CuDan] ([MaCuDan], [Ho], [Ten], [GioiTinh], [SDT], [Email], [is_Delete]) VALUES (N'CD0017', N'NGUYỄN VĂN', N'HUY', 1, N'0960620997 ', N'vanhuy@gmail.com', 0)
INSERT [dbo].[CuDan] ([MaCuDan], [Ho], [Ten], [GioiTinh], [SDT], [Email], [is_Delete]) VALUES (N'CD0018', N'NGUYỄN NHƯ', N'NGỌC', 0, N'0912880267 ', N'nhungoc@gmail.com', 0)
INSERT [dbo].[CuDan] ([MaCuDan], [Ho], [Ten], [GioiTinh], [SDT], [Email], [is_Delete]) VALUES (N'CD0019', N'PHẠM THÀNH', N'TÂM', 1, N'0918161783 ', N'thanhtam@gmail.com', 0)
INSERT [dbo].[CuDan] ([MaCuDan], [Ho], [Ten], [GioiTinh], [SDT], [Email], [is_Delete]) VALUES (N'CD0020', N'ĐINH TẤN', N'CÔNG', 1, N'0918182551 ', N'tancong@gmail.com', 0)
INSERT [dbo].[CuDan] ([MaCuDan], [Ho], [Ten], [GioiTinh], [SDT], [Email], [is_Delete]) VALUES (N'CD0021', N'LÊ MINH', N'ĐIỀN', 1, N'0948199564 ', N'minhdien@gmail.com', 0)
INSERT [dbo].[CuDan] ([MaCuDan], [Ho], [Ten], [GioiTinh], [SDT], [Email], [is_Delete]) VALUES (N'CD0022', N'LÊ PHẠM KIM', N'THANH', 0, N'0924696779 ', N'kkkimthanh@gmail.com', 0)
INSERT [dbo].[CuDan] ([MaCuDan], [Ho], [Ten], [GioiTinh], [SDT], [Email], [is_Delete]) VALUES (N'CD0023', N'TRẦN ĐÌNH', N'TRƯỜNG', 2, N'0941528106 ', N'dinhtruong@gmail.com', 0)
INSERT [dbo].[CuDan] ([MaCuDan], [Ho], [Ten], [GioiTinh], [SDT], [Email], [is_Delete]) VALUES (N'CD0024', N'NGUYỄN VĂN', N'SÁU', 1, N'0940711328 ', N'nguyenvansau@gmail.com', 0)
GO
SET IDENTITY_INSERT [dbo].[GiaoDich] ON 

INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (23, CAST(N'2023-11-22T15:49:01.567' AS DateTime), CAST(N'2023-11-23T15:49:01.000' AS DateTime), 2, NULL, 40000, 1, N'xd05.png', 1, NULL, 0, N'TVL0001')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (24, CAST(N'2023-11-22T15:49:01.567' AS DateTime), CAST(N'2023-12-06T15:49:01.000' AS DateTime), 15, NULL, 80000, 1, N'xd07.png', 2, NULL, 0, N'TVL0004')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (25, CAST(N'2023-11-22T15:49:01.567' AS DateTime), CAST(N'2023-11-28T15:49:01.000' AS DateTime), 7, N'84 - B1 74444', 21000, 1, N'xm10.png', 2, NULL, 0, N'TVL0010')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (26, CAST(N'2023-11-22T15:49:01.567' AS DateTime), CAST(N'2023-11-29T15:49:01.000' AS DateTime), 8, N'37A - 749.49 ', 160000, 1, N'oto 02.png', 1, NULL, 0, N'TVL0003')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (27, CAST(N'2023-11-22T15:49:01.567' AS DateTime), CAST(N'2023-11-26T15:49:01.000' AS DateTime), 5, NULL, 15000, 1, N'xd02.png', 1, N'TCD0002', 0, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (28, CAST(N'2023-11-22T15:49:01.567' AS DateTime), CAST(N'2023-11-28T15:49:01.000' AS DateTime), 7, NULL, 30000, 1, N'xd05.png', 2, N'TCD0006', 0, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (29, CAST(N'2023-11-22T15:49:01.567' AS DateTime), CAST(N'2024-03-22T15:49:01.000' AS DateTime), 122, N'47 - B2 31531', 540000, 1, N'xm09.png', 2, N'TCD0008', 0, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (30, CAST(N'2023-11-22T15:49:01.567' AS DateTime), CAST(N'2023-11-24T15:49:01.000' AS DateTime), 4, N'37A - 749.53 ', 80000, 1, N'oto 01.png', 1, N'TCD0010', 1, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (31, CAST(N'2023-11-22T15:49:01.570' AS DateTime), CAST(N'2023-11-22T19:57:59.000' AS DateTime), 1, N'37A - 444.444', 3000, 1, N'oto 03.png', 1, N'TCD0002', 0, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (32, CAST(N'2023-11-22T16:38:34.393' AS DateTime), CAST(N'2023-11-23T01:41:17.000' AS DateTime), 2, NULL, 30000, 1, N'xd05.png', 1, N'TCD0006', 0, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (33, CAST(N'2023-11-22T16:57:32.910' AS DateTime), CAST(N'2023-11-23T10:46:18.000' AS DateTime), 2, NULL, 60000, 1, N'xd03.png', 1, NULL, 0, N'TVL0021')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (34, CAST(N'2023-11-22T16:58:17.690' AS DateTime), CAST(N'2023-11-24T22:31:37.000' AS DateTime), 3, N'51G - 925.64 ', 100000, 1, N'oto 10.png', 1, NULL, 0, N'TVL0019')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (35, CAST(N'2023-11-22T17:02:22.610' AS DateTime), CAST(N'2023-11-25T11:00:31.000' AS DateTime), 4, N'15  -B1 56789', 100000, 1, N'xm01.png', 1, NULL, 0, N'TVL0015')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (36, CAST(N'2023-11-22T17:12:15.850' AS DateTime), CAST(N'2023-12-02T11:06:44.000' AS DateTime), 11, N'37 - F9 1555 ', 500000, 1, N'xm04.png', 1, NULL, 0, N'TVL0011')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (37, CAST(N'2023-11-22T17:14:00.417' AS DateTime), CAST(N'2023-11-22T19:50:50.000' AS DateTime), 1, N'51A - 138.83 ', 20000, 1, N'oto 07.png', 1, N'TCD0003', 0, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (38, CAST(N'2023-11-22T18:39:30.710' AS DateTime), CAST(N'2023-12-05T00:00:00.000' AS DateTime), 14, N'36A - 567.89 ', 300000, 1, N'oto 06.png', 1, N'TCD0010', 0, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (39, CAST(N'2023-11-22T21:05:52.230' AS DateTime), CAST(N'2023-12-01T00:29:07.000' AS DateTime), 10, N'71 - B3 33333', 80000, 1, N'xm02.png', 2, NULL, 0, N'TVL0007')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (40, CAST(N'2023-11-23T01:22:42.737' AS DateTime), NULL, 0, N'37A - 749.53 ', 20000, 0, N'oto 01.png', 2, NULL, 0, N'TVL0009')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (41, CAST(N'2023-11-23T01:39:37.690' AS DateTime), CAST(N'2023-11-23T20:48:51.000' AS DateTime), 1, NULL, 80000, 1, N'xd07.png', 2, NULL, 0, N'TVL0013')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (42, CAST(N'2022-04-15T00:00:00.000' AS DateTime), CAST(N'2022-05-15T00:00:00.000' AS DateTime), 31, N'79 - G1 36729', 500000, 1, NULL, 1, N'TCD0010', 0, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (43, CAST(N'2022-04-17T00:00:00.000' AS DateTime), CAST(N'2022-04-23T00:00:00.000' AS DateTime), 5, N'85 - K1 34552', 18000, 1, NULL, 1, N'TCD0002', 0, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (44, CAST(N'2022-07-03T00:00:00.000' AS DateTime), CAST(N'2022-07-09T00:00:00.000' AS DateTime), 7, N'23 - C1 34627', 21000, 1, NULL, 2, N'TCD0006', 0, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (45, CAST(N'2021-08-03T00:00:00.000' AS DateTime), CAST(N'2021-08-09T00:00:00.000' AS DateTime), 121, N'68 - H1 58376', 800000, 1, NULL, 2, N'TCD0008', 0, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (46, CAST(N'2021-01-22T00:00:00.000' AS DateTime), CAST(N'2021-01-25T00:00:00.000' AS DateTime), 3, N'79 - G1 36729', 60000, 1, NULL, 1, N'TCD0010', 0, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (47, CAST(N'2021-02-03T00:00:00.000' AS DateTime), CAST(N'2021-02-09T00:00:00.000' AS DateTime), 3, N'80 - X1 74907', 100000, 1, NULL, 1, N'TCD0002', 0, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (48, CAST(N'2020-07-13T00:00:00.000' AS DateTime), CAST(N'2020-07-17T00:00:00.000' AS DateTime), 3, N'79 - G1 36729', 60000, 1, NULL, 1, N'TCD0010', 0, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (49, CAST(N'2020-07-17T00:00:00.000' AS DateTime), CAST(N'2020-07-20T00:00:00.000' AS DateTime), 2, N'59 - G1 67890', 6000, 1, NULL, 1, NULL, 0, N'TVL0002')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (50, CAST(N'2020-09-13T00:00:00.000' AS DateTime), CAST(N'2020-09-16T00:00:00.000' AS DateTime), 3, N'63 - E1 32452', 60000, 1, NULL, 2, NULL, 0, N'TVL0006')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (51, CAST(N'2021-02-13T00:00:00.000' AS DateTime), CAST(N'2021-02-14T00:00:00.000' AS DateTime), 2, N'78 - H1 34578', 10000, 1, NULL, 2, NULL, 0, N'TVL0008')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (52, CAST(N'2021-12-08T00:00:00.000' AS DateTime), CAST(N'2021-12-12T00:00:00.000' AS DateTime), 7, N'77 - X1 67890', 100000, 1, NULL, 1, NULL, 0, N'TVL0014')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (53, CAST(N'2019-07-10T00:00:00.000' AS DateTime), CAST(N'2019-07-12T00:00:00.000' AS DateTime), 2, N'59 - G1 67890', 6000, 1, NULL, 1, N'TCD0010', 0, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (54, CAST(N'2020-04-05T00:00:00.000' AS DateTime), CAST(N'2019-04-12T00:00:00.000' AS DateTime), 3, N'63 - E1 32452', 60000, 1, NULL, 2, N'TCD0011', 0, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (55, CAST(N'2019-07-18T00:00:00.000' AS DateTime), CAST(N'2019-07-23T00:00:00.000' AS DateTime), 2, N'78 - H1 34578', 10000, 1, NULL, 2, N'TCD0020', 0, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (56, CAST(N'2019-10-16T00:00:00.000' AS DateTime), CAST(N'2019-11-12T00:00:00.000' AS DateTime), 7, N'77 - X1 67890', 100000, 1, NULL, 1, N'TCD0007', 0, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (57, CAST(N'2023-11-24T22:31:20.480' AS DateTime), CAST(N'2023-12-04T20:01:38.937' AS DateTime), 11, N'30E - 921.15 ', 60000, 1, N'oto 05.png', 1, N'TCD0008', 0, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (58, CAST(N'2023-11-25T10:25:33.753' AS DateTime), CAST(N'2023-11-25T10:25:45.137' AS DateTime), 0, NULL, 60000, 1, N'xd08.png', 1, NULL, 0, N'TVL0022')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (59, CAST(N'2023-11-25T10:26:32.427' AS DateTime), CAST(N'2023-11-28T10:27:48.770' AS DateTime), 0, N'47 - B2 31531', 5000, 1, N'xm09.png', 1, NULL, 0, N'TVL0020')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (60, CAST(N'2023-11-28T10:44:35.673' AS DateTime), CAST(N'2023-11-28T10:44:55.690' AS DateTime), 0, NULL, 3000, 1, NULL, 1, NULL, 0, N'TVL0019')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (61, CAST(N'2023-11-25T11:01:59.590' AS DateTime), CAST(N'2023-11-28T11:02:24.013' AS DateTime), 0, NULL, 3000, 1, NULL, 1, NULL, 0, N'TVL0010')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (62, CAST(N'2023-11-25T11:06:34.347' AS DateTime), CAST(N'2023-11-28T11:06:56.000' AS DateTime), 4, NULL, 80000, 1, NULL, 1, NULL, 0, N'TVL0016')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (63, CAST(N'2023-11-25T11:09:39.053' AS DateTime), CAST(N'2023-11-28T11:09:53.950' AS DateTime), 0, NULL, 3000, 1, NULL, 1, N'TCD0001', 0, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (64, CAST(N'2023-11-25T11:16:49.997' AS DateTime), CAST(N'2023-11-28T11:18:15.740' AS DateTime), 0, NULL, 5000, 1, NULL, 1, N'TCD0008', 0, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (65, CAST(N'2023-11-29T18:43:37.200' AS DateTime), CAST(N'2023-12-03T18:47:21.137' AS DateTime), 0, NULL, 20000, 1, NULL, 1, N'TCD0004', 0, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (66, CAST(N'2023-11-29T18:45:08.307' AS DateTime), CAST(N'2023-12-03T18:50:58.050' AS DateTime), 0, NULL, 20000, 1, NULL, 1, N'TCD0002', 0, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (67, CAST(N'2023-11-29T19:04:05.530' AS DateTime), CAST(N'2023-12-01T19:24:25.000' AS DateTime), 3, NULL, 9000, 1, NULL, 1, NULL, 0, N'TVL0002')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (68, CAST(N'2023-11-29T19:04:12.983' AS DateTime), CAST(N'2023-11-30T19:26:15.487' AS DateTime), 0, NULL, 5000, 1, NULL, 1, NULL, 0, N'TVL0001')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (69, CAST(N'2023-11-29T19:04:17.197' AS DateTime), CAST(N'2023-12-03T19:09:42.000' AS DateTime), 5, NULL, 100000, 1, NULL, 1, NULL, 0, N'TVL0007')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (70, CAST(N'2023-11-29T19:04:22.250' AS DateTime), CAST(N'2023-12-03T19:05:19.000' AS DateTime), 5, NULL, 25000, 1, NULL, 1, NULL, 0, N'TVL0004')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (71, CAST(N'2023-11-29T19:41:30.600' AS DateTime), CAST(N'2023-12-04T21:16:17.957' AS DateTime), 6, NULL, 120000, 1, NULL, 1, NULL, 0, N'TVL0008')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (72, CAST(N'2023-11-29T19:41:32.213' AS DateTime), CAST(N'2023-12-04T19:43:02.000' AS DateTime), 6, NULL, 120000, 1, NULL, 1, NULL, 0, N'TVL0010')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (73, CAST(N'2023-11-29T19:41:33.313' AS DateTime), CAST(N'2023-12-04T21:05:28.337' AS DateTime), 6, NULL, 20000, 1, NULL, 1, N'TCD0020', 0, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (74, CAST(N'2023-11-29T19:41:34.287' AS DateTime), CAST(N'2023-12-04T20:13:04.273' AS DateTime), 6, NULL, 20000, 1, NULL, 1, N'TCD0009', 0, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (75, CAST(N'2023-11-29T19:41:35.233' AS DateTime), CAST(N'2023-12-04T20:40:11.000' AS DateTime), 6, NULL, 120000, 1, NULL, 1, N'TCD0003', 0, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (76, CAST(N'2023-12-01T01:06:03.640' AS DateTime), NULL, 0, N'37A - 74949  ', 100000, 0, N'oto 02.png', 1, N'TCD0004', 0, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (77, CAST(N'2023-12-02T01:07:42.347' AS DateTime), CAST(N'2023-12-04T20:03:12.677' AS DateTime), 3, N'51h59565     ', 60000, 1, NULL, 1, N'TCD0001', 0, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (78, CAST(N'2023-12-02T01:17:42.523' AS DateTime), CAST(N'2023-12-10T15:21:21.250' AS DateTime), 9, N'30e92291     ', 180000, 1, NULL, 1, NULL, 0, N'TVL0001')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (81, CAST(N'2023-12-02T01:35:41.303' AS DateTime), CAST(N'2023-12-05T10:56:41.680' AS DateTime), 4, N'61t32222     ', 20000, 1, N'163271864.jpg', 1, NULL, 0, N'TVL0022')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (82, CAST(N'2023-12-02T01:54:16.147' AS DateTime), CAST(N'2023-12-04T18:49:44.223' AS DateTime), 0, N'17b724667    ', 80000, 1, N'273423454.jpg', 1, NULL, 0, N'TVL0007')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (83, CAST(N'2023-12-02T01:56:48.687' AS DateTime), CAST(N'2023-12-05T10:58:27.677' AS DateTime), 4, N'59m240963    ', 20000, 1, N'771850819.jpg', 1, N'TCD0005', 0, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (84, CAST(N'2023-12-02T02:05:29.633' AS DateTime), CAST(N'2023-12-05T09:50:02.323' AS DateTime), 4, N'29l143883    ', 30000, 1, N'663298578.jpg', 1, N'TCD0011', 0, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (85, CAST(N'2023-12-02T09:40:07.283' AS DateTime), NULL, 0, N'88k66789     ', 20000, 0, N'633144121.jpg', 1, NULL, 0, N'TVL0001')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (86, CAST(N'2023-12-02T10:48:51.377' AS DateTime), CAST(N'2023-12-04T21:59:58.030' AS DateTime), 3, N'88K66789     ', 60000, 1, N'450880539.jpg', 1, NULL, 0, N'TVL0001')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (87, CAST(N'2023-12-02T11:02:44.410' AS DateTime), CAST(N'2023-12-04T18:48:43.220' AS DateTime), 0, N'84B156789    ', 20000, 1, N'831394358.jpg', 1, NULL, 0, N'TVL0001')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (88, CAST(N'2023-12-02T11:05:52.567' AS DateTime), CAST(N'2023-12-02T11:06:18.130' AS DateTime), 0, N'84B156789    ', 20000, 1, N'861354683.jpg', 1, NULL, 0, N'TVL0001')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (89, CAST(N'2023-12-02T22:44:42.660' AS DateTime), CAST(N'2023-12-04T18:41:51.153' AS DateTime), 0, N'71B333333    ', 500000, 1, N'603330246.jpg', 1, N'TCD0012', 0, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (90, CAST(N'2023-12-04T20:12:38.743' AS DateTime), CAST(N'2023-12-10T15:06:19.847' AS DateTime), 7, N'49E122222    ', 80000, 1, N'768248767.jpg', 1, NULL, 0, N'TVL0004')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (91, CAST(N'2023-12-04T20:13:11.323' AS DateTime), NULL, 0, NULL, 3000, 0, N'xd01.png', 1, NULL, 0, N'TVL0018')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (92, CAST(N'2023-12-04T20:15:24.707' AS DateTime), CAST(N'2023-12-05T09:48:32.497' AS DateTime), 2, N'60B268381    ', 10000, 1, N'38851920.jpg', 1, NULL, 0, N'TVL0022')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (93, CAST(N'2023-12-05T10:55:10.897' AS DateTime), CAST(N'2023-12-10T15:07:59.330' AS DateTime), 6, N'29E145678    ', 30000, 1, N'892978732.jpg', 1, N'TCD0013', 0, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (94, CAST(N'2023-12-09T18:51:19.560' AS DateTime), CAST(N'2023-12-09T18:52:32.933' AS DateTime), 1, N'29U16789     ', 80000, 1, N'173299192.jpg', 1, NULL, 0, N'TVL0007')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (95, CAST(N'2023-12-10T15:14:13.057' AS DateTime), CAST(N'2023-12-10T15:15:23.167' AS DateTime), 1, NULL, 60000, 1, NULL, 1, NULL, 0, N'TVL0024')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (96, CAST(N'2023-12-10T15:20:04.523' AS DateTime), CAST(N'2024-04-23T16:36:33.853' AS DateTime), 136, N'88LD01158    ', 2720000, 1, N'723986614.jpg', 1, NULL, 0, N'TVL0001')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (97, CAST(N'2023-12-11T21:26:09.147' AS DateTime), NULL, 0, NULL, 20000, 0, NULL, 1, NULL, 0, N'TVL0001')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (98, CAST(N'2023-12-12T09:32:13.423' AS DateTime), NULL, 0, N'68G166886    ', 20000, 0, N'907351155.jpg', 1, NULL, 0, N'TVL0006')
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (99, CAST(N'2023-12-12T10:11:11.230' AS DateTime), NULL, 0, N'54L19999     ', 100000, 0, N'41565301.jpg', 1, N'TCD0004', 0, NULL)
INSERT [dbo].[GiaoDich] ([MaGD], [ThoiGianVao], [ThoiGianRa], [SoNgayGui], [BienSoXe], [PhiGuiXe], [TrangThai], [Hinh], [MaNV], [CuDanID], [is_Delete], [KhachVangLaiID]) VALUES (100, CAST(N'2024-04-23T16:30:50.923' AS DateTime), NULL, 0, N'29X712345    ', 80000, 0, N'355238660.jpg', 1, NULL, 0, N'TVL0007')
SET IDENTITY_INSERT [dbo].[GiaoDich] OFF
GO
INSERT [dbo].[KhuGuiXe] ([MaKhu], [TenKhu], [DiaChi], [SoLuongChoDau]) VALUES (N'OT01', N'Khu ô tô gò vấp', N'30 Lê Quang Định, Quận Gò Vấp, TP HCM', 220)
INSERT [dbo].[KhuGuiXe] ([MaKhu], [TenKhu], [DiaChi], [SoLuongChoDau]) VALUES (N'XD01', N'Khu xe đạp gò vấp', N'34 Lê Quang Định, Quận Gò Vấp, TP HCM', 100)
INSERT [dbo].[KhuGuiXe] ([MaKhu], [TenKhu], [DiaChi], [SoLuongChoDau]) VALUES (N'XDD1', N'Khu xe đạp điện', N'Quận 12 TP HCM', 120)
INSERT [dbo].[KhuGuiXe] ([MaKhu], [TenKhu], [DiaChi], [SoLuongChoDau]) VALUES (N'XM01', N'Khu xe máy gò vấp', N'37 Lê Quang Định, Quận Gò Vấp, TP HCM', 500)
GO
INSERT [dbo].[LoaiXe] ([MaLoaiXe], [TenLoaiXe], [MaKhu], [MaLoaiGia]) VALUES (N'OTO01', N'Xe ô tô', N'OT01', N'GOTO01')
INSERT [dbo].[LoaiXe] ([MaLoaiXe], [TenLoaiXe], [MaKhu], [MaLoaiGia]) VALUES (N'OTO02', N'Xe ô tô', N'OT01', N'GOTO02')
INSERT [dbo].[LoaiXe] ([MaLoaiXe], [TenLoaiXe], [MaKhu], [MaLoaiGia]) VALUES (N'OTO03', N'Xe ô tô', N'OT01', N'GOTO03')
INSERT [dbo].[LoaiXe] ([MaLoaiXe], [TenLoaiXe], [MaKhu], [MaLoaiGia]) VALUES (N'OTO04', N'Xe ô tô', N'OT01', N'GOTO04')
INSERT [dbo].[LoaiXe] ([MaLoaiXe], [TenLoaiXe], [MaKhu], [MaLoaiGia]) VALUES (N'XD01', N'Xe đạp', N'XD01', N'GXD01')
INSERT [dbo].[LoaiXe] ([MaLoaiXe], [TenLoaiXe], [MaKhu], [MaLoaiGia]) VALUES (N'XD02', N'Xe đạp', N'XD01', N'GXD02')
INSERT [dbo].[LoaiXe] ([MaLoaiXe], [TenLoaiXe], [MaKhu], [MaLoaiGia]) VALUES (N'XD03', N'Xe đạp', N'XD01', N'GXD03')
INSERT [dbo].[LoaiXe] ([MaLoaiXe], [TenLoaiXe], [MaKhu], [MaLoaiGia]) VALUES (N'XD04', N'Xe đạp', N'XD01', N'GXD04')
INSERT [dbo].[LoaiXe] ([MaLoaiXe], [TenLoaiXe], [MaKhu], [MaLoaiGia]) VALUES (N'XM01', N'Xe máy', N'XM01', N'GXM01')
INSERT [dbo].[LoaiXe] ([MaLoaiXe], [TenLoaiXe], [MaKhu], [MaLoaiGia]) VALUES (N'XM02', N'Xe máy', N'XM01', N'GXM02')
INSERT [dbo].[LoaiXe] ([MaLoaiXe], [TenLoaiXe], [MaKhu], [MaLoaiGia]) VALUES (N'XM03', N'Xe máy', N'XM01', N'GXM03')
INSERT [dbo].[LoaiXe] ([MaLoaiXe], [TenLoaiXe], [MaKhu], [MaLoaiGia]) VALUES (N'XM04', N'Xe máy', N'XM01', N'GXM04')
GO
SET IDENTITY_INSERT [dbo].[NhanVien] ON 

INSERT [dbo].[NhanVien] ([MaNV], [Ho], [Ten], [TenDangNhap], [MatKhau], [Email], [SDT], [GioiTinh], [DiaChi], [VaiTro], [TrangThai], [is_Delete], [HinhAnh]) VALUES (1, N'Nguyễn Hữu', N'Nghĩa', N'huunghia', N'SBsUtbVN/PCEwkHA2JYsZA==', N'nguyenhuunghia19998@gmail.com', N'0969773217', 1, N'Phù cát, Bình định', 1, 0, 0, N'avatar nam 8.png')
INSERT [dbo].[NhanVien] ([MaNV], [Ho], [Ten], [TenDangNhap], [MatKhau], [Email], [SDT], [GioiTinh], [DiaChi], [VaiTro], [TrangThai], [is_Delete], [HinhAnh]) VALUES (2, N'Lê Thị', N'Mai', N'lemai1', N'SgGTZ3RLwQWM7OGN434r2A==', N'lemai2000@gmail.com', N'0938572910', 0, N'Phù cát, Bình định', 0, 0, 0, N'avatar nữ 4.png')
INSERT [dbo].[NhanVien] ([MaNV], [Ho], [Ten], [TenDangNhap], [MatKhau], [Email], [SDT], [GioiTinh], [DiaChi], [VaiTro], [TrangThai], [is_Delete], [HinhAnh]) VALUES (3, N'Trần Mai', N'Anh', N'maianh', N'+VlucVhujf+23Ws4qzd0Dw==', N'maianh@gmail.com', N'0365938281', 2, N'Quận Gò Vấp, TP HCM', 0, 1, 0, NULL)
INSERT [dbo].[NhanVien] ([MaNV], [Ho], [Ten], [TenDangNhap], [MatKhau], [Email], [SDT], [GioiTinh], [DiaChi], [VaiTro], [TrangThai], [is_Delete], [HinhAnh]) VALUES (4, N'Phan', N'Đã', N'phanda', N'Z+g+SMO9faJdZnRpHXzSgA==', N'phanda@gmail.com', N'0973592758', 2, N'Phù cát, Bình định', 0, 2, 1, N'avatar nam 7.png')
INSERT [dbo].[NhanVien] ([MaNV], [Ho], [Ten], [TenDangNhap], [MatKhau], [Email], [SDT], [GioiTinh], [DiaChi], [VaiTro], [TrangThai], [is_Delete], [HinhAnh]) VALUES (5, N'Đinh văn', N'Tài', N'vantai', N'KA/G4hnBadoU5zBSTGCNHQ==', N'vantai@gmail.com', N'0947289385', 1, N'Quãng ngãi', 0, 0, 0, N'avatar nam 3.png')
INSERT [dbo].[NhanVien] ([MaNV], [Ho], [Ten], [TenDangNhap], [MatKhau], [Email], [SDT], [GioiTinh], [DiaChi], [VaiTro], [TrangThai], [is_Delete], [HinhAnh]) VALUES (6, N'Phan Đình', N'Tùng', N'dinhtung', N'l5q8Ke5fb094VBqf8QqKRA==', N'tung@gmail.com', N'0368394901', 1, N'Quận 1, Tp HCM', 0, 1, 0, NULL)
INSERT [dbo].[NhanVien] ([MaNV], [Ho], [Ten], [TenDangNhap], [MatKhau], [Email], [SDT], [GioiTinh], [DiaChi], [VaiTro], [TrangThai], [is_Delete], [HinhAnh]) VALUES (9, N'trùm', N'cuối', N'trumcuoi', N'YKjRqWEVapJkS0ogGNCmQQ==', N'trumcuoi@gmail.com', N'0969773218', 1, N'', 0, 0, 0, N'avatar nam 1.png')
INSERT [dbo].[NhanVien] ([MaNV], [Ho], [Ten], [TenDangNhap], [MatKhau], [Email], [SDT], [GioiTinh], [DiaChi], [VaiTro], [TrangThai], [is_Delete], [HinhAnh]) VALUES (10, N'Lò thị', N'Lài', N'lai2000', N'cnCg9VAT6bLCu2eZMPrpmg==', N'lai2000@gmail.com', N'0365827102', 0, N'Long an', 0, 2, 0, NULL)
INSERT [dbo].[NhanVien] ([MaNV], [Ho], [Ten], [TenDangNhap], [MatKhau], [Email], [SDT], [GioiTinh], [DiaChi], [VaiTro], [TrangThai], [is_Delete], [HinhAnh]) VALUES (11, N'phan', N'tam', N'phantam', N'dj+BZLZV2zEg7AL+jfUUEQ==', N'tam@gmail.com', N'0969873273', 1, N'', 0, 0, 0, N'avatar nam 5.png')
INSERT [dbo].[NhanVien] ([MaNV], [Ho], [Ten], [TenDangNhap], [MatKhau], [Email], [SDT], [GioiTinh], [DiaChi], [VaiTro], [TrangThai], [is_Delete], [HinhAnh]) VALUES (14, N'Nguyễn Hữu', N'Nghĩa', N'huunghia123', N'2oNTZ+r0I8XckzQNSy6DdA==', N'nguyenhuunghia@gmail.com', N'0969773217', 1, N'Phù cát, Bình định', 1, 1, 0, NULL)
INSERT [dbo].[NhanVien] ([MaNV], [Ho], [Ten], [TenDangNhap], [MatKhau], [Email], [SDT], [GioiTinh], [DiaChi], [VaiTro], [TrangThai], [is_Delete], [HinhAnh]) VALUES (15, N'Lê Thị', N'Mai', N'lemai123', N'SgGTZ3RLwQWM7OGN434r2A==', N'lemai2000@gmail.com', N'0938572912', 0, N'Phù cát, Bình định', 0, 0, 0, N'avatar nữ 5.png')
INSERT [dbo].[NhanVien] ([MaNV], [Ho], [Ten], [TenDangNhap], [MatKhau], [Email], [SDT], [GioiTinh], [DiaChi], [VaiTro], [TrangThai], [is_Delete], [HinhAnh]) VALUES (18, N'Trần Mai', N'Anh', N'maianh123', N'+VlucVhujf+23Ws4qzd0Dw==', N'maianh@gmail.com', N'0365938281', 2, N'Quận Gò Vấp, TP HCM', 0, 1, 0, NULL)
INSERT [dbo].[NhanVien] ([MaNV], [Ho], [Ten], [TenDangNhap], [MatKhau], [Email], [SDT], [GioiTinh], [DiaChi], [VaiTro], [TrangThai], [is_Delete], [HinhAnh]) VALUES (19, N'Phan', N'Đã', N'phanda123', N'RuPrb4ff9ONip02TRfHOsQ==', N'phanda@gmail.com', N'0973592758', 2, N'Phù cát, Bình định', 0, 2, 1, NULL)
INSERT [dbo].[NhanVien] ([MaNV], [Ho], [Ten], [TenDangNhap], [MatKhau], [Email], [SDT], [GioiTinh], [DiaChi], [VaiTro], [TrangThai], [is_Delete], [HinhAnh]) VALUES (20, N'Đinh văn', N'Tài', N'vantai123', N'KA/G4hnBadoU5zBSTGCNHQ==', N'vantai@gmail.com', N'0947289385', 1, N'Quãng ngãi', 0, 0, 0, N'avatar nam 6.png')
INSERT [dbo].[NhanVien] ([MaNV], [Ho], [Ten], [TenDangNhap], [MatKhau], [Email], [SDT], [GioiTinh], [DiaChi], [VaiTro], [TrangThai], [is_Delete], [HinhAnh]) VALUES (21, N'Phan Đình', N'Tùng', N'dinhtung123', N'l5q8Ke5fb094VBqf8QqKRA==', N'tung@gmail.com', N'0368394901', 1, N'Quận 1, Tp HCM', 0, 1, 0, NULL)
INSERT [dbo].[NhanVien] ([MaNV], [Ho], [Ten], [TenDangNhap], [MatKhau], [Email], [SDT], [GioiTinh], [DiaChi], [VaiTro], [TrangThai], [is_Delete], [HinhAnh]) VALUES (22, N'trùm', N'cuối', N'trumcuoi123', N'YKjRqWEVapJkS0ogGNCmQQ==', N'trumcuoi@gmail.com', N'0969773218', 1, N'', 0, 0, 0, N'avatar nam 8.png')
INSERT [dbo].[NhanVien] ([MaNV], [Ho], [Ten], [TenDangNhap], [MatKhau], [Email], [SDT], [GioiTinh], [DiaChi], [VaiTro], [TrangThai], [is_Delete], [HinhAnh]) VALUES (23, N'Lò thị', N'Lài', N'lai20000', N'cnCg9VAT6bLCu2eZMPrpmg==', N'lai2000@gmail.com', N'0365827102', 0, N'Long an', 0, 2, 0, N'avatar nữ 6.png')
INSERT [dbo].[NhanVien] ([MaNV], [Ho], [Ten], [TenDangNhap], [MatKhau], [Email], [SDT], [GioiTinh], [DiaChi], [VaiTro], [TrangThai], [is_Delete], [HinhAnh]) VALUES (24, N'võ', N'phat', N'vophat', N'wj1fPY4GcCgWhKjGSdIvDA==', N'phatvntps27938@fpt.edu.vn', N'0937485736', 1, N'', 0, 1, 0, NULL)
INSERT [dbo].[NhanVien] ([MaNV], [Ho], [Ten], [TenDangNhap], [MatKhau], [Email], [SDT], [GioiTinh], [DiaChi], [VaiTro], [TrangThai], [is_Delete], [HinhAnh]) VALUES (25, N'Nguyễn Hữu', N'Nghĩa', N'nghiaem1', N'DLWy4uhMkNzIEz6au027jQ==', N'nguyenhuunghia@gmail.com', N'0969773217', 1, N'Phù cát, Bình định', 1, 1, 0, NULL)
INSERT [dbo].[NhanVien] ([MaNV], [Ho], [Ten], [TenDangNhap], [MatKhau], [Email], [SDT], [GioiTinh], [DiaChi], [VaiTro], [TrangThai], [is_Delete], [HinhAnh]) VALUES (26, N'nguyễn', N'nghĩa', N'nghia12', N'9PoZa2y3CVIm/nDjLYOm8g==', N'nguyenhuunghia@gmail.com', N'0969773268', 1, N'', 0, 0, 0, N'avatar nam 10.png')
INSERT [dbo].[NhanVien] ([MaNV], [Ho], [Ten], [TenDangNhap], [MatKhau], [Email], [SDT], [GioiTinh], [DiaChi], [VaiTro], [TrangThai], [is_Delete], [HinhAnh]) VALUES (27, N'võ văn', N'tình', N'vantinh', N'dbTPQxg5XuJV6ddXeFa6oA==', N'vantinh@gmail.com', N'0962834572', 2, N'Thừa thiên huế', 0, 1, 0, N'avatar nam 1.png')
INSERT [dbo].[NhanVien] ([MaNV], [Ho], [Ten], [TenDangNhap], [MatKhau], [Email], [SDT], [GioiTinh], [DiaChi], [VaiTro], [TrangThai], [is_Delete], [HinhAnh]) VALUES (28, N'Trần Kim', N'Mai', N'kimmai12', N'2JqdVJZMb7qpaDGGbU+gEA==', N'kimmai2002@gmail.com', N'0365938293', 0, N'Phú Yên', 1, 2, 0, NULL)
INSERT [dbo].[NhanVien] ([MaNV], [Ho], [Ten], [TenDangNhap], [MatKhau], [Email], [SDT], [GioiTinh], [DiaChi], [VaiTro], [TrangThai], [is_Delete], [HinhAnh]) VALUES (30, N'Lê Anh', N'Tài', N'anhtai', N'+WGPaHwbSP+2z5SW5uBflg==', N'anhtai394@gmail.com', N'0365938293', 1, N'Bình Thuận', 0, 0, 0, NULL)
INSERT [dbo].[NhanVien] ([MaNV], [Ho], [Ten], [TenDangNhap], [MatKhau], [Email], [SDT], [GioiTinh], [DiaChi], [VaiTro], [TrangThai], [is_Delete], [HinhAnh]) VALUES (35, N'Trần văn', N'Tuấn', N'tuantran', N'OM5M2a/dLUj8wBn5qttNGg==', N'tuan@gmail.com', N'0368394121', 2, N'Tiền Giang', 0, 0, 0, N'avatar nam 5.png')
INSERT [dbo].[NhanVien] ([MaNV], [Ho], [Ten], [TenDangNhap], [MatKhau], [Email], [SDT], [GioiTinh], [DiaChi], [VaiTro], [TrangThai], [is_Delete], [HinhAnh]) VALUES (36, N'Chu Văn', N'Tiên', N'vantien', N'iLAal6RiVKTjpURT6lU2sA==', N'tien@gmail.com', N'0983748183', 1, N'Thanh hóa', 0, 0, 0, N'avatar nam 1.png')
INSERT [dbo].[NhanVien] ([MaNV], [Ho], [Ten], [TenDangNhap], [MatKhau], [Email], [SDT], [GioiTinh], [DiaChi], [VaiTro], [TrangThai], [is_Delete], [HinhAnh]) VALUES (37, N'Lò Thị', N'Yến', N'loyen2000', N'VvUlb1jLqmkT6dOrcgHimA==', N'yen@gmail.com', N'0943860959', 0, N'Hà Nội', 0, 1, 0, N'avatar nữ 6.png')
INSERT [dbo].[NhanVien] ([MaNV], [Ho], [Ten], [TenDangNhap], [MatKhau], [Email], [SDT], [GioiTinh], [DiaChi], [VaiTro], [TrangThai], [is_Delete], [HinhAnh]) VALUES (38, N'Trần sỹ', N'Hùng', N'hungtran', N'4SH+SzTkQUenBZ7iG3tMkA==', N'hung@gmail.com', N'0979023940', 1, N'Tiền Giang', 0, 0, 0, N'avatar nam 10.png')
INSERT [dbo].[NhanVien] ([MaNV], [Ho], [Ten], [TenDangNhap], [MatKhau], [Email], [SDT], [GioiTinh], [DiaChi], [VaiTro], [TrangThai], [is_Delete], [HinhAnh]) VALUES (39, N'Đinh văn', N'Đức', N'vanduc', N'Otk9Kag9aN/jucFurX9qJw==', N'duc@gmail.com', N'0983746279', 1, N'Bình định', 0, 0, 0, N'avatar nam 9.png')
SET IDENTITY_INSERT [dbo].[NhanVien] OFF
GO
INSERT [dbo].[Remember] ([userName], [pass]) VALUES (N'huunghia', N'nghia123')
GO
INSERT [dbo].[TheXeCuDan] ([MaThe], [ThoiGianDangKy], [MaCuDan], [MaLoaiXe]) VALUES (N'TCD0001', CAST(N'2020-10-18T00:00:00.000' AS DateTime), N'CD0001', N'OTO01')
INSERT [dbo].[TheXeCuDan] ([MaThe], [ThoiGianDangKy], [MaCuDan], [MaLoaiXe]) VALUES (N'TCD0002', CAST(N'2020-04-10T00:00:00.000' AS DateTime), N'CD0002', N'XD01')
INSERT [dbo].[TheXeCuDan] ([MaThe], [ThoiGianDangKy], [MaCuDan], [MaLoaiXe]) VALUES (N'TCD0003', CAST(N'2020-07-03T00:00:00.000' AS DateTime), N'CD0003', N'XD03')
INSERT [dbo].[TheXeCuDan] ([MaThe], [ThoiGianDangKy], [MaCuDan], [MaLoaiXe]) VALUES (N'TCD0004', CAST(N'2020-10-10T00:00:00.000' AS DateTime), N'CD0008', N'OTO02')
INSERT [dbo].[TheXeCuDan] ([MaThe], [ThoiGianDangKy], [MaCuDan], [MaLoaiXe]) VALUES (N'TCD0005', CAST(N'2020-12-10T00:00:00.000' AS DateTime), N'CD0010', N'XM01')
INSERT [dbo].[TheXeCuDan] ([MaThe], [ThoiGianDangKy], [MaCuDan], [MaLoaiXe]) VALUES (N'TCD0006', CAST(N'2020-11-18T00:00:00.000' AS DateTime), N'CD0011', N'XM02')
INSERT [dbo].[TheXeCuDan] ([MaThe], [ThoiGianDangKy], [MaCuDan], [MaLoaiXe]) VALUES (N'TCD0007', CAST(N'2020-08-10T00:00:00.000' AS DateTime), N'CD0024', N'OTO03')
INSERT [dbo].[TheXeCuDan] ([MaThe], [ThoiGianDangKy], [MaCuDan], [MaLoaiXe]) VALUES (N'TCD0008', CAST(N'2020-01-09T00:00:00.000' AS DateTime), N'CD0019', N'XM02')
INSERT [dbo].[TheXeCuDan] ([MaThe], [ThoiGianDangKy], [MaCuDan], [MaLoaiXe]) VALUES (N'TCD0009', CAST(N'2020-09-10T00:00:00.000' AS DateTime), N'CD0017', N'XD02')
INSERT [dbo].[TheXeCuDan] ([MaThe], [ThoiGianDangKy], [MaCuDan], [MaLoaiXe]) VALUES (N'TCD0010', CAST(N'2020-11-10T00:00:00.000' AS DateTime), N'CD0014', N'OTO02')
INSERT [dbo].[TheXeCuDan] ([MaThe], [ThoiGianDangKy], [MaCuDan], [MaLoaiXe]) VALUES (N'TCD0011', CAST(N'2023-11-24T00:58:37.433' AS DateTime), N'CD0002', N'XM02')
INSERT [dbo].[TheXeCuDan] ([MaThe], [ThoiGianDangKy], [MaCuDan], [MaLoaiXe]) VALUES (N'TCD0012', CAST(N'2023-12-01T11:02:23.143' AS DateTime), N'CD0021', N'OTO03')
INSERT [dbo].[TheXeCuDan] ([MaThe], [ThoiGianDangKy], [MaCuDan], [MaLoaiXe]) VALUES (N'TCD0013', CAST(N'2023-12-04T22:19:29.827' AS DateTime), N'CD0011', N'XM01')
INSERT [dbo].[TheXeCuDan] ([MaThe], [ThoiGianDangKy], [MaCuDan], [MaLoaiXe]) VALUES (N'TCD0020', CAST(N'2023-11-24T00:36:12.850' AS DateTime), N'CD0012', N'XD01')
GO
INSERT [dbo].[TheXeKhachVangLai] ([MaThe], [MaLoaiXe]) VALUES (N'TVL0001', N'OTO01')
INSERT [dbo].[TheXeKhachVangLai] ([MaThe], [MaLoaiXe]) VALUES (N'TVL0002', N'XD01')
INSERT [dbo].[TheXeKhachVangLai] ([MaThe], [MaLoaiXe]) VALUES (N'TVL0003', N'OTO01')
INSERT [dbo].[TheXeKhachVangLai] ([MaThe], [MaLoaiXe]) VALUES (N'TVL0004', N'XM03')
INSERT [dbo].[TheXeKhachVangLai] ([MaThe], [MaLoaiXe]) VALUES (N'TVL0005', N'OTO02')
INSERT [dbo].[TheXeKhachVangLai] ([MaThe], [MaLoaiXe]) VALUES (N'TVL0006', N'OTO01')
INSERT [dbo].[TheXeKhachVangLai] ([MaThe], [MaLoaiXe]) VALUES (N'TVL0007', N'XM03')
INSERT [dbo].[TheXeKhachVangLai] ([MaThe], [MaLoaiXe]) VALUES (N'TVL0008', N'XD03')
INSERT [dbo].[TheXeKhachVangLai] ([MaThe], [MaLoaiXe]) VALUES (N'TVL0009', N'OTO01')
INSERT [dbo].[TheXeKhachVangLai] ([MaThe], [MaLoaiXe]) VALUES (N'TVL0010', N'XD01')
INSERT [dbo].[TheXeKhachVangLai] ([MaThe], [MaLoaiXe]) VALUES (N'TVL0011', N'OTO03')
INSERT [dbo].[TheXeKhachVangLai] ([MaThe], [MaLoaiXe]) VALUES (N'TVL0012', N'XD03')
INSERT [dbo].[TheXeKhachVangLai] ([MaThe], [MaLoaiXe]) VALUES (N'TVL0013', N'XM03')
INSERT [dbo].[TheXeKhachVangLai] ([MaThe], [MaLoaiXe]) VALUES (N'TVL0014', N'OTO01')
INSERT [dbo].[TheXeKhachVangLai] ([MaThe], [MaLoaiXe]) VALUES (N'TVL0015', N'OTO02')
INSERT [dbo].[TheXeKhachVangLai] ([MaThe], [MaLoaiXe]) VALUES (N'TVL0016', N'XM01')
INSERT [dbo].[TheXeKhachVangLai] ([MaThe], [MaLoaiXe]) VALUES (N'TVL0017', N'OTO01')
INSERT [dbo].[TheXeKhachVangLai] ([MaThe], [MaLoaiXe]) VALUES (N'TVL0018', N'XD01')
INSERT [dbo].[TheXeKhachVangLai] ([MaThe], [MaLoaiXe]) VALUES (N'TVL0019', N'OTO02')
INSERT [dbo].[TheXeKhachVangLai] ([MaThe], [MaLoaiXe]) VALUES (N'TVL0020', N'OTO01')
INSERT [dbo].[TheXeKhachVangLai] ([MaThe], [MaLoaiXe]) VALUES (N'TVL0021', N'XD03')
INSERT [dbo].[TheXeKhachVangLai] ([MaThe], [MaLoaiXe]) VALUES (N'TVL0022', N'XM01')
INSERT [dbo].[TheXeKhachVangLai] ([MaThe], [MaLoaiXe]) VALUES (N'TVL0023', N'XD03')
INSERT [dbo].[TheXeKhachVangLai] ([MaThe], [MaLoaiXe]) VALUES (N'TVL0024', N'XD03')
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__NhanVien__55F68FC09F11C550]    Script Date: 24-04-2024 20:23:31 ******/
ALTER TABLE [dbo].[NhanVien] ADD UNIQUE NONCLUSTERED 
(
	[TenDangNhap] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[CuDan] ADD  DEFAULT ((0)) FOR [is_Delete]
GO
ALTER TABLE [dbo].[GiaoDich] ADD  DEFAULT (getdate()) FOR [ThoiGianVao]
GO
ALTER TABLE [dbo].[GiaoDich] ADD  DEFAULT ((0)) FOR [is_Delete]
GO
ALTER TABLE [dbo].[NhanVien] ADD  DEFAULT ((0)) FOR [VaiTro]
GO
ALTER TABLE [dbo].[NhanVien] ADD  DEFAULT ((0)) FOR [TrangThai]
GO
ALTER TABLE [dbo].[NhanVien] ADD  DEFAULT ((0)) FOR [is_Delete]
GO
ALTER TABLE [dbo].[TheXeCuDan] ADD  DEFAULT (getdate()) FOR [ThoiGianDangKy]
GO
ALTER TABLE [dbo].[GiaoDich]  WITH CHECK ADD FOREIGN KEY([CuDanID])
REFERENCES [dbo].[TheXeCuDan] ([MaThe])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[GiaoDich]  WITH CHECK ADD FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([MaNV])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[GiaoDich]  WITH CHECK ADD  CONSTRAINT [FK_GD_KVL] FOREIGN KEY([KhachVangLaiID])
REFERENCES [dbo].[TheXeKhachVangLai] ([MaThe])
GO
ALTER TABLE [dbo].[GiaoDich] CHECK CONSTRAINT [FK_GD_KVL]
GO
ALTER TABLE [dbo].[LoaiXe]  WITH CHECK ADD FOREIGN KEY([MaKhu])
REFERENCES [dbo].[KhuGuiXe] ([MaKhu])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[LoaiXe]  WITH CHECK ADD  CONSTRAINT [FK_LoaiXe_BangGia] FOREIGN KEY([MaLoaiGia])
REFERENCES [dbo].[BangGia] ([MaLoaiGia])
GO
ALTER TABLE [dbo].[LoaiXe] CHECK CONSTRAINT [FK_LoaiXe_BangGia]
GO
ALTER TABLE [dbo].[TheXeCuDan]  WITH CHECK ADD FOREIGN KEY([MaCuDan])
REFERENCES [dbo].[CuDan] ([MaCuDan])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[TheXeCuDan]  WITH CHECK ADD FOREIGN KEY([MaLoaiXe])
REFERENCES [dbo].[LoaiXe] ([MaLoaiXe])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[TheXeKhachVangLai]  WITH CHECK ADD  CONSTRAINT [FK_KVL_LoaiXe] FOREIGN KEY([MaLoaiXe])
REFERENCES [dbo].[LoaiXe] ([MaLoaiXe])
GO
ALTER TABLE [dbo].[TheXeKhachVangLai] CHECK CONSTRAINT [FK_KVL_LoaiXe]
GO
/****** Object:  StoredProcedure [dbo].[DoanhThuNam]    Script Date: 24-04-2024 20:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[DoanhThuNam]
AS
BEGIN
    -- Khai báo biến để lưu giá theo tháng
    DECLARE @GiaTheoThang DECIMAL(10, 3);

	
    -- Lấy giá theo tháng từ bảng BangGiaXe
    SELECT @GiaTheoThang = GiaTheoThang FROM BangGiaXe;

	IF OBJECT_ID('TempResult1') IS NOT NULL
    DROP TABLE TempResult1;

    -- Tạo bảng tạm để lưu trữ kết quả
    CREATE TABLE TempResult1
    (
        ThoiGian INT,
        SoLuong INT,
        DoanhThu DECIMAL(10, 3)
    );

    -- Insert dữ liệu vào bảng tạm
    INSERT INTO TempResult1 (ThoiGian, SoLuong, DoanhThu)
    SELECT 
        YEAR(TXCD.ThoiGianDangKy) AS ThoiGian,
        COUNT(TXCD.ThoiGianDangKy) AS SoLuong,
        COUNT(TXCD.ThoiGianDangKy) * @GiaTheoThang AS DoanhThu
    FROM 
        TheXeCuDan TXCD
    WHERE 
        TXCD.MaThe NOT IN ('')
    GROUP BY 
        YEAR(TXCD.ThoiGianDangKy);
	
    -- Tính toán doanh thu từ ThongTinGuiXe
    SELECT     
        YEAR(tt.ThoiGianVao) AS ThoiGian,
        (SUM(tt.TongTien) + tr.DoanhThu)AS TongDoanhThu,
        MAX(tt.TongTien) AS DoanhThuCaoNhat,
        MIN(tt.TongTien) AS DoanhThuThapNhat,
		AVG(tt.TongTien) AS DoanhThuTrungBinh
    FROM 
        ThongTinGuiXe tt
    LEFT JOIN 
        TempResult1 tr ON YEAR(tt.ThoiGianVao) = tr.ThoiGian 
    WHERE 
        MaTheVangLai IS NOT NULL
    GROUP BY 
        YEAR(tt.ThoiGianVao), tr.DoanhThu
    ORDER BY 
        YEAR(tt.ThoiGianVao) ASC;
    
END;		

	exec DoanhThuNam;
	drop proc DoanhThuNam;

CREATE TABLE DoanhThuTheoNam (
    ThoiGian INT,
    TongDoanhThu DECIMAL(10, 3),
    DoanhThuCaoNhat DECIMAL(10, 3),
    DoanhThuThapNhat DECIMAL(10, 3),
    DoanhThuTrungBinh DECIMAL(10, 3)
	);

GO
/****** Object:  StoredProcedure [dbo].[insertTableDoanhThuNam]    Script Date: 24-04-2024 20:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[insertTableDoanhThuNam]
as
begin
	IF OBJECT_ID('DoanhThuTheoNam') IS NOT NULL
	delete from DoanhThuTheoNam;

	INSERT INTO DoanhThuTheoNam (ThoiGian, TongDoanhThu, DoanhThuCaoNhat, DoanhThuThapNhat, DoanhThuTrungBinh)
	EXEC DoanhThuNam;
end;
drop proc insertTableDoanhThuNam


GO
/****** Object:  StoredProcedure [dbo].[Sp_DoanhThu]    Script Date: 24-04-2024 20:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROC [dbo].[Sp_DoanhThu](@TimeStart DATE, @TimeEnd DATE)
AS BEGIN
	SELECT  x.MaKhu KhuGuiXe,
			COUNT(DISTINCT x.MaLoaiXe) SoLoaiXeGui,
			COUNT(DISTINCT g.MaGD) SoXeGui,
			FORMAT(SUM(PhiGuiXe), '###,### VND') DoanhThu, 
			FORMAT(MIN(PhiGuiXe), '###,### VND') DoanhThuMin,
			FORMAT(MAX(PhiGuiXe), '###,### VND') DoanhThuMax,
			FORMAT(AVG(PhiGuiXe), '###,### VND') DoanhThuAVG
	FROM GiaoDich g JOIN TheXeCuDan t ON g.CuDanID = t.MaThe
					JOIN LoaiXe x ON t.MaLoaiXe = x.MaLoaiXe
					JOIN KhuGuiXe k ON k.MaKhu = x.MaKhu
	WHERE ThoiGianVao BETWEEN @TimeStart AND @TimeEnd
	GROUP BY  x.MaKhu
END
GO
/****** Object:  StoredProcedure [dbo].[Sp_DoanhThuKhachVangLai]    Script Date: 24-04-2024 20:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- PROCEDURE Doanh thu khách vãng lai
CREATE   PROC [dbo].[Sp_DoanhThuKhachVangLai](@TimeStart DATE, @TimeEnd DATE)
AS BEGIN
	SELECT  x.MaKhu KhuGuiXe,
			COUNT(DISTINCT x.MaLoaiXe) SoLoaiXeGui,
			COUNT(DISTINCT g.MaGD) SoXeGui,
			FORMAT(SUM(PhiGuiXe), '###,### VND') DoanhThu, 
			FORMAT(MIN(PhiGuiXe), '###,### VND') DoanhThuMin,
			FORMAT(MAX(PhiGuiXe), '###,### VND') DoanhThuMax,
			FORMAT(AVG(PhiGuiXe), '###,### VND') DoanhThuAVG
	FROM GiaoDich g JOIN TheXeKhachVangLai t ON g.KhachVangLaiID = t.MaThe
					JOIN LoaiXe x ON t.MaLoaiXe = x.MaLoaiXe
					JOIN KhuGuiXe k ON k.MaKhu = x.MaKhu
	WHERE ThoiGianVao BETWEEN @TimeStart AND @TimeEnd
	GROUP BY  x.MaKhu
END
GO
/****** Object:  StoredProcedure [dbo].[sp_DoanhThuTheoNam]    Script Date: 24-04-2024 20:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[sp_DoanhThuTheoNam]
as
begin
	exec insertTableDoanhThuNam

	select * from DoanhThuTheoNam
end;

exec sp_DoanhThuTheoNam
drop proc sp_DoanhThuTheoNam

--sp_SoLuongXe
GO
/****** Object:  StoredProcedure [dbo].[sp_DoanhThuTheoNgay]    Script Date: 24-04-2024 20:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[sp_DoanhThuTheoNgay]
as 
begin
	 SELECT 
        CONVERT(DATE, ThoiGianVao) AS ThoiGian,
        SUM(ISNULL(TongTien, 0)) AS TongDoanhThu,
        MAX(ISNULL(TongTien, 0)) AS DoanhThuCaoNhat,
        MIN(ISNULL(TongTien, 0)) AS DoanhThuThapNhat,
		AVG(ISNULL(TongTien, 0)) AS DoanhThuTrungBinh
    FROM 
        ThongTinGuiXe
    WHERE 
        MaTheVangLai IS NOT NULL
    GROUP BY 
        CONVERT(DATE, ThoiGianVao);
end;
exec sp_DoanhThuTheoNgay;

--
SELECT 
    
    YEAR(ThoiGianVao) AS ThoiGian,
    SUM(ISNULL(TongTien, 0)) AS TongDoanhThu,
    MAX(ISNULL(TongTien, 0)) AS DoanhThuCaoNhat,
    MIN(ISNULL(TongTien, 0)) AS DoanhThuThapNhat
FROM 
    ThongTinGuiXe
WHERE 
    MaTheVangLai IS NOT NULL
GROUP BY 
     YEAR(ThoiGianVao);


--
SELECT 
    
    MONTH(ThoiGianVao) AS ThoiGian,
    SUM(ISNULL(TongTien, 0)) AS TongDoanhThu,
    MAX(ISNULL(TongTien, 0)) AS DoanhThuCaoNhat,
    MIN(ISNULL(TongTien, 0)) AS DoanhThuThapNhat
FROM 
    ThongTinGuiXe
WHERE 
    MaTheVangLai IS NOT NULL
GROUP BY 
     MONTH(ThoiGianVao);


--sp_DoanhThuTheoThang
GO
/****** Object:  StoredProcedure [dbo].[sp_DoanhThuTheoThang]    Script Date: 24-04-2024 20:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_DoanhThuTheoThang]
AS
BEGIN
    -- Khai báo biến để lưu giá theo tháng
    DECLARE @GiaTheoThang DECIMAL(10, 3);

    -- Lấy giá theo tháng từ bảng BangGiaXe
    SELECT TOP 1 @GiaTheoThang = GiaTheoThang
    FROM BangGiaXe
    ORDER BY MaLoaiGia DESC;

	IF OBJECT_ID('tempdb..#TempResult') IS NOT NULL
    DROP TABLE #TempResult;

    -- Tạo bảng tạm để lưu trữ kết quả
    CREATE TABLE #TempResult
    (
        ThoiGian INT,
        SoLuong INT,
        DoanhThu DECIMAL(10, 3)
    );

    -- Insert dữ liệu vào bảng tạm
    INSERT INTO #TempResult (ThoiGian, SoLuong, DoanhThu)
    SELECT 
        MONTH(TXCD.ThoiGianDangKy) AS ThoiGian,
        COUNT(TXCD.ThoiGianDangKy) AS SoLuong,
        COUNT(TXCD.ThoiGianDangKy) * @GiaTheoThang AS DoanhThu
    FROM 
        TheXeCuDan TXCD
    WHERE 
        TXCD.MaThe NOT IN ('')
    GROUP BY 
        MONTH(TXCD.ThoiGianDangKy);

    -- Tính toán doanh thu từ ThongTinGuiXe
	   SELECT     
		MONTH(tt.ThoiGianVao) AS ThoiGian,
		SUM(ISNULL(tt.TongTien, 0) + ISNULL(tr.DoanhThu, 0)) AS TongDoanhThu,
		MAX(ISNULL(tt.TongTien, 0)) AS DoanhThuCaoNhat,
		MIN(ISNULL(tt.TongTien, 0)) AS DoanhThuThapNhat,
		AVG(ISNULL(tt.TongTien, 0)) AS DoanhThuTrungBinh
	FROM 
		ThongTinGuiXe tt
	LEFT JOIN 
		#TempResult tr ON Month(tt.ThoiGianVao) = tr.ThoiGian 
	WHERE 
		MaTheVangLai IS NOT NULL

	GROUP BY 
		MONTH(tt.ThoiGianVao), tr.DoanhThu
		order by month(tt.ThoiGianVao) asc;
		
    -- Xóa bảng tạm sau khi sử dụng
    
END;		

 exec sp_DoanhThuTheoThang;
 drop proc sp_DoanhThuTheoThang
 

 --sp_DoanhThuTheoNam
GO
/****** Object:  StoredProcedure [dbo].[Sp_LuongXeCuDanGuiTheoNam]    Script Date: 24-04-2024 20:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- PROCEDURE Lượng xe cư dân gửi theo năm
CREATE   PROC [dbo].[Sp_LuongXeCuDanGuiTheoNam](@MaKhu VARCHAR(10))
AS BEGIN
	SELECT  YEAR(ThoiGianVao) Nam, 
			COUNT(MaGD) SoLuong,
			MIN(ThoiGianVao) DauTien,
			MAX(ThoiGianVao) CuoiCung
	FROM GiaoDich g JOIN TheXeCuDan t ON g.CuDanID = t.MaThe
					JOIN LoaiXe x ON t.MaLoaiXe = x.MaLoaiXe
					JOIN KhuGuiXe k ON k.MaKhu = x.MaKhu
	WHERE x.MaKhu = @MaKhu
	GROUP BY  YEAR(ThoiGianVao)
END
GO
/****** Object:  StoredProcedure [dbo].[Sp_LuongXeCuDanGuiTheoNgay]    Script Date: 24-04-2024 20:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- PROCEDURE Lượng xe cư dân gửi theo năm, tháng, ngày
CREATE   PROC [dbo].[Sp_LuongXeCuDanGuiTheoNgay](@MaKhu VARCHAR(10), @nam INT, @thang INT)
AS BEGIN
	SELECT	CONCAT(	YEAR(ThoiGianVao), '/', MONTH(ThoiGianVao), '/', DAY(ThoiGianVao)) ThoiGian,
			COUNT(MaGD) SoLuong,
			MIN(ThoiGianVao) DauTien,
			MAX(ThoiGianVao) CuoiCung
	FROM GiaoDich g JOIN TheXeCuDan t ON g.CuDanID = t.MaThe
					JOIN LoaiXe x ON t.MaLoaiXe = x.MaLoaiXe
					JOIN KhuGuiXe k ON k.MaKhu = x.MaKhu
	WHERE x.MaKhu = @MaKhu AND YEAR(ThoiGianVao) = @nam AND MONTH(ThoiGianVao) = @thang
	GROUP BY  MONTH(ThoiGianVao), YEAR(ThoiGianVao), DAY(ThoiGianVao)
END
GO
/****** Object:  StoredProcedure [dbo].[Sp_LuongXeCuDanGuiTheoThang]    Script Date: 24-04-2024 20:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROC [dbo].[Sp_LuongXeCuDanGuiTheoThang](@MaKhu VARCHAR(10), @nam INT)
AS BEGIN
	SELECT  CONCAT(	YEAR(ThoiGianVao), '/', MONTH(ThoiGianVao)) ThoiGian,
			COUNT(MaGD) SoLuong,
			MIN(ThoiGianVao) DauTien,
			MAX(ThoiGianVao) CuoiCung
	FROM GiaoDich g JOIN TheXeCuDan t ON g.CuDanID = t.MaThe
					JOIN LoaiXe x ON t.MaLoaiXe = x.MaLoaiXe
					JOIN KhuGuiXe k ON k.MaKhu = x.MaKhu
	WHERE x.MaKhu = @MaKhu AND YEAR(ThoiGianVao) = @nam
	GROUP BY  YEAR(ThoiGianVao), MONTH(ThoiGianVao)
END
GO
/****** Object:  StoredProcedure [dbo].[Sp_LuongXeGui]    Script Date: 24-04-2024 20:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[Sp_LuongXeGui](@MaKhu VARCHAR(10))
AS BEGIN
	SELECT  YEAR(ThoiGianVao) Nam, 
			COUNT(MaGD) SoLuong,
			MIN(ThoiGianVao) DauTien,
			MAX(ThoiGianVao) CuoiCung
	FROM GiaoDich g JOIN LoaiXe x ON g.MaLoaiXe = x.MaLoaiXe
					JOIN KhuGuiXe k ON k.MaKhu = x.MaKhu
	WHERE x.MaKhu = @MaKhu
	GROUP BY  YEAR(ThoiGianVao)
END
GO
/****** Object:  StoredProcedure [dbo].[Sp_LuongXeKhachVangLaiGuiTheoNam]    Script Date: 24-04-2024 20:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

----------------------------
-- PROCEDURE Lượng xe khách vãng lai gửi theo năm
CREATE   PROC [dbo].[Sp_LuongXeKhachVangLaiGuiTheoNam](@MaKhu VARCHAR(10))
AS BEGIN
	SELECT  YEAR(ThoiGianVao) Nam, 
			COUNT(MaGD) SoLuong,
			MIN(ThoiGianVao) DauTien,
			MAX(ThoiGianVao) CuoiCung
	FROM GiaoDich g JOIN TheXeKhachVangLai t ON g.KhachVangLaiID = t.MaThe
					JOIN LoaiXe x ON t.MaLoaiXe = x.MaLoaiXe
					JOIN KhuGuiXe k ON k.MaKhu = x.MaKhu
	WHERE x.MaKhu = @MaKhu
	GROUP BY  YEAR(ThoiGianVao)
END
GO
/****** Object:  StoredProcedure [dbo].[Sp_LuongXeKhachVangLaiGuiTheoNgay]    Script Date: 24-04-2024 20:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- PROCEDURE Lượng xe khách vãng lai gửi theo năm, tháng, ngày
CREATE   PROC [dbo].[Sp_LuongXeKhachVangLaiGuiTheoNgay](@MaKhu VARCHAR(10), @nam INT, @thang INT)
AS BEGIN
	SELECT	CONCAT(	YEAR(ThoiGianVao), '/', MONTH(ThoiGianVao), '/', DAY(ThoiGianVao)) ThoiGian,
			COUNT(MaGD) SoLuong,
			MIN(ThoiGianVao) DauTien,
			MAX(ThoiGianVao) CuoiCung
	FROM GiaoDich g JOIN TheXeKhachVangLai t ON g.KhachVangLaiID = t.MaThe
					JOIN LoaiXe x ON t.MaLoaiXe = x.MaLoaiXe
					JOIN KhuGuiXe k ON k.MaKhu = x.MaKhu
	WHERE x.MaKhu = @MaKhu AND YEAR(ThoiGianVao) = @nam AND MONTH(ThoiGianVao) = @thang
	GROUP BY  MONTH(ThoiGianVao), YEAR(ThoiGianVao), DAY(ThoiGianVao)
END
GO
/****** Object:  StoredProcedure [dbo].[Sp_LuongXeKhachVangLaiGuiTheoThang]    Script Date: 24-04-2024 20:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- PROCEDURE Lượng xe khách vãng lai gửi theo năm, tháng
CREATE   PROC [dbo].[Sp_LuongXeKhachVangLaiGuiTheoThang](@MaKhu VARCHAR(10), @nam INT)
AS BEGIN
	SELECT  CONCAT(	YEAR(ThoiGianVao), '/', MONTH(ThoiGianVao)) ThoiGian,
			COUNT(MaGD) SoLuong,
			MIN(ThoiGianVao) DauTien,
			MAX(ThoiGianVao) CuoiCung
	FROM GiaoDich g JOIN TheXeKhachVangLai t ON g.KhachVangLaiID = t.MaThe
					JOIN LoaiXe x ON t.MaLoaiXe = x.MaLoaiXe
					JOIN KhuGuiXe k ON k.MaKhu = x.MaKhu
	WHERE x.MaKhu = @MaKhu AND YEAR(ThoiGianVao) = @nam
	GROUP BY  YEAR(ThoiGianVao), MONTH(ThoiGianVao)
END
GO
/****** Object:  StoredProcedure [dbo].[Sp_LuongXeKhachVangLainGuiTheoNam]    Script Date: 24-04-2024 20:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- PROCEDURE Lượng xe khách vãng lai gửi theo năm
CREATE   PROC [dbo].[Sp_LuongXeKhachVangLainGuiTheoNam](@MaKhu VARCHAR(10))
AS BEGIN
	SELECT  YEAR(ThoiGianVao) Nam, 
			COUNT(MaGD) SoLuong,
			MIN(ThoiGianVao) DauTien,
			MAX(ThoiGianVao) CuoiCung
	FROM GiaoDich g JOIN TheXeKhachVangLai t ON g.CuDanID = t.MaThe
					JOIN LoaiXe x ON t.MaLoaiXe = x.MaLoaiXe
					JOIN KhuGuiXe k ON k.MaKhu = x.MaKhu
	WHERE x.MaKhu = @MaKhu
	GROUP BY  YEAR(ThoiGianVao)
END
GO
/****** Object:  StoredProcedure [dbo].[sp_SoLuongXe]    Script Date: 24-04-2024 20:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
	CREATE PROCEDURE [dbo].[sp_SoLuongXe]
	AS
	BEGIN
		SELECT 
		CONVERT(Date, ThoiGianVao) AS ThoiGian,
		COUNT(*) AS SoLuongXe,
		(min(ThoiGianVao)) as VaoSom, 
		max(ThoiGianVao) as VaoTre, 
		min(ThoiGianRa) as RaSom, 
		max(ThoiGianRa) as RaTre
	
		FROM ThongTinGuiXe	
		GROUP BY 
		CONVERT(Date, ThoiGianVao);

	END;

EXEC sp_SoLuongXe;

--sp_SoLuongXeTheoThang
/*
go
	CREATE PROCEDURE sp_SoLuongXeTheoThang(@thang int)
	AS
	BEGIN
		SELECT 
		CONVERT(Date, ThoiGianVao) AS ThoiGian,
		COUNT(*) AS SoLuongXe,
		(min(ThoiGianVao)) as VaoSom, 
		max(ThoiGianVao) as VaoTre, 
		min(ThoiGianRa) as RaSom, 
		max(ThoiGianRa) as RaTre
	
		FROM ThongTinGuiXe
		where month(ThoiGianVao) = @thang
		GROUP BY 
		CONVERT(Date, ThoiGianVao);

	END;

EXEC  sp_SoLuongXeTheoThang 11;
drop proc sp_SoLuongXeTheoThang
*/

GO
/****** Object:  StoredProcedure [dbo].[sp_SoLuongXeTheoThang]    Script Date: 24-04-2024 20:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[sp_SoLuongXeTheoThang]
as
	begin
		SELECT 
		MONTH(ThoiGianVao) AS ThoiGian,
		COUNT(*) AS SoLuongXe,
		MIN(ThoiGianVao) AS VaoSom, 
		MAX(ThoiGianVao) AS VaoTre, 
		MIN(ThoiGianRa) AS RaSom, 
		MAX(ThoiGianRa) AS RaTre
		FROM ThongTinGuiXe
		GROUP BY MONTH(ThoiGianVao)
		ORDER BY ThoiGian;
	end;

	exec sp_SoLuongXeTheoThang;

--sp_SoLuongXeTheoNam
/*
go
	CREATE PROCEDURE sp_SoLuongXeTheoNam(@nam int)
	AS
	BEGIN
		SELECT 
		CONVERT(Date, ThoiGianVao) AS ThoiGian,
		COUNT(*) AS SoLuongXe,
		(min(ThoiGianVao)) as VaoSom, 
		max(ThoiGianVao) as VaoTre, 
		min(ThoiGianRa) as RaSom, 
		max(ThoiGianRa) as RaTre
	
		FROM ThongTinGuiXe
		where year(ThoiGianVao) = @nam
		GROUP BY 
		CONVERT(Date, ThoiGianVao);

	END;

	exec sp_SoLuongXeTheoNam 2023;
	drop proc sp_SoLuongXeTheoNam;
*/

GO
/****** Object:  StoredProcedure [dbo].[SP_TongDoanhThu]    Script Date: 24-04-2024 20:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- PROCEDURE TỔNG DOANH THU CƯ DÂN
CREATE PROC [dbo].[SP_TongDoanhThu](@TimeStart DATE, @TimeEnd DATE)
AS BEGIN
	SELECT FORMAT(SUM(PhiGuiXe), '###,### VND') DoanhThu FROM GiaoDich g JOIN TheXeCuDan t ON g.CuDanID = t.MaThe
					JOIN LoaiXe x ON t.MaLoaiXe = x.MaLoaiXe
					JOIN KhuGuiXe k ON k.MaKhu = x.MaKhu
					WHERE ThoiGianVao BETWEEN @TimeStart AND @TimeEnd
END
GO
/****** Object:  StoredProcedure [dbo].[SP_TongDoanhThuKhachVangLai]    Script Date: 24-04-2024 20:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_TongDoanhThuKhachVangLai](@TimeStart DATE, @TimeEnd DATE)
AS BEGIN
	SELECT FORMAT(SUM(PhiGuiXe), '###,### VND') DoanhThu 
	FROM GiaoDich g JOIN TheXeKhachVangLai t ON g.KhachVangLaiID = t.MaThe
					JOIN LoaiXe x ON t.MaLoaiXe = x.MaLoaiXe
					JOIN KhuGuiXe k ON k.MaKhu = x.MaKhu
					WHERE ThoiGianVao BETWEEN @TimeStart AND @TimeEnd
END
GO
