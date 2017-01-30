package ListenModul;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ListHandler{
    
    private  List liste = new List();
    private Scanner scanner = new Scanner(System.in);
    private String eingabe;
    public int position;
    public String content;
    
    public ListHandler(){
        
    }
    
    public static void main(String[]args){
        ListHandler testListe = new ListHandler();
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
    
    private void replaceContentAtSpecificPoint() throws InputMismatchException{
        askForPosition();
        askForInput();
        liste.set(position ,content);
        System.out.println("Erfolgreich ersetzt!");
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
            if(liste.get(i) != null){
                System.out.println(liste.get(i));
            }
        }
    }
    
    private void askForPosition(){
        System.out.println("An welcher Stelle?");
        try {
            position = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("Die Eingabe war Fehlerhaft! "+ e.getMessage()+ " Die Position wurde auf 0 gesetzt!");
            position = 0;
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
