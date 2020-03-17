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


public class ShowArtistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_artist);

        // Récupère l'url afin de l'afficher
        Bundle extra = getIntent().getExtras();
        String url = extra.getString("url");
        String urlSpotify = extra.getString("urlSpotify");

        ArtistResponse(url);
        // ArtistResponseSpotify(urlSpotify);
    }

    // Mets fin à l'activité et retourne sur l'activité précédente
    public void Back(View v) {
        finish();
    }

    private void ArtistResponse(final String url) {
        JsonObjectRequest Request1 = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ObjectMapper mapper = new ObjectMapper();
                        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                        ResponseDataArtist responseData;
                        try {
                            responseData = mapper.readValue(response.toString(), ResponseDataArtist.class);
                            for (Artist p : responseData.getdata()) {

                                // Modifie le titre
                                TextView nom_artiste = (TextView) findViewById(R.id.nom_artist);
                                nom_artiste.setText("Artiste : " + p.getName());

                                // Modifie la cover
                                new DownloadImageTask((ImageView) findViewById(R.id.cover_artist))
                                        .execute(p.getPicture_big());

                                // Modifie le nombre de fans
                                TextView nb_fan = (TextView) findViewById(R.id.nb_fans_artist_deezer);
                                nb_fan.setText(p.getNb_fan());

                                // Modifie le nombre d'album
                                TextView nb_albums = (TextView) findViewById(R.id.nb_albums_artist);
                                nb_albums.setText("Nombre d'albums : " + p.getNb_album());


                                // Modifie le lien Deezer
                                TextView link = (TextView) findViewById(R.id.link_artiste_deezer);
                                link.setText("Lien vers Deezer : " + p.getLink());
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
        queue.add(Request1);
    }

    /*private void ArtistResponseSpotify(final String url) {
        JsonObjectRequest Request1 = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ObjectMapper mapper = new ObjectMapper();
                        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                        ResponseDataArtist responseData;
                        try {
                            responseData = mapper.readValue(response.toString(), ResponseDataArtist.class);
                            for (Artist p : responseData.getdata()) {
                                // Modifie le nombre de fans
                                TextView nb_fan = (TextView) findViewById(R.id.nb_fans_artist_deezer);
                                nb_fan.setText(p.getNb_fan());

                                // Modifie le lien Deezer
                                TextView link = (TextView) findViewById(R.id.link_artiste_deezer);
                                link.setText("Lien vers Deezer : " + p.getLink());
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
        queue.add(Request1);
    }*/
}