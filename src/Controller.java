import dissimlab.monitors.Diagram;
import dissimlab.monitors.Statistics;
import dissimlab.random.SimGenerator;
import dissimlab.simcore.SimControlEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;
import dissimlab.simcore.SimParameters;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.*;
import java.util.Properties;
import java.util.Random;

public class Controller {
    public static SimGenerator simGenerator = new SimGenerator();
    @FXML
    public TextField prawdopodobienstwoMyjnia;
    @FXML
    public TextField liczbaKasTextField;
    @FXML
    public TextField ileStanowiskBenzynaTextField;
    @FXML
    public TextField ileStanowiskONTextField;
    @FXML
    public TextField maxDlugoscKolejkiDoStanowiskaTextField;
    @FXML
    public TextField ileStanowiskLPGTextField;
    @FXML
    public TextField rozkladCzasuTankowaniaBenzynyTextField;
    @FXML
    public TextField ustawGranicaPrawaCTBTextField;
    @FXML
    public TextField rozkladOdstepMiedzyKlientamiTextField;
    @FXML
    public TextField granicaLewaROPKTextField;
    @FXML
    public TextField granicaPrawaROPKTextField;
    @FXML
    public TextField ustawGranicaLewaCTBTextField;
    @FXML
    public TextField rozkladCzasuTankowaniaLPGTextField;
    @FXML
    public TextField ustawGranicaPrawaCTLTextField;
    @FXML
    public TextField ustawGranicaLewaCTLTextField;
    @FXML
    public TextField rozkladCzasuTankowaniaONTextField;
    @FXML
    public TextField ustawGranicaPrawaCTOTextField;
    @FXML
    public TextField ustawGranicaLewaCTOTextField;
    @FXML
    public TextField rozkladCzasuPlatnosciTextField;
    @FXML
    public TextField granicaPrawaPlatnosciTextField;
    @FXML
    public TextField granicaLewaPlatnosciTextField;
    @FXML
    public TextField rozkladCzasuMyciaTextField;
    @FXML
    public TextField granicaPrawaMyciaTextField;
    @FXML
    public TextField granicaLewaMyciaTextField;
    @FXML
    public Button rozpocznijSymulacjePrzycisk;
    @FXML
    public TextField simulationTimeTextField;

    public static double czyMyjnia;
    public static int ileKas;
    public static int ileStanowiskBenzyna;
    public static int ileStanowiskLPG;
    public static int ileStanowiskON;
    public static int maxDlugoscKolejkiDoStanowiska;
    public static int rozkladOdstepPomiedzyKlientami;
    public static double granicaLewaROPK;
    public static double granicaPrawaROPK;
    public static int rozkladCzasuTankowaniaBenzyny;
    public static double granicaLewaCTB;
    public static double granicaPrawaCTB;
    public static int rozkladCzasuTankowaniaLPG;
    public static double granicaLewaCTL;
    public static double granicaPrawaCTL;
    public static int rozkladCzasuTankowaniaON;
    public static double granicaLewaCTO;
    public static double granicaPrawaCTO;
    public static int rozkladCzasuPlatnosci;
    public static double granicaLewaCP;
    public static double granicaPrawaCP;
    public static int rozkladCzasuMycia;
    public static double granicaLewaCM;
    public static double granicaPrawaCM;
    public static double simulationTime;

