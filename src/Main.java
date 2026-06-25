import java.util.Scanner;
import java.util.List;

/**
 * Point d'entrée du programme.
 * Gère les interactions avec l'utilisateur et orchestre la génération des mots de passe.
 */
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // --- Collecte des préférences utilisateur ---
        System.out.println("La longueur du mot de passe:");
        int longueur_mdp = scanner.nextInt();

        System.out.println("Inclure des majuscules? (oui/non) :");
        boolean majuscules = scanner.next().equalsIgnoreCase("oui");

        System.out.println("Inclure des miniscules? (oui/non) :");
        boolean miniscules = scanner.next().equalsIgnoreCase("oui");

        System.out.println("Inclure des chiffres? (oui/non) :");
        boolean chiffres = scanner.next().equalsIgnoreCase("oui");

        System.out.println("Inclure des symboles? (oui/non) :");
        boolean symboles = scanner.next().equalsIgnoreCase("oui");

        System.out.println("Combien de mots de passe?:");
        int nombre_mdp = scanner.nextInt();

        // --- Initialisation des composants ---
        GenerateurMdp generateur = new GenerateurMdp();
        StrengthChecker checker  = new StrengthChecker();
        Display display          = new Display();

        // Construction de l'alphabet selon les choix de l'utilisateur
        String charset = generateur.choixUser(majuscules, miniscules, chiffres, symboles);

        // --- Génération et affichage ---
        if (nombre_mdp == 1) {
            // Mode simple : un seul mot de passe
            String mdp = generateur.genererMdp(longueur_mdp, charset);
            NiveauForce niveau = checker.analyser(mdp);
            display.afficher(mdp, niveau);
        } else {
            // Mode rafale : plusieurs mots de passe en une seule exécution
            List<String> liste_mdp = generateur.genererRafale(nombre_mdp, longueur_mdp, charset);
            for (String mdp : liste_mdp) {
                NiveauForce niveau = checker.analyser(mdp);
                display.afficher(mdp, niveau);
            }
        }
    }
}