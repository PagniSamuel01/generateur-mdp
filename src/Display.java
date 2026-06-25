/**
 * Gère l'affichage des mots de passe dans le terminal.
 * Utilise les codes ANSI pour colorier les niveaux de force.
 */
public class Display {

    // --- Codes couleurs ANSI ---
    private static final String ROUGE = "\u001B[31m"; // Très faible / Faible
    private static final String JAUNE = "\u001B[33m"; // Moyen
    private static final String VERT  = "\u001B[32m"; // Fort / Très fort
    private static final String RESET = "\u001B[0m";  // Remet la couleur par défaut

    /**
     * Affiche un mot de passe avec son niveau de force coloré.
     * @param aff_mdp le mot de passe à afficher
     * @param mdp     le niveau de force associé
     */
    public void afficher(String aff_mdp, NiveauForce mdp) {

        // Choisir la couleur selon le niveau de force
        String couleur;
        switch (mdp) {
            case TRES_FAIBLE -> couleur = ROUGE;
            case FAIBLE      -> couleur = ROUGE;
            case MOYEN       -> couleur = JAUNE;
            case FORT        -> couleur = VERT;
            case TRES_FORT   -> couleur = VERT;
            default          -> couleur = RESET;
        }

        // Afficher le mot de passe avec sa couleur, puis remettre la couleur par défaut
        System.out.println(couleur + aff_mdp + " [" + mdp + "]" + RESET);
    }
}