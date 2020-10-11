/*
Crear un programa con manejo de archivos y arreglos en java que permita solicitar la cantidad de alumnos que 
van a ser insertados en un archivo por cada alumno solicite como datos
Código
Nombre completo
Edad
Sexo
Se almacenen los datos de estos alumnos en un archivo y posterior a ello muestre un menu con opciones de 
buscar alumno por codigo, 
ordenar alumnos por edad, 
buscar alumnos por sexo y 
modificar los datos de un alumno por código
asi como poder salir del menu.
El programa debe de estar validado y realizar todo lo que se pide
 */

package escuela;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author pablo
 */
public class Escuela {
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    
    static String nombreArchivo = "datos.txt";

    public static void main(String[] args) throws IOException {
        int opc;
        do {
            System.out.println("\t\tDatos de los alumnos");
            System.out.println(" 1) Agregar alumnos");
            System.out.println(" 2) Buscar alumno");
            System.out.println(" 3) Ordenar alumnos por edad");
            System.out.println(" 4) Filtrar alumnos por sexo");
            System.out.println(" 5) Modificar datos de un alumno");
            System.out.println(" 6) Salir");
            opc = Validar.entero("Seleccione una opción");
            switch(opc) {
                case 1: // Agregar alumnos
                    agregarAlumnos();
                    break;
                case 2: // buscar alumnos
                    buscarAlumnos();
                    break;
                case 3: // ordenar alumnos por edad
                    ordenarPorEdad();
                    break;
                case 4: // Filtrar alumnos por sexo
                    filtrarPorSexo();
                    break;
                case 5: // Modificar datos de un alumno
                    modificarDatos();
                    break;
                case 6: // Salir
                    System.out.println("Hasta luego!");
                    break;
                default:
                    System.out.println("Esa opción no es válida");
                    break;      
            }
        }while(opc != 6);  
    }
    
