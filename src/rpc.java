/**********************************************************
 *          U Q A M   -   I N F 2 1 7 1
 * Organisation des ordinateurs et assembleur
 *
 * @author Cissé Saliou
 * @author Hadji Nadir
 *
 * Travail pratique 1: Jeu de roche-papier-ciseau
 * Ce programme sert de support a l'implémentation du jeu
 * roche papier ciseau en language d'assembleur PEP-8.
 **********************************************************/
public class rpc {

    public static void main(String[] args) {

        /** Les constantes d'affichage */

        final String PRESENTATION =
                "-----------------------------------------------\n" +
                "--- Bienvenue au jeu de roche-papier-ciseau ---\n" +
                "-----------------------------------------------\n";

        final String MSG_NB_MANCHES = "Combien de manches voulez-vous jouer?\n";

        //Example de phrase a afficher : Il reste 5 manche(s) à jouer.

        final String MSG_MANCHE_1 = "\n\nIl reste ";

        final String MSG_MANCHE_2  = " manche(s) à jouer.\n";

        //Example de phrase a afficher : JOUEUR 1, quel est votre choix? [r/p/c]

        final String MSG_JOUEUR_1 = "JOUEUR 1";

        final String MSG_JOUEUR_2 = "JOUEUR 2";

        final String MSG_CHOIX = ", quel est votre choix? [r/p/c]\n";

        final String MSG_MANCHE_NULL = "Manche nulle...";

        // Example de phrase a afficher : JOUEUR 2 a gagné cette manche! Score: 1-3

        final String MSG_VICTOIRE_MANCHE = " a gagné cette manche! Score: ";

        final char TIRET = '-';

        //Example de phrase a afficher : JOUEUR 2 A GAGNÉ LE MATCH! FÉLICITATIONS!

        final String MSG_VICTOIRE_PARTIE = " A GAGNÉ LE MATCH! FÉLICITATIONS!";

        final String MSG_SCORE_FINAL = "SCORE FINAL: ";

        //Message d'erreur menant a la fin du programme

        final String MSG_ERR = "Erreur d'entrée! Programme terminé.\n";

        /** Les variables */

        int nbManches = 0;
        char choixJ1;                       //choix du joueur 1
        char choixJ2;                       //choix du joueur 2
        int scoreJ1 = 0;                    //score du joueur 1
        int scoreJ2 = 0;                    //score du joueur 2
        int seuilleDeVictoire = 0;          //score a partie du quelle un joueur gagne
                                            //quelque soit l'issue des manches suivantes.
        boolean encoreJouable = true;       //indicateur de fin de jeu anticipé.

        //Affichage du message d'introduction du programme
        Pep8.stro(PRESENTATION);

        //Affichage du message de sollicitation du nombre de manches a jouer
        Pep8.stro(MSG_NB_MANCHES);

        //Sollicitation du nombre de manche a jouer (valeur entière)
        nbManches = Pep8.deci();

        /*
        Trouver le reste de la divion de nbManches par 2 (Sans division)
        On soustrait 2 au tampon de nbManches jusqu'a trouver un reste
        de 0 ou 1. Si le reste est 0, on sais que le nombre de manche est
        paire donc incrémentation.
        */
        int reste = nbManches;
        int quotient = 0;

        while(reste >= 2) {
            reste = reste - 2;
            quotient = quotient + 1;
        }

        if( reste == 0)
            nbManches = nbManches + 1;

        /*
        Seuille de victoire est atteind lorsque le joueur
        adverse ne pourra pas remonter le score pour gagner
        la partie.

        Example :
        - partie a 7 manches : à 4-0 , quelque soit le resultat
        des 3 manches restantes, le joueurs ayant 4 a déja gagner.
         */
        seuilleDeVictoire = quotient + 1;

        //Boucle principale du jeu
        do {

            // Affichage du nombre de manches a jouer
            Pep8.stro(MSG_MANCHE_1);
            Pep8.deco(nbManches);
            Pep8.stro(MSG_MANCHE_2);

            //Affichage message de sollicitation pour Joueur 1
            Pep8.stro(MSG_JOUEUR_1);
            Pep8.stro(MSG_CHOIX);

            //Vider la valeur de choixJ1
            choixJ1 = Pep8.chari();

            //Saisie de la nouvelle valeur de choixJ1
            choixJ1 = Pep8.chari();

            /*
            Validation de la saisie
            ASCII 'r' = 114
            ASCII 'p' = 112
            ASCII 'c' = 99
             */
            if( choixJ1 != 114 && choixJ1 != 112 && choixJ1 != 99){
                Pep8.stro(MSG_ERR);
                Pep8.stop();
            }

            //Affichage message de sollicitation pour Joueur 2
            Pep8.stro(MSG_JOUEUR_2);
            Pep8.stro(MSG_CHOIX);

            //Vider la valeur de choixJ2
            choixJ2 = Pep8.chari();

            //Saisie de la nouvelle valeur de choixJ2
            choixJ2 = Pep8.chari();

            //Validation de la valeur saisie
            if( choixJ2 != 114 && choixJ2 != 112 && choixJ2 != 99){
                Pep8.stro(MSG_ERR);
                Pep8.stop();
            }

            /*
            Si les deux joueurs joue le meme symbole, affichage du message
            de manche nulle.

            Sinon , dans chaque sous condition, on incremente le compteur
            de score du vainqueur et on affiche son nom.
            La suite de l'affichage vien a la fin
             */
            if(choixJ1 == choixJ2)
                Pep8.stro(MSG_MANCHE_NULL);
            else {
                /*
                Le joueur 1 joue papier
                Le joueur 2 joue roche
                Donc le joueur 1 gagne la manche
                 */
                if(choixJ1 == 'p' && choixJ2 == 'r') {
                    scoreJ1++;
                    Pep8.stro(MSG_JOUEUR_1);
                }

                /*
                Le joueur 1 joue roche
                Le joueur 2 joue papier
                Donc le joueur 2 gagne la manche
                 */
                if(choixJ1 == 'r' && choixJ2 == 'p') {
                    scoreJ2++;
                    Pep8.stro(MSG_JOUEUR_2);
                }

                /*
                Le joueur 1 joue roche
                Le joueur 2 joue ciseau
                Donc le joueur 1 gagne la manche
                 */
                if(choixJ1 == 'r' && choixJ2 == 'c') {
                    scoreJ1++;
                    Pep8.stro(MSG_JOUEUR_1);
                }

                /*
                Le joueur 1 joue ciseau
                Le joueur 2 joue roche
                Donc le joueur 2 gagne la manche
                 */
                if(choixJ1 == 'c' && choixJ2 == 'r') {
                    scoreJ2++;
                    Pep8.stro(MSG_JOUEUR_2);
                }

                /*
                Le joueur 1 joue ciseau
                Le joueur 2 joue papier
                Donc le joueur 1 gagne la manche
                 */
                if(choixJ1 == 'c' && choixJ2 == 'p') {
                    scoreJ1++;
                    Pep8.stro(MSG_JOUEUR_1);
                }

                /*
                Le joueur 1 joue papier
                Le joueur 2 joue ciseau
                Donc le joueur 2 gagne la manche
                 */
                if(choixJ1 == 'p' && choixJ2 == 'c') {
                    scoreJ2++;
                    Pep8.stro(MSG_JOUEUR_2);
                }

                /*
                La suite de l'affichage.
                Example : a gagné cette manche! Score: 1-2
                 */
                Pep8.stro(MSG_VICTOIRE_MANCHE);
                Pep8.deco(scoreJ1);
                Pep8.charo(TIRET);
                Pep8.deco(scoreJ2);

                //Décrementation du nombre de manches restante a jouer
                nbManches = nbManches - 1;

                //Verification de la victoire anticipé du joueur 1
                if(scoreJ1 == seuilleDeVictoire )
                    encoreJouable = false;

                //Verification de la victoire anticipé du joueur 2
                if(scoreJ2 == seuilleDeVictoire)
                    encoreJouable = false;
            }
        } while (nbManches > 0 && encoreJouable );

        Pep8.stro("\n\n");
        //Affichage du nom du vainqueur
        if (scoreJ1 > scoreJ2)
            Pep8.stro(MSG_JOUEUR_1);
        else
            Pep8.stro(MSG_JOUEUR_2);

        //Affichage du message de sortie de programme
        Pep8.stro(MSG_VICTOIRE_PARTIE);
        Pep8.stro("\n");
        Pep8.stro(MSG_SCORE_FINAL);
        Pep8.deco(scoreJ1);
        Pep8.charo(TIRET);
        Pep8.deco(scoreJ2);

        //Fin du programme
        Pep8.stop();
    }

    public static void main1(String[] args) {

        // Lit et affiche 5 caracteres (blancs compris)
        Pep8.stro("Debut :\n");
        for(int i = 0; i < 5; i++) {
            char c = Pep8.chari();
            Pep8.stro("``");
            Pep8.charo(c);
            Pep8.stro("'' est le caractere de code ASCII: ");
            Pep8.deco(c);
            Pep8.stro("\n");
        }

        Pep8.stop();
    }
}