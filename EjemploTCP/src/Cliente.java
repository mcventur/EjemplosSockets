import java.io.*;
import java.net.Socket;

public class Cliente {
    private final int NUM_PUERTO = 6000;

    private final String HOST = "localhost";

    public Cliente() {
        //1 - Conectarse con el servidor. Con try-with-resources
        try(Socket socketCliente = new Socket(HOST, NUM_PUERTO);) {
            //2 - Envío + Recepción de datos. Con try-with-resources. IMPORTANTE EL true PARA HACER AUTOFLUSH DEL FLUJO!!
            try(PrintWriter flujoSalida = new PrintWriter(socketCliente.getOutputStream(), true);
                BufferedReader flujoEntrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));){
                //2B - Enviar información
                flujoSalida.println("Cliente A");
                //2C - Recibir información
                String mensaje = null;
                while((mensaje = flujoEntrada.readLine()) != null){
                    System.out.println("Mensaje leído: " + mensaje);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Cliente();
    }
}
