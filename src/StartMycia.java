import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class StartMycia extends BasicSimEvent<Myjnia, Object> {

    Myjnia myjnia;

    public StartMycia(Myjnia myjnia) throws SimControlException {
        super(myjnia);
        this.myjnia = myjnia;
    }

    @Override
    protected void stateChange() throws SimControlException {
        if (myjnia.kolejkaKlientowDoMyjni.size() > 0) {
            myjnia.czyMyjniaWolna = false;
            Klient klient = myjnia.usunKlientaZMyjni();
            myjnia.klientAktualnieKorzystajacyZMyjni = klient;
            double czasObslugi = Controller.generujLosowyCzasMycia();
            System.out.println("Rozpoczecie mycia przez klienta " + klient.numerKlienta + ", czas: " + simTime());
            myjnia.koniecMycia = new KoniecMycia(myjnia, czasObslugi);
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
