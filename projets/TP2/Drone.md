# Exploration de terrain (Démarrage rapide)

  * Auteur : Sébastien Mosser
  * Version : 2019.09

**Lisez ce protocole jusqu'au bout avant de commencer votre projet. Typiquement la fin de ce document indique comment permettre à votre code de participer au championnat hebdomadaire**.

## Aperçu global de fonctionnement 

Le drone (de type avion) se déplace sur un terrain quadrillé, dont chaque case fait 30m de côté. Les relevés satellite préliminaires ont indiqué qu'une île est présente dans la région d'exploration, mais il n'y a pas d'indication précise sur sa position, à part que la zone d'exploration (qui peut être carrée ou rectangulaire) est à peu près centrée sur l'île. Il n'y a pas non plus d'informations quant à sa taille sur la zone d'exploration.


### Protocole d'exploration

L'exploration du terrain se fait au tour par tour. 

A chaque tour de jeu, le système de contrôle va demander à votre programme (via votre implémentation de l'interface `IExplorerRaid`) la décision d'exploration qui à été prise (en appellant la méthode `takeDecision`), puis vous informera des résultats de son exécution (en appellant la méthode `acknowledgeResults`).

Le premier tour de jeu ainsi que le dernier sont particuliers : 

  - Lors du premier tour de jeu, avant de vous demander votre décision, le système va envoyer des informations contextuelles à votre programme, en appellant la méthode `initialize`. Cela vous permet de récupérer le budget disponible pour mener à bien votre exploration.
  - Lors du dernier tour de jeu, le système demandera à votre programme de rendre son rapport d'exploration, en appellant la méthode `generateReport`. C'est dans ce rapport que vous rendrez compte des objectifs que vous remplissez.

Les messages échangés entre le drone est le système de contrôle utilise le format `JSON`. Le code d'exemple fourni au démarrage donne un exemple de lecture et d'écriture dans ce format en utilisant la bibliothèque de référence `org.json`.

### Interface de contrôle

Le squelette de code que vous avez reçu donne un exemple d'implémentation de l'interface `IExplorerRaid`, qui met en oeuvre un produit minimal et viable. Ce code va lire les informations obtenues à l'initialisation de l'exploration, puis envoyer pour seule décision celle d'arrêter l'exploration. Le rapport contient une description de ce qui s’est passé.

### Terminaison de l'exploration

L'exploration se termine dans les cas suivants : 

  - Cas nominal : vous décidez de mettre fin a l'exploration en utilisant l'action associée lors de votre prise de décision, et vous disposez de suffisament de budget pour rentre au port.
  - Cas d'erreur : 
    - le drone sort de la carte (perte du signal radio)
    - Le drone prend une décision illégale (voir actions ci dessous)
    - le drone dépasse le budget qui lui était alloué
    - le drone lance une exception non traitée dans le programme de contrôle
    - le drone essaye d'accéder au réseau ou aux entrées-sortie du système d'exécution (pas d'écriture sur le disque, ni d'écriture sur `stdout`).

### Information disponible au démarage

  - budget : la quantité de temps / énergie disponible pour mener à bien l'exploration avec le drone. 
  - cap : le cap auquel fait face le drone (_p.-ex._ faire cap à l'Est veut dire rentrer sur la zone d'exploration par le bord gauche de la carte)


## Actions disponibles

Vous disposez des 5 actions suivantes qui peuvent être envoyée au drone. Chaque action consomme du budget (de l'énergie et du temps). Les actions de déplacement sont plus gourmandes et variable que les actions de traitement du signal, car le déplacement est dépendant de facteurs environnementaux imprévisibles (comme le vent ou les turbulences)

### Retour au port (`STOP`)

En envoyant cette action lors de votre prise de décision, vous mettez fin a l'exploration en renvoyant le drone à sa base de départ. C'est une action à coût variable, dont le coût varie en fonction de la distance depuis le point d'entrée sur la carte.

  - Exemple de décision (envoyée via `takeDecision`) : 
      - `{ "action": "stop" }`
  - Exemple de réponse (envoyée à `acknowledgeResults`) : 
      - `{ "cost": 3, "extras": {}, "status": "OK" }`  

Cas d'erreur classique : 

  - Prendre la décision de rentrer au port de manière trop tardive et ne plus avoir assez de budget.

  
### Faire avancer le drone (`FLY`)

Le drone se déplace d'une case en ligne droite, dans la direction à laquelle il fait face. Cette action à un coût variable, qui dépend des vents rencontrés par le drone. 

  - Exemple de décision (envoyée via `takeDecision`) : 
      - `{ "action": "fly" }`
  - Exemple de réponse (envoyée à `acknowledgeResults`) : 
      - `{ "cost": 7, "extras": {}, "status": "OK" }`  

Cas d'erreur classique : 

  - Sortir de la carte en avançant trop loin.

### Changer de cap (`HEADING`)

Le drone peut tourner de 90º à droite ou à gauche et ainsi changer de direction. Cette action déplace le drone (il ne fait pas de sur-place), et coûte en moyenne un peu plus cher qu'un déplacement classique. 

Pour faire tourner le drone, on lui donne en paramètre la direction dans laquelle on souhaite se retrouver en fin de manoeuvre. Considérant un drone avançant vers l'`Est`, pour tourner à gauche on lui demandera un changement de cap au `Nord`, et pour tourner à droite un changement de cap au `Sud`.

  - Exemple de décision (envoyée via `takeDecision`) : 
      - `{ "action": "heading", "parameters": { "direction": "N" } }`
      - `{ "action": "heading", "parameters": { "direction": "S" } }`
  - Exemple de réponse (envoyée à `acknowledgeResults`) : 
      - `{ "cost": 10, "extras": {}, "status": "OK" }`  

Cas d'erreur classique : 

  - Il faut faire attention à ne pas sortir de la carte en changeant de direction trop tard.
  - Demander un changement de cap vers l'arrière, qui ferait faire se retourner le drone (_"backflip"_, entrainant une perte de contrôle).


### Utiliser le sonar (`ECHO`)

Le drone dispose d'un sonar à l'avant, ainsi que sur chacun de ses flancs. Le sonar envoi une onde sonore en ligne droite dans la direction choisie, et analyse la distance parcourue par l'onde avant de rencontrer un obstacle, en l'occurrence de la terre. Si l'onde met trop de temps à revenir, c'est qu'elle ne rencontre aucun obstacle et qu'il n'y a pas de terre dans cette direction.

 - Exemple de décision (envoyée via `takeDecision`) : 
      - `{ "action": "echo", "parameters": { "direction": "E" } }`
  - Exemple de réponse (envoyée à `acknowledgeResults`) : 
      - `{ "cost": 1, "extras": { "range": 2, "found": "GROUND" }, "status": "OK" }`
      - `{ "cost": 1, "extras": { "range": 10, "found": "OUT_OF_RANGE" }, "status": "OK" }`

Dans le premier cas, cela indique que la terre est présente dans deux déplacements en direction de l'`Est`. Dans le second, cela indique qu'il n'y a pas de terre à l'`Est`, et que le drone sortira de la carte dans 10 déplacements dans cette direction.

Cas d'erreur classique : 

  - Demander à utiliser le sonar en direction arrière (erreur de pilotage)


### Utiliser le capteur photo (`SCAN`)

Le drone dispose d'un capteur photo qui lui permet de prendre un cliché de la case présente en dessous. Ce cliché est ensuite traité par des algorithmes de traitement d'image qui permettent d'en extraire de l'information : 

  - Les _biomes_ présents sur la case (_p.-ex._ océan, lac, forêt, mangrove).
  - La présence (ou pas) de points d'intérêts, telles qu'une crique permettant potentiellement le débarquement d'une équipe de marins, ou d'un site suffisamment dégagé pour y évacuer une équipe par hélicoptère.

  
Pour qu'un biome soit détecté par le scanner, il doit occuper au moins 20% de l'espace de la photo.


  - Exemple de décision (envoyée via `takeDecision`) : 
      - `{ "action": "scan" }`
  - Exemple de réponse (envoyée à `acknowledgeResults`) : 
      - `{"cost": 2, "extras": { "biomes": ["GLACIER", "ALPINE"], "creeks": [], "sites": []}, "status": "OK"}`
      - `{"cost": 2, "extras": { "biomes": ["BEACH"], "creeks": ["uuid-554350"], "sites": []}, "status": "OK"}`
      - `{"cost": 2, "extras": { "biomes": ["BEACH"], "creeks": [], "sites": ["uuid-7843"]}, "status": "OK"}`

Dans le premier cas, le scanner détecte un glacier et de la roche montagneuse, et sans surprise aucune crique propice au débarquement ou à l'extraction en hélicoptère. Dans le second cas, le biome principal est une plage, et une crique identifiée par le code `uuid-554350` est présente sur cette case. Dans le dernier cas, le biome principal est encore une plage, et qu'un site d'extraction est présent (identifiant `uuid-7843`). On peut trouver sur la même case plusieurs biomes, plusieurs criques, plusieurs sites, ou toute combinaisons des trois.

Les biomes disponibles dépendent de chacune des îles, leur liste n'est pas fixe.


## Championnat Hebdomadaire

En acceptant le travail sur _Classroom_, vous avez obtenu un dépôt contenant un squelette de code. Pour pouvoir intégrer votre projet dans les championnats hebdomadaire, vous devez vous conformer aux contraintes de participation.

### Contraintes de participation

  1. Vous devez renommer le paquetage dans lequel est le code en remplaçant le `X` par la lettre qui a été attribuée à votre équipe.
  2. Vous devez changer le `artifact_id` dans le fichier `pom.xml`, en remplaçant le `X` par la lettre qui a été attribuée à votre équipe
  3. Vous devez change dans le 
  3. Chaque semaine, vous devez étiqueter la version mise en jeu avec le numéro de la semaine en question (voir sujet détaillé)


### Calcul des points dans le championnat

Chaque semaine, les projets sont classés en fonction de leurs résultats, selon le système de point suivant : 

  - drone rentré à bon port : +5 points
  - criques trouvées : +1 point / crique
  - site d'urgence identifié : +5 points
  - approximation de la surface : variable selon l'erreur d'approximation 
      - de 0% (inclus) à 10% (non-inclus) : +10 points 
      - de 10% (inclus) à 25% (non-inclus): + 5 points
  - approximation de la surface des lacs : variable selon l'erreur d'approximation 
      - de 0% (inclus) à 10% (non-inclus) : +10 points 
      - de 10% (inclus) à 25% (non-inclus): + 5 points
  - Nombre de biomes différents disponible sur l'île : 1 point / biome 

En cas d'égalité, le client départagera les équipes ex-aequo en fonction du budget disponible à la fin de la mission, l'équipe étant rentrée la plus vite prenant l'avantage.

**Si la mission d'exploration se termine en échec, l'équipe perd 20 points dans son classement cumulé, et ne comptabilise aucun gain pour cette mission.**
 
 Vos performances dans le championnat ne sont pas utilisées pour l'évaluation au sens d'une note. Le podium (places 1 à 3) repartira avec un gain symbolique (_p.-ex_ un paquet de jujubes ou de M&Ms) décerné lors de l'examen final.
 

