import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    public static void main(String[] args) throws IOException {
        int portNumber = 1234;
        Socket clientSocket = null;
        ServerSocket serverSocket;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;

        serverSocket = new ServerSocket(portNumber);

        while (true)
        {
            try {
                clientSocket = serverSocket.accept();

                inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(clientSocket.getOutputStream());

                bufferedWriter = new BufferedWriter(outputStreamWriter);
                bufferedReader = new BufferedReader(inputStreamReader);

                while (true)
                {
                    String msgFromClient = bufferedReader.readLine();

                    System.out.println("Client: " + msgFromClient);
                    bufferedWriter.write("Mesaj gitti galiba");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    if (msgFromClient.equalsIgnoreCase("SG"))
                    {
                        break;
                    }
                }

                clientSocket.close();
                outputStreamWriter.close();
                inputStreamReader.close();
                bufferedWriter.close();
                bufferedReader.close();

            }catch (IOException e){
                e.printStackTrace();
                break;
            }
        }
    }
}
