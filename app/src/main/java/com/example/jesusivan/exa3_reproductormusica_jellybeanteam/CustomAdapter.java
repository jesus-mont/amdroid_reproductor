package com.example.jesusivan.exa3_reproductormusica_jellybeanteam;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by jesusivan on 6/6/2017.
 */

public class CustomAdapter extends ArrayAdapter<Cancion> {
    Context cntApp;
    int iLayout;
    Cancion[] datosCancion;
    public CustomAdapter(Context context, int resource, Cancion[] objects) {
        super(context, resource, objects);
        cntApp=context;
        iLayout =resource;
        datosCancion=objects;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        View vFila=convertView;
        ImageView imgCancion;
        TextView txtnom;

        if(vFila==null){
            LayoutInflater liCrearlayout =((Activity)cntApp).getLayoutInflater();
            vFila= liCrearlayout.inflate(iLayout,parent,false);
        }
        imgCancion=(ImageView)vFila.findViewById(R.id.imgCancion);
        txtnom=(TextView)vFila.findViewById(R.id.txtNombreCancion);
        Cancion dcOb=datosCancion[position];
        imgCancion.setImageResource(dcOb.img);
        txtnom.setText(dcOb.nom);
        return vFila;
    }
}
