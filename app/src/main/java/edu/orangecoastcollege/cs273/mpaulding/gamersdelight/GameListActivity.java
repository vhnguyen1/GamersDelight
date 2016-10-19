package edu.orangecoastcollege.cs273.mpaulding.gamersdelight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;

import java.util.List;

public class GameListActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Game> gamesList;
    private GameListAdapter gamesListAdapter;
    private ListView gamesListView;

    private EditText nameEditText;
    private EditText descriptionEditText;
    private RatingBar gameRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);

        this.deleteDatabase(DBHelper.DATABASE_NAME);
        db = new DBHelper(this);

        db.addGame(new Game("League of Legends", "Multiplayer online battle arena", 4.5f, "lol.png"));
        db.addGame(new Game("Dark Souls III", "Action role-playing", 4.0f, "ds3.png"));
        db.addGame(new Game("The Division", "Single player experience", 3.5f, "division.png"));
        db.addGame(new Game("Doom FLH", "First person shooter", 2.5f, "doomflh.png"));
        db.addGame(new Game("Battlefield 1", "Single player campaign", 5.0f, "battlefield1.png"));

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        descriptionEditText = (EditText) findViewById(R.id.descriptionEditText);
        gameRatingBar = (RatingBar) findViewById(R.id.gameListRatingBar);

        // TODO:  Populate all games from the database into the list
        gamesList = db.getAllGames();
        // TODO:  Create a new ListAdapter connected to the correct layout file and list
        gamesListAdapter = new GameListAdapter(this, R.layout.game_list_item, gamesList);
        // TODO:  Connect the ListView with the ListAdapter
        gamesListView = (ListView) findViewById(R.id.gameListView);
        gamesListView.setAdapter(gamesListAdapter);
    }

    public void viewGameDetails(View view) {

        // TODO: Use an Intent to start the GameDetailsActivity with the data it needs to correctly inflate its views.
        Intent gamesListIntent = new Intent(this, GameDetailsActivity.class);

        gamesListIntent.putExtra("", "Name");
        gamesListIntent.putExtra("", "Description");
        gamesListIntent.putExtra("", "Rating");
        gamesListIntent.putExtra("", "Image");

        startActivity(gamesListIntent);
    }

    public void addGame(View view)
    {
        // TODO:  Add a game to the database, list, list adapter
        Game newGame = new Game("", "", 0.0f, "avatar.png");
        gamesListAdapter.add(newGame);
        // TODO:  Make sure the list adapter is updated
        gamesListAdapter.notifyDataSetChanged();
        // TODO:  Clear all entries the user made (edit text and rating bar)
        nameEditText.setText("");
        descriptionEditText.setText("");
        gameRatingBar.setRating(0.0f);
    }

    public void clearAllGames(View view)
    {
        // TODO:  Delete all games from the database and lists
        gamesList.clear();
        db.deleteAllGames();
        gamesListAdapter.notifyDataSetChanged();
    }
}