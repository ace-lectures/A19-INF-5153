# Projet _"Island"_

  - Auteur : Sébastien Mosser
  - Version : 2019.09
  - Durée : 11 semaines (82h de travail : travail personnel de 66h + 8 ateliers encadrés de 2h)

**Lisez bien le sujet jusqu'au bout avant de commencer à travailler sur le projet.**

## Objectifs du projet

### Objectifs Client

Votre client est Ephron Vestrit, représentant la _Confrérie des Marchands_ de la cité-état de Terrilville. 

La confrérie fait commerce des ressources collectées sur des territoires avoisinant la Cité portuaire, et fait face à des défis de diversification pour rester compétitive faces aux marchands des royaumes alentours. Il devient nécessaire d'aller explorer des îles de plus en plus lointaine dans la _Baie des Pirates_ et d'en connaitre les ressources avant de lancer une expédition pour les collecter. Ces expéditions sont risquées, la baie, ses îles, ses hautfonds et ses courants étant mal cartographiée à date et portant son nom pour une bonne raison. 

Visionnaire, Ephron propose d'utiliser un drone aérien pour cartographier les îles en évitant de rencontrer des pirates, avant de lancer des expéditions ciblées sur les îles intéressantes uniquement. Il a obtenu de la confrérie l'achat d'un drone et un financement pour 11 semaines de travail, qui permettra de prototyper un logiciel de cartographie pour en démontrer les capacités.

Votre travail est de concevoir ce logiciel de cartographie, et de préparer un prototype démontrable à la confrérie. 

### Objectifs Pédagogiques

  1. Concevoir et développer un logiciel de grande envergure
  2. Gestion de l'incertitude dans un contexte d'exécution inconnu
  3. Conception incrémentale
  4. Livraison régulière de valeur à un client

Les ateliers du mardi matin sont des temps privilégiés où l'équipe enseignante (prof + démo) est présente pour vous donner de la rétroaction sur votre projet. 

Il est crucial de profiter de ce temps de travail prévu dans votre emploi du temps pour améliorer vos projets avant les remises.


### Environnement nécessaire et outillage

  1. Java (version 8 ou supérieure)
  2. Maven (version 3.x)
  3. Git (version 2.x)
  4. Un environment de développement récent (_p.-ex_, IntelliJ)
  5. Un outil de modélisation permettant de réaliser des diagrammes UML conformes (_p.-ex._ PlantUML, Visual Paradigm)
  6. Un compte GitHub


