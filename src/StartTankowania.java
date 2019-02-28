import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class StartTankowania extends BasicSimEvent<Stanowisko, Object> {

    Stanowisko stanowisko;

    public StartTankowania(Stanowisko stanowisko) throws SimControlException {
        super(stanowisko);
        this.stanowisko = stanowisko;
    }

    @Override
    protected void stateChange() throws SimControlException {


        if (stanowisko.listaKlientow.size() > 0)
        {
            Stanowisko.monitorKlienciWKolejceDoStanowiska.setValue(stanowisko.listaKlientow.size(),simTime());
            stanowisko.czyStanowiskoWolne = false;
            Klient klient = stanowisko.usunKlientaZeStanowiska();
            stanowisko.klientPrzyStanowisku = klient;
            double czasObslugi = 0;
            if(klient.rodzajPaliwa == 1)
                czasObslugi = Controller.generujLosowyCzasTankowaniaBenzyny();
            else if(klient.rodzajPaliwa == 2)
                czasObslugi = Controller.generujLosowyCzasTankowaniaLPG();
            else if(klient.rodzajPaliwa == 3)
                czasObslugi = Controller.generujLosowyCzasTankowaniaON();
            System.out.println("Rozpoczęcie tankowania przez klienta numer " + klient.numerKlienta + " na stanowsku " + stanowisko.numerStanowiska + ", czas:"+simTime());
            stanowisko.klientPrzyStanowisku.aktualneStanowiskoKlienta=stanowisko;
            stanowisko.koniecTankowania = new KoniecTankowania(stanowisko, czasObslugi);
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
