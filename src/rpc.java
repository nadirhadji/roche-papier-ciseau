import java.awt.*;

/**********************************************************
 *          U Q A M   -   I N F 2 1 7 1
 * Organisation des ordinateurs et assembleur
 *
 * @author Cissé Saliou (CISS20129907)
 * @author Hadji Nadir (HADN08069703)
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

        //Constante des options de jeu

        final char ROCHE = 'r';
        final char PAPIER = 'p';
        final char CISEAU = 'c';

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
        L'objectif est de déterminer si le nombre de manche saisie par
        l'utilisateur est paire sans utiliser de division.

        On effectue des soustractions succesives de 2 sur le nombre de manches
        jusqu'a trouver un reste de 0 ou 1.

        Si la valeur de reste est 0, elle est paire. On incrémente donc
        le nombre de manches de 1.
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
        On a determinée plus haut le quotient de la division du nombre de manche
        par 2. Lorsque le score d'un joueur franchie le seuil du quotient,
        la victoire est acquise.
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
            if( choixJ1 != ROCHE && choixJ1 != PAPIER && choixJ1 != CISEAU){
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
            if( choixJ2 != ROCHE && choixJ2 != PAPIER && choixJ2 != CISEAU){
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
                if(choixJ1 == PAPIER && choixJ2 == ROCHE) {
                    scoreJ1++;
                    Pep8.stro(MSG_JOUEUR_1);
                }

                /*
                Le joueur 1 joue roche
                Le joueur 2 joue papier
                Donc le joueur 2 gagne la manche
                 */
                if(choixJ1 == ROCHE && choixJ2 == PAPIER) {
                    scoreJ2++;
                    Pep8.stro(MSG_JOUEUR_2);
                }

                /*
                Le joueur 1 joue roche
                Le joueur 2 joue ciseau
                Donc le joueur 1 gagne la manche
                 */
                if(choixJ1 == ROCHE && choixJ2 == CISEAU) {
                    scoreJ1++;
                    Pep8.stro(MSG_JOUEUR_1);
                }

                /*
                Le joueur 1 joue ciseau
                Le joueur 2 joue roche
                Donc le joueur 2 gagne la manche
                 */
                if(choixJ1 == CISEAU && choixJ2 == ROCHE) {
                    scoreJ2++;
                    Pep8.stro(MSG_JOUEUR_2);
                }

                /*
                Le joueur 1 joue ciseau
                Le joueur 2 joue papier
                Donc le joueur 1 gagne la manche
                 */
                if(choixJ1 == CISEAU && choixJ2 == PAPIER) {
                    scoreJ1++;
                    Pep8.stro(MSG_JOUEUR_1);
                }

                /*
                Le joueur 1 joue papier
                Le joueur 2 joue ciseau
                Donc le joueur 2 gagne la manche
                 */
                if(choixJ1 == PAPIER && choixJ2 == CISEAU) {
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

                //Verification qu'il reste encore des manches a jouer
                if (nbManches == 0)
                    encoreJouable = false;
            }
        } while (encoreJouable);

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
}