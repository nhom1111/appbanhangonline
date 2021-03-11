<?php
	$file_path ="imageuser/";
	$file_path = $file_path.basename($_FILES['uploaded_file']['name']);
//tmp la file tam khi no chuyen len
	if(move_uploaded_file($_FILES['uploaded_file']['tmp_name'], destination)){
		echo $_FILES['uploaded_file']['name'];
	}else{
		echo "Fail";
	}

?>