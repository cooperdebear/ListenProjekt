package ListenModul;

import java.util.List;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Liste{
    
    private List<String> liste;
    Scanner scanner;
    String eingabe;
    int position;
    String content;
    
    public Liste(){
        liste = new ArrayList();
        scanner = new Scanner(System.in);
    }
    
    public static void main(String[]args){
        Liste testListe = new Liste();
        testListe.starte();
    }
    
    private void starte(){
        while(true){
            processInput();
        }
    }
    
    private void processInput(){
        System.out.println("Was willst du tun? einfuegen/ersetzen/loeschen/anzeigen/allesAnzeigen/beenden");
        eingabe = scanner.next();
        switch(eingabe)
        {
            case "einfuegen": addContentAtSpecificPoint(); break;
            case "ersetzen": replaceContentAtSpecificPoint(); break;
            case "loeschen" : removeContentAtSpecificPoint(); break;
            case "anzeigen": showContentAtSpecificPoint(); break;
            case "allesAnzeigen": showAllContent(); break;
            case "beenden": System.exit(0); break;
            default: System.out.println("Bitte gib etwas von den vorgegebenen Worten ein!");
        }
    }
    
    private void addContentAtSpecificPoint(){
        askForPosition();
        askForInput();
        try{
            liste.add(position ,content);
            System.out.println("Erfolgreich eingefügt!");
        }catch(IndexOutOfBoundsException e){
            System.err.println("Die Position "+ e.getMessage() + " darf nicht verwendet werden!");
        }
    }
    
    private void replaceContentAtSpecificPoint(){
        askForPosition();
        askForInput();
        try{
            liste.set(position ,content);
            System.out.println("Erfolgreich ersetzt!");
        }catch(IndexOutOfBoundsException e){
            System.err.println("Die Position darf nicht verwendet werden! "+ e.getMessage());
        }
    }
    
    private void removeContentAtSpecificPoint(){
        askForPosition();
        try{
            liste.remove(position);
            System.out.println("Erfolgreich gelöscht!");
        }catch(IndexOutOfBoundsException e){
            System.err.println("Die Position darf nicht verwendet werden! "+ e.getMessage());
        }
    }
    
    private void showContentAtSpecificPoint(){
        askForPosition();
        try{
            System.out.println(liste.get(position));
        }catch(IndexOutOfBoundsException e){
            System.err.println("Die Position darf nicht verwendet werden! "+ e.getMessage());
        }
    }
    
    private void showAllContent()
    {
        for (int i = 0; i < liste.size(); i++) {
            System.out.println(liste.get(i));
        }
    }
    
    private void askForPosition(){
        System.out.println("An welcher Stelle?");
        try {
            position = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("Die Eingabe war Fehlerhaft!"+ e.getMessage());
        }
    }
    
    private void askForInput(){
        System.out.println("Was willst du einfügen?");
        try{
            content = scanner.next();
        } catch (InputMismatchException e){
            System.err.println("Die Eingabe war Fehlerhaft!"+ e.getMessage());
        }
    }
}
