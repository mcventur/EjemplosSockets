import java.io.IOException;
import java.net.*;

public class ClienteUDP {
    private static String DIRECCION = "localhost";
    private static int PUERTO = 1500;
    private static final int TAM_BUFFER = 1024;

    public static void main(String[] args) {
        String saludo = "Hola mundo!";
        byte[] mensaje = new byte[TAM_BUFFER];
        mensaje = saludo.getBytes();
        //Con UDP, la información de destino y puerto y va en el datagrama, no en el Socket
        try(DatagramSocket socketCliente = new DatagramSocket()) {
            DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, InetAddress.getByName(DIRECCION), PUERTO);
            socketCliente.send(envio);
        } catch (IOException e) {
            //Hay varias sentencias que generan excepción, pero todos los tipos se engloban en el supertipo IOException
            throw new RuntimeException(e);
        }
    }
}
