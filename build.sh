#!/bin/bash

# Encodage UTF-8
export LC_ALL=en_US.UTF-8

# Nettoyage des anciens fichiers
rm -rf build
rm -f myframework.jar

# Création du dossier build
mkdir build

# Recherche de tous les fichiers .java dans le dossier src
find src -name "*.java" > sources.txt

# Compilation
javac -encoding UTF-8 -d build -cp "lib/*" @sources.txt

# Création du JAR
jar cvf myframework.jar -C build .

# Nettoyage
rm sources.txt

echo "✅ myframework.jar généré avec succès !"