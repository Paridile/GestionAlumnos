package escuela;
public class ExcepcionLecturaEntrada extends NumberFormatException {
	public ExcepcionLecturaEntrada(String error) {
		super(error);
	}
}