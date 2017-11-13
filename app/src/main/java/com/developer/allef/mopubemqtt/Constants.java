package com.developer.allef.mopubemqtt;

/**
 * Created by allef on 12/11/2017.
 */

public class Constants {

    /**
     * url do serviço do eclipse paar mensageria
     */
    public static final String MQTT_BROKER_URL = "tcp://iot.eclipse.org:1883";

    /**
     * nome do canal que recebera as mensagens publicadas
     */
    public static final String PUBLISH_TOPIC = "EntreemContato";

    /**
     * id do cliente gerada pelo "Servidor MQTT"
     */
    public static final String CLIENT_ID = "PushMessageAllefSSantos";
}
