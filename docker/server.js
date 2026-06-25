const http = require('http');
const zxcvbn = require('zxcvbn');

// Serveur HTTP qui écoute sur le port 3000
const server = http.createServer((req, res) => {

    // On accepte uniquement les requêtes POST sur /analyse
    if (req.method === 'POST' && req.url === '/analyse') {
        let body = '';

        // Récupère les données envoyées
        req.on('data', chunk => { body += chunk.toString(); });

        req.on('end', () => {
            try {
                // Extrait le mot de passe du JSON reçu
                const { password } = JSON.parse(body);

                // Analyse avec Zxcvbn
                const result = zxcvbn(password);

                // Retourne le score (0 à 4) en JSON
                res.writeHead(200, { 'Content-Type': 'application/json' });
                res.end(JSON.stringify({
                    score: result.score,
                    feedback: result.feedback.warning
                }));

            } catch (e) {
                res.writeHead(400);
                res.end('Erreur : mot de passe invalide');
            }
        });

    } else {
        res.writeHead(404);
        res.end('Route introuvable');
    }
});

server.listen(3000, () => {
    console.log('Serveur Zxcvbn démarré sur le port 3000');
});