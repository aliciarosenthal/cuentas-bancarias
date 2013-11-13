package edu.tallerweb.cuentas;

/**
 * La más compleja de las cuentas, ésta permite establecer una
 * cantidad de dinero a girar en descubierto. Es por ello que
 * cada vez que se desee extraer dinero, no sólo se considera
 * el que se posee, sino el límite adicional que el banco
 * estará brindando.
 *
 * Por supuesto esto no es gratis, ya que el banco nos cobrará
 * un 5% como comisión sobre todo el monto en descubierto
 * consumido en la operación.
 *
 * Por ejemplo, si tuviéramos $ 100 en la cuenta, y quisiéramos
 * retirar $ 200 (con un descubierto de $ 150), podremos hacerlo.
 * Pasaremos a deberle al banco $ 105 en total: los $ 100 que
 * nos cubrió, más el 5% adicional sobre el descubierto otorgado.
 */
public class CuentaCorriente extends AbstractCuenta {

	/**
	 * Toda cuenta corriente se inicia con un límite total
	 * para el descubierto.
	 * @param descubiertoTotal
	 */
	 private final Double descubiertoTotal;
     private Double descubierto = 0.0;
     private final Double comision = 1.05;  
	public CuentaCorriente(final Double descubiertoTotal) {
		this.descubiertoTotal = descubiertoTotal;
	}

	/**
	 * Todo depósito deberá cubrir primero el descubierto,
	 * si lo hubiera, y luego contar para el saldo de la
	 * cuenta.
	 * @param monto a depositar
	 */
	@Override
	public void depositar(final Double monto) {
		if (monto < 0) {
			throw new CuentaBancariaException("El monto no puede ser negativo");
		} else if (this.descubierto == 0) {
			this.setSaldo(this.getSaldo() + monto);
		} else {
			this.setSaldo((this.getSaldo() + monto) + this.descubierto);
			this.descubierto = this.descubierto + monto;
			if (this.descubierto > 0) {
				this.descubierto = 0.0;
			}
		}
	}

	/**
	 * Se cobrará el 5% de comisión sobre el monto girado
	 * en descubierto.
	 * Por supuesto, no puede extraerse más que el total
	 * de la cuenta, más el descubierto (comisión incluída)
	 * @param monto a extraer
	 */
	public void extraer(final Double monto) {
		if (monto < 0) {
			throw new CuentaBancariaException("El monto no puede ser negativo");
		} else if (this.getSaldo() > monto) {
			this.setSaldo(this.getSaldo() - monto);
		} else if (this.getSaldo() < monto) {
			if (((monto - this.getSaldo()) * this.comision) > this.descubiertoTotal) {
				throw new CuentaBancariaException(
						"No se puede realizar la operaci�n");
			} else {
				this.descubierto -= ((monto - this.getSaldo()) * this.comision);
			}
		}
    }

	/**
	 * Permite saber el saldo de la cuenta
	 * @return el saldo de la cuenta
	 */
	/**
	 * Permite saber el saldo en descubierto
	 * @return el descubierto de la cuenta
	 */
	public Double getDescubierto() {
		return this.descubierto;
	}

}
