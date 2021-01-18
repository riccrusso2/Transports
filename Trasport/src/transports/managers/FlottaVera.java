package transports.managers;

import transports.domain_entities.Autocarro;
import transports.domain_entities.PeriodoOccupato;
import transports.enumerators.Tmerce;
import transports.exceptions.RichiestaNonSoddisfabileException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class FlottaVera {

    Collection<PeriodoOccupato> periodi;




    public FlottaVera(){
        periodi = new ArrayList<PeriodoOccupato>();
    }

    public Autocarro autocarroDisponibile(Date dateInizio, Date dataFine, int quantità, Tmerce tipoMerce) throws RichiestaNonSoddisfabileException {
        for(PeriodoOccupato p : periodi){
            if(p.compatibile(dateInizio, dataFine, quantità, tipoMerce)){
                return p.getAutocarro();
            }
        }
        throw new RichiestaNonSoddisfabileException("la richiesta non è soddisfabile");

    }

    public Collection<PeriodoOccupato>getPeriodi(){
    return periodi;}




    /**
     *
     * @param dataInizio
     * @param dataFine
     * @param autocarroCompatibile
     */
    public void prenotaAutocarro(Date dataInizio,Date dataFine,Autocarro autocarroCompatibile) {
        for(PeriodoOccupato p : periodi){
            if(p.getAutocarro().equals(autocarroCompatibile)){
                p.prenotaAutocarro(dataInizio,dataFine);
            }
        }
    }


}



