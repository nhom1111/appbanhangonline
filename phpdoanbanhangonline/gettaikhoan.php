<?php
	require "connect.php";
	$tentaikhoan = "quoc08510";
	$query = 'SELECT * FROM `tailhoan` WHERE $tentaikhoan = tailhoan.TenTaiKhoan';
	$data = mysqli_query($con,$query);
	class TaiKhoan{
		function TaiKhoan($idtaikhoan,$tentaikhoan,$sdt,$gmail,$password,$anhtaikhoan){
			$this->IdTaiKhoan=$idtaikhoan;
			$this->TenTaiKhoan=$tentaikhoan;
			$this->SDT=$sdt;
			$this->Gmail=$gmail;
			$this->Password=$password;
			$this->AnhTaiKhoan=$anhtaikhoan;
		}
	}
	$taikhoan = new TaiKhoan($data['IdTaiKhoan'],
							$data['TenTaiKhoan'],
							$data['SDT'],
							$data['Gmail'],
							$data['Password'],
							$data['AnhTaiKhoan']);
	echo json_encode($taikhoan);
?>