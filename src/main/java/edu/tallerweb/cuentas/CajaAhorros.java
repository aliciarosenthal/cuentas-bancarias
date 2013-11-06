package edu.tallerweb.cuentas;

/**
 * Similar a la CuentaSueldo, pero se pide que luego de la
 * quinta extracción de dinero se cobre un costo adicional
 * por extracción de $ 6
 */
public class CajaAhorros extends AbstractCuenta {

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
	private final double adicional = 6.0;
	private final int limite = 5;

	public void extraer(final Double monto) {
		if (monto < 0) {
			throw new CuentaBancariaException("El monto no puede ser negativo");
		}
		this.cantextracciones++;
		this.setSaldo(this.getSaldo() - monto);
		if (cantextracciones > limite) {
			this.setSaldo(this.getSaldo() - adicional);
		}
		if (this.getSaldo() < 0.0) {
			throw new CuentaBancariaException("Cuenta sin saldo");
		}
	}

	/**
	 * Permite saber el saldo de la cuenta
	 * @return el saldo de la cuenta
	 */

}
