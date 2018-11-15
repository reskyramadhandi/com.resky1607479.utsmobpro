package com.resky1607479.utsmobpro;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovieDetailActivity extends AppCompatActivity {

    public static String MOVIE_ID = "movie_id";

    private static String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w780";
    private static String YOUTUBE_VIDEO_URL = "http://www.youtube.com/watch?v=%s";
    private static String YOUTUBE_THUMBNAIL_URL = "http://img.youtube.com/vi/%s/0.jpg";

    private ImageView movieBackdrop;
    private ImageView moviePoster;
    private TextView movieTitle;
    private TextView featuredCrewLabel;
    private TextView movieCast;
    private TextView movieCrew;
    private TextView movieOverview;
    private TextView movieOverviewLabel;

    private MoviesRepository moviesRepository;
    private int movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        movieId = getIntent().getIntExtra(MOVIE_ID, movieId);

        moviesRepository = MoviesRepository.getInstance();

        initUI();

        getMovie();
    }

    private void initUI() {
        movieBackdrop = findViewById(R.id.movieDetailsBackdrop);
        moviePoster = findViewById(R.id.movieDetailsPoster);
        movieTitle = findViewById(R.id.movieDetailsTitle);
        featuredCrewLabel = findViewById(R.id.movieDetailsFeaturedCrew);
        movieCrew = findViewById(R.id.movieDetailsCrew);
        movieCast = findViewById(R.id.movieDetailsCast);
        movieOverviewLabel = findViewById(R.id.movieDetailsOverviewTitle);
        movieOverview = findViewById(R.id.movieDetailsOverviewText);
    }

    private void getMovie() {
        moviesRepository.getMovie(movieId, new OnGetMovieCallback() {
            @Override
            public void onSuccess(Movie movie) {
                movieTitle.setText(movie.getTitle()+"  ("+parseDateToddMMyyyy(movie.getReleaseDate())+")");
                movieOverviewLabel.setVisibility(View.VISIBLE);
                movieOverview.setText(movie.getOverview());
                Glide.with(movieBackdrop)
                        .load(IMAGE_BASE_URL + movie.getBackdrop())
                        .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                        .into(movieBackdrop);
                movieBackdrop.setColorFilter(Color.rgb(123, 123, 123), android.graphics.PorterDuff.Mode.MULTIPLY);
                Glide.with(moviePoster)
                        .load(IMAGE_BASE_URL + movie.getPosterPath())
                        .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                        .into(moviePoster);
            }

            @Override
            public void onError() {
                finish();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public String parseDateToddMMyyyy(String time) {
        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    private void showError() {
        Toast.makeText(MovieDetailActivity.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
    }
}
