package com.catalyst.travller.app.asyntask;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by rankush.agrawal on 07-02-2016.
 */
public class MapRouteDownloadTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... url) {

        String data = "";
        try {
            data = downloadUrl(url[0]);
        } catch (Exception e) {
            Log.d("Background Task", e.toString());
        }
        return data;
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        iMapJsonDownloadListener.onDownloadComplete(result);

    }

    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            iStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            data = sb.toString();
            br.close();

        } catch (Exception e) {
            Log.d("Excptn downloading url", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }
    IMapJsonDownloadListener iMapJsonDownloadListener;

    public void setMapJsonDownLoadListener(IMapJsonDownloadListener downloadListener){
        iMapJsonDownloadListener = downloadListener;
    }
    public interface IMapJsonDownloadListener{
        void onDownloadComplete(String result);
    }

}
