/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuela;

/**
 *
 * @author pablo
 */
public class Alumno implements Comparable<Alumno>{
    private int codigo;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private int edad;
    private String sexo;

    public Alumno(int codigo, String nombre, String apellidoPaterno, String apellidoMaterno, int edad, String sexo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.edad = edad;
        this.sexo = sexo;
    }
    
    public Alumno(){}
    
    @Override
    public int compareTo(Alumno alumno) {
        if (this.edad < alumno.edad) {
            return -1;
        }
        if (this.edad > alumno.edad) {
            return 1;
        }
            return 0;
    }
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

        static void imprimeArray(Alumno[] array) {
        for (Alumno i : array) {
            System.out.println( "Codigo: " + i.getCodigo() + " " 
                                + "Nombre: " + i.getNombre() + " "
                                + "Ap: " + i.getApellidoPaterno()+ " "
                                + "Am: " + i.getApellidoMaterno()+ " "
                                + "Edad: " + i.getEdad() + " "
                                + "Sexo: " + i.getSexo() + " ");
        }
    }
    
}
