<?php
 
class DbOperation3
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
 


   
    public function checkLog($emailsender,$emailreceiver){
        //echo ' eher ar you';
      $counter=1;
          //  echo ' herei am ';
      $stmt = $this->con->prepare("SELECT id FROM chatlog WHERE sender = ? and receiver = ? and counter = ? ");
        $stmt->bind_param("ssi",$emailsender,$emailreceiver,$counter);
        $stmt->execute();
        $stmt->store_result();
        $num_rows = $stmt->num_rows;
        $stmt->close();
      //  echo $num_rows;
        if($num_rows>0){
        return (0);}
        else{
            return 1;
        }
       
    }
}
 
    //the method will check if email already exist 
   
?>
