package css.bitmaptesting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    MainViewModel mainViewModel;   // View Model for the main activity
    TextView tvStatus;
    Button buttonSample1;
    Button buttonSample2;

    ImageView imageViewCamera;
    Map<String, Integer> map;
    ChemicalAnalysis chemicalAnalysis;
    private boolean bitmapAvailable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set up UI components
        tvStatus = findViewById(R.id.tvStatus);
        imageViewCamera = findViewById(R.id.imageView);
        // set up modelView
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // set up buttons
        setupButton1();
        setupButton2();
    }

    private void setupButton1() {
        buttonSample1 = findViewById(R.id.buttonSample1);
        buttonSample1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("CIS4444","Button 1 onClick");
                Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sample_a);
                mainViewModel.loadBitmap(imageBitmap);
                imageViewCamera.setImageBitmap(imageBitmap);
                bitmapAvailable = true;
            }
        });
    }

    private void setupButton2() {
        buttonSample1 = findViewById(R.id.buttonSample2);
        buttonSample1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("CIS4444","Button 2 onClick");
                Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sample_b);
                mainViewModel.loadBitmap(imageBitmap);
                imageViewCamera.setImageBitmap(imageBitmap);
                bitmapAvailable = true;
            }
        });
    }

    private void analyzeBitmap() {

        //analysis logic
        //populate map of regions from viewModel
        //map = mainViewModel.getRegionMap();
        // Update UI with intensities -- mainViewModel.updateUIWithCircleIntensities formats String for us
        //tvStatus.setText(mainViewModel.updateUIWithCircleIntensities(map));
        //start chemical analysis with map as param
        //chemicalAnalysis = new ChemicalAnalysis(map);
        //tvStatus.setText(chemicalAnalysis.getChemicalReading().toString());
    }

}