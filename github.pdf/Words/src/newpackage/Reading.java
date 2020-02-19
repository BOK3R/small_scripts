package newpackage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Reading {

    static int wybor;
    static String wyborSeparatora;
    static String separator = " ";
    static String text = "";
    static String path;

    static Scanner scanner = new Scanner(System.in);

    private static String readFileToString(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            helpWrongPath();
            entryText();
        }
        return contentBuilder.toString();
    }

    private static int countWords(String text, String separator) {
        String[] strArray = text.split(separator);
        int count = 0;
        for (int i = 0; i < strArray.length; i++) {
            count++;
        }
        return count;
    }

    private static void choiceEntry() {
        System.out.println("Program zliczający ilość słów");
        System.out.println("Wybierz źródło danych");
        System.out.println("1. Zlicz ilość słów w danych wpisanych z klawiatury");
        System.out.println("2. Zlicz ilość słów w danych zapisanych w pliku tekstowym");
        System.out.print("Twój wybór: ");
        wybor = scanner.nextInt();

        while (wybor != 1 && wybor != 2) {
            helpChoiceEntry();
            System.out.println("Wybierz źródło danych");
            System.out.println("1. Zlicz ilość słów w danych wpisanych z klawiatury");
            System.out.println("2. Zlicz ilość słów w danych zapisanych w pliku tekstowym");
            System.out.print("Twój wybór: ");
            wybor = scanner.nextInt();
        }
    }

    private static void choiceSeperator() {
        System.out.print("Czy chcesz skorzystać ze standarowego separatora słów (spacja)? [t/n]:");
        scanner.nextLine();
        wyborSeparatora = scanner.nextLine();

        while (!wyborSeparatora.equals("t") && !wyborSeparatora.equals("n")) {
            helpChoiceSeperator();
            System.out.print("Czy chcesz skorzystać ze standarowego separatora słów (spacja)? [t/n]:");
            wyborSeparatora = scanner.nextLine();
        }

        if (wyborSeparatora.equals("n")) {
            System.out.print("Wprowadź swój separator słów: ");
            separator = scanner.nextLine();
        }
    }

    private static void entryText() {
        if (wybor == 1) {
            System.out.println("Wprowadź tekst którego słowa chcesz zliczyć:");
            text = scanner.nextLine();
        } else if (wybor == 2) {
            System.out.println("Podaj ścieżkę do pliku: ");
            path = scanner.nextLine();
            text = readFileToString(path);
        }
    }

    private static void helpChoiceEntry() {
        System.out.println("\n--------------------------------------------------------");
        System.out.println("HELP BOX");
        System.out.println("Podano błędną wartość parametru!");
        System.out.println("Akceptowane odpowiedzi to: \n1\nlub\n2");
        System.out.println("Upewnij się że nie wpisano żadnych dodatkowych znaków");
        System.out.println("--------------------------------------------------------\n");
    }

    private static void helpChoiceSeperator() {
        System.out.println("\n--------------------------------------------------------");
        System.out.println("HELP BOX");
        System.out.println("Podano błędną wartość parametru!");
        System.out.println("Akceptowane odpowiedzi to: \nt - tak\nlub\nn - nie");
        System.out.println("Upewnij się że nie wpisano żadnych dodatkowych znaków");
        System.out.println("--------------------------------------------------------\n");
    }

    private static void helpWrongPath() {
        System.out.println("\n---------------------------------------------------------------------------------------------------------------------");
        System.out.println("HELP BOX");
        System.out.println("Podano błędną wartość parametru!");
        System.out.println("Prawdopodobnie wpisano nieprawidłową ścieżkę do pliku lub próbowano odczytać plik o nieprawidłowym rozszerzeniu");
        System.out.println("Ścieżka do pliku może wyglądać np. w taki sposób:");
        System.out.println("C:\\Users\\user\\Desktop\\test1.txt\nlub");
        System.out.println("C:/Users//user/Desktop/test1.txt");
        System.out.println("Upewnij się również, że podany plik ma rozszerzenie .txt, inne rozszerzenia nie są obsługiwane");
        System.out.println("---------------------------------------------------------------------------------------------------------------------\n");
    }

    public static void main(String[] args) {

        choiceEntry();
        choiceSeperator();
        entryText();

        int words = countWords(text, separator);
        System.out.println("Liczba słów w podanym tekście to: " + words);
    }
}
