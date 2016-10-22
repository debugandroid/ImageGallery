package com.debugandroid.imagegallery;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Pawan.
 */
public class MediaQuery {
    private  Context context;
    private int count = 0;
    private Cursor cursor;
    List<ImageItem> imageItems;
    public MediaQuery(Context context){
        this.context=context;
    }
    public  List<ImageItem> getAllImage() {
        String selection = null;

        cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null,
                selection,
                null,
                null);

        imageItems = new ArrayList<ImageItem>();
        ImageItem imageItem;
        while (cursor.moveToNext()) {
            imageItem = new ImageItem();
            imageItem.setDATA(cursor.getString(1));
            imageItem.setCREATED(getCreatedDate(cursor.getString(1)));
            imageItem.setDISPLAY_NAME(getFileName(cursor.getString(1)));
            imageItems.add(imageItem);
        }
    return imageItems;
    }

    public  static String getCreatedDate(String filePath){
        File file =new File(filePath);
        long currentTime=System.currentTimeMillis();
        long lastmodified=file.lastModified();
        String createdTime;

        SimpleDateFormat formatter = new SimpleDateFormat("HH:MM dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(lastmodified);
        return formatter.format(calendar.getTime());
    }
    public static String getFileName(String path) {
        if (path == null || path.length() == 0) {
            return "";
        }

        int query = path.lastIndexOf('?');
        if (query > 0) {
            path = path.substring(0, query);
        }

        int filenamePos = path.lastIndexOf(File.separatorChar);
        return (filenamePos >= 0) ? path.substring(filenamePos + 1) : path;
    }

}
