package academy.mindswap.client;

import java.io.*;
import java.net.Socket;

public class Client {

    /**
     * This method starts the Client, and connects it
     * to the server.
     *
     * @param args
     */
    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.start("localhost", 8080);
        } catch (IOException e) {
            System.out.println("Connection closed...");
        }

    }

    /**
     * This method connects to the server. Creates a thread
     * to deal with the client input(commands).
     * And listens to server's output, which then prints to
     * the client.
     *
     * @param host - String host name of the server
     * @param port - int port number of the server
     * @throws IOException
     */
    private void start(String host, int port) throws IOException {
        Socket socket = new Socket(host, port);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        new Thread(new KeyboardHandler(out, socket)).start();

        while (!socket.isClosed()) {
            String line = in.readLine();

            if (line == null) {
                socket.close();
                continue;
            }

            System.out.println(line);
        }
    }

    private class KeyboardHandler implements Runnable {
        private PrintWriter out;
        private Socket socket;
        private BufferedReader in;

        public KeyboardHandler(PrintWriter out, Socket socket) {
            this.out = out;
            this.socket = socket;
            this.in = new BufferedReader(new InputStreamReader(System.in));
        }

        /**
         * This method deals with commands sent to the server
         * by the client
         */
        @Override
        public void run() {

            while (!socket.isClosed()) {
                try {
                    String line = in.readLine();

                    out.println(line);

                    if (line.equals("/quit")) {
                        socket.close();
                        System.exit(0);
                    }
                } catch (IOException e) {
                    System.out.println("Something went wrong with the server. Connection closing...");
                }
            }
        }
    }
}