import DAO.*;
import com.github.javafaker.Faker;
import entities.*;
import enums.Stato;
import enums.StatoDistributore;
import enums.TipoAbbonamento;
import enums.TipoDiMezzo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("connections");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        boolean b = true;
        DocumentoVenditaDAO dd = new DocumentoVenditaDAO(em);
        ManutenzioneDAO mtd = new ManutenzioneDAO(em);
        MezzoTraspDAO mzd = new MezzoTraspDAO(em);
        PuntoVenditaDAO pd = new PuntoVenditaDAO(em);
        TrattaDAO td = new TrattaDAO(em);
        UtenteDAO ud = new UtenteDAO(em);
        ControlloTrattaDAO cd = new ControlloTrattaDAO(em);
        Faker faker = new Faker(new Locale("ITALY"));
        Random rndm = new Random();

        dd.bigliettiVidimatiPerPeriodo("2dff96c3-cb0e-446f-904f-dc803143ae30", LocalDate.parse("2023-01-09"), LocalDate.parse("2024-01-09"));

        System.out.println("hello world");
        /*creazioneUtente(ud, faker, rndm);
        creazioneTratta(td, faker, rndm);
        creazioneMezzoDiTrasporto(mzd, td, rndm);
        creazionePuntoVedita(pd, faker, rndm);
        creazioneDocumentoVendita(dd, ud, mzd, rndm, pd);
        creazioneManutenzione(mtd, mzd, rndm);
        creaControlloTratta(cd, mzd, rndm);
        mtd.listaManutenzioneMezzi("0b378bcb-c0cf-4619-8bc6-833c007f9de5").forEach(System.out::println);*/

         
        mzd.getContaTratte("332f4b16-8230-48f2-90d3-492f6b25859c");
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
                                            // ISTRUZIONI DA FARE IN INSERISCI DATA PER RICERCA
                                            break;
                                        }
                                        case 2: {
                                            System.out.println("hai scelto Per mezzo: inserisci il Mezzo");
                                            String choiceMezzo = scanner.nextLine();
                                            //ISTRUZIONI DA FARE IN INSERISCI DATA PER RICERCA
                                            break;
                                        }
                                    }
                                    // ISTRUZIONI DA FARE IN MANUTENZIONE
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

    public static String creaRandomData() {
        Random rndm = new Random();
        ;
        int anno = rndm.nextInt(2021, 2024);
        int mese = rndm.nextInt(1, 13);
        int giorno = 1;
        if (mese == 2) {
            if (anno % 4 == 0) {
                if (anno % 100 == 0) {
                    if (anno % 400 == 0) {
                        giorno = rndm.nextInt(1, 30);
                    } else {
                        giorno = rndm.nextInt(1, 29);
                    }
                }
            } else {
                giorno = rndm.nextInt(1, 29);
            }
        } else if (mese == 4 || mese == 6 || mese == 9 || mese == 11) {
            giorno = rndm.nextInt(1, 31);
            ;
        } else {
            giorno = rndm.nextInt(1, 32);
            ;
        }
        String annoString = String.valueOf(anno);
        String meseString = "";
        String giornoString = "";
        if (mese < 10) {
            meseString = "0" + mese;
        } else {
            meseString = String.valueOf(mese);
        }
        if (giorno < 10) {
            giornoString = "0" + giorno;
        } else {
            giornoString = String.valueOf(giorno);
        }
        return annoString + "-" + meseString + "-" + giornoString;
    }

    public static void creazioneUtente(UtenteDAO ud, Faker faker, Random rndm) {
        for (int i = 0; i < 50; i++) {
            Utente utente = new Utente(faker.funnyName().name(), faker.funnyName().name(),
                    LocalDate.parse(creaRandomData()));
            ud.save(utente);
        }
    }

    public static void creazioneTratta(TrattaDAO td, Faker faker, Random rndm) {
        for (int i = 0; i < 50; i++) {
            Tratta tratta = new Tratta(faker.country().capital(), faker.country().capital(), rndm.nextLong(30, 60));
            td.save(tratta);
        }
    }

    public static void creazioneMezzoDiTrasporto(MezzoTraspDAO mzd, TrattaDAO td, Random rndm) {
        for (int i = 0; i < 50; i++) {
            int n = rndm.nextInt(0, 1001);
            if (n % 2 == 0) {
                MezzoDiTrasporto mezzoDiTrasporto = new MezzoDiTrasporto(TipoDiMezzo.AUTOBUS, rndm.nextLong(40, 101),
                        td.getAllTratte().get(rndm.nextInt(0, td.getAllTratte().size())),
                        n % 3 == 0 ? Stato.IN_SERVIZIO : Stato.IN_MANUTENZIONE, rndm.nextInt(1, 6));
                mzd.save(mezzoDiTrasporto);
            } else {
                MezzoDiTrasporto mezzoDiTrasporto = new MezzoDiTrasporto(TipoDiMezzo.TRAM, rndm.nextLong(40, 101),
                        td.getAllTratte().get(rndm.nextInt(0, td.getAllTratte().size())),
                        n % 3 == 0 ? Stato.IN_SERVIZIO : Stato.IN_MANUTENZIONE, rndm.nextInt(1, 6));
                mzd.save(mezzoDiTrasporto);
            }
        }
    }

    public static void creazionePuntoVedita(PuntoVenditaDAO pd, Faker faker, Random rndm) {
        for (int i = 0; i < 50; i++) {
            int n = rndm.nextInt(0, 1001);
            if (n % 2 == 0) {
                PuntoVendita puntoVendita = new Distributore(faker.gameOfThrones().character(),
                        faker.country().capital(),
                        n % 3 == 0 ? StatoDistributore.ATTIVO : StatoDistributore.FUORI_SERVIZIO);
                pd.save(puntoVendita);
            } else {
                PuntoVendita puntoVendita = new Rivenditore(faker.gameOfThrones().character(),
                        faker.country().capital());
                pd.save(puntoVendita);
            }
        }
    }

    public static void creazioneDocumentoVendita(DocumentoVenditaDAO dd, UtenteDAO ud, MezzoTraspDAO mzd, Random rndm,
                                                 PuntoVenditaDAO pd) {

        for (int i = 0; i < 50; i++) {
            int n = rndm.nextInt(0, 1001);
            LocalDate dataDiRilascio = LocalDate.parse(creaRandomData());
            if (n % 2 == 0) {
                DocumentoVendita documentoVendita = new Biglietto(dataDiRilascio,
                        pd.getAllPuntiVendita().get(rndm.nextInt(0, pd.getAllPuntiVendita().size())),
                        n % 3 == 0 ? null : dataDiRilascio.plusDays(rndm.nextInt(10, 40)),
                        mzd.getAllMezziDiTrasporti().get(rndm.nextInt(0, mzd.getAllMezziDiTrasporti().size())));
                dd.save(documentoVendita);
            } else {
                DocumentoVendita documentoVendita = new Tessera(dataDiRilascio,
                        pd.getAllPuntiVendita().get(rndm.nextInt(0, pd.getAllPuntiVendita().size())),
                        ud.getAllUtenti().get(i),
                        n % 3 == 0 ? TipoAbbonamento.MENSILE : TipoAbbonamento.SETTIMANALE, dataDiRilascio);
                dd.save(documentoVendita);
            }
        }
    }

    public static void creazioneManutenzione(ManutenzioneDAO mtd, MezzoTraspDAO mzd, Random rndm) {
        String[] app = {"Cambio vetri", "Riparazione freni", "Cambio olio"};
        for (int i = 0; i < rndm.nextInt(50, 101); i++) {
            LocalDate dataInizio = LocalDate.parse(creaRandomData());
            Manutenzione manutenzione = new Manutenzione(dataInizio, dataInizio.plusDays(rndm.nextInt(3, 201)),
                    mzd.getAllMezziDiTrasporti().get(rndm.nextInt(0, mzd.getAllMezziDiTrasporti().size())),
                    app[rndm.nextInt(0, app.length)]);
            mtd.save(manutenzione);
        }
    }

    public static void creaControlloTratta(ControlloTrattaDAO cd, MezzoTraspDAO mzd, Random rndm) {
        for (int i = 0; i < 50; i++) {
            int n = rndm.nextInt(0, 1000);
            long x = rndm.nextLong(10, 20);

            MezzoDiTrasporto mezzoDiTrasporto = mzd.getAllMezziDiTrasporti().get(rndm.nextInt(0, mzd.getAllMezziDiTrasporti().size()));
            ControlloTratta ct = new ControlloTratta(mezzoDiTrasporto, n % 2 == 0 ? mezzoDiTrasporto.getTratta().getTempoMedio() + x : mezzoDiTrasporto.getTratta().getTempoMedio() - x);
            cd.save(ct);
        }
    }
}
