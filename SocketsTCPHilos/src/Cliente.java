import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    private final int NUM_PUERTO = 6000;

    private final String HOST = "localhost";

    public Cliente() {
        //1 - Conectarse con el servidor. Con try-with-resources
        try(Socket socketCliente = new Socket(HOST, NUM_PUERTO);) {
            //2 - Envío + Recepción de datos. Con try-with-resources. IMPORTANTE EL true PARA HACER AUTOFLUSH DEL FLUJO!!
            try(PrintWriter flujoSalida = new PrintWriter(socketCliente.getOutputStream(), true);
                BufferedReader flujoEntrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));){
                String mensaje = null;
                Scanner sc = new Scanner(System.in);
                while((mensaje = flujoEntrada.readLine()) != null){
                    System.out.println("Mensaje leído: " + mensaje);
                    if(!mensaje.contains(HiloComunicacionCliente.TOKEN_FIN)){
                        System.out.print(" Escribe tu respuesta: ");
                        String respuesta = sc.nextLine();
                        flujoSalida.println(respuesta);
                    }
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
