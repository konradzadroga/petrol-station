import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;

import java.util.LinkedList;

public class Kasa extends BasicSimObj
{
    LinkedList<Klient> listaKlientow = new LinkedList<Klient>();
    Stacja stacja;
    StartPlatnosci startPlatnosci;
    KoniecPlatnosci koniecPlatnosci;
    Klient klienciWKasach[] = new Klient[Controller.ileKas];
    boolean wolneKasy[] = new boolean[Controller.ileKas];

    public Kasa(Stacja stacja) {
        this.stacja = stacja;

        for (int i=0; i<Controller.ileKas; i++) {
            this.wolneKasy[i] = true;
        }

    }

    public int zwrocNumerWolnejKlasy() {
        for (int i=0; i<this.wolneKasy.length; i++) {
            if (this.wolneKasy[i]==true) {
                return i;
            }
        }
        return -1;
    }

    public void dodajKlienta(Klient klient){
        this.listaKlientow.add(klient);
    }

    public void usunKlienta() {
        this.listaKlientow.removeFirst();
    }





    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }
}
