import { initializeApp } from "firebase/app";
import { getMessaging, getToken } from "firebase/messaging";

// let db = null

export const initServiceWorker = async () => {

  const serviceWorker = await navigator.serviceWorker.getRegistration();
  await navigator.serviceWorker.ready;


  const firebaseConfig = {
    apiKey: "AIzaSyBvFUID_E-JBGmWzS4XURSjuwHN7tnx5co",
    authDomain: "comparify-notification.firebaseapp.com",
    projectId: "comparify-notification",
    storageBucket: "comparify-notification.appspot.com",
    messagingSenderId: "15699735562",
    appId: "1:15699735562:web:068127c181ad262ce99bf3"
  };

  const firebase = initializeApp(firebaseConfig);
  const messaging = getMessaging(firebase);

  Notification.requestPermission().then((permission) => {
    if (permission === 'granted') {
      console.log('Notification permission granted.');
    } else {
      console.log('Unable to get permission to notify.');
    }
  });

  navigator.serviceWorker.addEventListener('message', event => {
    // if (event.type === 'notification') {
    //   showNotification(event);
    // }
  });


  const currentToken = await getToken(messaging, {
      serviceWorkerRegistration: serviceWorker,
    validKey: 'BM-zrKtyYfQP0ixITm4mr8Sz-zJSIsYtqUPN1eOKpx49yNi2owRDSJrfUelgAGzZYYQAC8uaM02LosoyM2DH-CY'});
  
  console.log(currentToken);

  const url = process.env.REACT_APP_SERVER_BASE_URL + 
    process.env.REACT_APP_SERVER_CONTEXT_PATH + "notification/receiver/register";

  fetch(url, {
    method: "POST",
    body: JSON.stringify({identifier: currentToken}),
    headers:{
      "Content-Type": "application/json",
      "Authorization": "Bearer "+localStorage.getItem("auth-token")
    }
  })
  .then(r => r.text())
  .then(r => console.log(r));


}

// async function showNotification(event) {
//   const db = await getDb();
//   const tx = db.transaction(event.type, 'readonly');
//   const store = tx.objectStore(event.type);
// }


// async function getDb() {
//   if (db) {
//     return Promise.resolve(db);
//   }
//   return new Promise(resolve => {
//     const openRequest = indexedDB.open("chuck", 1);

//     openRequest.onupgradeneeded = event => {
//       const data = event.target.result;
//       data.createObjectStore('notification', { keyPath: 'id' });
//     };

//     openRequest.onsuccess = event => {
//       db = event.target.result;
//       resolve(db);
//     }
//   })
// };