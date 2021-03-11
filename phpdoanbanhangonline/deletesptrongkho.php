<?php
	require "connect.php";
	$idsp=$_POST['idsp'];
	$query="DELETE * FROM `sanpham` WHERE '$idsp'=sanpham.IdSP";
	
	if(mysqli_query($con,$query)){
		echo "success";
	}else{
		echo "fail";
	}
?>