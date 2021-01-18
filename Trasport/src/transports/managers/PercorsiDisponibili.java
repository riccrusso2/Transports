package transports.managers;

import transports.domain_entities.Città;
import transports.domain_entities.Percorso;
import transports.exceptions.RichiestaNonSoddisfabileException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * questa classe rappresenta tutti i percorsi disponibili,quindi percorribili per un viaggio
 */
public class PercorsiDisponibili {
    Collection<Percorso> listaPercorsi;




    public PercorsiDisponibili(){
        this.listaPercorsi = new ArrayList<Percorso>();
    }

    /**
     * scorre la lista di percorsi disponbili e controlla se uno di essi contiene le città passate
     * @param origine
     * @param destinazione
     * @return il percorso compatibile con le città passate
     * @throws RichiestaNonSoddisfabileException se non è possibile soddisfare la richiesta del cliente
     */

    public Percorso percorsoDisponibile(Città origine, Città destinazione) throws RichiestaNonSoddisfabileException {
        for(Percorso p : listaPercorsi){
            if(p.contiene(origine,destinazione)){
                return p;
            }
        }
        throw new RichiestaNonSoddisfabileException("la richiesta non è soddisfabile");
    }
}
