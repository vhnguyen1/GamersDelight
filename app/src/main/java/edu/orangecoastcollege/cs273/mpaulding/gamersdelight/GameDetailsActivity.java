package edu.orangecoastcollege.cs273.mpaulding.gamersdelight;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class GameDetailsActivity extends AppCompatActivity {

    private TextView gameDetailsNameTextView;
    private TextView gameDetailsDescriptionTextView;
    private RatingBar gameDetailsRatingBar;
    private ImageView gameDetailsImageView;

    private Context context = (Context) this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

        // TODO:  Use the Intent object sent from GameListActivity to populate the Views on
        Intent intentFromGameListActivity = getIntent();
        // TODO:  this layout, including the TextViews, RatingBar and ImageView with the Game details.
        gameDetailsImageView = (ImageView) findViewById(R.id.gameDetailsImageView);
        gameDetailsNameTextView = (TextView) findViewById(R.id.gameDetailsNameTextView);
        gameDetailsDescriptionTextView = (TextView) findViewById(R.id.gameDetailsDescriptionTextView);
        gameDetailsRatingBar = (RatingBar) findViewById(R.id.gameDetailsRatingBar);

        String gameDetailsName = intentFromGameListActivity.getStringExtra("Name");
        gameDetailsNameTextView.setText(gameDetailsName);

        String gameDetailsDescription = intentFromGameListActivity.getStringExtra("Description");
        gameDetailsDescriptionTextView.setText(gameDetailsDescription);

        float gameDetailsRating = 0.0f;
        if (intentFromGameListActivity.getStringExtra("Rating") != "")
            gameDetailsRating = Float.parseFloat(intentFromGameListActivity.getStringExtra("Rating"));
        gameDetailsRatingBar.setRating(gameDetailsRating);

        String gameDetailsImageName = "avatar.png";
        if (intentFromGameListActivity.getStringExtra("Image") != "")
            gameDetailsImageName = intentFromGameListActivity.getStringExtra("Image");
        gameDetailsImageName.replace(" ", "");

        //AssetManager am = this.getAssets();
        AssetManager am = context.getAssets();
        try {
            InputStream stream = am.open(gameDetailsImageName);
            Drawable event = Drawable.createFromStream(stream, gameDetailsName);
            gameDetailsImageView.setImageDrawable(event);
        }
        catch (IOException err) {
            Log.e("Gamers Delight", "Error Loading Image " + err.getMessage(), err);
        }
    }
}
