package transports.UserInterface;

import transports.managers.FlottaVera;
import transports.managers.PercorsiDisponibili;

public class Factory {

    public Factory() {
    }

    public AgenziaLogistica createAgenzia(FlottaVera flotta, PercorsiDisponibili percorsiDisponibili){
        return new AgenziaLogistica(flotta, percorsiDisponibili);
    }

    public FlottaVera createFlotta(){
        return new FlottaVera();
    }

    public PercorsiDisponibili createPercorsiDisponibili(){
        return new PercorsiDisponibili();
    }
}
