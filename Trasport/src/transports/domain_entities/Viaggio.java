package transports.domain_entities;

import transports.exceptions.RichiestaNonSoddisfabileException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * questa classe rappresenta un viaggio possibile(definito da un percorso,data di inizio e fine,
 * una serie di clienti associati ad esso e gli autocarri)
 */

public class Viaggio {

    /**
     * tutti gli attributi != null
     * len(cliente)>=1
     * len(autocarri)>=1
     */
    private Percorso percorso;
    private Date dataInizio;
    private Date dataFine;
    private Collection<Cliente> clienti;
    private Collection<Autocarro> autocarri;





    public Viaggio(Percorso percorso,Date dataInizio,Date dataFine){
        this.percorso = percorso;
        this.dataFine = dataFine;
        this.dataInizio = dataInizio;
        clienti = new ArrayList<Cliente>();
        autocarri= new ArrayList<Autocarro>();
    }
    /**
     *
     * @param origine
     * @param destinazione
     * @return vero se le città sono contenute nel percorso del viaggio
     */
    public boolean contieneCitta(Città origine,Città destinazione){
        return this.percorso.contiene(origine,destinazione);
    }

    /**
     *
     * @param dataInizio
     * @param dataFine
     * @return vero se le date passate sono comprese nelle date del viaggio,falso altrimenti
     */
    public boolean contieneDate(Date dataInizio,Date dataFine) throws RichiestaNonSoddisfabileException {
        return(this.dataInizio.before(dataInizio)&&this.dataFine.after(dataFine));
    }

    /**
     * modifica this aggiungendo il cliente passato al viaggio(il cliente ha prenotato questo viaggio)
     * @param cliente
     */
    public void aggiungiCliente(Cliente cliente){
        clienti.add(cliente);

    }

    /**
     * modifica this aggiungendo un autocarro al viaggio(è stato prenotato il viaggio con questo autocarro)
     * @param autocarro
     */
    public void aggiungiAutocarro(Autocarro autocarro){
        autocarri.add(autocarro);

    }

    /**
     * modifica this.percorso con il percorso passato
     * @param percorso
     */
    public void modificaPercorso(Percorso percorso){
        this.percorso= percorso;
    }

    /**
     *
     * @return la lista di clienti associati al percorso
     */
    public Collection<Cliente> getClienti(){
        return clienti;
    }


    public Date[] getDate(){
        Date[] date= new Date[1];
        date[0]=dataInizio;
        date[1]=dataFine;
        return date;
    }
}


