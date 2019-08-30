import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class DemoServer {
    ServerSocket serverSocket;
    Socket clientSocket;
    OutputStream outputStream;
    InputStream inputStream;
    int port;

    public DemoServer(int port) {
        this.port = port;
    }

    private void start() throws IOException {
        serverSocket = new ServerSocket(port);
    }

    private void accept() throws IOException {
        System.out.println("Server is waiting for new connection....");
        clientSocket =  serverSocket.accept();
        outputStream = clientSocket.getOutputStream();
        inputStream = clientSocket.getInputStream();
    }

    private void write(String str) throws IOException {
        System.out.println("Write to socket : " + str);
        outputStream.write(str.getBytes());
    }
    private String read() throws IOException {
        byte[] readBuffer = new byte[1024];
        inputStream.read(readBuffer);
        return new String(readBuffer);
    }

    private void close() throws IOException {
        outputStream.close();
        inputStream.close();
        clientSocket.close();
    }

    public void run() throws IOException {
        start();
        while (true) {
            this.accept();
            write("Hello from server");
            System.out.println(read());
            close();
        }
    }

    public static void main(String[] args) throws IOException {
        DemoServer demoServer = new DemoServer(8080);
        demoServer.run();
    }
}
