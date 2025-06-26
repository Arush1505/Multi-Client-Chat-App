# 🗨️ Multi-Client Group Chat Application in Java

A real-time group chat application built using **Java Socket Programming** and **Multithreading**.  
This project demonstrates how multiple clients can connect to a central server, send messages, and receive messages from others in the group — all in real time.

---

## 🚀 Features

- ✅ Supports multiple clients simultaneously using **multithreading**
- ✅ Real-time message broadcasting from server to all clients
- ✅ Clean client disconnection and server shutdown handling
- ✅ Stream-based I/O using `BufferedReader` and `BufferedWriter`

---

## 🧑‍💻 How It Works

- The **Server** listens on a port using `ServerSocket`.
- Each incoming client connection spawns a **new thread** using a `ClientHandler`.
- Messages received from a client are **broadcast to all connected clients**.
- Clients can type and send messages via the terminal, and also receive messages concurrently.

---

## 🛠️ Tech Stack

- Java (JDK 8+)
- Java Sockets (`java.net`)
- Multithreading
- Console-based input/output (`Scanner`, `BufferedReader`, `BufferedWriter`)

---
