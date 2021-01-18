package transports.domain_entities;

import transports.enumerators.Tmerce;
import transports.exceptions.RichiestaNonSoddisfabileException;
import transports.managers.FlottaVera;
import transports.managers.PercorsiDisponibili;

import java.util.Date;
import java.util.Objects;

/**
 * questa classe rappresenta la richiesta di un cliente nella creazione o prenotazione di un viaggio
 */

public class Richiesta {
    /**
     * tutti gli attributi!= null
     * quantita>0
     * una richiesta tipo del cliente : voglio trasportare [da,a] questa [quantita] di [tipomerce] dal giorno [data] al [data]
     *
     */
    private Città origine;
    private Città destinazione;
    private int quantità;
    private Tmerce tipoMerce;
    private Date dataInizio;
    private Date dataFine;


    public Richiesta(Città origine, Città destinazione,int quantità,Tmerce tipoMerce,Date dataInizio, Date dataFine){
        origine=origine;
        destinazione=destinazione;
        quantità=quantità;
        tipoMerce=tipoMerce;
        dataInizio=dataInizio;
        dataFine= dataFine;
    }

    /**
     *
     * @param viaggio
     * @return vero se this.origine e this.destinazione sono contenute
     *         nel percorso del viaggio passato,falso altrimenti
     * @throws RichiestaNonSoddisfabileException se non è possibile soddisfare la richiesta del cliente
     */
    public boolean compatibilePerCittà(Viaggio viaggio) {
        Objects.requireNonNull(viaggio);
        return viaggio.contieneCitta(this.origine,this.destinazione);

    }





    /**
     *
     * @param viaggio
     * @return vero se le date sono comprese tra le date del viaggio passato,falso altrimenti
     * @throws RichiestaNonSoddisfabileException se non è possibile soddisfare la richiesta del cliente
     */
    public boolean compatibilePerDate(Viaggio viaggio)throws RichiestaNonSoddisfabileException {
        Objects.requireNonNull(viaggio);
        return viaggio.contieneDate(this.dataInizio,this.dataFine);

    }

    /**
     *
     * @param percorsi
     * @return il percorso (se esiste) che contiene le due città di origine e destinazione della richiesta
     * @throws RichiestaNonSoddisfabileException se non è possibile soddisfare la richiesta del cliente
     */
    public Percorso trovaPercorso(PercorsiDisponibili percorsi) throws RichiestaNonSoddisfabileException {
        Objects.requireNonNull(percorsi);
        return percorsi.percorsoDisponibile(this.origine,this.destinazione);
    }


    /**
     *
     * @param flotta
     * @return l'autocarro (se disponibile) che è disponibile per le date della richiesta, che ha una capacità >= della quantità
     *         e il tipo merce uguale
     *@throws RichiestaNonSoddisfabileException se non è possibile soddisfare la richiesta del cliente
     */
    public Autocarro trovaAutocarro(FlottaVera flotta) throws RichiestaNonSoddisfabileException{
        Objects.requireNonNull(flotta);
        return flotta.autocarroDisponibile(this.dataInizio,this.dataFine,this.quantità,this.tipoMerce);

    }


    public Date getDataInizio(){
        return this.dataInizio;
    }



    public Date getDataFine(){
        return this.dataFine;
    }



}
