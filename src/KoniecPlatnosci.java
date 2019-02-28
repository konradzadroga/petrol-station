import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class KoniecPlatnosci extends BasicSimEvent<Kasa, Object> {

    Kasa kasa;
    int numerKasy;

    public KoniecPlatnosci(Kasa kasa, double delay, int numerKasy) throws SimControlException {
        super(kasa,delay);
        this.kasa = kasa;
        this.numerKasy = numerKasy;
    }

    @Override
    protected void stateChange() throws SimControlException {
        kasa.wolneKasy[numerKasy] = true;
        if (kasa.klienciWKasach[numerKasy].rodzajPaliwa != 0) {
            kasa.klienciWKasach[numerKasy].aktualneStanowiskoKlienta.czyStanowiskoWolne = true;

            if (kasa.klienciWKasach[numerKasy].aktualneStanowiskoKlienta.listaKlientow.size() > 0) {
                kasa.klienciWKasach[numerKasy].aktualneStanowiskoKlienta.startTankowania = new StartTankowania(
                        kasa.klienciWKasach[numerKasy].aktualneStanowiskoKlienta);
            }
        }
        System.out.println("Płatność w kasie nr " + (numerKasy) + " zakończona przez klienta numer "
                + kasa.klienciWKasach[numerKasy].numerKlienta + ", czas: " + simTime());

        if (kasa.listaKlientow.size() > 0) {
            kasa.startPlatnosci = new StartPlatnosci(kasa, numerKasy);
        }

        if (kasa.klienciWKasach[numerKasy].czyKorzystaZMyjni) {
            kasa.stacja.myjnia.dodajKlientaDoMyjni(kasa.klienciWKasach[numerKasy]);
            if (kasa.stacja.myjnia.kolejkaKlientowDoMyjni.size() == 1 && kasa.stacja.myjnia.czyMyjniaWolna) {
                kasa.stacja.myjnia.startMycia = new StartMycia(kasa.stacja.myjnia);
            }
        }
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
