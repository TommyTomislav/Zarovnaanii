package zarovnani;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Zarovnaanii {

    private static void zpracujEnter(String[] veta, int sirka, String zarovnani) {

        int nCntr = 0;

        while (nCntr < veta.length) {
            String sVystupRadek = "";
            int nJustDiv = 0;
            int nSirkaAktual = 0;
            int nPocetSlov = 0;
            int nMezera = 0;
            String sMezera = "";
            while (nCntr <  veta.length && nSirkaAktual + nMezera +  veta[nCntr].length() <= sirka) {
                nSirkaAktual = nSirkaAktual + nMezera + veta[nCntr].length();
                nPocetSlov++;
                nMezera = 1;
                nCntr++;
            }

            String sOdsazeni = "";
            int nVolne = sirka - nSirkaAktual;
            if (zarovnani.equals("--right")) {
                for (int iMez = 0; iMez < nVolne; iMez++) sOdsazeni = sOdsazeni + " ";
            } else if (zarovnani.equals("--center")) {
                for (int iMez = 0; iMez < nVolne / 2 ; iMez++) sOdsazeni = sOdsazeni + " ";
            } else if (zarovnani.equals("--justify")) {
                if (nPocetSlov == 1) {
                    nMezera = 1;
                    nJustDiv = 0;
                } else {
                    nMezera = 1 + nVolne / (nPocetSlov - 1);
                    nJustDiv = nVolne % (nPocetSlov - 1);
                }
            }
            for (int iMez = 0; iMez < nMezera; iMez++) sMezera = sMezera + " ";
            for (int iSlova = 0; iSlova < nPocetSlov; iSlova++) {
                if (sVystupRadek.equals("")) {
                    sVystupRadek = sOdsazeni + veta[nCntr - nPocetSlov + iSlova];
                } else {
                    if (iSlova <= nJustDiv) {
                        sVystupRadek = sVystupRadek + sMezera + " " + veta[nCntr - nPocetSlov + iSlova];
                    } else {
                        sVystupRadek = sVystupRadek + sMezera + veta[nCntr - nPocetSlov + iSlova];
                    }
                }
            }
            System.out.println(sVystupRadek);
        }
    }

    public static void main(String[] args)  throws java.io.IOException {

        String sZarovnani = "";
        int nSirka = -1;
        String sText = "";
        Scanner s;

        for (int i = 0; i < args.length; i++)
        {
            if (args[i].startsWith("--right") || args[i].startsWith("--center") || args[i].startsWith("--justify")) {
                sZarovnani = args[i];
            } else if (args[i].startsWith("--width=")) {
                nSirka = Integer.parseInt(args[i].substring(8));
            } else if (args[i].startsWith("--w")) {
                System.out.println(args[i+1]);
                nSirka = Integer.parseInt( args[i+1]);
            }
        }

        if (nSirka == -1){
            System.out.println("Nezadan parametr --w nebo --width=");
            return;
        }

        s = new Scanner(  System.in ).useDelimiter("\n");
        try {
            do {
                String radek = s.nextLine();
                String[] r = radek.split("\\s+");
                zpracujEnter(r,nSirka,sZarovnani);
            } while (true);
        }
        catch (NoSuchElementException e)
        {
        }

    }
}
