main:    STRO    strB,d      ;afficher la ligne principale
         STRO    strB2,d     ;afficher les trois tirets  de gauche
         STRO    strB3,d     ;afficher le message de Bienvenue
         STRO    strB2,d     ;afficher les trois tirets de droite
         STRO    strB,d      ;  ; afficher la ligne delimiteur principale
         STRO    str1,d      ;  ;afficher le message de sollicitation pour la saisie au clavier
         DECI    nbrMan,d    ;entrer au clavier un entier qui represente le nombre de manche
         LDA     nbrMan,d    ;charger le nombre de manche dans la mémoire.
         STA     reste,d     ;stocker l'information de l'accumulateur dans l'espace de stockage
         DECO    reste,d     ;afficher la valeur du reste
do:      LDA     reste,d     ; do {
         SUBA    2,i         ;soustraire la valeur de 2 dans la boucle
         STA     reste,d     
while:   LDA     deux,d       ; while (reste > = 2 )
         CPA     reste,d     
         BRLT    do               
         STOP                
nbrMan:  .WORD   0           ;nombre de manche restante à joueer
reste:   .WORD   0           ;reste de la variable  assigné
deux:    .WORD   2           ;la valeur de la constante 2 (parité)
strB:    .ASCII  "\n---------------------------------------------\n\x00"
strB2:   .ASCII  "---\x00"   
strB3:   .ASCII  "Bienvenue au jeu de roche-papier-ciseau\x00"
str1:    .ASCII  "\ncombien de manches voulez vous jouer?\n\x00"
         .END                  