package modelo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Jorge on 08/04/2017.
 */

public class GestionPermisos extends AppCompatActivity {

    private int SMS_CODE=10;
    private Context ctx;
    private Activity act;

    public GestionPermisos(Context ctx,Activity act){
        this.ctx=ctx;
        this.act=act;
    }

    public boolean comprobarPermisoSMS(){
        //Getting the permission status
        int result = ContextCompat.checkSelfPermission(ctx, Manifest.permission.SEND_SMS);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED){
            return true;
        }else{
            return false;
        }
    }

    public void pedirPermisoSMS(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(act,Manifest.permission.SEND_SMS)){
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(act,new String[]{Manifest.permission.SEND_SMS,Manifest.permission.READ_PHONE_STATE},SMS_CODE);
    }

    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if(requestCode == SMS_CODE){

            //If permission is granted
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //Displaying a toast
                Toast.makeText(ctx,"Permission granted",Toast.LENGTH_LONG).show();
            }else{
                //Displaying another toast if permission is not granted
                Toast.makeText(ctx,"You just denied the permission",Toast.LENGTH_LONG).show();
            }
            if(grantResults.length >0 && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                //Displaying a toast
                Toast.makeText(ctx,"Permission granted",Toast.LENGTH_LONG).show();
            }else{
                //Displaying another toast if permission is not granted
                Toast.makeText(ctx,"You just denied the permission",Toast.LENGTH_LONG).show();
            }
        }
    }
}
