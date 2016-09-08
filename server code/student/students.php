<?php
 
/*
 * Following code will list all the products
 */
 
// array for JSON response
$response = array();
 
// include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();
$conn = $db->connect();

// get all products from products table
$result = mysqli_query($conn , "SELECT *FROM record") or die(mysql_error());
 
// check for empty result
if (mysqli_num_rows($result) > 0) {
    // looping through all results
    // products node
	    //$response["success"] = 1;
        $response["student"] = array();
 
    while ($row = mysqli_fetch_array($result)) {
        // temp user array
        $profile = array();
		
       // $book["id"] = $row["id"];
        $book["id"] = $row["id"];
        $book["name"] = $row["name"];
        $book["faculty"] = $row["faculty"];
		$book["address"] = $row["address"];
		$book["email"] = $row["email"];
		$book["phone"] = $row["phone"];


		
	  
        // push single product into final response array
        array_push($response["student"], $book);
    }
	$db->close($conn);
			//print_r ($response);
	//var_dump(json_encode($response["militias"]));
	echo json_encode($response); 
	
//	echo <>
   
} else {

	$db->close($conn);
    // no products found
    $response["success"] = 0;
    $response["message"] = "No products found";
 
    // echo no users JSON
    echo json_encode($response);
}
?>
