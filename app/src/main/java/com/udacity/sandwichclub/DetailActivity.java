package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;


public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    TextView originIv;
    TextView descriptionIv;
    TextView ingredientsIv;
    TextView also_knownIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageIv = findViewById(R.id.image_iv);

        originIv = findViewById(R.id.origin_tv);
        descriptionIv = findViewById(R.id.description_tv);
        ingredientsIv = findViewById(R.id.ingredients_tv);
        also_knownIv = findViewById(R.id.also_known_tv);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(imageIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        descriptionIv.setText(sandwich.getDescription());

        if (sandwich.getPlaceOfOrigin().isEmpty()) {
            ingredientsIv.setText(R.string.not_available);
        } else {
            ingredientsIv.setText(sandwich.getIngredients().toString());
        }
        if (sandwich.getPlaceOfOrigin().isEmpty()) {
            originIv.setText(R.string.not_available);
        } else {
            originIv.setText(sandwich.getPlaceOfOrigin());
        }
        if (sandwich.getAlsoKnownAs().isEmpty()) {
            also_knownIv.setText(R.string.not_available);
        } else {
            also_knownIv.setText(sandwich.getAlsoKnownAs().toString());
        }
    }
}
