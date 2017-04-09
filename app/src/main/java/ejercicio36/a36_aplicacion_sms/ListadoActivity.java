package ejercicio36.a36_aplicacion_sms;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import modelo.GestionMensajes;
import modelo.GestionPermisos;
import modelo.Mensaje;

public class ListadoActivity extends AppCompatActivity {

    ListView lst;
    GestionMensajes gmens;
    GestionPermisos gpers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gpers=new GestionPermisos(this,this);
        if(gpers.comprobarPermisoSMS()!=true){
            gpers.pedirPermisoSMS();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        gmens=new GestionMensajes(this,"mensajes");
        lst=(ListView)this.findViewById(R.id.lstMensajes);
        final SimpleCursorAdapter adapter=new SimpleCursorAdapter(this,R.layout.fila,gmens.mostrarDatosCursor(),
                                                            new String[]{"origen","mensaje"},
                                                            new int[]{R.id.tvOrigen,R.id.tvMensaje},
                                                            CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        lst.setAdapter(adapter);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<Mensaje> mens=gmens.mostrarDatosArray();
                Mensaje m=mens.get(position);
                Intent intent=new Intent(ListadoActivity.this,MensajeActivity.class);
                intent.putExtra("telefono",m.getOrigen());
                ListadoActivity.this.startActivity(intent);
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListadoActivity.this,MensajeActivity.class);
                intent.putExtra("telefono","null");
                ListadoActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listado, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void guardarMensaje(Mensaje m){
        gmens.guardarMensaje(m);
    }
}
