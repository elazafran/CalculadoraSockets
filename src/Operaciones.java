import java.lang.Math.*;

public class Operaciones {
	double num1;
	float num,num2,num3, resultado;
	
	public Operaciones(String valor1, String valor2) {
		num2=Float.parseFloat(valor1);
		num3=Float.parseFloat(valor2);
	}

	public float suma() {
		num = num2+num3;
		return num;
	}
	public float resta() {
		num = num2-num3;
		return num;
	}
	public float multiplicacion() {
		num = num2*num3;
		return num;
	}
	public float division() {
		num = num2/num3;
		return num;
	}

	
}
