package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by samuel on 01-16-17.
 */

public class earthquakeAdapter extends ArrayAdapter<earthquake> {

    /**
     * Resource Id for the background color for this list of words
     */
    private int mColorResourceId;

    private static final String LOCATION_SEPARATOR = " of ";

    //not used in the adapter
    //private static final String LOG_TAG = earthquakeAdapter.class.getSimpleName();

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param word A List of Android objects to display in a list
     *             colorResourceId is the color setted to back graound
     */
    public earthquakeAdapter(Activity context, List<earthquake> earthquakes, int colorResourceId){
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, earthquakes);
        mColorResourceId = colorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        // Get the {@link AndroidFlavor} object located at this position in the list
        earthquake currentEarthquake = getItem(position);



        // ---->    Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameMagnitudView = (TextView) listItemView.findViewById(R.id.magnitud);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        String formattedMagnitude = formatMagnitude(currentEarthquake.getmMagnitud());

        nameMagnitudView.setText(formattedMagnitude);

        //----------> this is the way that we can change the gradient color on a

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) nameMagnitudView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getmMagnitud());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        //--------------->
        String cityAndDistanceFrom = currentEarthquake.getmCity();

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView numberCityView = (TextView) listItemView.findViewById(R.id.city);

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView numberDistanceCityView = (TextView) listItemView.findViewById(R.id.from_city);

        if(cityAndDistanceFrom.contains(LOCATION_SEPARATOR)){
            String[] cityView = cityAndDistanceFrom.split(LOCATION_SEPARATOR);

            // Get the version number from the current AndroidFlavor object and
            // set this text on the number TextView
            numberCityView.setText(cityView[1]);

            // Get the version number from the current AndroidFlavor object and
            // set this text on the number TextView
            numberDistanceCityView.setText(cityView[0] + LOCATION_SEPARATOR);
        } else {
            numberCityView.setText(cityAndDistanceFrom);
            numberDistanceCityView.setText("Near of ");
        }


        //---------------->
        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentEarthquake.getmDate());

        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);

        /**
        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.image);


        if(currentWord.hasImage()){
            // Get the image resource ID from the current AndroidFlavor object and
            // set the image to iconView
            iconView.setImageResource(currentWord.getmImageResourceId());

            //set the visibility of the view in the activity
            iconView.setVisibility(View.VISIBLE);

        } else {
            iconView.setVisibility(View.GONE);
        }

        // este es el contenedor de texto del child linear layout en la list item
        //set the theme color for the list item
        */
        View textContainer = listItemView.findViewById(R.id.text_container);
        //Find the color that the resource ID maps // TODO: 01-17-17
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        //set the background color of the text container View
        Log.v("Color", "este es el color" + color);
        textContainer.setBackgroundColor(color);

        /**
        // en el list item lo que se hace es que se ubican las cosas en la imagen y secciones de
        // texto de acuerdo a los arreglos que hace el adapter
        // esto permite que al contenedor del texto que es el child linear layout se identifique
        // y se le asigne el color mediante la extension de un nuevo parametro en el nuevo
        //  word adapter
        //return super.getView(position, convertView, parent);

        */

        return listItemView;
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorForCircleBackground;
        int magnitudeColor = (int) Math.floor(magnitude);

        switch(magnitudeColor) {
            case 1:
                magnitudeColorForCircleBackground = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorForCircleBackground = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorForCircleBackground = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorForCircleBackground = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorForCircleBackground = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorForCircleBackground = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorForCircleBackground = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorForCircleBackground = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorForCircleBackground = R.color.magnitude9;
                break;
            default:
                magnitudeColorForCircleBackground = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorForCircleBackground);
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

}
