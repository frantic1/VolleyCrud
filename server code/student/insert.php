<?php

// include('db_connect.php');

define('HOST','localhost');
define('USER','root');
define('PASS','');
define('DB','student');

$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');
if($con){
  echo 'connected';
}

//
echo '<pre>'; print_r($_POST); echo '</pre>';


//$title=$_POST["title"];
//$description=$_POST["desc"];
$name=$_POST["name"];
$faculty = $_POST["faculty"];
$address=$_POST["address"];
$email=$_POST["email"];
$phone=$_POST["phone"];


$insert = "INSERT INTO `record` (`id`, `name`, `faculty`, `address`, `email`, `phone`) VALUES (NULL, '".$name."',  '".$faculty."',  '".$address."',  '".$email."',  '".$phone."')";

    // $insert = "INSERT INTO blogs (id,title,description,image,type,author,created)
    // VALUES ('','11','12','13','14','15','16')";

    $test = mysqli_query($con, $insert);
	echo "Successful";

/*if() {
    //Tells you if its all ok
    //Writes the information to the database
    //$insert = "INSERT INTO 'blogs' ('id','title','description','image','type','author','created') VALUES ('','".$title."','".$description."','".$author."','".$type."','".$author."','".$date."')";

} else {
    //Gives and error if its not
    echo "Sorry, there was a problem uploading your file.";
}

header("Refresh: 2; url=form.php");
*/
 ?>
