package com.construccion.proyecto.model;

    /**
 * Clase que representa el proceso de pago, con métodos para realizar pagos
 * tanto con tarjeta como en efectivo.
 */

public class Pago {
    /**
     * Realiza un pago utilizando una tarjeta. Si el saldo de la tarjeta es
     * suficiente, el monto se retira; si no, se muestra un mensaje de saldo
     * insuficiente.
     *
     * @param tarjeta La tarjeta con la que se realizará el pago.
     * @param monto El monto a pagar con la tarjeta.
     */

    public void pagoTarjeta(Tarjeta tarjeta, double monto) {
        if (tarjeta.getSaldo() >= monto) {
            tarjeta.retirar(monto);
            System.out.println("Pago realizado exitosamente con tarjeta. Monto: " + monto);
        } else {
            System.out.println("Saldo insuficiente en la tarjeta.");
        }
    }
/**
     * Realiza un pago en efectivo. Si el monto entregado es suficiente,
     * calcula el cambio y lo muestra; si no, se muestra un mensaje de efectivo
     * insuficiente.
     *
     * @param monto El monto a pagar en efectivo.
     * @param efectivoEntregado El monto de efectivo entregado por el cliente.
     */

    public void pagoEfectivo(double monto, double efectivoEntregado) {
        if (efectivoEntregado >= monto) {
            double cambio = efectivoEntregado - monto;
            System.out.println("Pago realizado exitosamente en efectivo. Cambio: " + cambio);
        } else {
            System.out.println("Efectivo insuficiente para realizar el pago.");
        }
    }
}

