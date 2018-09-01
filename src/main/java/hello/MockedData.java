package hello;

import java.util.Date;

public final class MockedData {

    String descricao = "Feira para comprar frutas e vegetais";
    String titulo = "Feira orgânicos";
    Date date = new Date();
    String end = "Sicredi digital";
    long duration = 1896938735;

    public final Evento event1 = new Evento(descricao,titulo,date,date,end, duration);
    public Evento getEvent(){
        return event1;
    }

}
