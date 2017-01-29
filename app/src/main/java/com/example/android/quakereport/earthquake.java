package com.example.android.quakereport;

import static com.example.android.quakereport.R.id.date;

/**
 * Created by samuel on 01-16-17.
 */

public class earthquake {
    private String mCity;

    private Long mDate;

    //private int mImageResourceId = NO_IMAGE_PROVIDED;

    private double mMagnitud;

    private String mUrl;
    // Constant created because this serve as a compare value that fit with the imageResourseId,
    // and it is setting to a -1 because the id will be out of this value.
    //private static final int NO_IMAGE_PROVIDED = -1;

    /**
     * Create a new word object
     *
     * @param city the place where the event is aproximately near
     * @param date when the event hapenned
     */
    public earthquake(String city, Long date, double magnitud, String url){
        mCity = city;
        mDate = date;
        mMagnitud = magnitud;
        mUrl = url;
    }

    /**
     * Create a new word object and show a icon at his side
     *
     * @param defaultTranslation is the word in english
     * @param miwokTranslation is the word in miwok language
     * @param image is the id for the image resource inside the app

    public earthquake(String defaultTranslation, String miwokTranslation, int image, int audioResourceId){
        mDefaultTranslation = defaultTranslation;
        mMiworkTranslation = miwokTranslation;
        mImageResourceId = image;
        mAudioResourceId = audioResourceId;
    }
    */
    public String getmCity() {
        return mCity;
    }

    public Long getmDate() {
        return mDate;
    }

    /**
    public int getmImageResourceId(){
        return mImageResourceId;
    }
    */

    /**
     * This provide us a method to verify that the object word has a image, this bolean povide us
     * the skill to decide if we insert and image or not inside the array and the listview
     * @return


    public boolean hasImage(){
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }
    */

    /**
     *
     * @return return the mAudioResourceId to get the id that will identify the resource in the
     * media instance mediaPlayer = MediaPlayer.create(NumbersActivity.this, itemClicked.getmAudioResourceId());
     */

    public double getmMagnitud() {
        return mMagnitud;
    }

    /**
     * Returns the string representation of the {@link Word} object.

    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiworkTranslation + '\'' +
                ", mAudioResourceId=" + mAudioResourceId +
                ", mImageResourceId=" + mImageResourceId +
                '}';
    }
    */

    /**
     * this method return the Url
     * @return url to get the site of the earthquake
     */
    public String getmUrl() {
        return mUrl;
    }
}
