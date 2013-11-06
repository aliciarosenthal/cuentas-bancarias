package edu.tallerweb.cuentas;

/**
 * Modela el concepto de Cuenta. Esta clase abstracta sirve
 * como base para una posible jerarquía (si fuese necesaria)
 *
 * Es probable que la tarea se facilite otorgando una imple-
 * mentación a los métodos proporcionados.
 */
public abstract class AbstractCuenta {

	/**
	 * Agrega a la cuenta el monto determinado
	 * @param monto a depositar
	 */
	private double saldo;

	public void depositar(final Double monto) {
		if (monto < 0) {
			throw new CuentaBancariaException("El monto no puede ser negativo");
		}
		this.saldo += monto;
	}

	/**
	 * Retira de la cuenta el monto determinado
	 * @param monto a extraer
	 */
	public abstract void extraer(final Double monto);

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(final double saldo) {
		this.saldo = saldo;
	}

}
