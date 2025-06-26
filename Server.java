import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


private ServerSocket serverSocket = null;
public Server (ServerSocket serverSocket)
{
    this.serverSocket = serverSocket;
}

public void startServe ()
{
    try {
        while (!serverSocket.isClosed()) {
            Socket socket = serverSocket.accept();
            System.out.println("A new client has joined");
            ClientHandler clientHandler = new ClientHandler(socket);
            Thread thread = new Thread(clientHandler);
            thread.start();
        }
    }
    catch (IOException e)
    {
        e.printStackTrace();
    }
}

public void closeServer()
{
    try {
        if(serverSocket != null)  // means saying that serverSocket is a valid thing but still we want to close it for any reason
        {
            serverSocket.close();
        }
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}


    public static void main(String[] args) {
        int port = 2000;
        ServerSocket serverSocket1 = null;
        try {
            serverSocket1 = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Server server = new Server(serverSocket1);
        server.startServe();
    }

}


