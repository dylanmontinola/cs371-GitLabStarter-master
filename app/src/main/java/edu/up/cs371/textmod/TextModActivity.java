package edu.up.cs371.textmod;

/**
 * class TextModActivity
 *
 * Allow text to be modified in simple ways with button-presses.
 */
import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayList;

public class TextModActivity extends ActionBarActivity {

    // array-list that contains our images to display
    private ArrayList<Bitmap> images;

    public Button copyButton;
    //private TextView text;
    private Spinner spinner;
    private Button button6;
    private Button button7;
    private Button button9;



    // instance variables containing widgets
    private ImageView imageView; // the view that shows the image
    private Button reverseButton;
    private TextView editText;
    /**
     * @see Activity#onCreate(Bundle)
     */

    private Button button;
    private TextView text;
    /**
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    private GoogleApiClient client;

    protected void onCreate(Bundle savedInstanceState) {


        // perform superclass initialization; load the layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_mod);

        button6 = (Button)findViewById(R.id.button6);
        button7 = (Button)findViewById(R.id.button7);
        button9 = (Button)findViewById(R.id.button9);

        text = (EditText) findViewById(R.id.editText);

        button6.setOnClickListener(new upperButtonListener());
        button7.setOnClickListener(new lowerButtonListener());
        button9.setOnClickListener(new noSpaceButtonListener());
        // set instance variables for our widgets
        imageView = (ImageView) findViewById(R.id.imageView);

        button = (Button) findViewById(R.id.button);
        text = (TextView) findViewById(R.id.editText);
        button.setOnClickListener(new cButtonListener());


        text = (TextView)findViewById(R.id.editText);

        copyButton = (Button)findViewById(R.id.copyname);
        copyButton.setOnClickListener(new copyNameListener());

        editText = (TextView)findViewById(R.id.editText);
        reverseButton = (Button)findViewById(R.id.ReverseButton);
        reverseButton.setOnClickListener(new ReverseButtonListener());
        // Set up the spinner so that it shows the names in the spinner array resources
        //
        // get spinner object

        spinner = (Spinner)findViewById(R.id.spinner);
        // get array of strings
        String[] spinnerNames = getResources().getStringArray(R.array.spinner_names);
        // create adapter with the strings
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, spinnerNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // bind the spinner and adapter
        spinner.setAdapter(adapter);

        // load the images from the resources
        //
        // create the arraylist to hold the images
        images = new ArrayList<Bitmap>();
        // get array of image-resource IDs
        TypedArray imageIds2 = getResources().obtainTypedArray(R.array.imageIdArray);
        // loop through, adding one image per string
        for (int i = 0; i < spinnerNames.length; i++) {
            // determine the index; use 0 if out of bounds
            int id = imageIds2.getResourceId(i,0);
            if (id == 0) id = imageIds2.getResourceId(0,0);
            // load the image; add to arraylist
            Bitmap img = BitmapFactory.decodeResource(getResources(), id);
            images.add(img);
        }

        // define a listener for the spinner
        spinner.setOnItemSelectedListener(new MySpinnerListener());


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
    private class upperButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            String i = text.getText().toString().toUpperCase();
            text.setText(i);
        }
    }
    private class lowerButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            String i = text.getText().toString().toLowerCase();
            text.setText(i);
        }
    }
    private class noSpaceButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            String i = text.getText().toString();
            i = i.replaceAll("\\s","");
            text.setText(i);
        }
    }
    /**
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "TextMod Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://edu.up.cs371.textmod/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "TextMod Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://edu.up.cs371.textmod/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


    private class cButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            text.setText(" ");
        }
    }

    /**
     * @see Activity#onCreateOptionsMenu(Menu)
     */
    private class copyNameListener implements View.OnClickListener
    {
        public void onClick(View view)
        {
            String poop = text.getText().toString();
            String piss = spinner.getSelectedItem().toString();

            text.setText(poop+piss);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_text_mod, menu);
        return true;
    }

    /**
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * class that handles our spinner's selection events
     */
    private class MySpinnerListener implements OnItemSelectedListener {

        /**
         * @see android.widget.AdapterView.OnItemSelectedListener#onItemSelected(
         *                  android.widget.AdapterView, android.view.View, int, long)
         */
        @Override
        public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                   int position, long id) {
            // set the image to the one corresponding to the index selected by the spinner
            imageView.setImageBitmap(images.get(position));
        }

        /**
         * @see android.widget.AdapterView.OnItemSelectedListener#onNothingSelected(
         *                  android.widget.AdapterView)
         */
        @Override
        public void onNothingSelected(AdapterView<?> parentView) {
            // your code here
        }
    }
    private class ReverseButtonListener implements View.OnClickListener
    {
        public void onClick(View v)
        {
            String tbr = editText.getText().toString();
            String reverse = new StringBuffer(tbr).reverse().toString();
            editText.setText(reverse);

        }

    }
}
