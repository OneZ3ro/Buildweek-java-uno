import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("connections");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        boolean b = true;

        try {
            while (b) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("0: to exit");
                System.out.println("1: Amministazione");
                System.out.println("2: Utente");
                try {
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 0:
                            System.out.println("HAI SCELTO DI USCIRE");
                            b = false;
                            break;
                        case 1:
                            System.out.println("Hai scelto Amministrazione");
                            System.out.println("CHE SERVIZIO");
                            System.out.println("0: Torna indietro, 1:Manuetenzione, 2:Controllo mezzo 3:Verifica abbonamento");
                            int choiceAdmin = Integer.parseInt(scanner.nextLine());
                            switch (choiceAdmin) {
                                case 0: {
                                    System.out.println("hai scelto di tornare indietro");
                                    break;
                                }
                                case 1: {
                                    System.out.println("hai scelto manutenzione");
                                    //ISTRUZIONI DA FARE IN MANUTENZIONE
                                    break;
                                }
                                case 2: {
                                    System.out.println("hai scelto controllo mezzo");
                                    System.out.println("CHE SERVIZIO");
                                    System.out.println("0: Torna indietro, 1:Per data, 2: Per mezzo");
                                    int choiceAdminMezzo = Integer.parseInt(scanner.nextLine());
                                    switch (choiceAdminMezzo) {
                                        case 0: {
                                            System.out.println("hai scelto di tornare indietro");
                                            break;
                                        }
                                        case 1: {
                                            System.out.println("hai scelto Per data: inserisci la data");
                                            String choiceData = scanner.nextLine();
                                            //ISTRUZIONI DA FARE IN INSERISCI DATA PER RICERCA
                                            break;
                                        }
                                        case 2: {
                                            System.out.println("hai scelto Per mezzo: inserisci il Mezzo");
                                            String choiceMezzo = scanner.nextLine();
                                            //ISTRUZIONI DA FARE IN INSERISCI DATA PER RICERCA
                                            break;
                                        }
                                    }
                                    //ISTRUZIONI DA FARE IN MANUTENZIONE
                                    break;
                                }
                            }
                            break;
                        case 2:
                            System.out.println("Hai scelto Utente");
                            System.out.println("CHE SERVIZIO");
                            System.out.println("0: Torna indietro, 1:Convalida, 2:Acquisto biglietto 3:crea abbonamento");
                            int choiceUser = Integer.parseInt(scanner.nextLine());
                            switch (choiceUser) {
                                case 0: {
                                    System.out.println("hai scelto di tornare indietro");
                                    break;
                                }
                                case 1: {
                                    System.out.println("hai scelto Convalida");
                                    break;
                                }
                                case 2: {
                                    System.out.println("hai scelto Acquisto biglietto");
                                    break;
                                }
                                case 3: {
                                    System.out.println("hai scelto crea abbonamento");
                                    break;
                                }
                            }
                            break;
                        default:
                            System.out.println("You need to enter one of the controls above");
                            choice = Integer.parseInt(scanner.nextLine());
                            break;
                    }
                } catch (Exception ex) {
                    System.out.println("you must enter one of the appropriate controls");
                }
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }
}
