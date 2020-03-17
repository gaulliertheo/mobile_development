package com.project.musicapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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


public class ShowTrackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_track);

        // Récupère l'url afin de l'afficher
        Bundle extra = getIntent().getExtras();
        String url = extra.getString("url");

        TrackResponse(url);
    }

    // Mets fin à l'activité et retourne sur l'activité précédente
    public void Back(View v) {
        finish() ;
    }

    private void TrackResponse(final String  url) {
        JsonObjectRequest Request3 = new JsonObjectRequest(Request.Method.GET, url, null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ObjectMapper mapper = new ObjectMapper();
                        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                        ResponseDataTrack responseData;
                        try {
                            responseData = mapper.readValue(response.toString(), ResponseDataTrack.class);

                            for (Track p : responseData.getdata()) {

                                // Modifie le titre
                                TextView titre = (TextView) findViewById(R.id.titre_track);
                                titre.setText("Titre : " + p.getTitle());

                                /* Log.d("RESPONSE", p.getAlbum().toString());

                                // Modifie la cover
                                new DownloadImageTask((ImageView) findViewById(R.id.cover_track))
                                        .execute(p.getAlbum().getCover_big()); */

                                // Modifie le ranking Deezer
                                TextView ranking = (TextView) findViewById(R.id.ranking_track);
                                ranking.setText("Classement : " + p.getRank());

                                // Modifie le lien Deezer
                                TextView link = (TextView) findViewById(R.id.link_track);
                                link.setText("Lien vers Deezer : " + p.getLink());

                                // Modifie le lien de preview
                                TextView preview = (TextView) findViewById(R.id.preview);
                                preview.setText("Lien vers un extrait : " + p.getPreview());
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
        queue.add(Request3);
    }



}