import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * Moteur de génération de mots de passe.
 * Utilise SecureRandom pour garantir une génération cryptographiquement sécurisée.
 */
public class GenerateurMdp {

    // --- Jeux de caractères disponibles ---
    private static final String mdpmajus  = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String mdpmin    = "abcdefghijklmnopqrstuvwxyz";
    private static final String numeromdp = "0123456789";
    private static final String symbolmdp = "&(_#~{[|`^@$ù^:;,!*=)]]}";

    // Générateur aléatoire sécurisé (plus sûr que Random)
    private SecureRandom random = new SecureRandom();

    /**
     * Construit l'alphabet de génération selon les choix de l'utilisateur.
     * @param upper   inclure les majuscules
     * @param lower   inclure les minuscules
     * @param digits  inclure les chiffres
     * @param symbols inclure les symboles
     * @return l'alphabet sous forme de String
     */
    public String choixUser(boolean upper, boolean lower, boolean digits, boolean symbols) {
        StringBuilder charset = new StringBuilder();

        if (upper)   charset.append(mdpmajus);   // ajout des majuscules
        if (lower)   charset.append(mdpmin);      // ajout des minuscules
        if (digits)  charset.append(numeromdp);   // ajout des chiffres
        if (symbols) charset.append(symbolmdp);   // ajout des symboles

        return charset.toString();
    }

    /**
     * Génère un seul mot de passe aléatoire.
     * @param longueur la longueur souhaitée
     * @param charset  l'alphabet à utiliser
     * @return le mot de passe généré
     */
    public String genererMdp(int longueur, String charset) {
        StringBuilder construireMdp = new StringBuilder();

        for (int i = 0; i < longueur; i++) {
            // Pioche un caractère aléatoire dans l'alphabet
            int index = random.nextInt(charset.length());
            char c = charset.charAt(index);
            construireMdp.append(c);
        }

        return construireMdp.toString();
    }

    /**
     * Génère plusieurs mots de passe en mode rafale.
     * @param nombre   le nombre de mots de passe à générer
     * @param longueur la longueur de chaque mot de passe
     * @param charset  l'alphabet à utiliser
     * @return la liste des mots de passe générés
     */
    public List<String> genererRafale(int nombre, int longueur, String charset) {
        List<String> listemdp = new ArrayList<>();

        for (int i = 0; i < nombre; i++) {
            listemdp.add(genererMdp(longueur, charset)); // génère et ajoute directement
        }

        return listemdp;
    }
}