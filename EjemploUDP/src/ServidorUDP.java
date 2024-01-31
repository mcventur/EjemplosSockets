import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServidorUDP {
    private static final int PUERTO = 1500;
    private static final int NUM_PAQUETES = 3;

    private static final int TAM_BUFFER = 1024;

    public static void main(String[] args) {
        //Iniciamos el manejador publicando el puerto. Al no indicar una dirección en el constructor, se asocia al localhost
        try(DatagramSocket socketServidor = new DatagramSocket(PUERTO)) {
            //Los datagramas contienen arrays de bytes.
            byte[] buffer = new byte[TAM_BUFFER];

            //Declaro el datagrama de recepción: sólo le pasamos el buffer y su tamaño
            DatagramPacket recepcion = new DatagramPacket(buffer, buffer.length);

            System.out.println("A la espera de mensajes......");
            for (int i = 0; i < NUM_PAQUETES; i++) {
                //Recibo el datagrama desde el otro extremo. Los datos se guardan en DatagramPacket declarado
                socketServidor.receive(recepcion);
                //Mostramos información diversa del datagrama recibido
                muestraInfoRecibida(recepcion);
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Muestra distinta información recibida en el datagrama
     */
    private static void muestraInfoRecibida(DatagramPacket recepcion) {
        String leido = new String(recepcion.getData());
        System.out.println("Mensaje leido: " + leido.trim());
        System.out.println("Puerto de origen: " + recepcion.getPort());
        System.out.println("Dirección de origen: " + recepcion.getAddress());
        System.out.println("Socket Address (dirección y puerto) de origen: " + recepcion.getSocketAddress());
    }
}
