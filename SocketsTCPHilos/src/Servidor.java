import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor{

    //Usamos un puerto registrado
    private final int NUM_PUERTO = 6000;

    public Servidor(){
        //1 - Publica el puerto. Con try-with-resources no tengo que gestionar el cierre
        try{
            ServerSocket servidor = new ServerSocket(NUM_PUERTO);
            //2 - Espera peticiones. Con try-with-resources
            while(true){
                System.out.println("Esperando cliente...");
                Socket socketCliente = servidor.accept();
                new HiloComunicacionCliente(socketCliente).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Servidor();
    }

}
