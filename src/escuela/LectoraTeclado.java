package escuela;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class LectoraTeclado {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static String cadena;

    public static String leeCadena() throws ExcepcionLecturaEntrada {
        try {
            cadena = br.readLine();
            return cadena;
        } catch (IOException ioe) {
        	throw new ExcepcionLecturaEntrada("Error al leer los datos de entrada");
          }
    }

    public static boolean leeBooleano() {
    	return Boolean.parseBoolean(leeCadena());
    }

    public static byte leeByte() throws ExcepcionLecturaEntrada {
    	try {
    	    return Byte.parseByte(leeCadena());
    	} catch (NumberFormatException nfe) {
    		throw new ExcepcionLecturaEntrada("Error al leer: " + cadena + " no es un byte");
    	  }
    }

    public static char leeCaracter() throws ExcepcionLecturaEntrada {
    	leeCadena();
    	if (cadena.length() == 1) {
    		return cadena.charAt(0);
    	}
    	else {
    		throw new ExcepcionLecturaEntrada("Error al leer: " + cadena + " no es un caracter");
    	}
    }

    public static short leeEnteroCorto() throws ExcepcionLecturaEntrada {
    	try {
    	    return Short.parseShort(leeCadena());
    	} catch (NumberFormatException nfe) {
    		throw new ExcepcionLecturaEntrada("Error al leer: " + cadena + " no es un valor entero corto");
    	  }
    }

    public static int leeEnteroSimple() throws ExcepcionLecturaEntrada {
    	try {
    	    return Integer.parseInt(leeCadena());
    	} catch (NumberFormatException nfe) {
    		throw new ExcepcionLecturaEntrada("Error al leer: " + cadena + " no es un valor entero simple");
    	  }
    }

    public static long leeEnteroLargo() throws ExcepcionLecturaEntrada {
    	try {
    	    return Long.parseLong(leeCadena());
    	} catch (NumberFormatException nfe) {
    		throw new ExcepcionLecturaEntrada("Error al leer: " + cadena + " no es un valor entero largo");
    	  }
    }

    public static float leeRealSimple() throws ExcepcionLecturaEntrada {
    	try {
    	    return Float.parseFloat(leeCadena());
    	} catch (NumberFormatException nfe) {
    		throw new ExcepcionLecturaEntrada("Error al leer: " + cadena + " no es un valor de simple punto flotante");
    	  }
    }

    public static double leeRealDoble() throws ExcepcionLecturaEntrada {
    	try {
    	    return Double.parseDouble(leeCadena());
    	} catch (NumberFormatException nfe) {
    		throw new ExcepcionLecturaEntrada("Error al leer: " + cadena + " no es un valor de doble punto flotante");
    	  }
    }
}

