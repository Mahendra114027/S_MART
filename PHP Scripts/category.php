<?php
session_start();
?>

<?php
	$con = mysqli_connect('localhost','root','','shop_db');
	
	if(!$con)
	{
		echo 'Not Connected to Server!';
	}
	
	if(!mysqli_select_db($con,'shop_db'))
	{
		echo 'Database Not selected!';
	}

	//mysqli_real_escape_string($con,$_POST['catName']);
	$cname=$_POST['catName'];
	//echo $cname;
	$sql="SELECT * FROM Products_info WHERE CName='$cname'";
	$res = mysqli_query($con,$sql) or die(mysqli_error($con));
	$rows = array();
	 
	while($row = mysqli_fetch_array($res)){
	array_push($rows,array('Item'=>$row[1],
'Price'=>$row[8],
'Image'=>$row[6]
));
	}
	 
	echo json_encode(array("rows"=>$rows));
	

?> 

