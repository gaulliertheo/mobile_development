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
import java.util.HashMap;
import java.util.Map;


public class ShowArtistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_artist);

        // Récupère l'url afin de l'afficher
        Bundle extra = getIntent().getExtras();
        String url = extra.getString("url");
        String urlSpotify = extra.getString("urlSpotify");


        ArtistResponseSpotify(urlSpotify);
        ArtistResponse(url);
    }

    // Mets fin à l'activité et retourne sur l'activité précédente
    public void Back(View v) {
        finish();
    }

    private void ArtistResponseSpotify(final String urlSpotify) {

        JsonObjectRequest Request1 = new JsonObjectRequest(Request.Method.GET, urlSpotify, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ObjectMapper mapper = new ObjectMapper();
                        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                        ResponseDataArtistSpotify responseData;
                        Log.d("Response", response.toString());
                        try {
                            responseData = mapper.readValue(response.toString(), ResponseDataArtistSpotify.class);
                            for (Object p : responseData.getArtists().getItems()) {

                                // Modifie le nombre de fans Spotify
                                TextView nb_fan = (TextView) findViewById(R.id.nb_fans_artist_spotify);
                                nb_fan.setText(p.getFollowers().getTotal());
                                Log.d("Response",p.getFollowers().getTotal());


                                // Modifie le lien Spotify
                                TextView link = (TextView) findViewById(R.id.link_artist_spotify);
                                link.setText("Lien vers Spotify : " + responseData.getArtists().getHref());
                            }
                        } catch (IOException e) {

                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {

                HashMap<String, String> params = new HashMap<>();
                params.put("Authorization", "Bearer BQDFLAwI_temaRcgPUWU2JWzwCNNVk7wWt-xK3GGtZT0VAgj5O1qxG9eN6qCxCcUviaJr9Rk4-afvgslGDmMvQEa8uGf5cpAKK5K5e9t9NdzuLxNhTni5RiwUsRncFuNlA6_UOmjAIKmm4C9JmPUF6TLdUM\n");
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(Request1);
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

                                // Modifie le nombre de fans Deezer
                                TextView nb_fan = (TextView) findViewById(R.id.nb_fans_artist_deezer);
                                nb_fan.setText(p.getNb_fan());

                                // Modifie le nombre d'album
                                TextView nb_albums = (TextView) findViewById(R.id.nb_albums_artist);
                                nb_albums.setText("Nombre d'albums : " + p.getNb_album());


                                // Modifie le lien Deezer
                                TextView link = (TextView) findViewById(R.id.link_artist_deezer);
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
}