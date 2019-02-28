import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

import java.util.Random;

public class NowyKlient extends BasicSimEvent<Stacja, Object> {

    Stacja stacja;
    static int numerKlienta = 0;

    public NowyKlient(Stacja stacja, double delay) throws SimControlException {
        super(stacja,delay);
        this.stacja = stacja;
    }

    @Override
    protected void stateChange() throws SimControlException {
        Random randomGenerator = new Random();
        Klient klient = new Klient(++numerKlienta);
        stacja.liczbaWszystkichKlientow++;

        if (klient.czyKorzystaZMyjni) {
            klient.rodzajPaliwa = randomGenerator.nextInt(4);
        } else {
            klient.rodzajPaliwa = randomGenerator.nextInt(3) + 1;
        }

        klient.czasStartuObserwacji = simTime();
        System.out.println("Klient numer " + klient.numerKlienta + " przyjechał na stację, czas: " + klient.czasStartuObserwacji);

        if (klient.rodzajPaliwa != 0) {
            int wolneStanowisko = -1;
            for (int i = 0; i < Controller.maxDlugoscKolejkiDoStanowiska; i++) {
                for (int j = 0; j < stacja.stanowiska.size(); j++) {
                    if (stacja.stanowiska.get(j).typStanowiska == klient.rodzajPaliwa)
                        if (stacja.stanowiska.get(j).listaKlientow.size() < (i + 1)) {
                            wolneStanowisko = j;
                            break;
                    }
                }
            }

            if (wolneStanowisko != -1) {
                stacja.stanowiska.get(wolneStanowisko).dodajKlientaDoStanowiska(klient);

                if (stacja.stanowiska.get(wolneStanowisko).listaKlientow.size() == 1 && stacja.stanowiska.get(wolneStanowisko).czyStanowiskoWolne) {
                    stacja.stanowiska.get(wolneStanowisko).startTankowania = new StartTankowania(stacja.stanowiska.get(wolneStanowisko));
                }
            } else {
                stacja.liczbaKlientowKtorzyNieZmiesciliSieWKolejce += 1;
                System.out.println("Klient numer " + klient.numerKlienta + " zrezygnował.");
            }
        } else {
            stacja.kasa.dodajKlienta(klient);
            int wolnaKasa = stacja.kasa.zwrocNumerWolnejKlasy();
            if (stacja.kasa.listaKlientow.size() == 1 && wolnaKasa > -1) {
                stacja.kasa.startPlatnosci = new StartPlatnosci(stacja.kasa, wolnaKasa);
            }
        }

        double czasDoNastepnegoKlienta = Controller.generujLosowyCzasOdstepuMiedzyKlientami();
        stacja.nowyKlient = new NowyKlient(stacja, czasDoNastepnegoKlienta);

    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    protected void onInterruption() throws SimControlException {

    }

    @Override
    public Object getEventParams() {
        return null;
    }
}
