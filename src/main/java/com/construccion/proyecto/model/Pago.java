package com.construccion.proyecto.model;

    
public class Pago {
    public void pagoTarjeta(Tarjeta tarjeta, double monto) {
        if (tarjeta.getSaldo() >= monto) {
            tarjeta.retirar(monto);
            System.out.println("Pago realizado exitosamente con tarjeta. Monto: " + monto);
        } else {
            System.out.println("Saldo insuficiente en la tarjeta.");
        }
    }

    public void pagoEfectivo(double monto, double efectivoEntregado) {
        if (efectivoEntregado >= monto) {
            double cambio = efectivoEntregado - monto;
            System.out.println("Pago realizado exitosamente en efectivo. Cambio: " + cambio);
        } else {
            System.out.println("Efectivo insuficiente para realizar el pago.");
        }
    }
}

