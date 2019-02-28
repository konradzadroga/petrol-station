import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.monitors.MonitoredVar;
import dissimlab.simcore.BasicSimObj;

import java.util.LinkedList;
import java.util.List;

public class Stanowisko extends BasicSimObj {

    StartTankowania startTankowania;
    KoniecTankowania koniecTankowania;
    boolean czyStanowiskoWolne;
    LinkedList<Klient> listaKlientow = new LinkedList<Klient>();
    Klient klientPrzyStanowisku;
    Stacja stacja;
    int numerStanowiska;
    int typStanowiska;
    static MonitoredVar monitorKlienciWKolejceDoStanowiska;

    public Stanowisko(Stacja stacja, int numerStanowiska, int typStanowiska){
        this.stacja = stacja;
        this.numerStanowiska = numerStanowiska;
        this.typStanowiska = typStanowiska;
        this.czyStanowiskoWolne = true;
        monitorKlienciWKolejceDoStanowiska = new MonitoredVar();
    }

    public void dodajKlientaDoStanowiska(Klient klient){
        this.listaKlientow.add(klient);
    }

    public Klient usunKlientaZeStanowiska() {
        return this.listaKlientow.removeFirst();
    }


    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }
}
