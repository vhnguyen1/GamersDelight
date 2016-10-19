package edu.orangecoastcollege.cs273.mpaulding.gamersdelight;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

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
/*
        //AssetManager am = this.getAssets();
        AssetManager am = context.getAssets();
        try {
            InputStream stream = am.open(intentFromGameListActivity.getStringExtra("Image"));
            Drawable event = Drawable.createFromStream(stream, intentFromGameListActivity.getStringExtra("Image"));
            gameDetailsImageView.setImageDrawable(event);
        }
        catch (IOException err) {
            Log.e("Gamers Delight", "Error Loading Image" + err.getMessage(), err);
        }
*/
        gameDetailsNameTextView.setText(intentFromGameListActivity.getStringExtra("Name"));
        gameDetailsDescriptionTextView.setText(intentFromGameListActivity.getStringExtra("Description"));
        //gameDetailsRatingBar.setRating(Float.parseFloat(intentFromGameListActivity.getStringExtra("Rating")));

        //this.finish();
    }
}
