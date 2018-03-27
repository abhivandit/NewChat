<?php 
    require_once 'DbOperation3.php';
    $response = array(); 

 if($_SERVER['REQUEST_METHOD']=='POST'){

        $emailsender = $_POST['emailsender'];
      
        $emailreceiver =$_POST['emailreceiver'];

       
      //  echo json_encode('ss');

        $db = new DbOperation3();
       //echo 'kk'; 
      

        $result = $db->checkLog($emailsender,$emailreceiver);
        //echo $result;

        if($result == 0){
            
            $response['message'] = 'successful';
        }else{
            
            $response['message']='unsucessful';
        }
 }else{
        $response['error']=true;
        $response['message']='Invalid Request...';
   }

   echo json_encode($response);
