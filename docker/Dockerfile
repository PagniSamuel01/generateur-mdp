
# Image de base Node.js
FROM node:18-alpine

# Dossier de travail dans le conteneur
WORKDIR /app

# Initialise le projet Node et installe Zxcvbn
RUN npm init -y && npm install zxcvbn

# Copie le serveur
COPY server.js .

# Expose le port 3000
EXPOSE 3000

# Lance le serveur au démarrage du conteneur
CMD ["node", "server.js"]