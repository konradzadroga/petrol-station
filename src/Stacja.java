import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.monitors.MonitoredVar;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;

import java.util.LinkedList;

public class Stacja extends BasicSimObj {

    Kasa kasa;
    Myjnia myjnia;
    LinkedList<Stanowisko> stanowiska = new LinkedList<Stanowisko>();
    NowyKlient nowyKlient;
    static MonitoredVar monitorCzasTankowania;
    static MonitoredVar monitorCzasMycia;
    static double liczbaKlientowKtorzyNieZmiesciliSieWKolejce;
    static double liczbaWszystkichKlientow;


    public Stacja() {
        int numerStanowiska = 0;
        this.kasa = new Kasa(this);
        this.myjnia = new Myjnia(this);
        this.monitorCzasTankowania = new MonitoredVar();
        this.monitorCzasMycia = new MonitoredVar();
        try {
            this.nowyKlient = new NowyKlient(this,0);
        } catch (SimControlException e) {
            e.printStackTrace();
        }

        for (int i=0; i<Controller.ileStanowiskBenzyna; i++) {
            Stanowisko stanowisko = new Stanowisko(this, numerStanowiska++, 1);
            stanowiska.add(stanowisko);
        }

        for (int i=0; i<Controller.ileStanowiskLPG; i++) {
            Stanowisko stanowisko = new Stanowisko(this, numerStanowiska++, 2);
            stanowiska.add(stanowisko);
        }

        for (int i=0; i<Controller.ileStanowiskON; i++) {
            Stanowisko stanowisko = new Stanowisko(this, numerStanowiska++, 3);
            stanowiska.add(stanowisko);
        }

    }

    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }
}
