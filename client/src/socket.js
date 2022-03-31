import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import { toast } from "react-toastify";


let stompClient = null;

export const openSocket = () => {
  const sock = new SockJS('http://localhost:9001/api/socket');
  stompClient = Stomp.over(sock);

  const headers = { "Authorization": "Bearer " + localStorage.getItem("auth-token") }

  stompClient.connect(headers, (frame) => {
    stompClient.subscribe('/topic/new-alert', function (alert) {
      toast.success(alert.body);
    });
  }, (error) => {
    console.log(error)
  });

};


export const closeSocket = () => {
  stompClient.disconnect((response) => {
    console.log("Connection closed!")
  })
}

export const isSocketConnected = () => {
  return (stompClient != null)
}



