/**
 * Représente les niveaux de force possibles d'un mot de passe.
 * Utilisé par StrengthChecker et Display.
 */
public enum NiveauForce {
    TRES_FAIBLE, // Score 0-1 : mot de passe très vulnérable
    FAIBLE,      // Score 2   : manque de diversité ou trop court
    MOYEN,       // Score 3   : correct mais améliorable
    FORT,        // Score 4-5 : bonne combinaison de caractères
    TRES_FORT    // Score 6-7 : longueur et diversité optimales
}