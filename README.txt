===========================================================================
Version JAVA
===========================================================================

La version JAVA de ce projet est la version 8.

===========================================================================
Compiler notre projet
===========================================================================

Vous pouvez importer notre projet sous Eclipse ou Intellij afin de le compiler et lancer le main de controller.java
Vous pouvez aussi lancer ChatBruneauPera.jar qui est disponible dans out/artifact .

===========================================================================
Fonctionnalités
===========================================================================

Les fonctionnalités implémentées sont :
- se connecter au chat avec son pseudo.
- envoi de message à un destinataire connecté.
- envoi d'un fichier à un destinataire connecté.
- avoir une notification visuelle et audio d'un utilisateur qui se connecte / se déconnecte du chat.
- avoir une notification visuelle et audio d'un utilisateur qui vous envoi un message alors que sa fenêtre est fermée.
- être informé de l'envoi / reception d'un fichier.

===========================================================================
test JUnit
===========================================================================

Pour choisir l'ordre d'execution des tests nous avons ajouter l'annotation: 
"@FixMethodOrder(MethodSorters.NAME_ASCENDING)"

test1Connect:
Connecte un utilisateur pour s'assurer que la fenetre affichant les contacts
se lance et que le son de connection c'est bien exécuté.
Notre programme passe le test.

test2AddUser:
Ajoute un utilisateur pour s'assurer qu'il se retrouve dans l'IHM.
Notre programme passe le test.

test3GetCurrentUserPseudo:
Verifie que l'utilisateur actuel est celui renseigné à la connection.
Notre programme passe le test.

test4DeliverMessage:
Simule l'envoie d'un message pour controler qu'il s'affiche bien dans la boite
de dialogue.
Notre programme ne passe pas le test : absence du peroquet lors du test.

test5Disconnect:
Verifie que l'on envoie un message contenant"bye" lorsque l'on se deconnecte.
Notre programme passe le test.

===========================================================================
Compatibilité avec les bînomes
===========================================================================

Notre programme est compatible avec le projet CORFA / CROS ( testé ).
Il est sensé être compatible avec le projet LE LONQUER / BADAOUI et BALBLANC / SMINI (non testé).
