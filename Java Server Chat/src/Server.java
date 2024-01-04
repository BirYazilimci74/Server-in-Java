import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket)
    {
        this.serverSocket = serverSocket;
    }

    public void startServer()
    {
        try {
            while (!serverSocket.isClosed())
            {
                Socket socket = serverSocket.accept();
                System.out.println("Someone joined the server");
                ClientHandler clientHandler = new ClientHandler(socket);

                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        }catch (IOException e){
            closeSocket();
        }
    }

    public void closeSocket()
    {
        try {
            if (serverSocket != null)
            {
                serverSocket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        int portNumber = 4321;
        ServerSocket serverSocket = new ServerSocket(portNumber);
        Server server = new Server(serverSocket);

        server.startServer();
    }
}
