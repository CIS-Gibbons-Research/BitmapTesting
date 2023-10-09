package css.bitmaptesting;

import androidx.appcompat.app.AppCompatActivity;
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
    Bitmap imageBitmap;

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
        setupProcess1();
    }

    private void setupButton1() {
        buttonSample1 = findViewById(R.id.buttonSample1);
        buttonSample1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("CIS4444","Button 1 onClick");
                imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sample_a);
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
                imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sample_b);
                mainViewModel.loadBitmap(imageBitmap);
                imageViewCamera.setImageBitmap(imageBitmap);
                bitmapAvailable = true;
            }
        });
    }

    private void setupProcess1() {
        buttonSample1 = findViewById(R.id.buttonProcess);
        buttonSample1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("CIS4444","Process Button onClick");
                if (bitmapAvailable) {
                    Log.i("CIS4444","Bitmap ready for processing");
                    analyzeBitmap();
                }

            }
        });
    }


    private void analyzeBitmap() {

        Bitmap temp = extractSubwindow(imageBitmap, 900, 800, 1000, 1000);
        if (temp!=null){
            imageBitmap = temp;
            mainViewModel.loadBitmap(imageBitmap);
            imageViewCamera.setImageBitmap(imageBitmap);
        }
        //analysis logic
        //populate map of regions from viewModel
        //map = mainViewModel.getRegionMap();
        // Update UI with intensities -- mainViewModel.updateUIWithCircleIntensities formats String for us
        //tvStatus.setText(mainViewModel.updateUIWithCircleIntensities(map));
        //start chemical analysis with map as param
        //chemicalAnalysis = new ChemicalAnalysis(map);
        //tvStatus.setText(chemicalAnalysis.getChemicalReading().toString());
    }

    // from chat GPT
    public static Bitmap extractSubwindow(Bitmap sourceBitmap, int x, int y, int width, int height) {
        if (sourceBitmap == null || x < 0 || y < 0 || width <= 0 || height <= 0) {
            return null;
        }
        int sourceWidth = sourceBitmap.getWidth();
        int sourceHeight = sourceBitmap.getHeight();

        // Ensure the provided coordinates are within bounds
        if (x + width > sourceWidth || y + height > sourceHeight) {
            return null;
        }
        // Create a new bitmap for the sub-window
        Bitmap subwindowBitmap = Bitmap.createBitmap(sourceBitmap, x, y, width, height);
        return subwindowBitmap;
    }

}