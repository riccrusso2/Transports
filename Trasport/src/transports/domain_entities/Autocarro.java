package transports.domain_entities;

import transports.enumerators.Tmerce;

/**
 * questa classe rappresenta l'autocarro e i periodi in cui esso è occupato
 */
public class Autocarro {
    /**
     * capacità>=0
     * datainizio e datafine possono essere vuote-> autocarro completamente disponibile
     * le stesse posizioni delle due liste corrispondono alla data d'inizio e data fine (prenotazione autocarro da ,a)
     */
    private int capacità;
    private Tmerce tipoMerce;




    public int getCapacità(){
        return this.capacità;
    }


    public Tmerce getTmerce(){
        return this.tipoMerce;
    }



}
