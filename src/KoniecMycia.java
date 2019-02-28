import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class KoniecMycia extends BasicSimEvent<Myjnia, Object> {

    Myjnia myjnia;

    public KoniecMycia(Myjnia myjnia, double delay) throws SimControlException {
        super(myjnia, delay);
        this.myjnia = myjnia;
    }

    @Override
    protected void stateChange() throws SimControlException {

        myjnia.czyMyjniaWolna=true;
        System.out.println("Mycie zakończone przez klienta numer " + myjnia.klientAktualnieKorzystajacyZMyjni.numerKlienta + " czas: "+simTime());

        myjnia.monitorKlienciWKolejceDoMyjni.setValue(myjnia.kolejkaKlientowDoMyjni.size(),simTime());

        double czasObserwacji = simTime() - myjnia.klientAktualnieKorzystajacyZMyjni.czasStartuObserwacji;

        myjnia.stacja.monitorCzasMycia.setValue(czasObserwacji);


        if (myjnia.kolejkaKlientowDoMyjni.size() > 0)
        {
            myjnia.startMycia = new StartMycia(myjnia);
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
