/**
 * Analyse la robustesse d'un mot de passe.
 * Le score est basé sur la diversité des caractères et la longueur.
 */
public class StrengthChecker {

    /**
     * Calcule le niveau de force d'un mot de passe.
     * @param motdepasse le mot de passe à analyser
     * @return le niveau de force parmi TRES_FAIBLE, FAIBLE, MOYEN, FORT, TRES_FORT
     */
    public NiveauForce analyser(String motdepasse) {

        // --- Détection des types de caractères présents ---
        boolean estMajuscule = false;
        boolean estMinuscule = false;
        boolean estChiffre   = false;
        boolean estSymbole   = false;

        for (char c : motdepasse.toCharArray()) {
            if (Character.isUpperCase(c))       estMajuscule = true;
            if (Character.isLowerCase(c))       estMinuscule = true;
            if (Character.isDigit(c))           estChiffre   = true;
            if (!Character.isLetterOrDigit(c))  estSymbole   = true;
        }

        // --- Calcul du score de diversité (max 4 points) ---
        int score = 0;
        if (estMajuscule) score++; // +1 si contient une majuscule
        if (estMinuscule) score++; // +1 si contient une minuscule
        if (estChiffre)   score++; // +1 si contient un chiffre
        if (estSymbole)   score++; // +1 si contient un symbole

        // --- Bonus selon la longueur (max 3 points) ---
        int longueur = motdepasse.length();
        if (longueur >= 8)  score++; // longueur minimale acceptable
        if (longueur >= 12) score++; // longueur recommandée
        if (longueur >= 16) score++; // longueur très sécurisée

        // --- Score final entre 0 et 7 ---
        if (score <= 1) return NiveauForce.TRES_FAIBLE;
        if (score <= 2) return NiveauForce.FAIBLE;
        if (score <= 3) return NiveauForce.MOYEN;
        if (score <= 5) return NiveauForce.FORT;
        return NiveauForce.TRES_FORT;
    }
}