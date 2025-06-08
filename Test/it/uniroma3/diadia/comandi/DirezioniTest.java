package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Direzione;

class DirezioniTest {

    @Test
    void testOposta() {
        assertEquals(Direzione.SUD, Direzione.NORD.getDirezioneOpposta());
        assertEquals(Direzione.NORD, Direzione.SUD.getDirezioneOpposta());
        assertEquals(Direzione.OVEST, Direzione.EST.getDirezioneOpposta());
        assertEquals(Direzione.EST, Direzione.OVEST.getDirezioneOpposta());
    }
    
    
    //nota bene che il test fallisce se cambio l'ordine di dichiarazione nell'enum
    @Test
    void testOrdinal() {
        assertEquals(0, Direzione.NORD.ordinal());
        assertEquals(1, Direzione.SUD.ordinal());
        assertEquals(2, Direzione.EST.ordinal());
        assertEquals(3, Direzione.OVEST.ordinal());
    }
    
    @Test
    void testToString() {
    	assertEquals(Direzione.NORD.toString(), "nord");
    	assertEquals(Direzione.SUD.toString(), "sud");
    	assertEquals(Direzione.EST.toString(), "est");
    	assertEquals(Direzione.OVEST.toString(), "ovest");
    }

}
