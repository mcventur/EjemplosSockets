import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloComunicacionCliente extends Thread {

    public static final String TOKEN_FIN = "#corto#";
    private Socket cliente;

    public HiloComunicacionCliente(Socket socketCliente) {
        cliente = socketCliente;
    }

    @Override
    public void run() {
        try {
            BufferedReader lecturaCliente = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter escrituraCliente = new PrintWriter(cliente.getOutputStream(), true);
            escrituraCliente.println("Hola amigo. ¿Cómo te llamas?");
            String nombre = lecturaCliente.readLine();
            System.out.println("El nombre del cliente es: " + nombre);
            escrituraCliente.println("Encantado de concocerte " + nombre + "\t" + TOKEN_FIN);
            cliente.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
