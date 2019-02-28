import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class KoniecTankowania extends BasicSimEvent<Stanowisko, Object> {

    Stanowisko stanowisko;

    public KoniecTankowania(Stanowisko stanowisko, double delay) throws SimControlException {
        super(stanowisko,delay);
        this.stanowisko = stanowisko;
    }

    @Override
    protected void stateChange() throws SimControlException {
        System.out.println("Klient " + stanowisko.klientPrzyStanowisku.numerKlienta + " zakończył tankowanie przy stanowisku "  + stanowisko.numerStanowiska + " czas: "+simTime());
        double czasTankowania = simTime() - stanowisko.klientPrzyStanowisku.czasStartuObserwacji;
        Stacja.monitorCzasTankowania.setValue(czasTankowania);
        stanowisko.stacja.kasa.dodajKlienta(stanowisko.klientPrzyStanowisku);
        int wolnaKasa=stanowisko.stacja.kasa.zwrocNumerWolnejKlasy();
        if (stanowisko.stacja.kasa.listaKlientow.size()==1 && wolnaKasa >-1) {
            stanowisko.stacja.kasa.startPlatnosci = new StartPlatnosci(stanowisko.stacja.kasa, wolnaKasa);
            stanowisko.klientPrzyStanowisku = null;
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