## Déroulement du Projet

  * Le code de démarrage est présent sur _Classroom_ : 
    * [Travail P2 :Island](https://classroom.github.com/g/Vy6fdt6q)
  * Acceptez le travail et rejoignez une équipe existante (ou créez une équipe si la vôtre n'existe pas).

Avertissement : Les métadonnées de Git sont utilisées pour évaluer votre participation au projet. N'oubliez pas de configurer Git pour que l'on puisse faire le lien entre votre travail sur ce projet et votre identité à l'Université. Pour vous en assurer, lancez les commandes suivantes dans le répertoire du projet, **sur toutes les machines utilisées pour travailler sur le projet**. Si vous ne voulez pas répéter cette opération à chaque fois que vous réalisez un nouveau projet, ajoutez l'option `--global` pour dire à git que ces informations sont globales à votre système et non locale à ce projet en particulier.

```
mosser@lucifer P1-JeuDePoker$ git config user.email "mon_addresse@courriel.ca"
mosser@lucifer P1-JeuDePoker$ git config user.name  "MonVraiPrénom MonVraiNom"
```

### Livraison hebdomadaire & Championnat

Votre client attend de votre équipe-projet une livraison hebdomadaire, le mercredi à 18h. Ainsi, *chaque semaine* (de la semaine 40 à la semaine 50 inclusivement), votre client doit trouver sur votre dépôt Git : 

  1. Une version du code compilable (`mvn clean package`) et testée;
  2. Un document de suivi de projet à jour (fichier `journal.md`) indiquant les fonctionnalités présentes dans la version livrée;
  3. Des modèles de conception _à jour_ au format `PDF`, `PNG` ou `PUML` dans le répertoire `_models`.

Chaque semaine, votre projet sera automatiquement récupéré et exécuté sur une île inconnue. Vous recevrez par la suite un rapport d'exécution indiquant les performances de votre projet sur la nouvelle île. Les fichiers permettant d'ajouter l'île à la collection d'île existantes (par exemple pour faire de la non-régression) seront aussi mis à disposition.

### Phases de développement & Présentations techniques

  - La phase 1 commence en semaine 39, et termine par une présentation technique en semaine 43.
  - La phase 2 commence en semaine 43, termine en semaine 50, avec une présentation technique en semaine 49.

Les présentations consistent en un entretien technique de 20 minutes avec le professeur, sur rendez-vous au local `PK-4370`. La présentation requiert la présence de la totalité de l'équipe, et fait partie de l'évaluation du projet. Il s'agit pour vous d'y défendre vos choix de conception et de mise en œuvre dans le projet. 

**Il n'y a pas de diaporama à préparer**. Vous devez simplement venir au rendez-vous avec un ordinateur, prêt à montrer le code dans votre IDE et les modèles (ne perdez pas de temps à démarrer l'IDE ou visualParadigm pendant l'entretien, préparez votre session avant). Prévoyez suffisamment de batterie pour tenir la durée de l'entretien.

Vous présenterez pendant ~10 minutes votre conception et le code associé. Il est important de faire les bons choix de présentation durant cet entretien, pour mettre en valeur les points durs et vous concentrer sur l'essentiel. Il y aura ~10 minutes de question du professeur à l'équipe sur les choix de conceptions fait et le respect des principes de conceptions. 

**Important** : l'entretien consiste en ~10 minutes de présentation et ~10 minutes de questions, mais ce ne sont pas deux étapes séparées de la présentation. Il s'agit d'une discussion, et on peut alterner entre présentation de l'équipe et question. C'est le professeur qui s'assurera que le temps est équitablement réparti entre les deux parties de l'entretien.

### Répartition du travail

Chaque étudiant de l'équipe doit : 

  1. Participer à la conception du projet en étant capable d'expliquer lors de présentation les choix de conceptions effectués dans les modèles;
  2. Participer à l'implémentation du code du projet (et de ses tests).

**Typiquement un étudiant ayant passé sa session à dessiner des modèles UML sans aucune participation au code du projet se verra attribuer la note de zéro (0).**

## Spécifications

L'objectif du projet est de cartographier une île. Pour cela, vous pilotez un drone aérien à l'aide d'un protocole de contrôle reposant sur des ordres et réponses encodés dans des documents `JSON`.

D'un point de vue fonctionnel, le client souhaite poursuivre deux objectifs, à des niveaux de précision différents : 

  0. Exigences technique : **Sureté de fonctionnement**
      1. Le drone ne doit pas lever d'exception non attrapée lors de l'exploration;
      2. Le drone ne doit pas sortir de la carte;
      3. Le drone doit retourner à Terrilville avant d'avoir consommé toute sa batterie.
  1. Objectif #1 (_Obj.1_) : **Localisation de l'île et des sites d'intérêt**;
      1. _Obj.1.1_ : Localiser une crique permettant de débarquer sur l'île
      2. _Obj.1.2_ : Localiser le site d'urgence
      3. _Obj.1.3_ : Localiser toutes les criques permettant de débarquer sur l'île
      4. _Obj.1.4_ : Localiser la crique la plus proche du site d'urgence
  2. Objectif #2 (_Obj.2_) : **Cartographie des ressources**
      1. _Obj.2.1_ : Lister une ressource présente sur l'île
      2. _Obj.2.2_ : Lister toutes les ressources présente sur l'île
      3. _Obj.2.3_ : Approximer la surface de l'île par rapport à celle de l'océan
      4. _Obj.2.4_ : Approximer la surface de d'eau potable (lacs) présente sur l'île

### Exigences de livraison hebdomadaire

Pour chaque livraison hebdomadaire du mercredi, 18h: 

  1. Le code doit compiler et tester sans échec avec la commande `mvn clean package` lancée à la racine de votre dépôt;
  2. La version livrée doit être étiquetée (`git tag`) avec le numéro de la livraison (`version_01`, ..., `version_11`);
  3. Une version à jour de votre journal de bord (dans le fichier `journal.md`), indiquant : 
      1. Le travail effectué dans la semaine (une ligne par membre, avec le nombre d'heures passées sur le projet et les taches effectuées)
      2. Les objectifs que le projet est censé remplir
      3. Votre plan d'action pour la prochaine livraison. 

### Exigences pour le Produit Minimal Viable

  1. Le code doit compiler et tester sans échec avec la commande `mvn clean package` lancée à la racine de votre dépôt;
      * La version livrée doit être étiquetée (`git tag`) avec l'étiquette `version_mvp`;
      * L'exécution dans l'arène de championnat doit impérativement respecter les exigences techniques sur les îles déjà rencontrées
  2. On doit trouver à la racine de votre dépôt un fichier `rapport_X_mvp.pdf` (où `X` est votre lettre d'équipe), de 5 pages maximum (letter, police 11 pt, interligne simple), contenant : 
      * Le nom des participants de l'équipe
      * Les modèles de conceptions pertinent pour illustrer votre projet
      * La justification de leur pertinence
      * Une analyse critique (force / faiblesse) du projet
      * Les évolutions prioritaires qui doivent être mise en place par la suite

Dans le produit minimal viable, les objectifs fonctionnels `Obj.1.1` et `Obj.2.1` doivent être atteints.

**Toute remise ne respectant pas ces consignes ne sera pas évaluée et obtiendra la note de zéro (0).**

### Exigences pour le Produit final

  1. Le code doit compiler et tester sans échec avec la commande `mvn clean package` lancée à la racine de votre dépôt;
      * La version livrée doit être étiquetée (`git tag`) avec l'étiquette `version_finale`;
      * L'exécution dans l'arène de championnat doit impérativement respecter les exigences techniques sur les îles déjà rencontrées
  2. On doit trouver à la racine de votre dépôt un fichier `rapport_X_final.pdf` (où `X` est votre lettre d'équipe), de 10 pages maximum (police 11 pt, interligne simple), contenant : 
      * Le nom des participants de l'équipe
      * Les modèles de conceptions pertinent pour illustrer votre projet
          * La justification de leur pertinence
      * Une analyse critique (force / faiblesse) du projet
      * Les évolutions prioritaires qui doivent être mise en place par la suite

**Toute remise ne respectant pas ces consignes ne sera pas évaluée et obtiendra la note de zéro (0).**

  
## Grille d'évaluation

La grille d'évaluation est la même pour le produit minimal et pour le produit final.

Les trois éléments suivants sont évalués :  _(i)_ vos modèles de conception présentés dans le rapport (pour 35%), _(ii)_ votre code Java et les tests associés (pour 35%), et enfin _(iii)_ votre entretien technique (pour 30%). 

**Avertissement** : Les notes de projet seront _individualisées_ si l'évaluateur constate une différence d'implication entre les membres de l'équipe. Cette individualisation peut aller jusqu'à l'attribution d'un zéro (0) pour un étudiant n'ayant manifestement rien fait dans le projet.

| Élément      | Critère d'évaluation                      | Note (/100) |
| :---:        | :---                                      | :---: |
|  _Rapport_   | Justesse & Pertinence de la conception    | /20   |
|              | Cohérence inter-modèles                   | /5    |
|              | Respect des principes de conception       | /10   |
|  _Code_      | Qualité du code Java et du dépôt Git      | /15   |
|              | Cohérence du code avec les modèles        | /10   | 
|              | Qualité des tests                         | /10   | 
|  _Entretien_ | Clarté de la présentation                 | /5    |
|              | Pertinence des choix de conception        | /15   |
|              | Réponses aux questions                    | /10   |

Informations additionnelles : 

  - La qualité du français dans le rapport est prise en compte (avec un malus maximal de 25%).
  - Vos livraisons hebdomadaires ainsi que les performances de votre projet dans l'arène de championnat ne sont pas évaluées. Ceci étant dit, cela sert d'indicateur pour vous positionner dans la promotion et mesurer votre avancée.

