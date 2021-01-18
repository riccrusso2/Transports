package transports.domain_entities;

import java.util.List;

/**
 * questa classe rappresenta un percorso che un viaggio può fare
 */
public class Percorso {
    /**
     * sequenza ordinata città [città1,città2...]
     * cittaPercorso !=null
     * len(cittàpercorso)>0
     */
    List<Città> cittàPercorso;


    /**
     *
     * @param città1
     * @param città2
     * @return vero se il percorso contiene entrambe le città,falso altrimenti
     */
    public boolean contiene(Città città1,Città città2){
        return (cittàPercorso.contains(città1) && cittàPercorso.contains(città2));
    }




}
