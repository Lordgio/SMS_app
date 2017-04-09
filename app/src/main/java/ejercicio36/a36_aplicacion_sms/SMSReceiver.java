package ejercicio36.a36_aplicacion_sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.telephony.SmsMessage;
import android.widget.Toast;

import modelo.GestionMensajes;
import modelo.Mensaje;

public class SMSReceiver extends BroadcastReceiver {
@RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Mensaje recibido",Toast.LENGTH_LONG).show();
        String origen="";
        String texto="";
        Object[] trozos=(Object[])intent.getExtras().get("pdus");
        for(int i=0;i<trozos.length;i++){
            SmsMessage sms=SmsMessage.createFromPdu((byte[])trozos[i],"3gpp");
            texto+=sms.getMessageBody();
            origen=sms.getOriginatingAddress();
        }

        Mensaje m=new Mensaje(origen,texto);
        GestionMensajes gmens=new GestionMensajes(context,"mensajes");
        gmens.guardarMensaje(m);
        ListadoActivity lstAct=new ListadoActivity();
        lstAct.cargarLista();

        Toast.makeText(context,origen+": "+texto,Toast.LENGTH_LONG).show();
    }
}
