import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class DemoClient {
    Socket socket;
    OutputStream outputStream;
    InputStream inputStream;
    int port;

    public DemoClient(int port) {
        this.port = port;
    }

    private void start() throws IOException {
        this.socket = new Socket("localhost",this.port);
        this.outputStream = socket.getOutputStream();
        this.inputStream = socket.getInputStream();
    }
    private void write(String str) throws IOException {
        System.out.println("Write to socket : " + str);
        outputStream.write(str.getBytes());
    }
    private String read() throws IOException {
        byte[] bytes = new byte[1024];
        inputStream.read(bytes);
        return new String(bytes);
    }
    private void stop() throws IOException {
        outputStream.close();
        inputStream.close();
        socket.close();
    }

    public void run() throws IOException {
        start();
        System.out.println(read());
        write("Hello from client : " + socket.getLocalPort());
        stop();
    }

    public static void main(String[] args) throws IOException {
        DemoClient demoClient = new DemoClient(8080);
        demoClient.run();
    }
}
