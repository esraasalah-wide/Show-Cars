package com.example.showcars;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private GridView gridview ;
    private CarsAdapter adapter;
    List<Cars> carsDataArr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridview = findViewById(R.id.activity_cars_list);
        adapter = new CarsAdapter(carsDataArr, this);
        gridview.setAdapter(adapter);
        new OurAsyncTask().execute();

    }
    class OurAsyncTask extends AsyncTask<Void, Void, List<Cars>> {

        @Override
        protected List<Cars> doInBackground(Void... voids) {
            List<Cars> list = new ArrayList<>();
            try {
                URL url = new URL("http://demo1286023.mockable.io/api/v1/cars?page=1");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                String jsonString = convertFromInputToString(inputStream);
                Log.d("mytest",jsonString);
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray ourMoviesArray = jsonObject.getJSONArray("results");
                for(int i = 0; i < ourMoviesArray.length() ; i++){
                    JSONObject currentCar = ourMoviesArray.getJSONObject(i);
                    Cars cars = new Cars();

                    cars.setImage(currentCar.getString("imageUrl"));
                    cars.setBrand(currentCar.getString("brand"));
                    cars.setType(currentCar.getBoolean("isUsed"));
                    cars.setConstructionYear(currentCar.getString("constractionYear"));
                    list.add(cars);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return list;
        }

        private String convertFromInputToString(InputStream inputStream) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            try {
                while (((line = bufferedReader.readLine()) != null)){
                    stringBuilder.append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    inputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(List<Cars> carList) {
            super.onPostExecute(carList);
            Log.d("check",carList.toString());
            carsDataArr = carList;
        }
    }
}

