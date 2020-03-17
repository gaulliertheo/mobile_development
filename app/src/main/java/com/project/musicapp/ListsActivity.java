package com.project.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class ListsActivity extends AppCompatActivity {

    ListView search_music;
    Spinner search_category;
    ArrayAdapter<String> adapterSearch;
    ArrayAdapter<CharSequence> adapterSpinner;
    ArrayList<String> array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);
        search_music = findViewById(R.id.search_music);
        search_category = findViewById(R.id.search_category);
        Button button_rechercher = findViewById(R.id.button_rechercher);

        // Se lance à chaque fois que l'utilisateur clique sur un élément de la liste
        search_music.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View v, int position, long id) {
                String extra = arg0.getItemAtPosition(position).toString();
                Go_to_another_activity(extra);
            }
        });

        // Se lance à chaque fois que l'utilisateur rentre une valeur et clique sur button_rechercher
        button_rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.extra_input);
                String extra = editText.getText().toString();
                Go_to_another_activity(extra);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem itemSM = menu.findItem(R.id.search_music);
        MenuItem itemCat = menu.findItem(R.id.search_category);

        SearchView searchView = (SearchView) itemSM.getActionView();
        search_category = (Spinner) itemCat.getActionView();
        search_music = findViewById(R.id.search_music);

        array = new ArrayList<>();

        adapterSpinner = ArrayAdapter.createFromResource(this,
                R.array.categories, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        search_category.setAdapter(adapterSpinner);

        // Se lance à chaque fois que le texte de searchView change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterSearch.getFilter().filter(newText);
                return false;
            }
        });

        // Change de list en fonction du spinner selectionné
        search_category.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected_val = search_category.getSelectedItem().toString();

                // Efface les données précédentes
                array.clear();

                // Affectation d'une liste à l'array en fonction de la valeur du spinner
                switch (selected_val) {
                    case "Artistes":
                        array.addAll(Arrays.asList(getResources().getStringArray(R.array.artistes)));
                        break;
                    case "Albums":
                        array.addAll(Arrays.asList(getResources().getStringArray(R.array.albums)));
                        break;
                    case "Titres":
                        array.addAll(Arrays.asList(getResources().getStringArray(R.array.titres)));
                        break;
                }
                adapterSearch = new ArrayAdapter<>(
                        ListsActivity.this,
                        android.R.layout.simple_list_item_1,
                        array
                );
                search_music.setAdapter(adapterSearch);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    // Fonction qui lance une des activités suivantes
    private void Go_to_another_activity(String extra) {
        String selected_val = search_category.getSelectedItem().toString();
        Intent intent;
        String param;
        String url;
        String urlSpotify = null;

        // Remplacement des espaces (" ") par "%20" pour que la recherche puisse s'effectuer sans soucis
        param = extra.replace(" ", "%20");

        // Affectation d'une classe à l'intent en fonction de la valeur du spinner
        switch (selected_val) {
            case "Artistes":
                intent = new Intent(getApplicationContext(), ShowArtistActivity.class);
                urlSpotify = "https://api.spotify.com/v1/search?q=" + param + "&type=artist&limit=1";
                param = "/search/artist/?q=" + param + "&limit=1&output=json";
                break;
            case "Albums":
                intent = new Intent(getApplicationContext(), ShowAlbumActivity.class);
                param = "/search/album/?q=" + param + "&limit=1&output=json";
                break;
            case "Titres":
                intent = new Intent(getApplicationContext(), ShowTrackActivity.class);
                param = "/search/track/?q=" + param + "&limit=1&output=json";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + selected_val);
        }

        // Mise au singulier de la valeur du spinner
        selected_val = selected_val.replace("s", "");

        // Création d'un Toast qui rappel le choix que l'utilisateur a fait
        Toast.makeText(getApplicationContext(), selected_val + " - " + extra,
                Toast.LENGTH_SHORT).show();

        url = "https://api.deezer.com" + param;

        // Ajout de la recherche dans l'intent
        intent.putExtra("url", url);
        intent.putExtra("urlSpotify",urlSpotify);

        // Démarrage de l'activity souhaité
        startActivity(intent);
    }


}
