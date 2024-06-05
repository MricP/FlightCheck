# SAE Semestre 2

## Description

Cette SAE a pour but de créer une interface graphique pour permettre la visualisation du trafic aérien et d'en visualiser les conflit possible. L'interface graphique utilise des algorithmes de coloration pour définir les differents niveau de vols. Le logiciel permettra l'assistanat de la tour de controle pour définir un agenda de vol fonctionnel pour éviter les conflit.

## Structure du Projet

La structure du projet est organisée comme suit :

/projet-java
- Graphe
    - src
        - graphe
            - Aeroport.java
            - ListeAeroport.java
            - ListeVols.java
            - Vol.java
            - Main.java
            - RoutePainter.java
            - Aeroport.java
            - StatistiqueFrame.java
            - CustomWaypointRenderer.java
            - InterfaceIHMSAE.java
            - Aero.png
        - test
            - AeroportTest.java
            - ListeAeroportTest.java
            - ListeVolsTest.java
            - MainTest.java
            - StatistiquesTest.java
            - Vol.java
    - DataTest
        - Fichiers de tests (.csv,.png,.txt) permettant d'utiliser les données
    -  README.md
    - .gitignore
    - JMapViewer.jar

## Prérequis

- Java 8 ou version supérieure

## Installation

1. Clonez le dépôt :
    ```sh
    git clone https://forge.univ-lyon1.fr/p2306053/sae_mathieu_petit_pirrera.git
    cd votre-projet
    ```

## Utilisation

1. Sélectionner votre fichier avec les données d'aéroport :
    En appuyant sur le bouton aéroport, une fenêtre apparaitra et permettra de rentrer un fichier.
2. Sélectionner votre fichier avec les données de vols :
    En appuyant sur le bouton Vols, une fenêtre apparaitra et permettra de rentrer un fichier.
3. Utiliser les boutons de votre choix :
    - Show GraphStream : Permet de montrer le graphe des vols 
    - Draw all lines: Permet de visualiser les vols dans la map
    - statistque : Une fenêtre permettant de voir les informations sur les vols en cours
    - Draw Lines with hour : Dessine tous les avions volant à l'heure donnée
    - Draw lines with couleur: Dessine tous les avions volant sur la même hauteur (couleur)
4. Utiliser des options d'affichage :
    - CheckBox coloration : qui permet de dessiner les vols avec leurs differentes couleurs
    - CheckBox kmax : Sert à savoir les vols qui sont sur kmax (couche maximal)
    - Choix de kmax : avec les flèche choisisser votre kmax
5. Vous pouvez aussi choisir l'algorithme de votre choix 

## Charte Graphique
- `#283C4F` Pour la page d'accueil
- `#FFFFFF` Pour la couleur de la font
- `#007BFF` Pour la couleur du bouton

## Auteurs
    MATHIEU Robin - Développeur
    PETIT Angus - Développeur
    PIRRERA Emric - Développeur