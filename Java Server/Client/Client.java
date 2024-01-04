import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        int portNumber = 1234;
        Socket clientSocket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;


        try {
            clientSocket = new Socket("localhost",portNumber);

            inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(clientSocket.getOutputStream());

            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedReader = new BufferedReader(inputStreamReader);

            Scanner scanner = new Scanner(System.in);

            while (true)
            {
                String msgToServer = scanner.nextLine();

                bufferedWriter.write(msgToServer);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                System.out.println("Server:" + bufferedReader.readLine());

                if (msgToServer.equalsIgnoreCase("sg"))
                {
                    break;
                }
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }finally {
            try {
                if (clientSocket != null) {clientSocket.close();}
                if (outputStreamWriter != null) {outputStreamWriter.close();}
                if (inputStreamReader != null) {inputStreamReader.close();}
                if (bufferedReader != null) {bufferedReader.close();}
                if (bufferedWriter != null) {bufferedWriter.close();}
            }catch (IOException e){
                e.printStackTrace();
            }


        }

    }
}