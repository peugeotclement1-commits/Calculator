import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage fenetre) {
        VBox root = new VBox(18);
        root.getStyleClass().add("root"); // Fond noir en CSS
        root.setPadding(new Insets(35, 25, 25, 25));
        root.setAlignment(Pos.BOTTOM_CENTER);

        // On précise le type d'objets dans la liste entre < >
        ArrayList<String> liste = new ArrayList<>();

        Label nb = new Label("");
        nb.getStyleClass().add("promptNB");
        nb.setPrefWidth(350);
        nb.setMaxWidth(350);
        nb.setMinHeight(130);
        nb.setAlignment(Pos.BOTTOM_RIGHT);
        nb.setTextOverrun(OverrunStyle.ELLIPSIS);
        nb.setWrapText(false);
        root.getChildren().add(nb);

        GridPane clavier = new GridPane();
        clavier.setAlignment(Pos.CENTER);
        clavier.setHgap(10);
        clavier.setVgap(10);

        Button boutonAC = creerBouton("AC", "other-btn");
        boutonAC.setOnAction(event -> nb.setText(prompt(AC(liste))));

        Button boutonDelchar = creerBouton("DEL", "other-btn");
        boutonDelchar.setOnAction(event -> nb.setText(prompt(del_char_prompt(liste))));

        Button boutonParentheseOuvrante = creerBouton("(", "other-btn");
        boutonParentheseOuvrante.setOnAction(event -> ajouterAuPrompt(liste, nb, "("));

        Button boutonParentheseFermante = creerBouton(")", "other-btn");
        boutonParentheseFermante.setOnAction(event -> ajouterAuPrompt(liste, nb, ")"));

        Button bouton7 = creerBouton("7", "bouton-chiffre");
        bouton7.setOnAction(event -> ajouterAuPrompt(liste, nb, "7"));

        Button bouton8 = creerBouton("8", "bouton-chiffre");
        bouton8.setOnAction(event -> ajouterAuPrompt(liste, nb, "8"));

        Button bouton9 = creerBouton("9", "bouton-chiffre");
        bouton9.setOnAction(event -> ajouterAuPrompt(liste, nb, "9"));

        Button boutonDivision = creerBouton("/", "boutons-ope");
        boutonDivision.setOnAction(event -> ajouterAuPrompt(liste, nb, "/"));

        Button bouton4 = creerBouton("4", "bouton-chiffre");
        bouton4.setOnAction(event -> ajouterAuPrompt(liste, nb, "4"));

        Button bouton5 = creerBouton("5", "bouton-chiffre");
        bouton5.setOnAction(event -> ajouterAuPrompt(liste, nb, "5"));

        Button bouton6 = creerBouton("6", "bouton-chiffre");
        bouton6.setOnAction(event -> ajouterAuPrompt(liste, nb, "6"));

        Button boutonMultiplication = creerBouton("X", "boutons-ope");
        boutonMultiplication.setOnAction(event -> ajouterAuPrompt(liste, nb, "*"));

        Button bouton1 = creerBouton("1", "bouton-chiffre");
        bouton1.setOnAction(event -> ajouterAuPrompt(liste, nb, "1"));

        Button bouton2 = creerBouton("2", "bouton-chiffre");
        bouton2.setOnAction(event -> ajouterAuPrompt(liste, nb, "2"));

        Button bouton3 = creerBouton("3", "bouton-chiffre");
        bouton3.setOnAction(event -> ajouterAuPrompt(liste, nb, "3"));

        Button boutonMoins = creerBouton("-", "boutons-ope");
        boutonMoins.setOnAction(event -> ajouterAuPrompt(liste, nb, "-"));

        Button bouton0 = creerBouton("0", "bouton-chiffre");
        bouton0.setOnAction(event -> ajouterAuPrompt(liste, nb, "0"));

        Button boutonModulo = creerBouton("%", "boutons-ope");
        boutonModulo.setOnAction(event -> ajouterAuPrompt(liste, nb, "%"));

        Button boutonEgal = creerBouton("=", "boutons-ope");
        boutonEgal.setOnAction(event -> {
            String expression = prompt(liste); // "12+5"
            Double resultat = calculAvecPriorites(expression); // 17.0
            String resultatTexte = String.valueOf(resultat);
            liste.clear();
            liste.add(resultatTexte);
            nb.setText(resultatTexte);
            System.out.println("Le résultat est : " + resultat);
        });

        Button boutonPlus = creerBouton("+", "boutons-ope");
        boutonPlus.setOnAction(event -> ajouterAuPrompt(liste, nb, "+"));

        clavier.addRow(0, boutonAC, boutonDelchar, boutonParentheseOuvrante, boutonParentheseFermante);
        clavier.addRow(1, bouton7, bouton8, bouton9, boutonDivision);
        clavier.addRow(2, bouton4, bouton5, bouton6, boutonMultiplication);
        clavier.addRow(3, bouton1, bouton2, bouton3, boutonMoins);
        clavier.addRow(4, bouton0, boutonModulo, boutonEgal, boutonPlus);

        root.getChildren().add(clavier);

        Scene scene = new Scene(root, 400, 600);
        
        // Chargement du fichier CSS
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        fenetre.setTitle("Calculatrice JavaFX");
        fenetre.setScene(scene);
        fenetre.show();

    }

    private Button creerBouton(String texte, String classeCss) {
        Button bouton = new Button(texte);
        bouton.setPrefSize(70, 70);
        bouton.setMinSize(70, 70);
        bouton.setMaxSize(70, 70);
        bouton.getStyleClass().add(classeCss);
        return bouton;
    }

    private void ajouterAuPrompt(ArrayList<String> liste, Label nb, String valeur) {
        liste.add(valeur);
        nb.setText(prompt(liste));
    }

    // supprime le dernier nomre present dans le prompt / retirer le dernier nombre
    public ArrayList<String> del_char_prompt(ArrayList<String> liste){
        if (!liste.isEmpty()) {
            liste.remove(liste.size() - 1);
        }
        return liste;
    }

    public ArrayList<String> AC(ArrayList<String> liste){
        liste.clear();
        return liste;
    }

    public String prompt( ArrayList<String> liste){
        String fullcalc="";
        for (String elem : liste){
            fullcalc=fullcalc+elem;
        }
        return fullcalc;
    }

    public Double calculPrompt(String fullcalc) {
        // 1. On sépare les nombres (on coupe au niveau des opérateurs)
        // Cela nous donne par exemple ["12", "5"] pour "12+5"
        String[] nombres = fullcalc.split("[\\+\\-\\*/]");
    
        // 2. On sépare les opérateurs uniquement
        // Cela nous donne ["+"] pour "12+5"
        String[] operateurs = fullcalc.split("[0-9.]+");
        // Note : le premier élément de 'operateurs' est souvent vide à cause du split, 
        // on commence donc souvent à l'index 1.

        // 3. On récupère le premier nombre
        double resultat = Double.parseDouble(nombres[0]);

        // 4. On parcourt le reste pour appliquer les calculs
        for (int i = 0; i < operateurs.length - 1; i++) {
            String op = operateurs[i + 1]; // On prend l'opérateur
            double suivant = Double.parseDouble(nombres[i + 1]); // Le nombre suivant

            if (op.equals("+")){
                resultat += suivant;
            }
            else if (op.equals("-")){ 
                resultat -= suivant;
            }
            else if (op.equals("*")){ 
                resultat *= suivant;
            }
            else if (op.equals("/")){ 
                resultat /= suivant;
            }
        }
        return resultat;
    }

    public Double calculAvecPriorites(String expression) {
        return new AnalyseurExpression(expression).analyser();
    }

    private static class AnalyseurExpression {
        private final String expression;
        private int position;

        AnalyseurExpression(String expression) {
            this.expression = expression;
        }

        double analyser() {
            double resultat = additionOuSoustraction();
            if (position != expression.length()) {
                throw new IllegalArgumentException("Expression invalide : " + expression);
            }
            return resultat;
        }

        private double additionOuSoustraction() {
            double resultat = multiplicationDivisionModulo();
            while (position < expression.length()) {
                char operateur = expression.charAt(position);
                if (operateur != '+' && operateur != '-') {
                    break;
                }
                position++;
                double suivant = multiplicationDivisionModulo();
                resultat = operateur == '+' ? resultat + suivant : resultat - suivant;
            }
            return resultat;
        }

        private double multiplicationDivisionModulo() {
            double resultat = facteur();
            while (position < expression.length()) {
                char operateur = expression.charAt(position);
                if (operateur != '*' && operateur != '/' && operateur != '%') {
                    break;
                }
                position++;
                double suivant = facteur();
                if (operateur == '*') {
                    resultat *= suivant;
                } else if (operateur == '/') {
                    resultat /= suivant;
                } else {
                    resultat %= suivant;
                }
            }
            return resultat;
        }

        private double facteur() {
            if (position < expression.length()) {
                char signe = expression.charAt(position);
                if (signe == '+' || signe == '-') {
                    position++;
                    double valeur = facteur();
                    return signe == '-' ? -valeur : valeur;
                }
            }

            if (position < expression.length() && expression.charAt(position) == '(') {
                position++;
                double resultat = additionOuSoustraction();
                if (position >= expression.length() || expression.charAt(position) != ')') {
                    throw new IllegalArgumentException("Parenthese fermante manquante");
                }
                position++;
                return resultat;
            }

            int debut = position;
            while (position < expression.length()
                    && (Character.isDigit(expression.charAt(position))
                    || expression.charAt(position) == '.')) {
                position++;
            }
            if (debut == position) {
                throw new IllegalArgumentException("Nombre attendu");
            }
            return Double.parseDouble(expression.substring(debut, position));
        }
    }
}
