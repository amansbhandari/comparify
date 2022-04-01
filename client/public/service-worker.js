importScripts('https://www.gstatic.com/firebasejs/7.10.0/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/7.10.0/firebase-messaging.js');

firebase.initializeApp({
  apiKey: "AIzaSyBvFUID_E-JBGmWzS4XURSjuwHN7tnx5co",
  authDomain: "comparify-notification.firebaseapp.com",
  projectId: "comparify-notification",
  storageBucket: "comparify-notification.appspot.com",
  messagingSenderId: "15699735562",
  appId: "1:15699735562:web:068127c181ad262ce99bf3"
});

const messaging = firebase.messaging();
messaging.usePublicVapidKey('BM-zrKtyYfQP0ixITm4mr8Sz-zJSIsYtqUPN1eOKpx49yNi2owRDSJrfUelgAGzZYYQAC8uaM02LosoyM2DH-CY');


self.addEventListener('push', async event => {
  const db = await getDb();

  let data = null
  try {
    data = await event.data.json()
    console.log(data)
  } catch(e){
    return;
  }

  const notificationTitle = data.notification.title;
  const notificationOptions = {
    body: data.notification.body,
    icon: data.notification.icon
  };

  //self.registration.showNotification(notificationTitle, notificationOptions)

  // if(data.type) {

  //   const tx = this.db.transaction(data.type, 'readwrite');
  //   const store = tx.objectStore(data.type);

  //   store.put(data.data);

  //   tx.oncomplete = async e => {
  //     const allClients = await clients.matchAll({ includeUncontrolled: true });
  //     for (const client of allClients) {
  //       client.postMessage('newData');
  //     }
  //   };
  // }
});

async function getDb() {
  if (this.db) {
    return Promise.resolve(this.db);
  }

  return new Promise(resolve => {
    const openRequest = indexedDB.open("chuck", 1);

    openRequest.onupgradeneeded = event => {
      const db = event.target.result;
      db.createObjectStore('notification', { keyPath: 'id' });
    };

    openRequest.onsuccess = event => {
      this.db = event.target.result;
      resolve(this.db);
    }
  });
}

const CACHE_NAME = 'comparify-cache-v1';
const urlsToCache = [];

self.addEventListener('install', event => {
  // event.waitUntil(caches.open(CACHE_NAME)
  //   .then(cache => cache.addAll(urlsToCache)));
});

self.addEventListener('fetch', event => {
  // event.respondWith(
  //   caches.match(event.request)
  //     .then(response => {
  //       if (response) {
  //         return response;
  //       }
  //       return fetch(event.request);
  //     }
  //     )
  // );
});