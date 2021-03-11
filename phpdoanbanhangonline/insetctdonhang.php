<?php
	require "connect.php";
	$json = $_POST['json'];
	$data = json_decode($json);
	foreach ($data as $key => $value) {
		$idsp 		= $value['idsp'];
		$idhoadon	= $value['idhoadon'];
		$soluong	= $value['soluong'];
		$gia		= $value['gia'];
		$query = "INSERT INTO `chitiethoadon` VALUES (null,'$idsp','$idhoadon','$soluong','$gia','1')";
		$Dta =mysqli_query($con,$query);
	}
	if($Dta){
		echo "1";
	}else{
		echo "fail";
	}

?>