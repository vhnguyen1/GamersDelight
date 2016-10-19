package edu.orangecoastcollege.cs273.mpaulding.gamersdelight;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper class to provide custom adapter for the <code>Game</code> list.
 */
public class GameListAdapter extends ArrayAdapter<Game> {

    private Context mContext;
    private List<Game> mGamesList = new ArrayList<>();
    private int mResourceId;

    private LinearLayout gameListLinearLayout;
    private TextView gameListNameTextView;
    private TextView gameListDescriptionTextView;
    private RatingBar gameRatingBar;
    private ImageView gamesListImageView;

    /**
     * Creates a new <code>GameListAdapter</code> given a mContext, resource id and list of games.
     *
     * @param c The mContext for which the adapter is being used (typically an activity)
     * @param rId The resource id (typically the layout file name)
     * @param games The list of games to display
     */
    public GameListAdapter(Context c, int rId, List<Game> games) {
        super(c, rId, games);
        mContext = c;
        mResourceId = rId;
        mGamesList = games;
    }

    /**
     * Gets the view associated with the layout.
     * @param pos The position of the Game selected in the list.
     * @param convertView The converted view.
     * @param parent The parent - ArrayAdapter
     * @return The new view with all content set.
     */
    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        //TODO:  Code for getting the view of a list item correctly inflated.
        final Game game = mGamesList.get(pos);

        gameListLinearLayout = (LinearLayout) view.findViewById(R.id.gameListLinearLayout);
        gameListNameTextView = (TextView) view.findViewById(R.id.gameListNameTextView);
        gameListDescriptionTextView = (TextView) view.findViewById(R.id.gameListDescriptionTextView);
        gameRatingBar = (RatingBar) view.findViewById(R.id.gameListRatingBar);
        gamesListImageView = (ImageView) view.findViewById(R.id.gameListImageView);

        String gameName = game.getName();
        String gameDescription = game.getDescription();
        float gameRating = game.getRating();
        //String gameImageName = game.getImageName();

        gameListLinearLayout.setTag(game);
        gameListNameTextView.setText(gameName);
        gameListDescriptionTextView.setText(gameDescription);
        gameRatingBar.setRating(gameRating);

        AssetManager am = mContext.getAssets();
        try {
            InputStream stream = am.open(game.getImageName());
            Drawable event = Drawable.createFromStream(stream, game.getName());
            gamesListImageView.setImageDrawable(event);
        }
        catch (IOException err) {
            Log.e("Gamers Delight", "Error Loading Image" + err.getMessage(), err);
        }

        return view;
    }
}
