<?php
	require "connect.php";
	$idtaikhoan =1;
	$query = "SELECT sanpham.IdSP,sanpham.IdLoaiSP,sanpham.IdTaiKhoan,tailhoan.TenTaiKhoan,sanpham.TenSP,sanpham.SoLuong,sanpham.AnhSP,sanpham.GiaSP,sanpham.MoTa FROM `tailhoan` INNER JOIN `sanpham` ON sanpham.IdTaiKhoan=tailhoan.IdTaiKhoan WHERE FIND_IN_SET('$idtaikhoan',tailhoan.IdTaiKhoan)";
	$data = mysqli_query($con,$query);
	class SanPham{
		function SanPham($idsp,$idloaisp,$idtaikhoan,$tentaikhoan,$sdt,$tensp,$soluong,$anhsp,$giasp,$mota){
			$this->IdSP = $idsp;
			$this->SDT  = $sdt;
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
			$row['SDT'],
			$row['TenSP'],
			$row['SoLuong'],
			$row['AnhSP'],
			$row['GiaSP'],
			$row['MoTa']));
	}
	echo json_encode($mangsanpham);
?>