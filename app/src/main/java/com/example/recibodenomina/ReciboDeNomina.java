package com.example.recibodenomina;

public class ReciboDeNomina {
    private int numRecibo;
    private String nombre;
    private int horasTrabajadas;
    private int horasExtras;
    private int puesto;
    private static final double PAGO_BASE = 200;

    public ReciboDeNomina(int numRecibo, String nombre, int horasTrabajadas, int horasExtras, int puesto) {
        this.numRecibo = numRecibo;
        this.nombre = nombre;
        this.horasTrabajadas = horasTrabajadas;
        this.horasExtras = horasExtras;
        this.puesto = puesto;

    }

    public double calcularPagoPorHora() {
        switch (puesto) {
            case 1:
                return PAGO_BASE * 1.2;
            case 2:
                return PAGO_BASE * 1.5;
            case 3:
                return PAGO_BASE * 2.0;
            default:
                return PAGO_BASE;
        }
    }

    public double calcularSubtotal() {
        double pagoPorHora = calcularPagoPorHora();
        return (horasTrabajadas * pagoPorHora) + (horasExtras * pagoPorHora * 2);
    }

    public double calcularImpuesto() {
        return calcularSubtotal() * 0.16;
    }

    public double calcularTotalAPagar() {
        return calcularSubtotal() - calcularImpuesto();
    }
}
