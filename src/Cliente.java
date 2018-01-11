import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Cliente {
	
	public Cliente() {
		
	}
	//se definen dos variables que se leen desde el teclado
	private static BufferedReader stdin1 = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedReader stdin2 = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
	
		String IP_CALCU="localhost";//con null también funciona
		int PUERTO_CALCU=5001;//guarda el puerto del servidor
		
		String sigue ="s";
		
		while(sigue.equals("s")) {
			
			try {
				
				System.out.println("Calculadora Básica");
				System.out.print("Operación a realizar: ");
				System.out.print("(a+b, a-b, a*b, a/b)\n -->");
				
				String valor = stdin2.readLine();//leer el valor del teclado

				//Envia el valor a la funcion más abajo
				String resultado = realizar_operacion(IP_CALCU,PUERTO_CALCU,valor);
				System.out.println("El resultado es :" + resultado);
				
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			System.out.print("\n Desea realizar otra operacion? (s/n)");
			sigue = stdin1.readLine();
			System.out.println("");
			
			
		}
		
		//si la respuesta es distinta a s salimos del bucle while
		System.out.println("Adios.");
		
	}

	private static String realizar_operacion(String host, int puerto, String valor) {
		
		String respuesta = null;
		
		try {
			
			//creamos el socket y creamos flujo de datos (Streams)
			Socket socketEn = new Socket(host,puerto);
			
			//creamos dos canales de comunicacion uno de entrada y otro de salida
			DataOutputStream salida = new DataOutputStream(new BufferedOutputStream(socketEn.getOutputStream()));
			DataInputStream entrada = new DataInputStream(new BufferedInputStream(socketEn.getInputStream()));
			
			//pasamos los valores en el canal de salida
			salida.writeUTF(valor);
			salida.flush();//enviamos lo que hay en el canal de salida
			
			//leemos lo recibido por el canal de entrada
			respuesta = entrada.readUTF();
			
			socketEn.close();
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return respuesta;
	}
	
	

}
