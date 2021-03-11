<?php
	require "connect.php";
	class CT{
		function($idsp,$idhoadon,$soluong,$gia){
				$this->IdSP = $idsp;
				$this->IdHoaDon = $idhoadon;
				$this->SoLuong = $soluong;
				$this->Gia = $gia;
		}
	}

	$query = "SELECT `chitiethoadon`.`IdSP`,`chitiethoadon`.`IdHoaDon`,`chitiethoadon`.`SoLuong`,`chitiethoadon`.`Gia` FROM `chitiethoadon` WHERE IdSP=`1`";
	$data = mysqli_query($con,$query);
	$array = array();
	while($row = mysqli_fetch_assoc($data)){
		array_push($array,new CT($row['IdSP'],$row['IdHoaDon'],$row['SoLuong'],$row['Gia']));
	}
	echo json_encode($array);
?>