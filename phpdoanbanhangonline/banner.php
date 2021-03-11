<?php
	require "connect.php";
	class QuangCao{
		function QuangCao($idquangcao,$idloai,$anhqc,$mota){
				$this->IdQuangCao 	= $idquangcao;
				$this->IdLoai		= $idloai;
				$this->AnhQC		= $anhqc;
				$this->MoTa 		= $mota;

		}
	}

	$query = "SELECT * FROM `quangcao`";
	$data = mysqli_query($con,$query);
	$mangquangcao	= array();

	while($row = mysqli_fetch_assoc($data)){
		array_push($mangquangcao,new QuangCao($row['IdQuangCao'],$row['IdLoai'],$row['AnhQC'],$row['MoTa']));
	}
	echo json_encode($mangquangcao);
?>