import java.io.*;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientHandler  implements Runnable{

    private  static List<ClientHandler> list_clients = new ArrayList<>();

    /**
     *
         In java there are 2 types of streams
        Character stream - ends with Writer (PrintWriter , BufferedWriter)
        Byte stream - end with stream (outputstream)
     */

    private Socket socket = null;

    private BufferedReader bufferedReader ;
    private BufferedWriter bufferedWriter;
    private String user_name;

    public void run()
    {
        try {
            while(socket.isConnected()) {
                String recieved_message = bufferedReader.readLine();
                BroadCast(recieved_message);
            }
        } catch (IOException e) {
            closeAll(socket,bufferedWriter,bufferedReader);
        }
    }

    public ClientHandler(Socket socket){
        try{
            this.socket = socket;
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            this.bufferedWriter = new BufferedWriter(outputStreamWriter);
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println("Enter your user name :");
            this.user_name = bufferedReader.readLine();
            list_clients.add(this);
            BroadCast("Server : "+ user_name + "Has entered the Jhaat");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            closeAll(socket,bufferedWriter,bufferedReader);
        }
    }


    public void BroadCast(String string)
    {
        for(ClientHandler c : list_clients)
        {
            try {
                if(!(c.user_name.equals(user_name)))
//                c.bufferedWriter.write( c.user_name +" : "+ string);  Test karke dekhna
                bufferedWriter.write( c.user_name +" : "+ string);
                bufferedWriter.newLine(); // Compulsory because the BufferedReader need \n character to mark end of string so we send explicilty
                bufferedWriter.flush();
            } catch (IOException e) {
                closeAll(socket,bufferedWriter,bufferedReader);
                throw new RuntimeException(e);
            }
        }
    }



    public   void  closeAll(Socket socket , BufferedWriter bufferedWriter1 , BufferedReader bufferedReader1)
    {
            list_clients.remove(this);
        System.out.println("Server " + this.user_name + " Left the chat");
        try {
            if (socket!=null) {
                socket.close();
            }
            if (bufferedReader1!=null) {
                bufferedReader1.close();
            }
            if (bufferedWriter1!=null) {
                bufferedWriter1.close();
            }
            System.out.println("Closed evettything");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
