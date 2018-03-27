<?php
 
class DbOperation2
{
    //Database connection link
    private $con;
 
    //Class constructor
    function __construct()
    {
        //Getting the DbConnect.php file
        //echo 'hereher';
        require_once dirname(__FILE__) . '/DbConnect.php';
       // echo 'here';
 
        //Creating a DbConnect object to connect to the database
        $db = new DbConnect();
 
        //Initializing our connection link of this class
        //by calling the method connect of DbConnect class
        $this->con = $db->connect();
    }
 


   
    public function chatLog1($sender,$senderId,$receiver,$receiverId,$message,$counter){
        //echo ' eher ar you';
      
          //  echo ' herei am ';
         $counter=1;
            $stmt = $this->con->prepare("INSERT INTO chatlog (sender,senderId,receiver,receiverId,message,counter) VALUES (?,?,?,?,?,?) ");
            $stmt->bind_param("sssssi",$sender,$senderId,$receiver,$receiverId,$message,$counter);
            if($stmt->execute())
                return 0; //return 0 means success
            return 1; //return 1 means failure
       
    }
}
 
    //the method will check if email already exist 
   
?>
