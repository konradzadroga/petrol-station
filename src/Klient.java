import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;

public class Klient extends BasicSimObj {

    int numerKlienta;
    int rodzajPaliwa;
    boolean czyKorzystaZMyjni;
    double czasStartuObserwacji;
    Stanowisko aktualneStanowiskoKlienta;

    public Klient(int numerKlienta) {
        this.numerKlienta = numerKlienta;
        this.rodzajPaliwa = 0;
        this.czyKorzystaZMyjni = Controller.czySkorzystaZMyjni();
    }



    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }
}
