package crist.bci.utils;


public class Utils {


    public static void menuBanquier() {
        System.out.println("***************************************");
        System.out.println("*                 BCI                 *");
        System.out.println("*_____________________________________*");
        System.out.println("|                                     |");
        System.out.println("|           Menu Banquier             |");
        System.out.println("|_____________________________________|");
        System.out.println("|                                     |");
        System.out.println("|           1- créer un client        |");
        System.out.println("|           2- operation sur un compte|");
        System.out.println("|           3- deconnexion            |");
        System.out.println("|                                     |");
        System.out.println("|_____________________________________|");
    }
    
    public static void menuPrincipal() {
        System.out.println("***************************************");
        System.out.println("*                 BCI                 *");
        System.out.println("*_____________________________________*");
        System.out.println("|                                     |");
        System.out.println("|           Menu Principal            |");
        System.out.println("|_____________________________________|");
        System.out.println("|                                     |");
        System.out.println("|           1- connexion Client       |");
        System.out.println("|           2- Connexion Banquier     |");
        System.out.println("|           3- Quitter                |");
        System.out.println("|_____________________________________|");
    }


    public static void menuConnexion(){
        System.out.println("***************************************");
        System.out.println("*                 BCI                 *");
        System.out.println("*_____________________________________*");
        System.out.println("|                                     |");
        System.out.println("|           Menu Connexion            |");
        System.out.println("|_____________________________________|");
        System.out.println("|                                     |");
        System.out.println("|           1- Client                 |");
        System.out.println("|           2- Banquier               |");
        System.out.println("|           3- Quitter                |");
        System.out.println("|_____________________________________|");
    }


    public static void menuClient() {

            System.out.println("***************************************");
            System.out.println("*                 BCI                 *");
            System.out.println("*_____________________________________*");
            System.out.println("|                                     |");
            System.out.println("|           Menu Client               |");
            System.out.println("|_____________________________________|");
            System.out.println("|                                     |");
            System.out.println("|           1- consulter votre solde  |");
            System.out.println("|           2- faire un dépôt         |");
            System.out.println("|           3- faire un retrait       |");
            System.out.println("|           4-deconnexion             |");
            System.out.println("|                                     |");
            System.out.println("|_____________________________________|");
    }

    public static boolean isEmailValid(String email) {
        return email.matches("^[a-zA-Z0-9._-]+@[a-zA-Z]+\\.[a-zA-Z]{2,3}$");
    }


}
