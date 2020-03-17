package com.project.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import java.io.IOException;


public class ShowAlbumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_album);

        // Récupère l'url afin de l'afficher
        Bundle extra = getIntent().getExtras();
        String url = extra.getString("url");

        AlbumResponse(url);
    }

    // Mets fin à l'activité et retourne sur l'activité précédente
    public void Back(View v) {
        finish();
    }

    private void AlbumResponse(final String url) {
        JsonObjectRequest Request2 = new JsonObjectRequest(Request.Method.GET, url, null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ObjectMapper mapper = new ObjectMapper();
                        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                        ResponseDataAlbum responseData;
                        try {
                            responseData = mapper.readValue(response.toString(), ResponseDataAlbum.class);
                            for (Album p : responseData.getdata()) {

                                // Modifie le titre
                                TextView titre_album = (TextView) findViewById(R.id.titre_album);
                                titre_album.setText("Titre : " + p.getTitle());

                                // Modifie la cover
                                new DownloadImageTask((ImageView) findViewById(R.id.cover_album))
                                        .execute(p.getCover_big());

                                // Modifie le nombre de morceau
                                TextView nb_morceaux = (TextView) findViewById(R.id.nb_morceaux_album);
                                nb_morceaux.setText("Nombre de morceaux : " + p.getNb_tracks());

                                //TextView nb_albums = (TextView) findViewById(R.id.nbFans);
                                //nb_albums.setText("Nombre d'albums : " + p.get());

                                // Modifie le lien Deezer
                                TextView link = (TextView) findViewById(R.id.link_album);
                                link.setText("Lien vers Deezer : " + p.getLink());


                                //Log.d("Response", p.getName());
                            }
                        } catch (IOException e) {

                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Response", url);
                    }
                });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(Request2);
    }
}