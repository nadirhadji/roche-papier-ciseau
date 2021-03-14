;//Affichage du message d'introduction du programme

msg_b:           stro		strLigne,d  
                 stro		strTiret,d
                 stro		intro,d 
                 stro		strTiret,d 
                 stro		strLigne,d 
				
;//Affichage du message de sollicitation du nombre de manches a jouer

msg_sol:         stro		strSol,d 

;//Sollicitation du nombre de manche a jouer (valeur entière)

entree:          deci		nbManch,d  			;saisir le nombre de manche à jouer.
                 lda		nbManch,d  			;charger la valeur du nombre de manche dans A.
                 sta		reste,d   			;stocker le nombre de manche dans le reste.
                 deco		nbManch,d      		;Afficher le nombre saisie.
				 
;//utilisation d'une boucle pour verifier la parité du nombre de manche

do:              lda		reste,d  			;prendre la valeur du nombre de manche  initialiser en reste  
                 suba		2,i    				;soutraire la valeur de 2
                 sta		reste,d  
                 lda		quotient,d
                 adda		1,i
                 sta		quotient,d
				 
while:           lda		reste,d   			;tant que reste est superieur à 2   
                 cpa		deux,d 
                 brgt		do    
	 

;//travail sur le seuil de victoire

sv:              lda		quotient,d 
                 adda		1,i
                 sta		svict,d 
                     
;///incrémentation de la valeur du nombre de manche s'il est pair

verif:           lda		reste,d   
                 cpa		2,i
                 breq		pair
                 lda		reste,d 
                 cpa		0,i
                 breq		pair
				 

;//si le nombre de manche choisie est 0 marque la fin du programme

                 lda		nbManch,d
                 cpa		0,i
                 breq		sortie    
                 
psImpair:        br			depart

pair:            lda		svict,d
                 adda		1,i
                 sta		svict,d
                 
                 lda		nbManch,d  			;charger le nombre de manche
                 adda		1,i       			;incrementer la valeur 1
                 sta		nbManch,d 
                 br			depart


;//affichage du nombre restant de manche à jouer
         
depart:          stro		strMr,d 
                 deco		nbManch,d 
                 stro		strMr2,d
				 
;//message de sollicitation du joueur 1

j1:              charo		0x000A,i  			;saut de ligne 
                 stro		joueur1,d 
                 stro		msgChoix,d  
                 chari		chrJ1,d   			;entrée au clavier du joueur 1
                 charo      chrJ1,d
                 charo		0x000A,i

;///validation du choix du joueur 1

valideJ1:        lda		chrJ1tp,d      
                 cpa		'r',i
                 breq		j2         
                 lda		chrJ1tp,d 
                 cpa		'p',i 
                 breq		j2 
                 lda		chrJ1tp, d 
                 cpa		'c', i
                 breq		j2
                 br			invalide

				 
;//message d'erreur si le joueur 1 effectue une mauvaise entrée qui met fin directement à la partie

invalide:        stro		msgErr,d
                 br			sortie        		;appel du fin du programme

;//message de sollicitation du joueur 

j2:              stro		joueur2,d
                 stro		msgChoix,d 
                 chari		chrJ2, d  			;entrée au clavier du joueur 2
                 chari		chrJ2, d
                 charo      chrJ2, d
                 charo		0x000A,i
		  
;//validation du choix du joueur 2

valideJ2:        lda		chrJ2tp,d 
                 cpa		'r',i
                 breq		jeu
				 
                 lda		chrJ2tp,d 
                 cpa		'p',i
                 breq		jeu
				 
                 lda		chrJ2tp,d 
                 cpa		'c',i
                 breq		jeu
				 
                 br			invalide
				 

;//logique de jeu et allocation de score au 2 joueurs

jeu:             lda		chrJ1tp,d
                 cpa		chrJ2tp,d
                 breq		mNull
				 
cas1:            lda		chrJ1tp,d
                 cpa		'r',i 
                 breq		cel1
				 
cas2:            lda		chrJ1tp,d
                 cpa		'c',i
                 breq		cel2 
				 
cas3:            lda		chrJ2tp,d
                 cpa		'r',i 
                 breq		del1  
				 
cas4:            lda		chrJ1tp,d 
                 cpa		'p',i 
                 breq		cel3
                 
mNull:           stro		strMnul,d 
                 chari		chrJ1,d
                 br			depart              

vicJ1:           lda		scoreJ1,d
                 adda		1,i
                 sta		scoreJ1,d
                 stro		joueur1,d
                 br			victM
				 
