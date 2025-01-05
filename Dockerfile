# Utiliser une image de base Java
FROM openjdk:17-jdk-slim

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le fichier JAR de l'application dans le conteneur
COPY target/course_management-0.0.1-SNAPSHOT.jar app.jar

# Exposer le port sur lequel l'application écoutera
EXPOSE 9696

# Commande pour démarrer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
