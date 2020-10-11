/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuela;

import java.util.Scanner;

/**
 *
 * @author pablo
 */
public class Validar {
    
    public static int entero(String mensaje) {
        Scanner leeEntero = new Scanner(System.in);
        boolean flag;
        int num = 0;
        String aux;
        do {
            try{
                    System.out.println(mensaje);
                    aux = leeEntero.nextLine();
                    num = Integer.parseInt(aux);
                    if(num > 0) {
                        flag = true;
                    }
                    else {
                        flag = false;
                        System.err.println("Debes ingresar un numero mayor a 0");
                    }
                }
                catch(NumberFormatException e){
                    System.err.println("Debes ingresar un entero " + e);
                    flag = false;
                }
        }while(!flag);
        return num;
    }

    public static String cadena(String mensaje) {
        Scanner leeCadena = new Scanner(System.in);
        boolean flag = false;
        String texto = "";
        do {
            try{
                    flag = true;
                    System.out.println(mensaje);
                    texto = leeCadena.nextLine();
                    for(int i = 0; i<texto.length();i++) {
                        char caracter = texto.toUpperCase().charAt(i);
			            int valorASCII = (int)caracter;
			            if (valorASCII != 165 && valorASCII != 32 && (valorASCII < 65 || valorASCII > 90)){
                            flag = false;
                            System.err.println("La cadena solamente debe incluir letras");
                            break;
                        }
                    }
                }
                catch(NumberFormatException e){
                    System.err.println("Debes ingresar un entero " + e);
                    flag = false;
                }
        }while(!flag);
        return texto;
    }
}
