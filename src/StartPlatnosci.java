import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class StartPlatnosci extends BasicSimEvent<Kasa, Object> {

    Kasa kasa;
    int numerKasy;


    public StartPlatnosci(Kasa kasa, int numerKasy) throws SimControlException {
    super(kasa);
    this.kasa = kasa;
    this.numerKasy = numerKasy;
    }

    @Override
    protected void stateChange() throws SimControlException {


        if (kasa.listaKlientow.size() > 0)
        {
            kasa.wolneKasy[numerKasy]=false;
            Klient klient = kasa.listaKlientow.removeFirst();
            kasa.klienciWKasach[numerKasy] = klient;

            double czasObslugi = Controller.generujLosowyCzasPlatnosci();
            System.out.println("Platnosc w kasie nr "+ numerKasy + " rozpoczęta przez  klienta numer " + klient.numerKlienta +", czas: "+simTime());
            kasa.koniecPlatnosci = new KoniecPlatnosci(kasa, czasObslugi, numerKasy);
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
