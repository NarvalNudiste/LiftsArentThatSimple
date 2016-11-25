TotallySeriousLift 1.0
12/25/2016

Comme écrit dans le mail, je l'upload sur ce git provisoir, désolé ..
Pour l'instant, l'ascenseur a l'air de plutôt marcher et ne rencontre pas de bugs majeurs
Encore quelques soucis dans la prise de décision (Il lui arrive de ne pas prendre les bonnes personnes
lorsque plusieurs personnes sont en train d'attendre)

Reste la doc à écrire, aussi.

Basiquement, l'ascenseur est composé de deux stackedlist de clients.
Les clients possèdent un étage de départ, et un étage auquel ils veulent accéder.
L'une des liste garde référence des clients qui attendent, alors que l'autre contient les clients dans l'asceseur.
La boucle principale marche comme suit et est appelée à chaque étage:

  0 - Les gens qui doivent descendre à cet étage le font

  1 - Les gens qui doivent rentrer à cet étage le font, en fonction de la direction de l'ascenseur
    -- Si il monte, les gens qui montent rentrent, et inversement
    -- Si il est inactif à ce moment là , on respecte la majorité

  2 - On définit la nouvelle direction à prendre pour l'ascenseur suivant les 3 cas possibles :
    -- File d'attente vide et File ascenseur vide -> rien ne se passe
    -- File d'attente occupée, ascenseur vide -> on va chercher les premiers qui ont pressés,
       ou les plus proches si plusieurs personnes attendent
    -- File d'attente vide, Ascenseur plein || Ascenseur plein, file pleine -> on suit la file de l'ascenseur.
  3 - Résolution

La console possède 4 commandes:
0 : Créer un nouvel utilisateur (nom, étage de départ, puis étage désiré)
1 : Résoudre une fois les évenements
2 : Print du contenu des deux files, et du status de l'ascenseur
3 : Résoudre les évenements jusqu'à que les gens soient chez eux

compilation : javac TestClass.java EState.java Client.java SmartElevator.java
utilisation : java (../)narval.TestClass

L'ascenseur est bavard.
Voilà, désolé d'avoir utilisé une méthode du ghetto pour le rendu, mais à force de me connaitre vous découvrirez que c'est souvent le cas ..

Un bon week end !

Guillaume Noguera
inf1-dlm a
