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
import java.util.*;

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

//        dd.bigliettiVidimatiPerPeriodo("2dff96c3-cb0e-446f-904f-dc803143ae30", LocalDate.parse("2023-01-09"), LocalDate.parse("2024-01-09"));

//        System.out.println("hello world");

//        creazioneUtente(ud, faker, rndm);
//        creazioneTratta(td, faker, rndm);
//        creazioneMezzoDiTrasporto(mzd, td, rndm);
//        creazionePuntoVedita(pd, faker, rndm);
//        creazioneDocumentoVendita(dd, ud, mzd, rndm, pd);
//        creazioneManutenzione(mtd, mzd, rndm);
//        creaControlloTratta(cd, mzd, rndm);

//        mtd.listaManutenzioneMezzi("0b378bcb-c0cf-4619-8bc6-833c007f9de5").forEach(System.out::println);
//
//        mzd.getContaTratte("332f4b16-8230-48f2-90d3-492f6b25859c");
        MezzoDiTrasporto mezzoDiTrasporto = mzd.getById(UUID.fromString("276f2034-5560-45fc-8895-6d08557c0b86"));
        List<Biglietto> bigliettoList = mezzoDiTrasporto.getBigliettoList();
        for (int i = 0; i < bigliettoList.size(); i++) {
            if (bigliettoList.get(i).getDataDiConvalidazione() != null) {
                System.out.println(bigliettoList.get(i));
            }
        }
        try {
            while (b) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("0: Esci: 1: Amministazione, 2: Rinnovo Abbonamento");
                try {
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 0: {
                            System.out.println("HAI SCELTO DI USCIRE");
                            b = false;
                            break;
                        }
                        case 1: {
                            System.out.println("Hai scelto Amministrazione, che servizio?");
                            System.out.println("0: Torna indietro, 1:Manuetenzione, 2:Mezzo 3:Verifica abbonamento");
                            System.out.println("4: Aggiungi rivenditore affiliato, 5: Documenti viaggio da punto vendita");
                            int choiceAdmin = Integer.parseInt(scanner.nextLine());
                            switch (choiceAdmin) {
                                case 0: {
                                    System.out.println("hai scelto di tornare indietro");
                                    break;
                                }
                                case 1: {
                                    System.out.println("hai scelto manutenzione");
                                    System.out.println("0: Torna indietro, 1:insesci nuava manutenzione su mezzo(per id mezzo)");
                                    System.out.println("2: Cerca manutenzione attive su mezzo(per id mezzo)");
                                    System.out.println("3: Fine lavori (per id mezzo)");
                                    int choiceManu = Integer.parseInt(scanner.nextLine());
                                    switch (choiceManu) {
                                        case 0: {
                                            System.out.println("hai scelto di tornare indietro");
                                            break;
                                        }
                                        case 1: {
                                            System.out.println("insesci nuova manutenzione su mezzo(per id mezzo)");
                                            //MOSTRA TUTTI MEZZI PER COPIARE ID
                                            mzd.getAllMezziDiTrasporti().forEach(System.out::println);
                                            System.out.println("insesci id mezzo");
                                            String s = scanner.nextLine();
                                            MezzoDiTrasporto mezzo = mzd.getById(s);
                                            System.out.println("insesci descrizione problema");
                                            String descrizione = scanner.nextLine();
                                            Manutenzione m = new Manutenzione(mezzo, descrizione);
                                            mtd.save(m);
                                            break;
                                        }
                                        case 2: {
                                            System.out.println("Cerca manutenzione attive su mezzo(per id mezzo)");
                                            mtd.getAllManutenzioni().forEach(manutenzione -> System.out.println("Manutenzione id: " + manutenzione.getManutenzioneId() +
                                                    "TIPO: " + manutenzione.getMezzoDiTrasporto().getTipoDiMezzo() +
                                                    " Mezzo ID : " + manutenzione.getMezzoDiTrasporto().getMezzoDiTrasportoId()));
                                            System.out.println("insesci id mezzo");
                                            String s = scanner.nextLine();
                                            mtd.getAllManutenzioniPerMezzo(s).forEach(System.out::println);
                                            break;
                                        }
                                        case 3: {
                                            System.out.println("Hai scelto la fine dei lavori la data impostata di default sara oggi");
                                            mtd.getAllManutenzioni().forEach(System.out::println);
                                            System.out.println("insesci id manutenzione");
                                            String s = scanner.nextLine();
                                            mtd.uscitaMezzoManutenzione(s);
                                        }
                                    }
                                    break;
                                }
                                case 2: {
                                    System.out.println("hai scelto Mezzo");
                                    System.out.println("0: Torna indietro, 1: Inserisci nuovo Mezzo, 2: Cerca veicoli in servizio");
                                    System.out.println("3: Verifica biglietti su Mezzo, 4:Verifica tempi effittivi tratta");
                                    int choiceAdminMezzo = Integer.parseInt(scanner.nextLine());
                                    switch (choiceAdminMezzo) {
                                        case 0: {
                                            System.out.println("hai scelto di tornare indietro");
                                            break;
                                        }
                                        case 1: {
                                            System.out.println("insesci nuovo mezzo ");
                                            System.out.println("insesci tipo: 0:AUTOBUS 1:TRAM");
                                            int i = Integer.parseInt(scanner.nextLine());
                                            TipoDiMezzo tipo;
                                            if (i == 0) {
                                                tipo = TipoDiMezzo.AUTOBUS;
                                            } else {
                                                tipo = TipoDiMezzo.TRAM;
                                            }
                                            System.out.println("insesci capienza");
                                            int capienza = Integer.parseInt(scanner.nextLine());
                                            td.getAllTratte().forEach(System.out::println);
                                            System.out.println("insesci l'id della tratta del nuovo mezzo");
                                            String s = scanner.nextLine();
                                            Tratta tratta = td.getById2(s);
                                            MezzoDiTrasporto m = new MezzoDiTrasporto(tipo, capienza, tratta);
                                            mzd.save(m);
                                            break;
                                        }
                                        case 2: {
                                            System.out.println("Elenco veicoli in servizio");
                                            mzd.getTransportStatus();
                                            /*
                                            System.out.println("inserisci id mezzo ");
                                            String s = scanner.nextLine();

                                             */
                                            break;
                                        }
                                        case 3: {
                                            //MOSTRA TUTTI MEZZI PER COPIARE ID
                                            mzd.getAllMezziDiTrasporti().forEach(System.out::println);
                                            System.out.println("Verifica biglietti su Mezzo per data");
                                            System.out.println("0: torna indietro, 1: Cerca per data, 2:Cerca per periodo, 3:Totale biglietti su mezzo");
                                            int scelta = Integer.parseInt(scanner.nextLine());
                                            switch (scelta) {
                                                case 0: {
                                                    System.out.println("hai scelto di tornare indietro");
                                                    break;
                                                }
                                                case 1: {
                                                    System.out.println("Cerca per data");
                                                    System.out.println("insesci id mezzo da elenco soprastante");
                                                    String s = scanner.nextLine();
                                                    System.out.println("insesci data inizio");
                                                    LocalDate ds = LocalDate.parse(scanner.nextLine());
                                                    dd.bigliettiVidimatiPerData(s, ds);
                                                    break;
                                                }
                                                case 2: {
                                                    System.out.println("insesci id mezzo da elenco soprastante");
                                                    String s = scanner.nextLine();
                                                    System.out.println("insesci data inizio");
                                                    LocalDate ds = LocalDate.parse(scanner.nextLine());
                                                    System.out.println("insesci data fine");
                                                    LocalDate df = LocalDate.parse(scanner.nextLine());
                                                    dd.bigliettiVidimatiPerPeriodo(s, ds, df);
                                                    break;
                                                }
                                                case 3: {
                                                    System.out.println("insesci id mezzo da elenco soprastante");
                                                    String s = scanner.nextLine();
                                                    dd.totaleBigliettiVidimati(s);
                                                }
                                            }
                                        }
                                        case 4: {
                                            System.out.println("Verifica tempi effittivi tratta");

                                            break;
                                        }
                                    }
                                    // ISTRUZIONI DA FARE IN MANUTENZIONE
                                    break;
                                }
                                case 3: {
                                    System.out.println("Verifica abbonamento");
                                    dd.getAllTessere();
                                    System.out.println("Inserisci id abbonamento");
                                    String s = scanner.nextLine();
                                    dd.controlloAbbonamento(s);
                                    break;
                                }
                                case 4: {
                                    System.out.println("Inserisci nuovo Punto vendita");
                                    System.out.println("0: Torna indietro, 1:Inserisci nuovo rivenditore, 2:Inserisci nuovo distributore automatico");
                                    int scelta = Integer.parseInt(scanner.nextLine());
                                    switch (scelta) {
                                        case 0: {
                                            System.out.println("hai scelto di tornare indietro");
                                            break;
                                        }
                                        case 1: {
                                            System.out.println("Inserisci nome");
                                            String nome = scanner.nextLine();
                                            System.out.println("Inserisci citta");
                                            String citta = scanner.nextLine();
                                            PuntoVendita p = new Rivenditore(nome, citta);
                                            pd.save(p);
                                            break;
                                        }
                                        case 2: {
                                            System.out.println("Inserisci nome");
                                            String nome = scanner.nextLine();
                                            System.out.println("Inserisci citta");
                                            String citta = scanner.nextLine();
                                            System.out.println("inserisci stato: 0:IN SERVIZO, 1:FOURI SERVIZIO");
                                            int s = Integer.parseInt(scanner.nextLine());
                                            StatoDistributore stato;
                                            if (s == 0) {
                                                stato = StatoDistributore.ATTIVO;
                                            } else {
                                                stato = StatoDistributore.FUORI_SERVIZIO;
                                            }
                                            PuntoVendita p = new Distributore(nome, citta, stato);
                                            pd.save(p);
                                            break;
                                        }
                                    }
                                    break;
                                }
                                case 5: {
                                    System.out.println("Punti Vendita");
                                    pd.getAllPuntiVendita().forEach(puntoVendita -> {
                                        System.out.println("Id: " + puntoVendita.getPuntoVenditaId() + " nome: " + puntoVendita.getNome());
                                    });
                                    System.out.println("inserisci id Punto vendita");
                                    String s = scanner.nextLine();
                                    PuntoVendita pv = pd.getById(s);
                                    dd.dammiDocumentiVenditaPerDataePv(pv.getPuntoVenditaId().toString()).forEach(System.out::println);

                                    break;
                                }
                            }
                            break;
                        }
                        case 2: {
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
                        }
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
        for (int i = 0; i < 200; i++) {
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
                        n % 3 == 0 ? Stato.IN_MANUTENZIONE : Stato.IN_SERVIZIO, rndm.nextInt(1, 6), LocalDate.parse(creaRandomData()));
                mzd.save(mezzoDiTrasporto);
            } else {
                MezzoDiTrasporto mezzoDiTrasporto = new MezzoDiTrasporto(TipoDiMezzo.TRAM, rndm.nextLong(40, 101),
                        td.getAllTratte().get(rndm.nextInt(0, td.getAllTratte().size())),
                        n % 3 == 0 ? Stato.IN_MANUTENZIONE : Stato.IN_SERVIZIO, rndm.nextInt(1, 6), LocalDate.parse(creaRandomData()));
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

        for (int i = 0; i < 200; i++) {
            int n = rndm.nextInt(0, 1001);
            MezzoDiTrasporto mezzoDiTrasporto = mzd.getAllMezziDiTrasporti().get(rndm.nextInt(0, mzd.getAllMezziDiTrasporti().size()));
            if (mezzoDiTrasporto.getStato() == Stato.IN_SERVIZIO) {
                LocalDate dataDiRilascio = LocalDate.parse(creaRandomData());
                if (n % 2 == 0) {
                    DocumentoVendita documentoVendita = new Biglietto(dataDiRilascio,
                            pd.getAllPuntiVendita().get(rndm.nextInt(0, pd.getAllPuntiVendita().size())),
                            n % 3 == 0 ? null : mezzoDiTrasporto.getDataDiImmatricolazione().plusDays(rndm.nextInt(0, 1000)),
                            mezzoDiTrasporto);
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
    }

    public static void creazioneManutenzione(ManutenzioneDAO mtd, MezzoTraspDAO mzd, Random rndm) {
        String[] app = {"Cambio vetri", "Riparazione freni", "Cambio olio"};
        for (int i = 0; i < rndm.nextInt(50, 101); i++) {
            MezzoDiTrasporto mezzoDiTrasporto = mzd.getAllMezziDiTrasporti().get(rndm.nextInt(0, mzd.getAllMezziDiTrasporti().size()));
            LocalDate dataInizio = mezzoDiTrasporto.getDataDiImmatricolazione();
            if (mezzoDiTrasporto.getStato() != Stato.IN_SERVIZIO) {
                Manutenzione manutenzione = new Manutenzione(dataInizio.plusDays(rndm.nextInt(3, 101)), dataInizio.plusDays(rndm.nextInt(101, 201)),
                        mezzoDiTrasporto,
                        app[rndm.nextInt(0, app.length)]);
                mtd.save(manutenzione);
            }
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
