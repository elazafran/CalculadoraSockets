import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) {
		
		//puerto que se abrira en el servidor para respoder a los clientes
		int PUERTO_CALCU = 5001;
		
		String error = "no hay operando";
		Socket socket = null;
		ServerSocket serverSocket = null;
		String[] lista;
		String resultado = null;
		
		try {
			
			//creamos el serverSocket
			serverSocket = new ServerSocket(PUERTO_CALCU);
			
		} catch (Exception e) {
			System.out.println("error al crear el puerto");
			return;
		}
		
		while(true) {
			
			try {
			
				System.out.println("SERVIDOR CALCULADORA");
				
				//aceptamos las peticiones de conexion
				socket= serverSocket.accept();
				System.out.println("Esperando operaciones a realizar...");
				
				DataOutputStream salida = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
				DataInputStream entrada = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
				
				//guardamos el valor que nos llega para realizar la operacion
				String valor = entrada.readUTF();
				
				if(valor.indexOf("+")!=-1) {
					lista=valor.split("\\+");
					Operaciones operar = new Operaciones(lista[0],lista[1]);
					System.out.print("La suma de "+valor+" es...");
					resultado = String.valueOf(operar.suma());
				}else if (valor.indexOf("-")!=-1) {
					lista=valor.split("\\-");
					Operaciones operar = new Operaciones(lista[0],lista[1]);
					System.out.print("La resta de "+valor+" es...");
					resultado = String.valueOf(operar.resta());
				}else if (valor.indexOf("*")!=-1) {
					lista=valor.split("\\*");
					Operaciones operar = new Operaciones(lista[0],lista[1]);
					System.out.print("La resta de "+valor+" es...");
					resultado = String.valueOf(operar.multiplicacion());
				}else if (valor.indexOf("-")!=-1) {
					lista=valor.split("\\/");
					Operaciones operar = new Operaciones(lista[0],lista[1]);
					System.out.print("La division de "+valor+" es...");
					resultado = String.valueOf(operar.division());
				}else {
					System.out.println(error);
				}
				
				System.out.println(resultado);
				
				// ponemos el resultado en el canal de salida
				salida.writeUTF(resultado); 
				
				System.out.println("Enviando respuesta...");
				salida.flush();//se limpia la salida
				System.out.println("ok\n");
				
				socket.close();
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}

}
