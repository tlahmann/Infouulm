import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Irrgarten {
  static int countermin = 100000;

  public static void main(String[] args) throws IOException {
    long time = System.currentTimeMillis();
    File file = new File("C:/Users/jonas/Desktop/test/riesig.txt");
    Scanner sc = new Scanner(new FileInputStream(file));
    // feld einlesen
    char[][] feld2 = new char[500][500];
    int counter = 0;
    int x = 0;
    int startx = 0;
    int starty = 0;
    while (sc.hasNext()) {
      x = 0;
      String line = sc.nextLine();
      for (int i = 0; i < line.length(); i++) {
        feld2[counter][i] = line.charAt(i);
        x++;
      }
      counter++;
    }
    sc.close();

    char[][] feld = new char[counter][x];

    // feld printen
    for (int i = 0; i < counter; i++) {
      for (int k = 0; k < x; k++) {
        feld[i][k] = feld2[i][k];
        System.out.print(feld[i][k]);
      }
      System.out.println();
    }
    feld2 = null;
    // start finden
    boolean ende = false;
    for (int i = 0; i < feld.length; i++) {
      for (int k = 0; k < feld[1].length; k++) {
        if (feld[i][k] == 'X') {
          feld[i][k] = ' ';
          starty = i;
          startx = k;
          ende = true;
          break;
        }
      }
      if (ende == true) {
        break;
      }
    }
    // start position ausgeben
    System.out.println(starty);
    System.out.println(startx);
    // lösung berechnen und ausgeben
    // System.out.println(garten(feld, 1, starty, startx, 299));

    System.out.println("Loesung: " + garten(feld, 1, starty, startx));
    System.out.println("Benoetigte Zeit: " + (System.currentTimeMillis() - time));
  }

  public static int garten(char[][] feld, int counter, int y, int x) {
    int[] weg = new int[4];
    weg[0] = 100000; // wenn kein weg gefunden
    weg[1] = 100000;
    weg[2] = 100000;
    weg[3] = 100000;
    if (counter > countermin) {
      feld = null;
      return 100000;
    }
    if (feld[y][x] == 'X') { // Ende gefunden --> abbruch
      if (counter < countermin) {
        feld = null;
        countermin = counter;
        System.out.println(countermin);
      }
      return counter;
    }

    feld[y][x] = '.'; // auf markiert setzen

    if ((feld[y][x - 1] != '#') && (feld[y][x - 1] != '.')) {
      int x1 = x, y1 = y;
      int counter2 = counter;
      x1 -= 1;
      counter2++;
      if (counter > countermin) {
        feld = null;
        return 100000;
      }
      char[][] feld3 = new char[feld.length][feld[0].length];
      for (int i = 0; i < feld.length; i++) {
        for (int k = 0; k < feld[0].length; k++) {
          feld3[i][k] = feld[i][k];
        }
      }
      weg[3] = garten(feld3, counter2, y1, x1);
      feld3 = null;
      // System.out.println("links");
    }
    if ((feld[y][x + 1] != '#') && (feld[y][x + 1] != '.')) {// prüfen, wie weitergehen
      int x1 = x, y1 = y;
      int counter2 = counter;
      x1 += 1;
      counter2++;
      if (counter > countermin) {
        feld = null;
        return 100000;
      }
      char[][] feld3 = new char[feld.length][feld[0].length];
      for (int i = 0; i < feld.length; i++) {
        for (int k = 0; k < feld[0].length; k++) {
          feld3[i][k] = feld[i][k];
        }
      }
      weg[0] = garten(feld3, counter2, y1, x1);
      feld3 = null;
      // System.out.println("rechts");
    }
    if ((feld[y + 1][x] != '#') && (feld[y + 1][x] != '.')) {
      int x1 = x, y1 = y;
      int counter2 = counter;
      y1 += 1;
      counter2++;
      if (counter > countermin) {
        feld = null;
        return 100000;
      }
      char[][] feld3 = new char[feld.length][feld[0].length];
      for (int i = 0; i < feld.length; i++) {
        for (int k = 0; k < feld[0].length; k++) {
          feld3[i][k] = feld[i][k];
        }
      }
      weg[1] = garten(feld3, counter2, y1, x1);
      feld3 = null;
      // System.out.println("unten");
    }
    if ((feld[y - 1][x] != '#') && (feld[y - 1][x] != '.')) {
      int x1 = x, y1 = y;
      int counter2 = counter;
      y1 -= 1;
      counter2++;
      if (counter > countermin) {
        feld = null;
        return 100000;
      }
      char[][] feld3 = new char[feld.length][feld[0].length];
      for (int i = 0; i < feld.length; i++) {
        for (int k = 0; k < feld[0].length; k++) {
          feld3[i][k] = feld[i][k];
        }
      }
      weg[2] = garten(feld3, counter2, y1, x1);
      feld3 = null;
      // System.out.println("oben");
    }
    int min = 0; // kürzester weg von allen benutzen, Vorsicht, es kann auch keiner funktionieren
    for (int i = 1; i < weg.length; i++) {
      if (weg[i] < weg[min]) {
        min = i;
      }
    }
    if (weg[min] == 100000) {
      feld = null;
    }
    return weg[min];
  }
}

