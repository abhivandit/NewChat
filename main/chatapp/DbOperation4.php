<?php
 
class DbOperation4
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
 


   
    public function chatsetter($receiver,$sender){
        //echo ' eher ar you';
      $counter=0;
          //  echo ' herei am ';
      $stmt = $this->con->prepare("UPDATE chatlog SET counter = ? WHERE sender = ? and receiver = ?");
        $stmt->bind_param("iss",$counter,$sender,$receiver);
        if($stmt->execute()){return 0;}
        else{
            return 1;
        }

        
       
    }
}
 
    //the method will check if email already exist 
   
?>
