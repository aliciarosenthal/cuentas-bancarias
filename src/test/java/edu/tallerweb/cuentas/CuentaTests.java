package edu.tallerweb.cuentas;

import org.junit.Assert;
import org.junit.Test;

public class CuentaTests {

	@Test
	public void queVerifiqueLaConsigna() {
		CuentaSueldo cuenta = new CuentaSueldo();
		cuenta.depositar(4000.0);

		Assert.assertEquals(
				"al depositar $ 4000.0 en una cuenta vacía, tiene $ 4000.0",
				4000.0, cuenta.getSaldo(), 0.0);

		cuenta.extraer(500.0);
	
		Assert.assertEquals(
				"al extraer $ 500.0 de una cuenta con $ 4000.0 se obtienen $ 3500.0",
				3500.0, cuenta.getSaldo(), 0.0);
	}

	@Test(expected=CuentaBancariaException.class)
	public void queVerifiqueLaConsignaException() {
		CuentaSueldo cuenta = new CuentaSueldo();
		cuenta.depositar(3500.0);

		cuenta.extraer(4000.0);
	}
	@Test
	public void queLaCajaDeAhorroVerifiqueLaConsigna() {
		CajaAhorros cuenta = new CajaAhorros();
		cuenta.depositar(4000.0);

		Assert.assertEquals(
				"al depositar $ 4000.0 en una cuenta vacía, tiene $ 4000.0",
				4000.0, cuenta.getSaldo(), 0.0);

		cuenta.extraer(500.0);
		cuenta.extraer(500.0);
		cuenta.extraer(500.0);
		cuenta.extraer(500.0);
		cuenta.extraer(500.0);
		cuenta.extraer(500.0);
	
		Assert.assertEquals(
				"al extraer 5 veces $ 500.0 de una cuenta con $ 4000.0 se obtienen $ 1000.0 -6",
				994.0, cuenta.getSaldo(), 0.0);
	}
	@Test(expected=CuentaBancariaException.class)
	public void queLaCajaDeAhorroVerifiqueLaConsignaException() {
		CuentaSueldo cuenta = new CuentaSueldo();
		cuenta.depositar(3500.0);

		cuenta.extraer(4000.0);
	}
	
	@Test
	public void queLaCuentaCorrienteCumplaLaConsigna() {
		CuentaCorriente cuenta = new CuentaCorriente(500.00);
		cuenta.depositar(2000.0);

		Assert.assertEquals(
				"al depositar $ 2000.0 en una cuenta vacía, tiene $ 2000.0",
				2000.0, cuenta.getSaldo(), 0.0);
		
		cuenta.extraer(2300.0);
	
		Assert.assertEquals(
				"al extraer puedo girar hasta 500 pesos",
				-315, cuenta.getDescubierto(), 0.0);
		
		cuenta.depositar(500.0);
		Assert.assertEquals(
				"al depositar $ 2000.0 en una cuenta con un descubierto de $315, me va a descontarlos",
				2185, cuenta.getSaldo(), 0.0);
	}
	@Test(expected=CuentaBancariaException.class)
	public void queLaCuentaCorrienteCumplaLaConsignaException() {
		CuentaCorriente cuenta = new CuentaCorriente(200.0);
		cuenta.depositar(3500.0);
		cuenta.extraer(4000.0);
	}
}
