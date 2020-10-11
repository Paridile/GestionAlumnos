package escuela;
public class ExcepcionLecturaEntrada extends NumberFormatException {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ExcepcionLecturaEntrada(String error) {
		super(error);
	}
}