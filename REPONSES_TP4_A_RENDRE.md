# Réponses TP4 - Enrichissement du paiement avec Decorator

## Informations

- Nom et prénom :
- Filière / groupe :
- Date :
- Travail réalisé :
  - [ ] Individuel
  - [ ] Binôme
- Binôme le cas échéant :

## 1. Analyse de la situation après TP3

### Question 1
Dans le TP3, quelles responsabilités sont déjà séparées par `Adapter`, `Strategy` et `Factory Method` ?

Réponse :

### Question 2
Pourquoi ne faut-il pas ajouter directement le logging, les frais ou l'anti-fraude dans les classes `PaypalAdapter`, `BankTransferAdapter` et `MobileWalletAdapter` ?

Réponse :

### Question 3
Qu'est-ce qui varie dans le TP4 ?

Réponse :

## 2. Conception cible avec Decorator

### Question 4
Quelle interface joue le rôle de composant commun ?

Réponse :

### Question 5
Quelle classe joue le rôle de décorateur abstrait ?

Réponse :

### Question 6
Quelles classes jouent le rôle de décorateurs concrets ?

Réponse :

### Question 7
Pourquoi un décorateur peut-il être utilisé par `CheckoutService` comme une passerelle normale ?

Réponse :

### Question 8
Pourquoi l'ordre de composition des décorateurs peut-il avoir une importance ?

Réponse :

## 3. Travail réalisé dans le code

### Décorateurs complétés

- `LoggingPaymentDecorator` :
- `ServiceFeePaymentDecorator` :
- `FraudCheckPaymentDecorator` :
- `CashbackPaymentDecorator` :
- `RetryPaymentDecorator` :

### Compositions réalisées par boutique

- `MoroccoCheckoutApplication` :
- `EuropeCheckoutApplication` :
- `PremiumCheckoutApplication` :
- `AfricaCheckoutApplication` :

## 4. Comparaison des patrons

### Question 9
Quelle difference faites-vous entre `Adapter`, `Strategy`, `Factory Method` et `Decorator` dans ce projet ?

Réponse :

### Question 10
Comment ajouter un nouveau comportement, par exemple `EncryptionPaymentDecorator`, sans modifier `CheckoutService` ?

Réponse :

### Question 11
Quelles sont les limites d'une composition manuelle des décorateurs dans les classes de boutique ?

Réponse :

### Question 12
Pourquoi `Decorator` enrichit-il une passerelle déjà créée, alors que `Factory Method` crée le catalogue de passerelles ?

Réponse :

## 5. Scénarios testes

### Scénario 1
- Boutique :
- Stratégie :
- Décorateurs attendus :
- Passerelle sélectionnée :
- Résultat observé :

### Scénario 2
- Boutique :
- Stratégie :
- Décorateurs attendus :
- Passerelle sélectionnée :
- Résultat observé :

### Scénario 3
- Boutique :
- Stratégie :
- Décorateurs attendus :
- Passerelle sélectionnée :
- Résultat observé :

### Scénario 4 - Extension
- Boutique :
- Stratégie :
- Décorateurs attendus :
- Passerelle sélectionnée :
- Résultat observé :

## 6. Trace console à joindre

Collez ici une trace console obtenue avec :

```bash
mvn exec:java
```

Trace :

```text

```

Collez egalement la trace du mini-test des décorateurs :

```bash
mvn test-compile exec:java -Dexec.mainClass=ma.ensias.ecommerce.tp4.DecoratorCompositionTest -Dexec.classpathScope=test
```

Trace attendue après implémentation :

```text
=== Mini-test Decorator : enrichissement des passerelles ===
- Boutique Maroc -> 3 passerelle(s)
- Boutique Europe -> 1 passerelle(s)
- Boutique Premium -> 2 passerelle(s)
- Boutique Afrique -> 1 passerelle(s)
Validation : les passerelles restent des PaymentGateway enrichies.
Tous les tests Decorator sont passés.
```

## 7. Livrables remis

- [ ] Code source complété dans la structure Maven fournie
- [ ] Ce fichier Markdown complète
- [ ] Trace console ajoutée
- [ ] Test autonome lancé ou scénario équivalent documenté
- [ ] Extension `AfricaCheckoutApplication` complétée

## 8. Checklist avant dépôt

- [ ] Les adaptateurs ne contiennent pas de logique de logging, frais, cashback ou anti-fraude
- [ ] `PaymentGatewayDecorator` implémente `PaymentGateway`
- [ ] Chaque décorateur conserve le contrat de `PaymentGateway`
- [ ] Les boutiques composent les décorateurs autour des passerelles créées
- [ ] `CheckoutService` reste inchangé et ne connaît pas les décorateurs concrets
- [ ] Les stratégies continuent de choisir parmi des `PaymentGateway`
- [ ] Le projet compile sans erreur avec Maven
