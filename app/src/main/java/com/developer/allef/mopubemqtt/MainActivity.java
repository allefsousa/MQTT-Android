package com.developer.allef.mopubemqtt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.io.UnsupportedEncodingException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.Increver)
    Button subscribe;
    @BindView(R.id.sair)
    Button unSubscribe;
    @BindView(R.id.Publicar)
    Button publishMessage;
    @BindView(R.id.editText)
    EditText textMessage;
    @BindView(R.id.editText2)
    EditText subscribeTopic;
    @BindView(R.id.editText3)
    EditText unSubscribeTopic;

    private MqttAndroidClient client;
    private String TAG = "MainActivity";
    private PahoMqttClient pahoMqttClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        pahoMqttClient = new PahoMqttClient(); // instancia do objeto

        // conectando aao cliente
        client = pahoMqttClient.getMqttClient(getApplicationContext(), Constants.MQTT_BROKER_URL, Constants.CLIENT_ID);

        publishMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = textMessage.getText().toString().trim();
                if (!msg.isEmpty()) {
                    try {
                        pahoMqttClient.publishMessage(client, msg, 1, Constants.PUBLISH_TOPIC);
                        limpar();
                    } catch (MqttException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic = subscribeTopic.getText().toString().trim();
                if (!topic.isEmpty()) {
                    try {
                        pahoMqttClient.subscribe(client, topic, 1);
                        limpar();
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        unSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic = unSubscribeTopic.getText().toString().trim();
                if (!topic.isEmpty()) {
                    try {
                        pahoMqttClient.unSubscribe(client, topic);
                        limpar();
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        /**
         * iniciando o serviço para ficar ouvindo caso receba uma mensagem
         */
        Intent intent = new Intent(MainActivity.this, MqttMessageService.class);
        startService(intent);
    }


    private void limpar(){
        textMessage.setText("");
        subscribeTopic.setText("");
        unSubscribeTopic.setText("");
    }
}
