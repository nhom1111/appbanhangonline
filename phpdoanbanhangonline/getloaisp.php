<?php
	require "connect.php";
	$query = "SELECT * FROM `loaisanphanm` ORDER BY rand() LIMIT 5";
	$data = mysqli_query($con,$query);
	class LoaiSP{
		function LoaiSP($iploai,$tenloai,$anhloai){
			$this->IdLoaiSP=$iploai;
			$this->TenLoaiSP=$tenloai;
			$this->AnhLoaiSP=$anhloai;
		}
	}
	$loaisp=array();
	while($row = mysqli_fetch_assoc($data)){
		array_push($loaisp,new LoaiSP($row['IdLoaiSP'],
									$row['TenLoaiSP'],
									$row['AnhLoaiSP']));
	}
	echo json_encode($loaisp);
?>