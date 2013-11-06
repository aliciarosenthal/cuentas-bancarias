package edu.tallerweb.cuentas;

/**
 * Similar a la CuentaSueldo, pero se pide que luego de la
 * quinta extracción de dinero se cobre un costo adicional
 * por extracción de $ 6
 */
public class CajaAhorros extends AbstractCuenta{

	/**
	 * No hay reglas adicionales para el depósito
	 * @param monto a depositar
	 */
	/**
	 * Se cobran $6 adicionales por cada extracción luego de
	 * la quinta.
	 * @param monto a extraer
	 */
	private int cantextracciones = 0;
	final double adicional = 6.0;

	public void extraer(final Double monto) {
		this.cantextracciones++;
		this.saldo -= monto;
		if (cantextracciones > 5) {
			this.saldo -= adicional;
		}
		if (this.saldo < 0.0) {
			throw new CuentaBancariaException("Cuenta sin saldo");
		}
	}

	/**
	 * Permite saber el saldo de la cuenta
	 * @return el saldo de la cuenta
	 */

}
