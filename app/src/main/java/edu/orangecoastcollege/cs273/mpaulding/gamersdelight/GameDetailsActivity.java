package edu.orangecoastcollege.cs273.mpaulding.gamersdelight;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

        // TODO:  Use the Intent object sent from GameListActivity to populate the Views on
        Intent intentFromGameListActivity = getIntent();
        // TODO:  this layout, including the TextViews, RatingBar and ImageView with the Game details.
        gameDetailsNameTextView = (TextView) findViewById(R.id.gameDetailsNameTextView);
        gameDetailsDescriptionTextView = (TextView) findViewById(R.id.gameDetailsDescriptionTextView);
        gameDetailsRatingBar = (RatingBar) findViewById(R.id.gameRatingBar);
        gameDetailsImageView = (ImageView) findViewById(R.id.gameDetailsImageView);

        gameDetailsNameTextView.setText(intentFromGameListActivity.getStringExtra("Name"));
        gameDetailsDescriptionTextView.setText(intentFromGameListActivity.getStringExtra("Description"));

        //this.finish();
    }
}
