# Application de Gestion de Bibliothèque

## Objectifs

Cette application de gestion de bibliothèque a été améliorée pour inclure une base de données relationnelle et des fonctionnalités avancées. Les objectifs d'apprentissage incluent :

- Approfondir les concepts de POO en Java (polymorphisme, héritage)
- Concevoir et implémenter une base de données relationnelle simple
- Appliquer les principes de conception logicielle
- Maîtriser la création et l'interprétation de diagrammes UML
- Utiliser des outils de gestion de projet

## Structure de l'Application

### Couche de Présentation

- **Interface Console Améliorée (ConsoleUI)**
  - Menu amélioré pour la gestion des documents et des utilisateurs
  - Gestion des entrées utilisateur avec validation

### Couche de Métier

- **Hiérarchie de Documents**
  - `Document` (classe abstraite)
  - `Livre` (sous-classe)
  - `Magazine` (sous-classe)
  - `JournalScientifique` (sous-classe, attributs : domaineRecherche)
  - `ThèseUniversitaire` (sous-classe, attributs : université, domaine)

- **Classe `Bibliotheque`**
  - Gestion des documents

- **Interfaces**
  - `Empruntable` (méthodes : `emprunter()`, `retourner()`)
  - `Réservable` (méthodes : `réserver()`, `annulerRéservation()`)

### Couche de Persistance

- **DAO (Data Access Objects)**
  - Gestion des opérations CRUD sur les documents et les utilisateurs
  - Connexion et opérations sur la base de données PostgreSQL
  - Utilisation du pattern DAO et Singleton

### Couche Utilitaire

- **Classe `DateUtils`**
  - Manipulation avancée des dates

- **Classe `InputValidator`**
  - Validation améliorée des entrées utilisateur

## Fonctionnalités Principales

### Gestion des Documents

- **CRUD pour tous les types de documents**
  - Ajouter, mettre à jour, afficher et supprimer des documents

- **Recherche Avancée**
  - Recherche par titre, auteur, etc.

### Gestion des Utilisateurs

- **CRUD pour les utilisateurs**
  - Ajouter, mettre à jour, afficher et supprimer des utilisateurs

- **Gestion des Droits d'Emprunt**
  - Gestion des droits d'emprunt en fonction du type d'utilisateur (Étudiant, Professeur)

### Gestion des Emprunts

- **Emprunter et Retourner des Documents**
  - Fonctionnalités pour emprunter et retourner des documents

### Gestion des Réservations

- **Réserver un Document**
  - Réservation de documents

- **Annuler une Réservation**
  - Annulation de réservations

## Spécifications Techniques

- **Java 8**
  - Utilisation des Reference Methods
  - Gestion des valeurs nulles avec Optionals
  - API Java Time pour la gestion avancée des dates
  - API Stream pour le traitement des collections
  - Interfaces fonctionnelles et expressions lambda

- **Base de Données**
  - PostgreSQL
  - Conception d'un schéma de base de données relationnel
  - Utilisation du pilote JDBC pour la connexion et les opérations sur la base de données
  - Implémentation de requêtes SQL adaptées à PostgreSQL

- **Optimisation**
  - Utilisation de Hashmaps pour la recherche de documents
  - Gestion efficace de la mémoire et des ressources (try-with-resources pour les connexions à PostgreSQL)

## Contraintes

- Utilisation du pattern DAO pour l'accès aux données
- Implémentation du design pattern Singleton
- Utilisation efficace du polymorphisme dans la hiérarchie de classes

## Gestion de Projet

- Utilisation avancée de Git pour la gestion des branches
- Gestion des tâches dans JIRA (backlog, sprint, user stories)

## Modélisation

- **Diagramme de Cas d'Utilisation**
  - Élaboré pour les principales fonctionnalités

- **Diagramme de Classes UML**
  - Mis à jour pour refléter la nouvelle structure de l'application

## Installation et Exécution

1. **Cloner le Référentiel**
   ```bash
   git clone [https://github.com/votre-repo/bibliotheque.git](https://github.com/JavaAura/ABDELLAH_TALEMSI_s1_b2_gestionBiblio.V2
