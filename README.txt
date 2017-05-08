===========================================================================
test JUnit
===========================================================================
Pour choisir l'ordre d'execution des tests nous avons ajouter l'annotation: 
"@FixMethodOrder(MethodSorters.NAME_ASCENDING)"

test1Connect:
Connecte un utilisateur pour s'assurer que la fenetre affichant les contacts
se lance et que le son de connection c'est bien exécuté.

test2AddUser:
Ajoute un utilisateur pour s'assurer qu'il se retrouve dans l'IHM.

test3GetCurrentUserPseudo:
Verifie que l'utilisateur actuel est celui renseigné à la connection.

test4DeliverMessage:
Simule l'envoie d'un message pour controler qu'il s'affiche bien dans la boite
de dialogue.

test5Disconnect:
Verifie que l'on envoie un message contenant"bye" lorsque l'on se deconnecte.