vicJ2:           lda		scoreJ2,d
                 adda		1,i
                 sta		scoreJ2,d
                 stro		joueur2,d
                 br			victM
				 
cel1:            lda		chrJ2tp,d 
                 cpa		'c',i 
                 breq		vicJ1 
                 lda		chrJ2tp,d
                 cpa		'p',i
                 breq		vicJ2 
                 br			cas2   
				 
cel2:            lda		chrJ2tp,d 
                 cpa		'p',i 
                 breq		vicJ1
                 br			cas3
				 
cel3:            lda		chrJ2tp,d 
                 cpa		'r',i 
                 breq		vicJ1
                 lda		chrJ2tp,d 
                 cpa		'c',i 
                 breq		vicJ2
                 
del1:            lda		chrJ1tp,d
                 cpa		'c',i
                 breq		vicJ2
                 lda		chrJ1tp,d
                 cpa		'p',i
                 breq		vicJ1
                 br			cas2
				 

;//victoire remporté par un joueur dans le jeu

victM:           stro		strVmc,d    	 	;message de victoire d'une manche
                 deco		scoreJ1,d 	
                 stro		strT,d    	 	 	;tiret de séparation du score 
                 deco		scoreJ2,d   
				 

;//condition de sortie pour la fin du jeu

finJeu:          lda		nbManch,d
                 suba		1,i 
                 sta		nbManch,d
                 chari		chrJ1,d
                 charo		0x000A,i
                 lda		svict,d
                 cpa		scoreJ1,d
                 breq		gagne
                 lda		svict,d 
                 cpa		scoreJ2,d
                 breq		gagne
                 lda		nbManch,d
                 cpa		0,i 
                 brne		depart
				
				
;//message pour annoncer la fin du jeu en général
 
gagne:           lda		scoreJ1,d   
                 cpa		scoreJ2,d
                 brgt		gagneJ1  
                 charo		'\n',i
                 stro		joueur2,d
                 br			finP
				 
gagneJ1:         charo		0x000A,i
                 stro		joueur1,d
                 br			finP
				 
finP:            stro		strMmc,d 
                 charo		0x000A,i
                 stro		strSfin,d 
                 deco		scoreJ1,d
                 stro		strT,d
                 deco		scoreJ2,d    
                              
sortie:          stop

;///////////////////////////////// Les variables /////////////////////////////

nbManch:         .word 0 			;le nombre de manches à jouer
reste:           .word 0 			;reste de la variable assigné
quotient:        .word 0 			;le quotient de la victoire
deux:            .word 2 			;initialisation de la constante de parité
zero:            .word 0  			;initialisation de la variable zero
imp:             .word 0  			;initialisation de la valeur impair
scoreJ1:         .word 0  			;le score du joueur 1
scoreJ2:         .word 0  			;le score du joueur 2
svict:           .word 0  			;le seuil de victoire du match
chrJ1tp:         .byte 0
chrJ1:           .byte 0  			;choix du joueur 1
chrJ2tp:         .byte 0
chrJ2:           .byte 0  			;choix du joueur 2


;///////////////////////////////// Les constantes d'affichage ///////////////////////////

intro:           .ascii " Bienvenue au jeu de roche-papier-ciseau \x00 " 			; Message de présentation
joueur2:         .ascii "JOUEUR 2\x00"   											;nomination du second joueur 
joueur1:         .ascii "JOUEUR 1\x00"   											;nomination du prémier joueur 
msgChoix:        .ascii ", quel est votre choix? [r/p/c]\n\x00" 					;message de sollicitation pour le choix du joueur
msgErr:          .ascii "\nErreur d'entrée! Programme terminé.\x00" 				;message d'erreur pour le choix 
strLigne:        .ascii "\n-----------------------------------------------\n\x00"   ;ligne de décoration
strTiret:        .ascii "---\x00"  													;la pétite ligne de décoration
strT:            .ascii "-\x00"
strSol:          .ascii "\nCombien de manches voulez-vous jouer?\n\x00"  			;Message de sollicitation
strMr:           .ascii "\nil reste \x00"
strMr2:          .ascii " manche(s) à jouer \x00"
strMnul:         .ascii "Manche nulle...\n\x00" 									;Message pour une manche nulle 
strVmc:          .ascii " a gagné cette manche! Score: \x00" 						;Message lorsque un joueur remporte une manche
strMmc:          .ascii "  A GAGNÉ LE MATCH! FELICITATIONS!\x00"  					;message de victoire de la fin du match 
strSfin:         .ascii "SCORE FINAL: \x00"   										;score final de la rencontre  
                 .end