package ejercicio36.a36_aplicacion_sms;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MensajeActivity extends AppCompatActivity {

    EditText edtNum;
    EditText edtMens;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje);
        Intent intent=this.getIntent();
        String tel=intent.getStringExtra("telefono");
        edtNum=(EditText)this.findViewById(R.id.edtNumero);
        edtMens=(EditText)this.findViewById(R.id.edtMens);
        b=(Button)this.findViewById(R.id.BotonContactos);
        if(tel.equals("null")){
            edtNum.setText("");
            b.setVisibility(View.VISIBLE);
        }else{
            edtNum.setText(tel);
            b.setVisibility(View.GONE);
        }
    }

    public void contactos(View v){

    }

    public void enviar(View v){
        TelephonyManager tMgr = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
        String telf = tMgr.getLine1Number();

        SmsManager manager=SmsManager.getDefault();
        manager.sendTextMessage(edtNum.getText().toString(),telf,edtMens.getText().toString(),null,null);
        Toast.makeText(this,"Mensaje enviado",Toast.LENGTH_LONG).show();
    }
}
