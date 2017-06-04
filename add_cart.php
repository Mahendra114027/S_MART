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

	$ord=$_GET['add'];
	$pname=$_GET['proName'];
	$name=$_GET['custName'];
	$res=mysqli_fetch_assoc(mysqli_query($con,"SELECT * FROM Products_info WHERE PName=$pname"));
	$inplace=$res['In_Place_Qty'];
	$curr=$res['Curr_Qty'];
	$pid=$res['PID'];
	$x=mysqli_query($con,"SELECT CID FROM Customer_Info WHERE Customer_Name=$name");
	$cid=mysqli_fetch_array($x)['CID'];
	//echo "SELECT OID FROM Order_Info WHERE CID IN (SELECT O.CID FROM Order_Info O WHERE O.CID=$cid AND O.PID=$pid)";
	$res1=mysqli_fetch_array(mysqli_query($con,"SELECT OID FROM Order_Info WHERE CID IN (SELECT O.CID FROM Order_Info O WHERE O.CID=$cid AND O.PID=$pid)"));
	$oid=$res1['OID'];
	if(!$oid)
	{
		if($ord<intval($curr))
		{
			mysqli_query($con,"UPDATE Products_info SET In_Place_Qty=$inplace-$ord WHERE PID=$pid");
			mysqli_query($con,"INSERT INTO Order_Info VALUES (null,$pid,$cid,$ord,$ord,'2017-06-08',0)");
		}
		else
		{
			mysqli_query($con,"UPDATE Products_info SET In_Place_Qty=0  WHERE PID=$pid");
			mysqli_query($con,"INSERT INTO Order_Info VALUES (null,$pid,$cid,$inplace,$ord,'2017-06-08',0)") or die(mysqli_error());
		}	
	}
	else
	{
		
		if($ord<intval($curr))
		{
			mysqli_query($con,"UPDATE Products_info SET In_Place_Qty=$inplace-$ord WHERE PID=$pid");
			mysqli_query($con,"INSERT INTO Order_Info VALUES ($oid,$pid,$cid,$ord,0,'2017-06-08',0)");
		}
		else
		{
			mysqli_query($con,"UPDATE Products_info SET In_Place_Qty=0  WHERE PID=$pid");
			mysqli_query($con,"INSERT INTO Order_Info VALUES ($oid,$pid,$cid,$inplace,$ord,'2017-06-08',0)");
		}
	}

?> 

