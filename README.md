# TP4 - Enrichissement du paiement e-commerce avec le patron Decorator

Ce dossier englobe le contenu du TP4 du module *Design Patterns*.
Il contient l'énoncé, les diagrammes, un squelette de code à compléter et un fichier Markdown à rendre.

## Objectif pedagogique

Ajouter des responsabilités autour d'une passerelle de paiement sans modifier les adaptateurs existants.

## Position dans la sequence

- `TP1` : `Adapter` a permis de rendre les APIs externes compatibles avec `PaymentGateway`.
- `TP2` : `Strategy` a permis de choisir une passerelle selon un critère.
- `TP3` : `Factory Method` a permis de créer les catalogues de passerelles selon la boutique.
- `TP4` : `Decorator` permet d'enrichir ces passerelles avec logging, frais, anti-fraude, cashback ou retry.

## Travail attendu

- Completer les décorateurs du package `decorator/`.
- Composer les décorateurs dans les boutiques du package `factory/`.
- Vérifier que `CheckoutService` reste indépendant des décorateurs concrets.
- Completer `REPONSES_TP4_A_RENDRE.md`.
- Lancer le test autonome fourni.

## Exécution avec Maven

Compilation :

```bash
mvn compile
```

Exécution :

```bash
mvn exec:java
```

Test autonome :

```bash
mvn test-compile exec:java -Dexec.mainClass=ma.ensias.ecommerce.tp4.DecoratorCompositionTest -Dexec.classpathScope=test
```

## Pré-requis logiciels

- JDK 17 ou plus recent
- Maven 3.9 ou plus recent
