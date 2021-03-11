<?php
	require "connect.php";
	$diachi = "23 hung vuong, nui thanh,ha noi";
	$ngay = "2020-03-12";
	$gio	="12:24:00";
	$idkhachhang=;
	$query ="INSERT INTO `hoadon` (`IdHoaDon`, `DiaChi`, `Ngay`, `Gio`, `IdKhachHang`) VALUES (NULL, 'asdasd', '2021-03-16', '08:09:06.000', '1')";
	if (mysqli_query($con,$query)) {
		$idhoadon = $con->insert_id;
		echo $idhoadon;
	}else{
		echo "Fail";
	}

?>