    @FXML
    public void initialize() throws FileNotFoundException {
        Properties properties = new Properties();
        String InFile = "input.properties";
        InputStream inputVariables = null;
        try {
            inputVariables = new FileInputStream(InFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            properties.load(inputVariables);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File OutFile = new File("log.txt");
        PrintStream ps = new PrintStream(OutFile);
        System.setOut(ps);

        prawdopodobienstwoMyjnia.setText(properties.getProperty("czyMyjnia"));
        liczbaKasTextField.setText(properties.getProperty("ileKas"));
        ileStanowiskBenzynaTextField.setText(properties.getProperty("ileStanowiskBenzyna"));
        ileStanowiskLPGTextField.setText(properties.getProperty("ileStanowiskLPG"));
        ileStanowiskONTextField.setText(properties.getProperty("ileStanowiskON"));
        maxDlugoscKolejkiDoStanowiskaTextField.setText(properties.getProperty("maxDlugoscKolejkiDoStanowiska"));
        rozkladOdstepMiedzyKlientamiTextField.setText(properties.getProperty("rozkladOdstepPomiedzyKlientami"));
        granicaLewaROPKTextField.setText(properties.getProperty("granicaLewaROPK"));
        granicaPrawaROPKTextField.setText(properties.getProperty("granicaPrawaROPK"));
        rozkladCzasuTankowaniaBenzynyTextField.setText(properties.getProperty("rozkladCzasuTankowaniaBenzyny"));
        ustawGranicaLewaCTBTextField.setText(properties.getProperty("granicaLewaCTB"));
        ustawGranicaPrawaCTBTextField.setText(properties.getProperty("granicaPrawaCTB"));
        rozkladCzasuTankowaniaLPGTextField.setText(properties.getProperty("rozkladCzasuTankowaniaLPG"));
        ustawGranicaLewaCTLTextField.setText(properties.getProperty("granicaLewaCTL"));
        ustawGranicaPrawaCTLTextField.setText(properties.getProperty("granicaPrawaCTL"));
        rozkladCzasuTankowaniaONTextField.setText(properties.getProperty("rozkladCzasuTankowaniaON"));
        ustawGranicaLewaCTOTextField.setText(properties.getProperty("granicaLewaCTO"));
        ustawGranicaPrawaCTOTextField.setText(properties.getProperty("granicaPrawaCTO"));
        rozkladCzasuPlatnosciTextField.setText(properties.getProperty("rozkladCzasuPlatnosci"));
        granicaLewaPlatnosciTextField.setText(properties.getProperty("granicaLewaCP"));
        granicaPrawaPlatnosciTextField.setText(properties.getProperty("granicaPrawaCP"));
        rozkladCzasuMyciaTextField.setText(properties.getProperty("rozkladCzasuMycia"));
        granicaLewaMyciaTextField.setText(properties.getProperty("granicaLewaCM"));
        granicaPrawaMyciaTextField.setText(properties.getProperty("granicaPrawaCM"));
        simulationTimeTextField.setText(properties.getProperty("simulationTime"));

    }



    public static boolean czySkorzystaZMyjni() {
        Random generatorPrawdopodobienstwa = new Random();
        if (czyMyjnia > generatorPrawdopodobienstwa.nextDouble()) {
            return true;
        }
        return false;
    }

    public static double generujLosowoWedlugRozkladu (int rodzajRozkladu, double granicaLewa, double granicaPrawa) {
        if (rodzajRozkladu == 0)
            return Math.abs(simGenerator.normal(granicaLewa, granicaPrawa));
        else if (rodzajRozkladu == 1)
            return Math.abs(simGenerator.uniform(granicaLewa, granicaPrawa));
        else if (rodzajRozkladu == 2)
            return Math.abs(simGenerator.gamma(granicaLewa, granicaPrawa));
        else if (rodzajRozkladu == 3)
            return Math.abs(simGenerator.beta(granicaLewa, granicaPrawa));
        else
            return 0;
    }

    public static double generujLosowyCzasOdstepuMiedzyKlientami() {
        return generujLosowoWedlugRozkladu(rozkladOdstepPomiedzyKlientami, granicaLewaROPK, granicaPrawaROPK);
    }

    public static double generujLosowyCzasTankowaniaBenzyny() {
        return generujLosowoWedlugRozkladu(rozkladCzasuTankowaniaBenzyny, granicaLewaCTB, granicaPrawaCTB);
    }

    public static double generujLosowyCzasTankowaniaLPG() {
        return generujLosowoWedlugRozkladu(rozkladCzasuTankowaniaLPG, granicaLewaCTL, granicaPrawaCTL);
    }

    public static double generujLosowyCzasTankowaniaON() {
        return generujLosowoWedlugRozkladu(rozkladCzasuTankowaniaON, granicaLewaCTO, granicaPrawaCTO);
    }

    public static double generujLosowyCzasPlatnosci() {
        return generujLosowoWedlugRozkladu(rozkladCzasuPlatnosci, granicaLewaCP, granicaPrawaCP);
    }

    public static double generujLosowyCzasMycia() {
        return generujLosowoWedlugRozkladu(rozkladCzasuMycia, granicaLewaCM, granicaPrawaCM);
    }




    public void rozpocznijSymulacje(ActionEvent actionEvent) {

        czyMyjnia = Double.parseDouble(prawdopodobienstwoMyjnia.getText());
        ileKas = Integer.parseInt(liczbaKasTextField.getText());
        ileStanowiskBenzyna = Integer.parseInt(ileStanowiskBenzynaTextField.getText());
        ileStanowiskLPG = Integer.parseInt(ileStanowiskLPGTextField.getText());
        ileStanowiskON = Integer.parseInt(ileStanowiskONTextField.getText());
        maxDlugoscKolejkiDoStanowiska = Integer.parseInt(maxDlugoscKolejkiDoStanowiskaTextField.getText());
        rozkladOdstepPomiedzyKlientami = Integer.parseInt(rozkladOdstepMiedzyKlientamiTextField.getText());
        granicaLewaROPK = Double.parseDouble(granicaLewaROPKTextField.getText());
        granicaPrawaROPK = Double.parseDouble(granicaPrawaROPKTextField.getText());
        rozkladCzasuTankowaniaBenzyny = Integer.parseInt(rozkladCzasuTankowaniaBenzynyTextField.getText());
        granicaLewaCTB = Double.parseDouble(ustawGranicaLewaCTBTextField.getText());
        granicaPrawaCTB = Double.parseDouble(ustawGranicaPrawaCTBTextField.getText());
        rozkladCzasuTankowaniaLPG = Integer.parseInt(rozkladCzasuTankowaniaLPGTextField.getText());
        granicaLewaCTL = Double.parseDouble(ustawGranicaLewaCTLTextField.getText());
        granicaPrawaCTL = Double.parseDouble(ustawGranicaPrawaCTLTextField.getText());
        rozkladCzasuTankowaniaON = Integer.parseInt(rozkladCzasuTankowaniaONTextField.getText());
        granicaLewaCTO = Double.parseDouble(ustawGranicaLewaCTOTextField.getText());
        granicaPrawaCTO = Double.parseDouble(ustawGranicaPrawaCTOTextField.getText());
        rozkladCzasuPlatnosci = Integer.parseInt(rozkladCzasuPlatnosciTextField.getText());
        granicaLewaCP = Double.parseDouble(granicaLewaPlatnosciTextField.getText());
        granicaPrawaCP = Double.parseDouble(granicaPrawaPlatnosciTextField.getText());
        rozkladCzasuMycia = Integer.parseInt(rozkladCzasuMyciaTextField.getText());
        granicaLewaCM = Double.parseDouble(granicaLewaMyciaTextField.getText());
        granicaPrawaCM = Double.parseDouble(granicaPrawaMyciaTextField.getText());
        simulationTime = Double.parseDouble(simulationTimeTextField.getText());



        try {
            SimManager simManager = SimManager.getInstance();
            Stacja stacja = new Stacja();
            SimControlEvent stopEvent = new SimControlEvent(simulationTime, SimParameters.SimControlStatus.STOPSIMULATION);
            simManager.startSimulation();


            System.out.println("Oczekiwana graniczna liczba samochodów w kolejkach do stanowisk = "
                        + Statistics.arithmeticMean(Stanowisko.monitorKlienciWKolejceDoStanowiska));

            System.out.println("Oczekiwana graniczna liczba samochodów w kolejkach do myjnii = "
                    + Statistics.arithmeticMean(Myjnia.monitorKlienciWKolejceDoMyjni));
            System.out.println(
                    "Oczekiwany graniczny czas tankowania samochodu= " + Statistics.arithmeticMean(Stacja.monitorCzasTankowania));
            System.out
                    .println("Oczekiwany graniczny czas mycia samochodu = " + Statistics.arithmeticMean(Stacja.monitorCzasMycia));

            System.out.println("Graniczne prawdopodobieństwo rezygnacji z obsługi przez kierowcę samochodu = "
                    + Stacja.liczbaKlientowKtorzyNieZmiesciliSieWKolejce / Stacja.liczbaWszystkichKlientow);



            Diagram diagram1 = new Diagram(Diagram.DiagramType.HISTOGRAM, "Czas Mycia");
            diagram1.add(Stacja.monitorCzasMycia, Color.BLUE);
            diagram1.show();

            Diagram diagram2 = new Diagram(Diagram.DiagramType.HISTOGRAM, "Czas Tankowania");
            diagram2.add(Stacja.monitorCzasTankowania, Color.GREEN);
            diagram2.show();

            Diagram diagram3 = new Diagram(Diagram.DiagramType.TIME_FUNCTION, "Ilosc w kolejce do myjni");
            diagram3.add(Myjnia.monitorKlienciWKolejceDoMyjni, Color.RED);
            diagram3.show();

            Diagram diagram4 = new Diagram(Diagram.DiagramType.TIME_FUNCTION, "Ilosc w kolejce do stanowiska");
            diagram4.add(Stanowisko.monitorKlienciWKolejceDoStanowiska, Color.ORANGE);
            diagram4.show();

        } catch (SimControlException e) {
            e.printStackTrace();
        }

    }
}