    public static void agregarAlumnos() throws IOException {
        FileWriter fichero = null;
        PrintWriter pw = null;
        boolean flag=false;
        Scanner read = new Scanner(System.in);
        int cantidadAlumnos=0, codigoAlumno=0,edadAlumno=0, alumnoActual=0;
        String nombreAlumno = "", apellidoPaterno="",apellidoMaterno="",sexoAlumno = "";
        cantidadAlumnos = Validar.entero("¿Cuantos alumnos desea agregar?");
        Alumno alumno[] = new Alumno[cantidadAlumnos];
        for(int i = 0 ; i<cantidadAlumnos ; i++){
            alumnoActual = i+1;
            System.out.println("Alumno [ " + alumnoActual + " ]");
            do{
                codigoAlumno = Validar.entero("¿Cual es el código del alumno?");
            }while(buscarAlumnos(codigoAlumno));
            nombreAlumno = Validar.cadena("¿Cual es el nombre del alumno?");
            apellidoPaterno = Validar.cadena("¿Cual es el apellido paterno del alumno?");
            apellidoMaterno = Validar.cadena("¿Cual es el apellido materno del alumno?");
            edadAlumno = Validar.entero("¿Cual es la edad del alumno?");
            do
            {
                sexoAlumno = Validar.cadena("¿Cual es el sexo del alumno? [ masculino | femenino]");
                if (sexoAlumno.equals("MASCULINO") || sexoAlumno.equals("FEMENINO") || sexoAlumno.equals("masculino") || sexoAlumno.equals("femenino")) {
                    flag = true;
                    sexoAlumno = sexoAlumno.toUpperCase();
                } else {
                    flag = false;
                    System.out.println("Debes ingresar alguna de estas opciones [ masculino | femenino]");
                }
            } while(!flag);

            System.out.println("");
            alumno[i] = new Alumno(codigoAlumno,nombreAlumno,apellidoPaterno,apellidoMaterno,edadAlumno,sexoAlumno);
        }
        try
        {
            fichero = new FileWriter(nombreArchivo,true);
            pw = new PrintWriter(fichero);
            for(int i = 0 ; i<cantidadAlumnos ; i++){
                pw.println(alumno[i].getCodigo());
                pw.println(alumno[i].getNombre());
                pw.println(alumno[i].getApellidoPaterno());
                pw.println(alumno[i].getApellidoMaterno());
                pw.println(alumno[i].getEdad());
                pw.println(alumno[i].getSexo());
                pw.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (IOException e2) {
              e2.printStackTrace();
           }
        }
    }
    
    
    
    public static void filtrarPorSexo() throws FileNotFoundException, IOException {
        int numLine,lineCount;
        String sexoAlumno="";
        boolean flag;
        String line;
        File archivo = new File(nombreArchivo);
        if(!archivo.exists()) {
            System.out.println("El archivo " + nombreArchivo + " no fue encontrado");
        }
        else {
            numLine = 0;
            lineCount = 0;
            int totalAlumnos = 0;
            do
            {
                sexoAlumno = Validar.cadena("¿Cual es el sexo de los alumnos que desea filtrar? [ masculino | femenino]");
                if (sexoAlumno.equals("MASCULINO") || sexoAlumno.equals("FEMENINO") || sexoAlumno.equals("masculino") || sexoAlumno.equals("femenino")) {
                    flag = true;
                    sexoAlumno = sexoAlumno.toUpperCase();
                } else {
                    flag = false;
                    System.out.println("Debes ingresar alguna de estas opciones [ masculino | femenino]");
                }
            } while(!flag);
            try (FileReader fr = new FileReader(nombreArchivo)) {
                BufferedReader br = new BufferedReader(fr);
                while((br.readLine()) != null){
                    lineCount++;
                }
                totalAlumnos = lineCount/7;
                Alumno list[] = new Alumno[totalAlumnos];
                for(int i=0; i<totalAlumnos;i++) {
                    list[i] = new Alumno();
                    line = Files.readAllLines(Paths.get(nombreArchivo)).get(numLine);
                    list[i].setCodigo(Integer.parseInt(line));
                    numLine++;
                    
                    line = Files.readAllLines(Paths.get(nombreArchivo)).get(numLine);
                    list[i].setNombre(line);
                    numLine++;
                    
                    line = Files.readAllLines(Paths.get(nombreArchivo)).get(numLine);
                    list[i].setApellidoPaterno(line);
                    numLine++;
                    
                    line = Files.readAllLines(Paths.get(nombreArchivo)).get(numLine);
                    list[i].setApellidoMaterno(line);
                    numLine++;
                    
                    line = Files.readAllLines(Paths.get(nombreArchivo)).get(numLine);
                    list[i].setEdad(Integer.parseInt(line));
                    numLine++;
                    
                    line = Files.readAllLines(Paths.get(nombreArchivo)).get(numLine);
                    list[i].setSexo(line);
                    numLine+=2;
                }
                
                for(int i=0; i<totalAlumnos;i++) {
                    if(list[i].getSexo().equals(sexoAlumno)) {
                        System.out.println("Código: " + list[i].getCodigo());
                        System.out.println("Nombre: " + list[i].getNombre());
                        System.out.println("Apellido paterno: " + list[i].getApellidoPaterno());
                        System.out.println("Apellido Materno: " + list[i].getApellidoMaterno());
                        System.out.println("Edad: " + list[i].getEdad());
                        System.out.println("Sexo: " + list[i].getSexo());
                        System.out.println("");
                    }
                }
            }
        }
    }
    
    
    
    public static void buscarAlumnos() {
        int buscarCodigo = 0, numLine = 0, lineCount= 0;
        File archivo = new File(nombreArchivo);
        boolean encontrado = false;
        String line;
        if(!archivo.exists()) {
            System.out.println("El archivo " + nombreArchivo + " no fue encontrado");
        }
        else {
            try {
                FileReader fr = new FileReader(nombreArchivo);
                BufferedReader br = new BufferedReader(fr); 
                buscarCodigo = Validar.entero("Introduce el código que desea buscar");
                while((br.readLine()) != null){
                    lineCount++;
                }
                for(int i = 0; i<lineCount/7;i++) {
                    line = Files.readAllLines(Paths.get(nombreArchivo)).get(numLine);
                    if(buscarCodigo == Integer.parseInt(line)) {
                        encontrado = true;
                        System.out.println("\nEl código fue encontrado con exito\n");
                        System.out.println("Código: " + line );
                        numLine++;
                                    
                        line = Files.readAllLines(Paths.get(nombreArchivo)).get(numLine);
                        System.out.println("Nombre: " + line );
                        numLine++;
                                    
                        line = Files.readAllLines(Paths.get(nombreArchivo)).get(numLine);
                        System.out.println("Apellido paterno: " + line );
                        numLine++;
                                    
                        line = Files.readAllLines(Paths.get(nombreArchivo)).get(numLine);
                        System.out.println("Apellido Materno: " + line );
                        numLine++;
                                    
                        line = Files.readAllLines(Paths.get(nombreArchivo)).get(numLine);
                        System.out.println("Edad: " + line );
                        numLine++;
                                    
                        line = Files.readAllLines(Paths.get(nombreArchivo)).get(numLine);
                        System.out.println("Sexo: " + line  + "\n");
                        break;
                    }
                    numLine += 7;
                }
                if(!encontrado) {
                    System.out.println("No se encontro ningun alumno con ese código");
                }
                fr.close();
            }
            catch(IOException | NumberFormatException e) {
                System.out.println("");
            }
        }
    }

    public static boolean buscarAlumnos(int buscarCodigo) {
        int numLine = 0, lineCount= 0;
        File archivo = new File(nombreArchivo);
        boolean encontrado = false;
        String line;
        if(!archivo.exists()) {
            System.out.println("El archivo " + nombreArchivo + " no fue encontrado");
        }
        else {
            try {
                FileReader fr = new FileReader(nombreArchivo);
                BufferedReader br = new BufferedReader(fr); 
                while((br.readLine()) != null){
                    lineCount++;
                }
                for(int i = 0; i<lineCount/7;i++) {
                    line = Files.readAllLines(Paths.get(nombreArchivo)).get(numLine);
                    if(buscarCodigo == Integer.parseInt(line)) {
                        encontrado = true;
                        System.err.println("Ya existe un alumno con ese código");
                        break;
                    }
                    numLine += 7;
                }
                if(!encontrado) {
                    
                }
                fr.close();
            }
            catch(IOException | NumberFormatException e) {
                System.out.println("");
            }
        }
        return encontrado;
    }
    
    public static void ordenarPorEdad() throws FileNotFoundException, IOException {
        int numLine = 0, lineCount, totalAlumnos;
        String line;
        File archivo = new File(nombreArchivo);
        if(!archivo.exists()) {
            System.out.println("El archivo " + nombreArchivo + " no fue encontrado");
        }
        else {
            numLine = 0;
            lineCount = 0;
            totalAlumnos = 0;
            FileReader fr = new FileReader(nombreArchivo);
            BufferedReader br = new BufferedReader(fr); 
            while((br.readLine()) != null){
                lineCount++;
            }
            totalAlumnos = lineCount/7;
            Alumno list[] = new Alumno[totalAlumnos];       
            for(int i=0; i<totalAlumnos;i++) {
                list[i] = new Alumno();
                line = Files.readAllLines(Paths.get(nombreArchivo)).get(numLine);
                list[i].setCodigo(Integer.parseInt(line));
                numLine++;
                        
                line = Files.readAllLines(Paths.get(nombreArchivo)).get(numLine);
                list[i].setNombre(line);
                numLine++;
                            
                line = Files.readAllLines(Paths.get(nombreArchivo)).get(numLine);
                list[i].setApellidoPaterno(line);
                numLine++;
                        
                line = Files.readAllLines(Paths.get(nombreArchivo)).get(numLine);
                list[i].setApellidoMaterno(line);
                numLine++;
                        
                line = Files.readAllLines(Paths.get(nombreArchivo)).get(numLine);
                list[i].setEdad(Integer.parseInt(line));
                numLine++;
                        
                line = Files.readAllLines(Paths.get(nombreArchivo)).get(numLine);
                list[i].setSexo(line);
                numLine+=2;
            }
            Arrays.sort(list);
            System.out.println("\nAlumnos Ordenados por edad\n");
            Alumno.imprimeArray(list);
            System.out.println("");
            fr.close();
        }
    }
    
    
    public static void modificarDatos() throws FileNotFoundException, IOException {
        int buscarCodigo = 0;
        String nuevoDato="";
        int nuevaEdad;
        File archivo = new File(nombreArchivo);
        int numLine = 0,numLineAux,opc=0;
        int lineCount= 0;
        boolean encontrado = false;
        
        StringBuffer inputBuffer = new StringBuffer();
        BufferedReader file = new BufferedReader(new FileReader(nombreArchivo));
        String lines;
        String line;
        
        
        while ((lines = file.readLine()) != null) {
            inputBuffer.append(lines);
            inputBuffer.append('\n');
        }
        
        if(!archivo.exists()) {
            System.out.println("El archivo " + nombreArchivo + " no fue encontrado");
        }
        else {
            try {
                FileReader fr = new FileReader(nombreArchivo);
                BufferedReader br = new BufferedReader(fr); 
                int contador=1;
                System.out.println(contador);
                file.close();
                buscarCodigo = Validar.entero("Introduce el codigo del alumno que deseas modificar");
                while((br.readLine()) != null){
                    lineCount++;
                }
                for(int i = 0; i<lineCount/7;i++) {
                    line = Files.readAllLines(Paths.get(nombreArchivo)).get(numLine);
                    if(buscarCodigo == Integer.parseInt(line)) {
                        encontrado = true;
                        numLineAux = numLine;
                        do {
                            List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(nombreArchivo), StandardCharsets.UTF_8));
                            contador = 0;
                            System.out.println("¿Que dato desea modificar?");
                            System.out.println("\t1) Nombre");
                            System.out.println("\t2) Apellido paterno");
                            System.out.println("\t3) Apellido materno");
                            System.out.println("\t4) Edad");
                            System.out.println("\t5) Sexo");
                            System.out.println("\t6) Salir");
                            opc = Validar.entero("Seleccione una opción");
                            switch(opc) {
                                case 1:
                                    numLineAux = numLine + 1;
                                    line = Files.readAllLines(Paths.get(nombreArchivo)).get(numLineAux);
                                    System.out.println("El actual nombre del alumno es " + line);
                                    nuevoDato = Validar.cadena("Introduce el nuevo nombre del alumno");
                                    for (i = 0; i < fileContent.size(); i++) {
                                        if (fileContent.get(i).equals(line)) {
                                            fileContent.set(i, nuevoDato);
                                            break;
                                        }
                                    }
                                        Files.write(Paths.get(nombreArchivo), fileContent, StandardCharsets.UTF_8);
                                    break;
                                case 2:
                                    numLineAux = numLine + 2;
                                    line = Files.readAllLines(Paths.get(nombreArchivo)).get(numLineAux);
                                    System.out.println("El actual apellido paterno del alumno es " + line);
                                    nuevoDato = Validar.cadena("Introduce el nuevo apellido paterno del alumno");
                                    for (i = 0; i < fileContent.size(); i++) {
                                        if (fileContent.get(i).equals(line)) {
                                            fileContent.set(i, nuevoDato);
                                            break;
                                        }
                                    }
                                        Files.write(Paths.get(nombreArchivo), fileContent, StandardCharsets.UTF_8);
                                    break;
                                case 3:
                                    numLineAux = numLine + 3;
                                    line = Files.readAllLines(Paths.get(nombreArchivo)).get(numLineAux);
                                    System.out.println("El actual apellido materno del alumno es " + line);
                                    nuevoDato = Validar.cadena("Introduce el nuevo apellido materno del alumno");
                                    for (i = 0; i < fileContent.size(); i++) {
                                        if (fileContent.get(i).equals(line)) {
                                            fileContent.set(i, nuevoDato);
                                            break;
                                        }
                                    }
                                        Files.write(Paths.get(nombreArchivo), fileContent, StandardCharsets.UTF_8);
                                    break;
                                case 4:
                                    numLineAux = numLine + 4;
                                    line = Files.readAllLines(Paths.get(nombreArchivo)).get(numLineAux);
                                    System.out.println("La edad actual del alumno es " + line);
                                    nuevaEdad= Validar.entero("Introduce la nueva edad del alumno");
                                    for (i = 0; i < fileContent.size(); i++) {
                                        if (fileContent.get(i).equals(line)) {
                                            fileContent.set(i, String.valueOf(nuevaEdad));
                                            break;
                                        }
                                    }
                                        Files.write(Paths.get(nombreArchivo), fileContent, StandardCharsets.UTF_8);
                                    break;
                                case 5:
                                    numLineAux = numLine + 5;
                                    line = Files.readAllLines(Paths.get(nombreArchivo)).get(numLineAux);
                                    System.out.println("El sexo del alumno es " + line);
                                    if(line.equals("MASCULINO")) {
                                        nuevoDato = "FEMENINO"; 
                                        System.out.println("Se cambio el sexo a FEMENINO");
                                    }
                                    if(line.equals("FEMENINO")) {
                                        nuevoDato = "MASCULINO"; 
                                        System.out.println("Se cambio el sexo a MASCULINO");
                                    }
                                    if(!line.equals("MASCULINO") && !line.equals("FEMENINO")){
                                        nuevoDato = "FEMENINO";
                                    }
                                    for (i = 0; i < fileContent.size(); i++) {
                                        if (fileContent.get(i).equals(line)) {
                                           fileContent.set(i, nuevoDato);
                                           break;
                                        }
                                    }
                                        Files.write(Paths.get(nombreArchivo), fileContent, StandardCharsets.UTF_8);
                                    break;
                            }
                        }while(opc!=6);
                        break;
                    }
                    //System.out.println(line);
                    numLine += 7;
                }
                if(!encontrado) {
                    System.out.println("No se encontro ningun alumno con ese código");
                }
                br.close();
            }
            catch(IOException | NumberFormatException e) {
                System.out.println("");
            }
        }
    }

}