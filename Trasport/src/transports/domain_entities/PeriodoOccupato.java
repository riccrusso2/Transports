package transports.domain_entities;

import transports.enumerators.Tmerce;

import java.util.Date;
import java.util.List;

public class PeriodoOccupato {
    private Autocarro autocarro;
    private List<Date> dateInizio;
    private List<Date> dateFine;


    /**
     *
     * @param dateInizio
     * @param dataFine
     * @param quantità
     * @param tipoMerce
     * @return vero  se l'autocarro ha una capacità maggiore,uno stesso tipo merce e date compatibili,falso altrimenti
     */
    public boolean compatibile(Date dateInizio, Date dataFine, int quantità, Tmerce tipoMerce){
        if(quantità<= autocarro.getCapacità()&& tipoMerce.equals(this.autocarro.getTmerce())&&dateCompatibili(dateInizio,dataFine)){
            return true;
        }else{
            return false;
        }
    }


    private boolean dateCompatibili(Date dataInizio, Date dataFine){
        for(int i= 0;i<dateInizio.size();i++){
            if(dateInizio.get(i).after(dataInizio)||dateFine.get(i).before(dataFine)){
                return false;
            }
        }
        return true;
    }


    /**
     * modifica this aggiungendo le date alle due liste
     * @param dataInizio
     * @param dataFine
     */
    public void prenotaAutocarro(Date dataInizio,Date dataFine){
        this.dateInizio.add(dataInizio);
        this.dateFine.add(dataFine);

    }






    public Autocarro getAutocarro(){
        return this.autocarro;
    }






}
