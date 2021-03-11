<?php
	require "connect.php";
	$tentaikhoan 	= $_POST['tentaikhoan'];
	$gmail			= $_POST['gmail'];
	$SDT			= $_POST['SDT'];
	$password		= $_POST['password'];
	
	
	$queryten = "SELECT * FROM `tailhoan`WHERE FIND_IN_SET('$tentaikhoan',tailhoan.TenTaiKhoan)";
	$querygmail="SELECT * FROM `tailhoan`WHERE FIND_IN_SET('$gmail',tailhoan.Gmail)";
	$querySDT   ="SELECT * FROM `tailhoan`WHERE FIND_IN_SET('$SDT',tailhoan.SDT)";
	
	$query = "INSERT INTO `tailhoan`(`IdTaiKhoan`, `TenTaiKhoan`, `SDT`, `Gmail`, `Password`, `AnhTaiKhoan`) VALUES (NULL,'$tentaikhoan','$SDT','$gmail','$password',NULL)";
	
	class TaiKhoan{
		function TaiKhoan($idtaikhoan,$tentaikhoan,$sdt,$gmail,$password,$anhtaikhoan){
			$this->IdTaiKhoan=$idtaikhoan;
			$this->TenTaiKhoan=$tentaikhoan;
			$this->SDT=$sdt;
			$this->Gmail=$gmail;
			$this->Password=$password;
			$this->AnhTaiKhoan=$anhtaikhoan;
		}
		public function gettentaikhoan(){
		    return $this->TenTaiKhoan;
		}
		public function getgmail(){
		    return $this->Gmail;
		}
		public function getSDT(){
		    return $this->SDT;
		}
		
	}
	$data = mysqli_query($con,$queryten);
	$row=mysqli_fetch_assoc($data);
	$taikhoan=new TaiKhoan($row['IdTaiKhoan'],
							$row['TenTaiKhoan'],
							$row['SDT'],
							$row['Gmail'],
							$row['Password'],
							$row['AnhTaiKhoan']);
	
	if($taikhoan->gettentaikhoan()!=NULL){
	    echo "tentaikhoantrung";
	}else{
	    $data = mysqli_query($con,$querygmail);
    	$row=mysqli_fetch_assoc($data);
    	$taikhoan=new TaiKhoan($row['IdTaiKhoan'],
    							$row['TenTaiKhoan'],
    							$row['SDT'],
    							$row['Gmail'],
    							$row['Password'],
    							$row['AnhTaiKhoan']);
	    
	    if($taikhoan->getgmail()!=NULL){
	        echo "gmailtrung";
	    }else{
	        $data = mysqli_query($con,$querySDT);
            $row=mysqli_fetch_assoc($data);
            $taikhoan=new TaiKhoan($row['IdTaiKhoan'],
            							$row['TenTaiKhoan'],
            							$row['SDT'],
            							$row['Gmail'],
            							$row['Password'],
            							$row['AnhTaiKhoan']);
	        if($taikhoan->getSDT()!=NULL){
	            echo "SDTtrung";
	        }else{
	            if(mysqli_query($con,$query)){
		            echo "success";
            	}else{
            		echo "fail";
            	}
	        }
	    }
	}
	
?>