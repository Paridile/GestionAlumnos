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
modificar los datos de un alumno por código a
si como poder salir del menu.

El programa debe de estar validado y realizar todo lo que se pide

 */
package escuela;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
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
        int opc=0;
        Scanner read = new Scanner(System.in);
        File archivo = new File(nombreArchivo);
        do {
            System.out.println("\t\tDatos de los alumnos");
            System.out.println("Seleccione una opción");
            System.out.println(" 1) Agregar alumnos");
            System.out.println(" 2) Buscar alumno");
            System.out.println(" 3) Ordenar alumnos por edad");
            System.out.println(" 4) Filtrar alumnos por sexo");
            System.out.println(" 5) Modificar datos de un alumno");
            System.out.println(" 6) Salir");
            boolean flag;
            do
            {
                flag = true;
                try {
                	opc = LectoraTeclado.leeEnteroSimple();
                } catch (ExcepcionLecturaEntrada ele){
                    flag = false;
                    System.err.println(ele.getMessage());
                }
            } while(!flag);
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
        Scanner read = new Scanner(System.in);
        int cantidadAlumnos, codigoAlumno=0,edadAlumno=0;
        String nombreAlumno = "", apellidoPaterno="",apellidoMaterno="",sexoAlumnoAux = "";
        char sexoAlumno;
        System.out.println("¿Cuantos alumnos desea agregar?");
        cantidadAlumnos = read.nextInt();
        Alumno alumno[] = new Alumno[cantidadAlumnos];
        boolean flag;
        for(int i = 0 ; i<cantidadAlumnos ; i++){
            do
            {
                flag = true;
                System.out.println("¿Cual es el código del alumno?");
                try {
                    codigoAlumno = LectoraTeclado.leeEnteroSimple();
                } catch (ExcepcionLecturaEntrada ele){
                    flag = false;
                    System.err.println(ele.getMessage());
                }
            } while(!flag);
            
            do
            {
                flag = true;
                System.out.println("¿Cual es el nombre del alumno?");
                try {
                    nombreAlumno = LectoraTeclado.leeCadena();
                } catch (ExcepcionLecturaEntrada ele){
                    flag = false;
                    System.err.println(ele.getMessage());
                }
            } while(!flag);
            
            do
            {
                flag = true;
                System.out.println("¿Cual es el apellido paterno del alumno?");
                try {
                    apellidoPaterno = LectoraTeclado.leeCadena();
                } catch (ExcepcionLecturaEntrada ele){
                    flag = false;
                    System.err.println(ele.getMessage());
                }
            } while(!flag);
            
            do
            {
                flag = true;
                System.out.println("¿Cual es el apellido materno del alumno?");
                try {
                    apellidoMaterno = LectoraTeclado.leeCadena();
                } catch (ExcepcionLecturaEntrada ele){
                    flag = false;
                    System.err.println(ele.getMessage());
                }
            } while(!flag);

            do
            {
                flag = true;
                System.out.println("¿Cual es la edad del alumno?");
                try {
                    edadAlumno = LectoraTeclado.leeEnteroSimple();
                } catch (ExcepcionLecturaEntrada ele){
                    flag = false;
                    System.err.println(ele.getMessage());
                }
            } while(!flag);
            
            do
            {
                System.out.println("¿Cual es el sexo del alumno? [m = masculino, f=femenino]");
                sexoAlumnoAux = read.next();
                sexoAlumno = sexoAlumnoAux.charAt(0);
                if (sexoAlumno == 'M' || sexoAlumno == 'F' || sexoAlumno == 'm' || sexoAlumno == 'f') {
                    flag = true;
                    sexoAlumno = Character.toUpperCase(sexoAlumno);
                } else {
                    flag = false;
                    System.out.println("Debes ingresar alguna de estas opciones [ M m F f ]");
                    
                }
                
            } while(!flag);
            
            alumno[i] = new Alumno(codigoAlumno,nombreAlumno,apellidoPaterno,apellidoMaterno,edadAlumno,sexoAlumno);

        }
        try
        {
            fichero = new FileWriter(nombreArchivo,true);
            pw = new PrintWriter(fichero);
            for(int i = 0 ; i<cantidadAlumnos ; i++){
                /*
                pw.println(alumno[i].getCodigo() + ":"
                           +alumno[i].getNombre() + ":"
                           +alumno[i].getApellidoPaterno()+ ":"
                           +alumno[i].getApellidoMaterno()+ ":"
                           +alumno[i].getEdad()+ ":"
                           +alumno[i].getSexo()+ ";");
                */
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
        String sexoAlumnoAux;
        char sexoAlumno;
        boolean flag;
        Scanner read = new Scanner(System.in);
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
                System.out.println("¿Cual es el sexo que desea buscar? [m = masculino, f=femenino]");
                sexoAlumnoAux = read.next();
                sexoAlumno = sexoAlumnoAux.charAt(0);
                if (sexoAlumno == 'M' || sexoAlumno == 'F' || sexoAlumno == 'm' || sexoAlumno == 'f') {
                    flag = true;
                    sexoAlumno = Character.toUpperCase(sexoAlumno);
                } else {
                    flag = false;
                    System.out.println("Debes ingresar alguna de estas opciones [ M m F f ]");
                    
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
                    list[i].setSexo(line.charAt(0));
                    numLine+=2;
                }
                
                for(int i=0; i<totalAlumnos;i++) {
                    if(list[i].getSexo() == sexoAlumno) {
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
        int buscarCodigo = 0;
        File archivo = new File(nombreArchivo);
        boolean flag = false;
        int numLine = 0;
        int lineCount= 0;
        boolean encontrado = false;
        String line;
        if(!archivo.exists()) {
            System.out.println("El archivo " + nombreArchivo + " no fue encontrado");
        }
        else {
            try {
                FileReader fr = new FileReader(nombreArchivo);
                BufferedReader br = new BufferedReader(fr); 
                do {
                    flag = true;
                    try {
                        System.out.println("Cuál es el código de alumno que desea buscar");
                        buscarCodigo = LectoraTeclado.leeEnteroSimple();
                    } 
                    catch (ExcepcionLecturaEntrada ele){
                        flag = false;
                        System.err.println(ele.getMessage());
                    }
                } while(!flag);
                while((br.readLine()) != null){
                    lineCount++;
                }
                for(int i = 0; i<lineCount/7;i++) {
                    line = Files.readAllLines(Paths.get(nombreArchivo)).get(numLine);
                    if(buscarCodigo == Integer.parseInt(line)) {
                        encontrado = true;
                        System.out.println("El código fue encontrado con exito");
                        System.out.println("Código: " + line);
                        numLine++;
                                    
                        line = Files.readAllLines(Paths.get(nombreArchivo)).get(numLine);
                        System.out.println("Nombre: " + line);
                        numLine++;
                                    
                        line = Files.readAllLines(Paths.get(nombreArchivo)).get(numLine);
                        System.out.println("Apellido paterno: " + line);
                        numLine++;
                                    
                        line = Files.readAllLines(Paths.get(nombreArchivo)).get(numLine);
                        System.out.println("Apellido Materno: " + line);
                        numLine++;
                                    
                        line = Files.readAllLines(Paths.get(nombreArchivo)).get(numLine);
                        System.out.println("Edad: " + line);
                        numLine++;
                                    
                        line = Files.readAllLines(Paths.get(nombreArchivo)).get(numLine);
                        System.out.println("Sexo: " + line);
                        break;
                    }
                    //System.out.println(line);
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
                list[i].setSexo(line.charAt(0));
                numLine+=2;
            }
            Arrays.sort(list);
            System.out.println("Alumnos Ordenados por edad");
            Alumno.imprimeArray(list);
            fr.close();
        }
    }
    
    
    public static void modificarDatos() throws FileNotFoundException, IOException {
        Scanner read = new Scanner(System.in);
        int buscarCodigo = 0;
        String nuevoDato="";
        File archivo = new File(nombreArchivo);
        boolean flag = false;
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
                String inputStr = inputBuffer.toString();
                int contador=1;
                System.out.println(contador);
                file.close();
                do {
                    flag = true;
                    try {
                        System.out.println("Introduce el codigo del alumno que deseas modificar");
                        buscarCodigo = LectoraTeclado.leeEnteroSimple();
                    } 
                    catch (ExcepcionLecturaEntrada ele){
                        flag = false;
                        System.err.println(ele.getMessage());
                    }
                } while(!flag);
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
                            do
                            {
                                flag = true;
                                try {
                                    opc = LectoraTeclado.leeEnteroSimple();
                                } catch (ExcepcionLecturaEntrada ele){
                                    flag = false;
                                    System.err.println(ele.getMessage());
                                }
                            } while(!flag);
                            switch(opc) {
                                case 1:
                                    numLineAux = numLine + 1;
                                    line = Files.readAllLines(Paths.get(nombreArchivo)).get(numLineAux);
                                    System.out.println("El actual nombre del alumno es " + line);
                                    do
                                    {
                                        flag = true;
                                        System.out.println("Introduce el nuevo nombre del alumno");
                                        try {
                                            nuevoDato = LectoraTeclado.leeCadena();
                                        } catch (ExcepcionLecturaEntrada ele){
                                            flag = false;
                                            System.err.println(ele.getMessage());
                                        }
                                    } while(!flag);
                                    
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
                                    do
                                    {
                                        flag = true;
                                        System.out.println("Introduce el nuevo apellido paterno del alumno");
                                        try {
                                            nuevoDato = LectoraTeclado.leeCadena();
                                        } catch (ExcepcionLecturaEntrada ele){
                                            flag = false;
                                            System.err.println(ele.getMessage());
                                        }
                                    } while(!flag);
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
                                    do
                                    {
                                        flag = true;
                                        System.out.println("Introduce el nuevo apellido materno del alumno");
                                        try {
                                            nuevoDato = LectoraTeclado.leeCadena();
                                        } catch (ExcepcionLecturaEntrada ele){
                                            flag = false;
                                            System.err.println(ele.getMessage());
                                        }
                                    } while(!flag);
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
                                    do
                                    {
                                        flag = true;
                                        System.out.println("Introduce la nueva edad del alumno");
                                        try {
                                            nuevoDato = String.valueOf(LectoraTeclado.leeEnteroSimple());
                                        } catch (ExcepcionLecturaEntrada ele){
                                            flag = false;
                                            System.err.println(ele.getMessage());
                                        }
                                    } while(!flag);
                                        for (i = 0; i < fileContent.size(); i++) {
                                            if (fileContent.get(i).equals(line)) {
                                                fileContent.set(i, nuevoDato);
                                                break;
                                            }
                                        }
                                        Files.write(Paths.get(nombreArchivo), fileContent, StandardCharsets.UTF_8);
                                    break;
                                case 5:
                                    numLineAux = numLine + 5;
                                    line = Files.readAllLines(Paths.get(nombreArchivo)).get(numLineAux);
                                    System.out.println("El sexo actual del alumno es " + line);
                                    if(line == "M"){
                                        nuevoDato = "F";
                                        System.out.println("El sexo fue cambiado a F");
                                    }
                                    if(line == "F") {
                                        nuevoDato = "M";
                                        System.out.println("El sexo fue cambiado a M");
                                    }
                                    if(line != "M" && line != "F"){
                                        nuevoDato = "M";
                                        System.out.println(":o");
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
            }
            catch(IOException | NumberFormatException e) {
                System.out.println("");
            }
        }
    }
}
