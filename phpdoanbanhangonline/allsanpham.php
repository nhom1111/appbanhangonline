<?php
	require "connnect.php";
	$query = "SELECT sanpham.IdSP,sanpham.IdLoaiSP,sanpham.IdTaiKhoan,tailhoan.TenTaiKhoan,sanpham.TenSP,sanpham.SoLuong,sanpham.AnhSP,sanpham.GiaSP,sanpham.MoTa FROM `tailhoan` INNER JOIN `sanpham` ON sanpham.IdTaiKhoan=tailhoan.IdTaiKhoan ORDER BY rand() LIMIT 10";
	$data = mysqli_query($con,$query);
	class SanPham{
		function SanPham($idsp,$idloaisp,$idtaikhoan,$tentaikhoan,$tensp,$soluong,$anhsp,$giasp,$mota){
			$this->IdSP = $idsp;
			$this->IdLoaiSP = $idloaisp;
			$this->IdTaiKhoan = $idtaikhoan;
			$this->TenTaiKhoan = $tentaikhoan;
			$this->TenSP = $tensp;
			$this->SoLuong = $soluong;
			$this->AnhSP = $anhsp;
			$this->GiaSP = $giasp;
			$this->MoTa = $mota;
		}
	}
	$mangsanpham=array();
	while($row=mysqli_fetch_assoc($data)){
		array_push($mangsanpham,new SanPham($row['IdSP'],$row['IdLoaiSP'],
			$row['IdTaiKhoan'],
			$row['TenTaiKhoan'],
			$row['TenSP'],
			$row['SoLuong'],
			$row['AnhSP'],
			$row['GiaSP'],
			$row['MoTa']));
	}
	echo json_encode($mangsanpham);
?>