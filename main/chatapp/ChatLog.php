<?php 
    require_once 'DbOperation2.php';
    $response = array(); 

if($_SERVER['REQUEST_METHOD']=='POST'){

        $sender =$_POST['sender'];
        $senderId=$_POST['senderId'];
        $receiver =$_POST['receiver'];
        $receiverId=$_POST['receiverId'];
       $message=$_POST['message'];
       $counter2=$_POST['counter'];

      /*  $sender =//$_POST['sender'];
        $senderId=//$_POST['senderId'];
        $receiver =//$_POST['receiver'];
        $receiverId=$_POST['receiverId'];
       $message=$_POST['message'];
       $counter2=$_POST['counter'];*/

       
      //  echo json_encode('ss');
$counter=1;
        $db = new DbOperation2();
       // echo 'kk'; 
        if($counter2=="1"){
          $counter=1;
        }
        else{
          $counter=0;

        }
        $result = $db->chatLog1($sender,$senderId,$receiver,$receiverId,$message,$counter);

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
