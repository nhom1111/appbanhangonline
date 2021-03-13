<?php
	require "connect.php";
	$json = $_POST['json'];
	$data = json_decode($json,true);
	class SanPham{
		function SanPham($idsp,$idloaisp,$idtaikhoan,$tentaikhoan,$tensp,$soluong,$anhsp,$giasp,$mota){
			$this->IdSP = $idsp;
			$this->IdLoaiSP = $idloaisp;
			$this->IdTaiKhoan = $idtaikhoan;
			$this->TenSP = $tensp;
			$this->SoLuong = $soluong;
			$this->AnhSP = $anhsp;
			$this->GiaSP = $giasp;
			$this->MoTa = $mota;
		}
	}


	foreach ($data as $key => $value) {
		$idsp 		= $value['IdSP'];
	
		$idhoadon	= $value['IdHoaDon'];
		
		$soluong	= $value['SoLuong'];
	
		$gia		= $value['Gia'];

		$query = "INSERT INTO `chitiethoadon`(`IdCT`, `IdSP`, `IdHoaDon`, `SoLuong`, `Gia`, `TrangThai`) VALUES (null,'$idsp','$idhoadon','$soluong','$gia','1')";
		$Dta =mysqli_query($con,$query);

		$querysanpham = "SELECT * FROM `sanpham` WHERE sanpham.IdSP = '$idsp'";
		$datasanpham = mysqli_query($con,$querysanpham);
		$sp = new SanPham($datasanpham['IdSP'],
						$datasanpham['IdSP'],
						$datasanpham['IdLoaiSP'],
						$datasanpham['TenSP'],
						$datasanpham['SoLuong'],
						$datasanpham['AnhSP'],
						$datasanpham['GiaSP'],
						$datasanpham['MoTa']);
		$soluongconlai = $sp->soluong-$soluong;
		$queryupdate = "UPDATE `sanpham` SET `SoLuong`=$soluongconlai WHERE sanpham.IdSP = '$idsp'";
		mysqli_query($con,$queryupdate);


	}
	if($Dta){
		echo "success";
	}else{
		echo "fail";
	}

?>