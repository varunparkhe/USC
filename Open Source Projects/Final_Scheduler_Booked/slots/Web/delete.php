<?php
   // user has clicked a delete hyperlink

  define('ROOT_DIR', '../');
	include('connection.php');
	require_once(ROOT_DIR . 'Domain/Access/namespace.php');
	require_once(ROOT_DIR . 'lib/Config/namespace.php');
	require_once(ROOT_DIR . 'lib/Common/namespace.php');
	require_once(ROOT_DIR . 'lib/Application/Attributes/namespace.php');
   
   if($_GET['action'] && $_GET['action'] == 'delete') {

   	$filename=$_GET['filename'];
   	$action=$_GET['action'];

   	/*8echo $filename;
   	echo $action;*/

   	if(file_exists($filename))
   	{
   	 unlink($filename);
   	}
    }
   	$userSession = ServiceLocator::GetServer()->GetUserSession();
	$uid = $userSession->UserId;

	$query = "SELECT * from scprofile where user_id = '$uid'";
	$result1 = mysql_query($query,$bd);

	if ( mysql_num_rows($result1) > 0)
	header("location: scupdate.php");

	else {
	$query = "SELECT * from docprofile where user_id = '$uid'";
	$result1 = mysql_query($query,$bd);
	if ( mysql_num_rows($result1) > 0)
		header("location: docupdate.php");
	}


     // header("Location:docupdate.php");
       //exit();
   	 
   	 
   
?>