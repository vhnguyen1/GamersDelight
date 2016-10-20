package edu.orangecoastcollege.cs273.mpaulding.gamersdelight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.List;

import static edu.orangecoastcollege.cs273.mpaulding.gamersdelight.R.id.descriptionEditText;
import static edu.orangecoastcollege.cs273.mpaulding.gamersdelight.R.id.nameEditText;

public class GameListActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Game> gamesList;
    private GameListAdapter gamesListAdapter;
    private ListView gamesListView;
    private LinearLayout gameLinearLayout;

    private EditText gameNameEditText;
    private EditText gameDescriptionEditText;
    private RatingBar gameRatingBar;
    //private ImageView gameImageView;

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

        gameLinearLayout = (LinearLayout) findViewById(R.id.gameListLinearLayout);
        gameNameEditText = (EditText) findViewById(nameEditText);
        gameDescriptionEditText = (EditText) findViewById(descriptionEditText);
        gameRatingBar = (RatingBar) findViewById(R.id.gameRatingBar);

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

        final Game game = (Game) view.getTag();

        Toast.makeText(this, "Loading " + game.getName() + "...", Toast.LENGTH_SHORT).show();

        String gameName = game.getName();
        String gameDescription = game.getDescription();
        String gameRating = String.valueOf(game.getRating());
        String gameImageName = game.getImageName();

        gamesListIntent.putExtra("Name", gameName);
        gamesListIntent.putExtra("Description", gameDescription);
        gamesListIntent.putExtra("Rating", gameRating);
        gamesListIntent.putExtra("Image", gameImageName);

        startActivity(gamesListIntent);
    }

    public void addGame(View view) {
        // TODO:  Add a game to the database, list, list adapter
        //gameNameEditText = (EditText) findViewById(nameEditText);
        //gameDescriptionEditText = (EditText) findViewById(descriptionEditText);
        //gameRatingBar = (RatingBar) findViewById(R.id.gameRatingBar);

        String gameName = gameNameEditText.getText().toString();
        String temp = gameName.replaceAll("\\s+","");

        if (temp.isEmpty())
            Toast.makeText(this, "Game name cannot be empty.", Toast.LENGTH_SHORT).show();
        else {
            String gameDescription = gameDescriptionEditText.getText().toString();
            temp = gameDescription.replaceAll("\\s+","");

            if (temp.isEmpty())
                Toast.makeText(this, "Game description cannot be empty.", Toast.LENGTH_SHORT).show();
            else {
                String gameImageName = "avatar.png";
                float gameRating = gameRatingBar.getRating();

                //Game newGame = new Game("", "", 0.0f, "avatar.png");
                Game newGame = new Game(gameName, gameDescription, gameRating, gameImageName);

                db.addGame(newGame);
                gamesList.add(newGame);
                gamesListAdapter.add(newGame);

                Toast.makeText(this, gameName + " added successfully!", Toast.LENGTH_SHORT).show();

                // TODO:  Make sure the list adapter is updated
                gamesListAdapter.notifyDataSetChanged();

                // TODO:  Clear all entries the user made (edit text and rating bar)
                gameNameEditText.setText("");
                gameDescriptionEditText.setText("");
                gameRatingBar.setRating(0.0f);
            }
        }
    }

    public void clearAllGames(View view) {
        // TODO:  Delete all games from the database and lists
        if (!gamesList.isEmpty()) {
            gamesList.clear();
            db.deleteAllGames();
            gamesListAdapter.notifyDataSetChanged();
        }
        else
            Toast.makeText(this, "Game list is already empty.", Toast.LENGTH_SHORT).show();
    }
}