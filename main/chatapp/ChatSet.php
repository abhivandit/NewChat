<?php 
    require_once 'DbOperation4.php';
    $response = array(); 

if($_SERVER['REQUEST_METHOD']=='POST'){

        $sender =$_POST['sender'];
     
        $receiver =$_POST['receiver'];
    
     

       
      //  echo json_encode('ss');

        $db = new DbOperation4();
       // echo 'kk'; 
     
        $result = $db->chatsetter($receiver,$sender);

        if($result == 0){
            $response['error'] = false; 
            $response['message'] = 'successful';
        }else{
            $response['error'] = true;
            $response['message']='unsucessful';
        }
 }else{
        $response['error']=true;
        $response['message']='Invalid Request...';
   }

   echo json_encode($response);
