import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Irrgarten {
  public static void main(String[] args) throws IOException {
    File file = new File("h:/.win10_profile/Desktop/irrgarten.txt");
    Scanner sc = new Scanner(new FileInputStream(file));
    // feld einlesen
    char[][] feld2 = new char[500][500];
    int counter = 0;
    int x = 0;
    int startx = 0;
    int starty = 0;
    int startx5 = 0;
    int starty5 = 0;
    int startx6 = 0;
    int starty6 = 0;
    int startx7 = 0;
    int starty7 = 0;
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
    char[][] feld5 = rotateArray(feld);
    char[][] feld6 = rotateArray(feld5);
    char[][] feld7 = rotateArray(feld6);

    // start finden
    boolean ende = false;
    for (int i = 0; i < feld5.length; i++) {
      for (int k = 0; k < feld5[1].length; k++) {
        if (feld5[i][k] == 'X') {
          feld5[i][k] = ' ';
          starty5 = i;
          startx5 = k;
          ende = true;
          break;
        }
      }
      if (ende == true) {
        break;
      }
    }
    ende = false;
    for (int i = 0; i < feld6.length; i++) {
      for (int k = 0; k < feld6[1].length; k++) {
        if (feld6[i][k] == 'X') {
          feld6[i][k] = ' ';
          starty6 = i;
          startx6 = k;
          ende = true;
          break;
        }
      }
      if (ende == true) {
        break;
      }
    }
    ende = false;
    for (int i = 0; i < feld7.length; i++) {
      for (int k = 0; k < feld7[1].length; k++) {
        if (feld7[i][k] == 'X') {
          feld7[i][k] = ' ';
          starty7 = i;
          startx7 = k;
          ende = true;
          break;
        }
      }
      if (ende == true) {
        break;
      }
    }
    ende = false;
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
    printArray(feld);
    System.out.println(garten(feld, 1, starty, startx));
    printArray(feld5);
    System.out.println(garten(feld5, 1, starty5, startx5));
    printArray(feld6);
    System.out.println(garten(feld6, 1, starty6, startx6));
    printArray(feld7);
    System.out.println(garten(feld7, 1, starty7, startx7));
    sc.close();
    // int min = Math.min(garten(feld, 1, starty, startx), garten(feld5, 1, starty5, startx5),
    // garten(feld6, 1, starty6, startx6), garten(feld7, 1, starty7, startx7))
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
      // System.out.println(counter);
      return counter;
    }
    feld[y][x] = '.'; // auf markiert setzen
    /*
     * for (int i = 0; i < feld.length; i++) { for (int k = 0; k < feld[1].length; k++) {
     * System.out.print(feld[i][k]); } System.out.println(); }
     */
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



    int min = 0; // kürzester weg von allen benutzen, Vorsicht, es kann auch keiner funktionieren
    for (int i = 1; i < weg.length; i++) {
      if (weg[i] < weg[min]) {
        min = i;
      }
    }
    return weg[min];
  }

  public static char[][] rotateArray(char[][] mat) {
    final int M = mat.length;
    final int N = mat[0].length;
    char[][] ret = new char[N][M];
    for (int r = 0; r < M; r++) {
      for (int c = 0; c < N; c++) {
        ret[c][M - 1 - r] = mat[r][c];
      }
    }
    return ret;
  }

  public static void printArray(char[][] Test) {
    for (int i = 0; i < Test.length; i++) {
      for (int k = 0; k < Test[0].length; k++) {
        System.out.print(Test[i][k]);
      }
      System.out.println();
    }
  }
}

