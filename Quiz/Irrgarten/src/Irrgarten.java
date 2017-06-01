import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Irrgarten {
  static int countermin = 1000;

  public static void main(String[] args) throws IOException {
    File file = new File("./res/klein.txt");
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

    char[][] feld = new char[counter][x];

    // feld printen
    for (int i = 0; i < counter; i++) {
      for (int k = 0; k < x; k++) {
        feld[i][k] = feld2[i][k];
        System.out.print(feld[i][k]);
      }
      System.out.println();
    }
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
    garten(feld, 1, starty, startx);
    sc.close();
  }

  public static int garten(char[][] feld, int counter, int y, int x) {
    int[] weg = new int[4];
    weg[0] = 100000; // wenn kein weg gefunden
    weg[1] = 100000;
    weg[2] = 100000;
    weg[3] = 100000;
    // System.out.println();
    // System.out.println(counter);
    if (feld[y][x] == 'X') { // Ende gefunden --> abbruch
      if (counter < countermin) {

        countermin = counter;
        System.out.println(countermin);
      }
      return counter;
    }
    if (counter > countermin) {
      return 100000;
    }
    feld[y][x] = '.'; // auf markiert setzen
    /*
     * for (int i = 0; i < feld.length; i++) { for (int k = 0; k < feld[1].length; k++) {
     * System.out.print(feld[i][k]); } System.out.println(); }
     */
    if ((feld[y][x - 1] != '#') && (feld[y][x - 1] != '.')) {
      int x1 = x;
      int y1 = y;
      int counter2 = counter;
      char[][] feld3 = new char[feld.length][feld[0].length];
      for (int i = 0; i < feld.length; i++) {
        for (int k = 0; k < feld[0].length; k++) {
          feld3[i][k] = feld[i][k];
        }
      }
      x1 -= 1;
      counter2++;
      weg[3] = garten(feld3, counter2, y1, x1);
      // System.out.println("links");
    }
    if ((feld[y][x + 1] != '#') && (feld[y][x + 1] != '.')) {// prüfen, wie weitergehen
      int x1 = x;
      int y1 = y;
      int counter2 = counter;
      x1 += 1;
      counter2++;
      char[][] feld3 = new char[feld.length][feld[0].length];
      for (int i = 0; i < feld.length; i++) {
        for (int k = 0; k < feld[0].length; k++) {
          feld3[i][k] = feld[i][k];
        }
      }
      weg[0] = garten(feld3, counter2, y1, x1);
      // System.out.println("rechts");
    }
    if ((feld[y + 1][x] != '#') && (feld[y + 1][x] != '.')) {
      int x1 = x;
      int y1 = y;
      int counter2 = counter;
      y1 += 1;
      counter2++;
      char[][] feld3 = new char[feld.length][feld[0].length];
      for (int i = 0; i < feld.length; i++) {
        for (int k = 0; k < feld[0].length; k++) {
          feld3[i][k] = feld[i][k];
        }
      }
      weg[1] = garten(feld3, counter2, y1, x1);
      // System.out.println("unten");
    }

    if ((feld[y - 1][x] != '#') && (feld[y - 1][x] != '.')) {
      int x1 = x;
      int y1 = y;
      int counter2 = counter;
      y1 -= 1;
      counter2++;
      char[][] feld3 = new char[feld.length][feld[0].length];
      for (int i = 0; i < feld.length; i++) {
        for (int k = 0; k < feld[0].length; k++) {
          feld3[i][k] = feld[i][k];
        }
      }
      weg[2] = garten(feld3, counter2, y1, x1);
      // System.out.println("oben");
    }



    int min = 0; // kürzester weg von allen benutzen, Vorsicht, es kann auch keiner funktionieren
    for (int i = 1; i < weg.length; i++) {
      if (weg[i] < weg[min]) {
        min = i;
      }
    }
    return weg[min];
  }
}

