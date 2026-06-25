import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Analyse la robustesse d'un mot de passe.
 * Délègue le calcul du score à un conteneur Docker Zxcvbn via une requête HTTP.
 */
public class StrengthChecker {

    // URL du conteneur Docker
    private static final String DOCKER_URL = "http://localhost:3000/analyse";

    /**
     * Envoie le mot de passe au conteneur Docker et récupère le score.
     * @param motdepasse le mot de passe à analyser
     * @return le niveau de force parmi TRES_FAIBLE, FAIBLE, MOYEN, FORT, TRES_FORT
     */
    public NiveauForce analyser(String motdepasse) {
        try {
            // --- Connexion au conteneur Docker ---
            URL url = new URL(DOCKER_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // --- Envoi du mot de passe en JSON ---
            String json = "{\"password\":\"" + motdepasse + "\"}";
            try (OutputStream os = conn.getOutputStream()) {
                os.write(json.getBytes());
            }

            // --- Lecture de la réponse ---
            StringBuilder reponse = new StringBuilder();
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()))) {
                String ligne;
                while ((ligne = br.readLine()) != null) {
                    reponse.append(ligne);
                }
            }

            // --- Extraction du score depuis le JSON reçu ---
            String reponseJson = reponse.toString();
            int score = Character.getNumericValue(
                    reponseJson.charAt(reponseJson.indexOf("score") + 7)
            );

            // --- Conversion du score Zxcvbn (0-4) en NiveauForce ---
            switch (score) {
                case 0: return NiveauForce.TRES_FAIBLE;
                case 1: return NiveauForce.FAIBLE;
                case 2: return NiveauForce.MOYEN;
                case 3: return NiveauForce.FORT;
                case 4: return NiveauForce.TRES_FORT;
                default: return NiveauForce.MOYEN;
            }

        } catch (Exception e) {
            // En cas d'erreur de connexion, retourne MOYEN par défaut
            System.err.println("Erreur connexion Docker : " + e.getMessage());
            return NiveauForce.MOYEN;
        }
    }
}