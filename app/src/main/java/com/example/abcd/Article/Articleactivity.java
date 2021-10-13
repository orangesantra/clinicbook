package com.example.abcd.Article;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.abcd.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Articleactivity extends AppCompatActivity {

    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> urls = new ArrayList<>();

    ArrayAdapter arrayAdapter;

    SQLiteDatabase articlesDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articleactivity);


        RecyclerView recyclerView =  findViewById(R.id.articlerecyclerview);
        Myadapterarticle adapter = new Myadapterarticle(this, Naturemodelarticle.getObjectList());
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);//for linear configuration of layout

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        articlesDB = this.openOrCreateDatabase("Articles1", MODE_PRIVATE, null);

        articlesDB.execSQL("CREATE TABLE IF NOT EXISTS articles1 (id INTEGER PRIMARY KEY, articleId INTEGER, title VARCHAR,  url VARCHAR)");



        DownloadTask task = new DownloadTask();
        try {

            task.execute("http://newsapi.org/v2/top-headlines?country=in&category=health&apiKey=4a55dc19f80d4b79a0027b55c3339cb4");

        } catch (Exception e) {

        }

//        ListView listView = findViewById(R.id.ArticlelistView);
//        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, titles);
//        listView.setAdapter(arrayAdapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(getApplicationContext(), Articledetails.class);
//                intent.putExtra("content", urls.get(i));
//
//                startActivity(intent);
//            }
//        });

        updateListView();
    }

    public void updateListView() {
        //till here all the titles and urls associated with those title.are stored in the 'articles1' table of 'articlesDB'.
        //now when we query then we will deal with downloaded titles and urls.
        Cursor c = articlesDB.rawQuery("SELECT * FROM articles1", null);

        int urlIndex = c.getColumnIndex("url");
        int titleIndex = c.getColumnIndex("title");

        if (c.moveToFirst()) {//removing from database tables
            titles.clear();
            urls.clear();

            do {

                titles.add(c.getString(titleIndex));//adding to arraylists.
                urls.add(c.getString(urlIndex));//adding to arraylists.

            } while (c.moveToNext());

            arrayAdapter.notifyDataSetChanged();
        }
    }

    String articleTitle;
    String articleUrl;
    int articleId;

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {

                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = urlConnection.getInputStream();

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                int data = inputStreamReader.read();

                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = inputStreamReader.read();
                }



                articlesDB.execSQL("DELETE FROM articles1");




                    JSONObject jsonObject = new JSONObject(result);

                    String results1 = jsonObject.getString("articles");

                    JSONArray arr = new JSONArray(results1);

                for (int i=0; i < arr.length(); i++) {
                    JSONObject jsonPart = arr.getJSONObject(i);

                    articleId=i;
                    String s1=Integer.toString(i);

                        articleTitle = jsonPart.getString("title");//get information of key 'title'.
                        articleUrl = jsonPart.getString("url");
                        Log.i("Title and URL",articleTitle+articleUrl);


                        String sql = "INSERT INTO articles1 (articleId, title, url) VALUES (?, ?, ?)";
                        SQLiteStatement statement = articlesDB.compileStatement(sql);
                        statement.bindString(1,s1);
                        statement.bindString(2,articleTitle);
                        statement.bindString(3,articleUrl);

                        statement.execute();



                }




                Log.i("URL Content", result);
                return result;

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            updateListView();//do this when "do in background is finished".
        }
    }
}