package com.constructor.sidisas.constructor;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.constructor.sidisas.constructor.Models.FichaHogar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by PC-1 on 12/3/2018.
 */

public class Save {
    private Context TheThis;
    private String NameOfFolder = "/Nuevacarpeta";
    private String NameOfFile = "imagen";
    public final int MY_PERMISSION_REQUEST = 1;

    public void SaveImage(Context context, Bitmap ImageToSave, Activity activity) {

        TheThis = context;
        if (ActivityCompat.checkSelfPermission(TheThis, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(TheThis, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Log.i("GPS","Faltan permisos SD");
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},MY_PERMISSION_REQUEST);
            return;
        }
        String state = Environment.getExternalStorageState();
        boolean perm=Environment.MEDIA_MOUNTED.equals(state);
        if (!perm){

        }
        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + NameOfFolder;
        String CurrentDateAndTime = getCurrentDateAndTime();
        File dir = new File(file_path);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(dir, NameOfFile + CurrentDateAndTime + ".jpg");

        try {
            FileOutputStream fOut = new FileOutputStream(file);
            ImageToSave.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
            String imgBase64=ImageUtil.convert(ImageToSave);
            Log.i("base64",imgBase64);
            FichaHogar ficha=new FichaHogar();
            ficha.guardarFicha(TheThis,imgBase64);
            fOut.flush();
            fOut.close();
            MakeSureFileWasCreatedThenMakeAvabile(file);
            AbleToSave();
        }

        catch(FileNotFoundException e) {
            Log.e("fileNott",e.getMessage());
            UnableToSave();
        }
        catch(IOException e) {
            Log.e("ioExec",e.getMessage());
            UnableToSave();
        }

    }

    private void MakeSureFileWasCreatedThenMakeAvabile(File file){
        MediaScannerConnection.scanFile(TheThis,
                new String[] { file.toString() } , null,
                new MediaScannerConnection.OnScanCompletedListener() {

                    public void onScanCompleted(String path, Uri uri) {
                    }
                });
    }

    private String getCurrentDateAndTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-­ss");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }

    private void UnableToSave() {
        Toast.makeText(TheThis, "¡No se ha podido guardar la imagen!", Toast.LENGTH_SHORT).show();
    }

    private void AbleToSave() {
        Toast.makeText(TheThis, "Imagen guardada en la galería.", Toast.LENGTH_SHORT).show();
    }

    private void alertMessageNoGps() {
        final AlertDialog.Builder builder= new AlertDialog.Builder(TheThis);
        builder.setMessage("El gps no esta habilitado conceda permisos").setCancelable(false)
                .setPositiveButton("Yes conceder", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TheThis.startActivity(new Intent(Settings.ACTION_INTERNAL_STORAGE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        final AlertDialog alert= builder.create();
        alert.show();
    }
}
