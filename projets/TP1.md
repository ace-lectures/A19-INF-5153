# Projet "Jeu de Poker"

  - Auteur : Sébastien Mosser
  - Version : 2019.09
  - Durée : 16 heures sur 2,5 semaines (2 ateliers de 2h + travail personnel de 12h)

**Lisez bien le sujet jusqu'au bout avant de commencer à travailler sur le projet.**

## Objectifs du projet

  1. Prendre en main les outils utilisés en support du cours;
  2. Analyser un code légataire en vue de le faire évoluer;
  3. Transformer ce code en véritable application orientée objet;
  4. Faire évoluer un code objet en respectant dans la mesure du possible :
      1. les principes `S`, `O` et `L` de `SOLID`;
      2. la Loi de Demeter (principe de connaissance minimale);
      3. les principes objet de faible couplage / forte cohésion.
  5. Décrire une application orientée objet avec le langage UML (diagrammes d'_objets_, de _séquences_ et de _classes_).

Les ateliers sont des temps où l'équipe enseignante (prof + démo) est présente pour vous donner de la rétro-action sur votre projet. Il est vital de profiter de ce temps de travail prévu dans votre emploi du temps pour améliorer vos projets avant la remise finale.

### Consignes 

  1. Votre projet doit être sur GitHub à 23:50 (heure de Montréal) le jour de la date de remise, où un script le récupèrera automatiquement. 
  2. Vous devez répondre en français aux quatre (4) questions posées dans ce sujet dans le fichier `README.md`
  3. Vous devez enregistrer votre code dans Git sous votre vrai nom, et lier votre compte _Classroom_ à votre code permanent. 
  4. Vos trois modèles (`object_diagram.xxx`, `sequence_diagram.xxx` et `class_diagram.xxx`) doivent se trouver dans le repertoire `_models`, au format `PUML`, `PDF` ou `PNG`.  
  5. Votre code doit compiler et tester (sans échouer) en lançant la commande `mvn clean package` à la racine de votre projet
  6. Votre code doit se lancer depuis la ligne de commande, toujours depuis la racine:
      * `mvn exec:java` pour la version demandant de saisir les mains au clavier
      * `mvn -q exec:java -Dexec.args="'AA BB CC DD EE' '1C 4H QD 9D 3S'"` 

**Toute remise ne respectant pas ces consignes ne sera pas évaluée et obtiendra la note de zéro (0).**

### Environnement nécessaire et outillage

  1. Java (version 8 ou supérieure)
  2. Maven (version 3.x)
  3. Git (version 2.x)
  4. Un environment de développement récent (_p.-ex_, IntelliJ)
  5. Un outil de modélisation permettant de réaliser des diagrammes UML conformes (_p.-ex._ PlantUML, Visual Paradigm)
  6. Un compte GitHub

## Spécifications

On s'intéresse à une version _simplifiée_ d'un arbitre de poker, qui permet de décider parmi deux mains de poker laquelle des deux est la gagnante. Il s'agit d'un exercice classique de programmation, voir _p.-ex_ [Coding Dojos](http://codingdojo.org/kata/PokerHands/).

  - Une carte est encodée sous la forme d'un doublet de caractères majuscule `XY` où le premier caractère représente la valeur de la carte (dans `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `T` pour 10, `J` pour valet, `Q` pour reine et `K` pour roi) et le second sa couleur (dans `C` pour trèfle, `H` pour coeur, `D` pour carreau et `S` pour pique). Ainsi `TD` représente le _dix de carreaux_, et `QH` la _reine de coeur_.
  - Une main de poker est encodée sous la forme d'une chaine de caractères qui contient 5 cartes, séparées par des espaces (_p.-ex._, `1C 4H KD 9D 3S`)
  - Le programme attend deux mains données sur la ligne de commande pour le joueur `J1` et le joueur `J2`. Il donne en réponse quelle est la main gagnante parmi les deux.
  - Si les mains ne sont pas données sur la ligne de commande, le programme demande de les saisir au clavier.
  - Si une main saisie est erronée, le programme termine.
  - La réponse du programme est `J1` si le premier joueur gagne, `J2` si le second gagne, ou `TIE` si les deux mains sont à égalité.

Nous avons fait quelques simplifications dans le jeu de poker pour ce premier projet : 

  - Seuls les combinaisons de cartes suivantes sont gérées pour le moment : _plus haute carte_ (`HIGHEST_CARD`), _paire_ (`PAIR`) et _couleur_ (`FLUSH`).
  - L'as (_c.à.d._ les cartes de valeur `1`) est considérée comme la carte la plus faible du jeu.
  - On ne gère pas la triche, _p.-ex._ deux mains qui contiendrait la même carte. 

Rappel des règles du Poker : 

  - La _couleur_ (toutes les cartes d'une même couleur) bat la _paire_ (deux cartes de même valeur), qui bat la _plus haute carte_

## Étape 1. Récupération du code légataire

### Connexion à GitHub Classroom

  * Si ce n'est pas déjà le cas, créez un compte sur la plateforme [GitHub](http://github.com/)
  * Rendez vous sur la page du travail : 
      * [Travail P1 : Jeu de Poker](https://classroom.github.com/a/_EFWjuKn)
  * Acceptez le travail, et identifiez vous dans la liste en choisissant votre code permanent (**attention à ne pas prendre le code d'un de vos collègue**)
  * Vous obtenez un dépôt de code chez GitHub dédié à ce travail, dans lequel vous pouvez travailler.

Vous pouvez maintenant travailler sur ce projet ! 

```
mosser@lucifer ~$ git clone .../P1-JeuDePoker.git
mosser@lucifer ~$ cd P1-JeuDePoker
mosser@lucifer P1-JeuDePoker$
```

N'oubliez pas de configurer Git pour que l'on puisse faire le lien entre votre travail sur ce projet et votre identité à l'Université. Pour vous en assurer, lancez les commandes suivantes dans le repertoire du projet, **sur toutes les machines utilisées pour travailler sur le projet**. Si vous ne voulez pas répéter cette opération a chaque fois que vous réalisez un nouveau projet, ajoutez l'option `--global` pour dire à git que ces informations sont globales à votre système et non locale à ce projet en particulier.

```
mosser@lucifer P1-JeuDePoker$ git config user.email "mon_addresse@courriel.ca"
mosser@lucifer P1-JeuDePoker$ git config user.name  "MonVraiPrénom MonVraiNom"
```

### Compilation et Exécution

Si votre machine dispose de Java 8+ et de maven 3.x, vous pouvez maintenant compiler le code avance Maven

```
mosser@lucifer P1-JeuDePoker$ mvn clean package
...
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
...
mosser@lucifer P1-JeuDePoker$
```

Pour executer le code, vous pouvez lancer la commande suivante, qui va utiliser la classe `Main` fournie pour lancer le projet : 

```
mosser@lucifer P1-JeuDePoker$ mvn -q exec:java
p1? 1H 4H 6H JH 8H
p2? 2C 2D 5H TS KS
Result: P1
mosser@lucifer P1-JeuDePoker$ mvn -q exec:java \
                                  -Dexec.args="'1H 4H 6H JH 8H' '2C 2D 5H TS KS'"
Result: P1
mosser@lucifer P1-JeuDePoker$
```
Rendu à cette étape, vous êtes prêt à contribuer sur le projet !

### Bug possibles

  - Si vous rencontrez des _warnings_ a l'exécution (_p.-ex._, `IllegalReflectiveAccessException`), il s'agit d'un problème de compatibilité entre votre installation de Java et votre version de Maven et votre version de la bibliothèque `guice`. Vous pouvez réparer en harmonisant vos versions de logiciels, mais sinon ce n'est pas grave pour l'exécution du TP
  - Si votre version de Maven n'a pas été compilée avec le même JDK que le votre, il est possible que le prompt `p1?`. `p2?` s'affiche après la saisie des mains en mode intercatif. Pour exécuter le programme sans l'aide de maven (et donc fixer temporairement le problème), vous pouvez utiliser la commande : `java -cp ./target/poker-1.0-SNAPSHOT.jar uqam.inf5153.poker.Main`


## Étape 2 : Analyse du Code Légataire

Le code source légataire fourni est stocké dans le repertoire `src/main/java`, en respect des conventions Maven. La classe `uqam.inf5153.poker.Main` contient l'implémentation de départ d'un programme répondant (on l'espère) aux spécifications.

### Question 1: Évolution du code légataire

Le client envisage deux opérations de maintenance évolutive sur ce projet : 

  1. Pouvoir detecter plus de combinaisons que les trois combinaisons actuelles (_p.-ex._ ajouter le brelan et la suite);
  2. Pouvoir arbitrer des parties à plus de deux joueurs.

Sans l'implémenter, expliquez comment le code devrait être modifié pour prendre en compte ces deux évolutions.
  
### Question 2 : Analyse des défauts du code légataire
  
Le code fourni est un amalgame des pires atrocités remises par des étudiants dans le cadre du cours de conception sur les trois (3) dernières années.

Analysez ce code en fonction de votre connaissance de la programmation objet et des principes de génie logiciels vu dans le cours, et dressez une liste des défauts qu'il contient, en donnant des exemples des situations qui sont problématique dans le code. Si un défaut est répété à plusieurs endroits dans le code, n'en donnez pas la liste exhaustive, ce n'est pas utile, un exemple suffit.

## Étape 3 : Réusinage en code objet  

Le code fourni est trop endetté à date. Nous prenons la décision d'effectuer un _réusinage_ du code pour le transformer en application orientée objet et aini mettre en oeuvre les demandes du client de la partie précédente.

En restant dans le paquetage `uqam.inf5153.poker`, identifiez un ensemble de concepts (_c.à.d_ des classes, des interfaces, des énumérés) qui permettront de travailler au bon niveau d'abstraction de la logique d'affaire du projet pour définir un vrai système d'arbitrage et implémenter les besoins cités précédemment. 

Typiquement on ne devrait plus travailler sur des tableaux de tableaux de caractères pour représenter les cartes des joueurs, mais sur des mains (_p.-ex._ une classe `Hand`), des cartes (_p.-ex._ une classe `Card`), _etc_. Lors du réusinage, vous êtes libre d'utiliser un vocabulaire francophone ou anglophone pour nommer les concepts que vous décidez d'intégrer dans votre conception, mais ne mélangez pas les deux langues. 

Votre système d'arbitrage doit respecter les propriétés architecturale suivante : 

  1. On doit pouvoir ajouter de nouvelles combinaisons de cartes;
  2. On doit pouvoir passer de deux (2) joueurs à _n_ joueurs
  3. On doit afficher en plus du résultat une explication de celui-ci (_p.-ex._, "_couleur à trèfle pour J1 bat une paire de rois pour J2_".
  4. Un nouveau développeur doit pouvoir intégrer l'équipe uniquement en lisant le code et les modèles (_c.à.d_ que le code et les modèles doivent pouvoir servir de documentation technique suffisante à la compréhension du projet).

Développez le code java (logique d'affaire + tests unitaires) mettant en oeuvre votre système d'arbitrage. Vous documenterez votre code avec trois modèles, dans le repertoire `_models`. 

  1. Un diagramme de classe décrivant les concepts présents dans votre code et leurs relations;
  2. Un diagramme de séquence représentant la réalisation d'un arbitrage depuis l'invocation de la classe `Main` par l'utilisateur jusqu'à l'affichage de la réponse à celui-ci;
  3. Un diagramme d'objet montrant une instantiation possible de vos concepts lors d'un arbitrage.

**Dans vos modèles, ne représentez que les éléments pertinents pour la compréhension de votre projet**, les détails techniques (_p.-ex-_ accesseurs et modificateurs dans le diagramme de classe, appels de fonctions interne _helper_ dans le diagramme de séquence) sont a laisser dans le code. 

**Astuce**: _Si vous utilisez PlantUML, les squelettes des diagrammes demandés sont déjà présent, et vous pouvez utiliser dans un environnement unix la commande `make` à la racine du projet pour lancer la compilation des fichiers et la production des diagrammes._

## Étape 4 : Analyse du code objet

Maintenant que le code est réusiné et que l'évolution fonctionnelle à été mise en oeuvre, il convient de prendre du recul sur le code développé pour être capable de l'analyser.

### Question 3: Justification des choix de conceptions

  1. Justifiez le choix des concepts que vous avez identifié dans votre modèle de conception, particulièrement en expliquant comment votre modèle respecte les propriétés architecturale demandées.
  2. Expliquez comment votre conception actuelle du projet respecte les principes de responsabilité unique (`S` de `SOLID`), de substitution de Liskov (`L` de `SOLID`), et de connaissance minimale (_c.à.d._ la Loi de Demeter).
  3. Si vous pensez que votre conception souffre de certaines faiblesses, indiquez les, et expliquez pourquoi ce sont des faiblesses.
 
  
### Question 4 : Évolution du code objet
  
Expliquez en quelques lignes comment l'ajout du Brelan et du multi-joueur a pu être fait dans votre projet en respectant le principe de conception `O` de `SOLID`. Ou, le cas échéant, pourquoi il n'a pas été possible de suivre ce principe dans votre conception actuelle du projet.
  
## Grille d'évaluation

Les trois éléments suivants sont évalués : _(i)_ vos réponses aux questions (pour 35%), _(ii)_ vos modèles de conception (pour 35%), et enfin _(iii)_ votre code Java et les tests associés (pour 30%). La qualité du français dans les réponses aux questions est prise en compte (avec un malus maximal de 25%).

| Élement      | Critère d'évaluation                      | Note (/100) |
| :---:        | :---                                      | :---: |
|  _Questions_ | (#1) Évolution du code légataire           | /5    |
|              | (#2) Analyse des défauts du code légataire | /10   |
|              | (#3) Justification des choix de conception | /15   |
|              | (#4) Évolution du code objet               | /5    |
|  _Modèles_   | Justesse & Pertinence de la conception    | /15   |
|              | Cohérence inter-modèles                   | /5    |
|              | Respect des principes de conception       | /15   |
|  _Code_      | Qualité du code Java et du dépôt Git      | /10   |
|              | Cohérence du code avec les modèles        | /10   | 
|              | Qualité des tests                         | /10   | 


---
**Mise à jour le 8 septembre: Les bonus ont tous été distribués**.

_~Si vous êtes arrivé à ce stade du document, c'est que vous l'avez lu en entier, comme demandé dans la consigne au tout début du sujet. Envoyez en message privé au professeur sur le Slack une photo d'un animal mignon (p.-ex. une loutre, un chaton, un chiot, un capybara) pour en témoigner. Les cinq (5) premiers étudiants à remplir cette condition bénéficieront de cinq (5) points bonus sur l'évaluation de ce projet (la note maximale reste de 100)~_. 

---
