package ex2;

import java.io.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    private static final List<Produs> listaProduse = new ArrayList<>();
    private static double incasariTotale = 0;

    public static void main(String[] args) {
        //  citesteProduseDinFisier("produse.csv");

        Scanner scanner = new Scanner(System.in);
        int optiune;

        do {
            afiseazaMeniu();
            System.out.print("Alege o opțiune: ");
            optiune = scanner.nextInt();

            switch (optiune) {
                case 1:
                    afiseazaProduse();
                    break;
                case 2:
                    afiseazaProduseExpirate();
                    break;
                case 3:
                    vindeProdus();
                    break;
                case 4:
                    afiseazaProduseCuPretMinim();
                    break;
                case 5:
                    salvareProduseCantitateMica();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opțiune invalidă. Alege din nou.");
            }
        } while (optiune != 0);
    }



    private static void afiseazaMeniu() {
        System.out.println("\nMeniu:");
        System.out.println("1. Afișare produse");
        System.out.println("2. Afișare produse expirate");
        System.out.println("3. Vânzare produs");
        System.out.println("4. Afișare produse cu preț minim");
        System.out.println("5. Salvare produse cu cantitate mică");
        System.out.println("0. Ieșire");
    }

    private static void afiseazaProduse() {
        System.out.println("Lista de produse:");
        for (Produs produs : listaProduse) {
            System.out.println(produs);
        }
    }

    private static void afiseazaProduseExpirate() {
        System.out.println("Produse expirate:");
        LocalDate currentDate = LocalDate.now();
        for (Produs produs : listaProduse) {
            if (produs.getDataExpirare().isBefore(currentDate)) {
                System.out.println(produs);
            }
        }
    }

    private static void vindeProdus() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceți denumirea produsului de vândut: ");
        String denumire = scanner.next();
        System.out.print("Introduceți cantitatea de vândut: ");
        int cantitateVanduta = scanner.nextInt();

        for (Produs produs : listaProduse) {
            if (produs.getDenumire().equalsIgnoreCase(denumire)) {
                Produs.vindeProdus(produs, cantitateVanduta);
                if (produs.getCantitate() == 0) {
                    listaProduse.remove(produs);
                }
                return;
            }
        }
        System.out.println("Produsul nu a fost găsit.");
    }

    private static void afiseazaProduseCuPretMinim() {
        if (listaProduse.isEmpty()) {
            System.out.println("Nu există produse în listă.");
            return;
        }

        double pretMinim = listaProduse.get(0).getPret();
        for (Produs produs : listaProduse) {
            if (produs.getPret() < pretMinim) {
                pretMinim = produs.getPret();
            }
        }

        System.out.println("Produse cu preț minim:");
        for (Produs produs : listaProduse) {
            if (produs.getPret() == pretMinim) {
                System.out.println(produs);
            }
        }
    }

    private static void salvareProduseCantitateMica() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceți cantitatea minimă: ");
        int cantitateMinima = scanner.nextInt();

        try (FileWriter writer = new FileWriter("produse_cantitate_mica.csv")) {
            for (Produs produs : listaProduse) {
                if (produs.getCantitate() < cantitateMinima) {
                    writer.write(produs.getDenumire() + "," + produs.getPret() + "," + produs.getCantitate() + "," + produs.getDataExpirare() + "\n");
                }
            }
            System.out.println("Produsele cu cantitate mai mică decât " + cantitateMinima + " au fost salvate.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
