package com.example.jesusivan.exa3_reproductormusica_jellybeanteam;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.Random;

public class Principal extends AppCompatActivity implements ListView.OnItemClickListener {
    Intent inService;
    ListView listVCli;
    ArrayList<Integer> previas;
    Context c;
    ImageView imgplay, imgprev, imgnext, imgStop;
LinearLayout control;
    boolean bandera=true;
    RadioButton RBContinua, RBAleatoria,RBRepetir;
    int idcancion,pos=1;
    Cancion datos[]= new Cancion[]{
            new Cancion("cara luna",R.raw.caraluna_bacilos,R.drawable.img),
            new Cancion("mi primer Millon",R.raw.mi_primer_millon_bacilos,R.drawable.img),
            new Cancion("Pasos de Gigante",R.raw.pasos_de_gigante_bacilos,R.drawable.img)

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        c=this.getApplicationContext();
        imgplay=(ImageView) findViewById(R.id.imgplay);
        imgStop=(ImageView) findViewById(R.id.imgStop);
        imgprev=(ImageView) findViewById(R.id.imgPrev);
        imgnext=(ImageView) findViewById(R.id.imgnext);
        RBContinua=(RadioButton)findViewById(R.id.rbContinua);
        RBAleatoria=(RadioButton)findViewById(R.id.RBAleatoria);
        RBRepetir=(RadioButton)findViewById(R.id.RBRepetir);
        control=(LinearLayout)findViewById(R.id.control);
        inService = new Intent(this,MusicPlayer.class);
        listVCli=(ListView)findViewById(R.id.lstCanciones);
        listVCli.setAdapter(new CustomAdapter(this,R.layout.canciones,datos));
        listVCli.setOnItemClickListener(this);
        previas=new ArrayList<>();
        control.setVisibility(View.INVISIBLE);

        imgplay.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( MusicPlayer.mpReproductor.isPlaying()){
                    MusicPlayer.mpReproductor.pause();
                    imgplay.setImageResource(R.drawable.play);
                }else{ MusicPlayer.mpReproductor.start();
                    imgplay.setImageResource(R.drawable.pause);}

            }
        }));
        imgStop.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stopService(inService);

                imgplay.setImageResource(R.drawable.play);
                control.setVisibility(View.INVISIBLE);

            }
        }));
        imgprev.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            prev();
            }
        }));
        imgnext.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            next();

            }

        }));
    }
    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

        pos=i;
        previas.add(pos);
        control.setVisibility(View.VISIBLE);
       reproducir(pos);






    }
    public void next(){

        if (RBContinua.isChecked()){
        pos+=1;}
        else if(RBAleatoria.isChecked()){

           double r= Math.random();
if(r<0.4){
    pos=0;
}
            if(r>0.4&&  r<0.7){
                pos=1;
            }else{pos=2;
            }



        }else if(RBRepetir.isChecked()){

        }


        if(pos>=datos.length){
            pos=0;
        }

        reproducir(pos);
        previas.add(pos);
    }
    public void prev(){

        if (RBContinua.isChecked()){
            pos-=1;}

        if(RBAleatoria.isChecked()){
          if(previas.size()>=1){ pos=previas.get(previas.size()-1);
            previas.remove(previas.size()-1);}

        }

        if (pos<0){
            pos=datos.length-1;

        }
        reproducir(pos);
    }
    public void reproducir(int pos){
        idcancion = datos[pos].cancion;
        inService.putExtra("Cancion",idcancion);
        stopService(inService);
        startService(inService);
        imgplay.setImageResource(R.drawable.pause);
    }
}
