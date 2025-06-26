import javax.imageio.stream.ImageInputStream;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket socket ;
    private BufferedReader bufferedReader ;
    private BufferedWriter bufferedWriter;
    private String user_name;

    public Client(Socket socket, String user_name) {
        try {
            this.socket = socket;
            this.user_name = user_name;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void sendMessage() {
        // Because waiting for messages is a blocking opertion so we need to alot a separate thread for this.
        try {
            bufferedWriter.write(user_name);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String message = scanner.nextLine();
                bufferedWriter.write(user_name + " : " + message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
        public void listening()
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                 String message_from_other;
                 try{
                     message_from_other = bufferedReader.readLine();
                     System.out.println(message_from_other);
                 }
                 catch(IOException e){
                     e.printStackTrace();
                    }
                }
            }).start();
        }




    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username for groupchat");
        String name = scanner.nextLine();
        Socket socket1 = new Socket("localhost",2000);
        Client client = new Client(socket1,name);
        client.sendMessage();
        client.listening();  // separate thread pe chal raha hai

    }

}
