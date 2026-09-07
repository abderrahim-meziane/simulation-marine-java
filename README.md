# 🌊 Simulation Marine — Java POO

Projet universitaire réalisé dans le cadre du cours **UL2IN002 — Introduction à la programmation objet**  
Sorbonne Université | L3 Informatique parcours DANT | Février 2026

---

## 📋 Description

Simulation orientée objet d'un écosystème marin sur un terrain en 2D.  
Des **bateaux** (agents mobiles) interagissent avec des **ressources** (animaux, déchets) placées sur le terrain à chaque étape de la simulation.

### Objectif
Modéliser et observer l'évolution d'un écosystème marin :
- Les sardines se reproduisent (+10%/étape)
- Les requins et dauphins chassent les sardines
- Les bateaux de pêche, touristiques et nettoyeurs agissent en parallèle
- Des statistiques finales sont produites à chaque simulation

---

## 🏗️ Architecture des classes

```
Ressource          (fournie par Sorbonne)
├── Animal_marin   (abstraite)
│   ├── Sardine        → évolue +10%/étape
│   ├── Dauphine       → évolue +5%/étape, chasse, Observable
│   └── Requin         → évolue +2%/étape, chasse, Observable
└── Dechet             → statique, retirée par Netoyeur

Bateau             (abstraite)
├── Peche          → cible la sardine la plus proche
├── Touristique    → cible l'Observable le plus attractif
└── Netoyeur       → cible le déchet le plus proche

Interface : Observable   (Dauphine, Requin)
Exception  : SimulationException (checked)
Utilitaire : SimulationUtils (classe finale, méthodes statiques)
```

**Hiérarchie d'héritage à 3 niveaux :** `Ressource → Animal_marin → Sardine/Dauphine/Requin`

---

## 🧠 Concepts POO appliqués

| Concept | Où |
|---|---|
| Héritage (3 niveaux) | `Ressource → Animal_marin → Sardine` |
| Classe abstraite | `Bateau`, `Animal_marin` |
| Interface | `Observable` |
| Checked Exception | `SimulationException` |
| Polymorphisme | `agir()`, `evoluer()`, `instanceof` |
| Encapsulation | Getters/Setters, attributs privés |
| Classe utilitaire statique | `SimulationUtils` |
| Constructeur par copie | `Dauphine(Dauphine)`, `Dechet(Dechet)` |
| Collections | `ArrayList<Ressource>`, `ArrayList<Bateau>` |

---

## 📁 Structure du projet

```
simulation-marine-java/
├── src/
│   ├── Simulation.java
│   ├── SimulationException.java
│   ├── Observable.java
│   ├── Bateau.java
│   ├── Peche.java
│   ├── Touristique.java
│   ├── Netoyeur.java
│   ├── Animal_marin.java
│   ├── Sardine.java
│   ├── Dauphine.java
│   ├── Requin.java
│   ├── Dechet.java
│   ├── SimulationUtils.java
│   ├── TestSimulation.java
│   └── TestTerrain.java
├── doc/              ← Javadoc générée
├── logs.txt          ← Exemples de simulations
└── README.md
```

> `Terrain.class` et `Ressource.class` sont fournis par Sorbonne Université (non modifiables).

---

## ⚙️ Compilation et exécution

### Prérequis
- Java 17+ (`java -version`)
- Avoir `Terrain.class` et `Ressource.class` dans le même dossier

### Compiler
```bash
cd src/
javac -cp . *.java
```

### Lancer les simulations
```bash
java -cp . TestSimulation
```

### Générer les logs dans un fichier
```bash
java -cp . TestSimulation > logs.txt
```

### Générer la Javadoc
```bash
javadoc -cp . -d ../doc *.java
```

---

## 🖥️ Exemple de sortie

```
=== Tests d'erreurs (exceptions) ===

[ERREUR ATTENDUE] Dimensions du terrain invalides : 0x5
[ERREUR ATTENDUE] Le nombre d etapes doit etre > 0, recu : -1
[ERREUR ATTENDUE] Trop de ressources (10) pour un terrain de 4 cases.

########################################
#  SIMULATION 1 : petite mer          #
########################################
=== Debut de la simulation ===
:------:------:------:------:------:------:------:------:
|Dauphi|Sardin|      |      |      |      |      |      |
:------:------:------:------:------:------:------:------:
|      |      |      |      |      |      |Dauphi|      |
:------:------:------:------:------:------:------:------:

=== Etape 1 ===
Peche en (3,5) peche 5 Sardine(s) (reste 5).
Touristique en (2,7) observe 5 Dauphin(s) en (2,7).
Netoyeur en (4,4) ramasse 1 unite(s) de Plastique.
-- Phase de chasse --
Requin(s) chassent 4 Sardine(s).
Dauphin(s) chassent 2 Sardine(s).

=== Statistiques finales ===
Total sardines pechees   : 23
Total observations       : 25
Total dechets ramasses   : 3
----------------------------------------
  BILAN DES RESSOURCES
  Sardines     : 1
  Dauphins     : 7
  Requins      : 2
  Dechets (cases) : 0
----------------------------------------
```

---

## 👤 Auteur

**Abderrahim MEZIANE**    
[LinkedIn](https://linkedin.com/in/abderrahim-meziane) | [Portfolio](https://abderrahim-meziane.github.io)
