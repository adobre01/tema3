package ex2;

import java.time.LocalDate;

public class Produs {
    private String denumire;
    private double pret;
    private int cantitate;
    private LocalDate dataExpirare;

    public Produs(String denumire, double pret, int cantitate, LocalDate dataExpirare) {
        this.denumire = denumire;
        this.pret = pret;
        this.cantitate = cantitate;
        this.dataExpirare = dataExpirare;
    }


    public String getDenumire() {
        return denumire;
    }

    public double getPret() {
        return pret;
    }

    public int getCantitate() {
        return cantitate;
    }

    public LocalDate getDataExpirare() {
        return dataExpirare;
    }

    /*public static void vanzare(int cantitateVanduta) {
        if (cantitateVanduta <= cantitate) {
            cantitate = cantitate - cantitateVanduta;
            double incasari = pret * cantitateVanduta;
        } else {
            System.out.println("Nu există suficientă cantitate pe stoc pentru a vinde acest produs.");
        }
    }*/


    public static void vindeProdus(Produs produs, int cantitateVanduta) {
        if (produs.cantitate >= cantitateVanduta) {
            produs.cantitate -= cantitateVanduta;
            double incasari = cantitateVanduta * produs.pret;
            // incasariTotale += incasari;
            System.out.println("Produs vândut cu succes. Încasări: " + incasari);
        } else {
            System.out.println("Cantitate insuficientă pe stoc.");
        }
    }


    public String toString() {
        return "Denumire: " + denumire + ", Preț: " + pret + ", Cantitate: " + cantitate + ", Expiră la: " + dataExpirare;
    }

}
