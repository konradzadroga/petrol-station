import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.monitors.MonitoredVar;
import dissimlab.simcore.BasicSimObj;

import java.util.LinkedList;

public class Myjnia extends BasicSimObj {

    Stacja stacja;
    LinkedList<Klient> kolejkaKlientowDoMyjni = new LinkedList<Klient>();
    Klient klientAktualnieKorzystajacyZMyjni;
    boolean czyMyjniaWolna;
    StartMycia startMycia;
    KoniecMycia koniecMycia;
    static MonitoredVar monitorKlienciWKolejceDoMyjni;

    public Myjnia(Stacja stacja) {
        this.stacja = stacja;
        this.czyMyjniaWolna = true;
        monitorKlienciWKolejceDoMyjni = new MonitoredVar();
    }


    public void dodajKlientaDoMyjni(Klient klient){
        this.kolejkaKlientowDoMyjni.add(klient);
    }

    public Klient usunKlientaZMyjni() {
        return this.kolejkaKlientowDoMyjni.removeFirst();
    }



    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }
}
