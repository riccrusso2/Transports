package transports.UserInterface;

import transports.domain_entities.*;
import transports.exceptions.RichiestaNonSoddisfabileException;
import transports.exceptions.ViaggioNonReinstradabileException;
import transports.managers.FlottaVera;
import transports.managers.PercorsiDisponibili;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Questa classe offre metodi all utente per creare,prenotare e reinstradare un viaggio
 *
 */
public class AgenziaLogistica {
    /**
     * l'agenzia tiene conto di tutti i viaggi disponibili,i percorsi disponibili e gli autocarri con i loro relativi tempi di prenotazione
     * len(viaggiDisponibili)>=0
     * len(Flotta)>=0
     * len(percorsiDisponibili)>=0
     */

    private Collection<Viaggio> viaggiDisponibili;
    private FlottaVera autocarriDisponibili;
    private PercorsiDisponibili percorsiDisponibili;


    public AgenziaLogistica(FlottaVera flotta,PercorsiDisponibili percorsiDisponibili){
        Objects.requireNonNull(flotta);
        Objects.requireNonNull(flotta);
        viaggiDisponibili = new ArrayList<Viaggio>();
        this.viaggiDisponibili=viaggiDisponibili;
        this.percorsiDisponibili=percorsiDisponibili;

    }

    /**
     * controlla se esiste un autocarro compatibile con la richiesta del cliente(date quantita e tipo merce)
     * controlla se esiste un percorso che contiene le citta di origine e destinazione
     * se la richiesta è soddisfatta crea un viaggio aggiungendo ad esso il cliente
     * e l'autocarro prenotato con quelle date
     * modifica this.viaggiDisponibili aggiungendo il viaggio appena creato e lo ritorna
     *
     * @param richiesta
     * @param cliente
     * @throws RichiestaNonSoddisfabileException se non è possibile soddisfare la richiesta del cliente
     */
    public Viaggio CreaViaggio(Richiesta richiesta, Cliente cliente) throws RichiestaNonSoddisfabileException {
        Objects.requireNonNull(richiesta);
        Objects.requireNonNull(cliente);
        Autocarro autocarroCompatibile = richiesta.trovaAutocarro(autocarriDisponibili);
        Percorso percorsoCompatibile = richiesta.trovaPercorso(percorsiDisponibili);
        autocarriDisponibili.prenotaAutocarro(richiesta.getDataInizio(), richiesta.getDataFine(),autocarroCompatibile);

        Viaggio nuovoViaggio = new Viaggio(percorsoCompatibile, richiesta.getDataInizio(), richiesta.getDataFine());
        nuovoViaggio.aggiungiCliente(cliente);
        nuovoViaggio.aggiungiAutocarro(autocarroCompatibile);
        viaggiDisponibili.add(nuovoViaggio);
        return nuovoViaggio;
    }


    /**
     * controlla se è disponinibile un viaggio tra this.viaggiDisponibili che contiene le città e le date della richiesta del cliente
     * se esiste cerchiamo un autocarro che soddisfi anchesso le richieste (date tipo merce e quantità)
     * se esiste aggiungiamo esso al viaggio(prenotandolo) aggiungendo anche il cliente
     * @param richiesta
     * @throws RichiestaNonSoddisfabileException se non esistono viaggi o autoccarri che soddisfino la richiesta del cliente o
     */
    public Viaggio prenotaViaggio(Richiesta richiesta, Cliente cliente) throws RichiestaNonSoddisfabileException {
        Objects.requireNonNull(richiesta);
        Objects.requireNonNull(cliente);
        for (Viaggio viaggio : viaggiDisponibili) {
            if (richiesta.compatibilePerCittà(viaggio) &&
                richiesta.compatibilePerDate(viaggio)) {
                    Autocarro autocarroCompatibile = richiesta.trovaAutocarro(autocarriDisponibili);
                    this.autocarriDisponibili.prenotaAutocarro(richiesta.getDataInizio(), richiesta.getDataFine(),autocarroCompatibile);
                    viaggio.aggiungiAutocarro(autocarroCompatibile);
                    viaggio.aggiungiCliente(cliente);
                    return viaggio;
            }
        }
        throw new RichiestaNonSoddisfabileException("la richiesta non è soddisfabile");
    }

    /**
     * modifica il viaggio passato cambiando il percorso,
     * non possibile se ci sono altri clienti associati ad esso
     *
     * @param viaggio
     * @param percorso
     * @throws ViaggioNonReinstradabileException il viaggio ha più di un ciente associato(quello passato)
     */
    public void reinstradareViaggio(Viaggio viaggio, Percorso percorso) throws ViaggioNonReinstradabileException {
        Objects.requireNonNull(viaggio);
        Objects.requireNonNull(percorso);
        for (Viaggio viaggioCorr : viaggiDisponibili) {
            if (viaggioCorr.equals(viaggio)) {
                if (viaggioCorr.getClienti().size() > 1) {
                    throw new ViaggioNonReinstradabileException("Il viaggio non è reinstradabile");
                } else {
                    viaggioCorr.modificaPercorso(percorso);
                }
            }

        }
    }



}





