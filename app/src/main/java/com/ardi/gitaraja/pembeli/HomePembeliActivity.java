package com.ardi.gitaraja.pembeli;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ardi.gitaraja.R;
import com.ardi.gitaraja.adapter.AdapterGitar;
import com.ardi.gitaraja.model.ModelGitar;
import com.ardi.gitaraja.server.BaseURL;
import com.ardi.gitaraja.session.PrefSetting;
import com.ardi.gitaraja.session.SessionManager;
import com.ardi.gitaraja.users.LoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomePembeliActivity extends AppCompatActivity {

    ProgressDialog pDialog;

    AdapterGitar adapter;
    ListView list;

    ArrayList<ModelGitar> newsList = new ArrayList<ModelGitar>();
    private RequestQueue mRequestQueue;

    FloatingActionButton floatingExit;

    SessionManager session;
    SharedPreferences prefs;
    PrefSetting prefSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pembeli);

        prefSetting = new PrefSetting(this);
        prefs = prefSetting.getSharePreference();

        session = new SessionManager(HomePembeliActivity.this);

        prefSetting.isLogin(session, prefs);

        getSupportActionBar().setTitle("Data Gitar");
        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        list = (ListView) findViewById(R.id.array_list);

        floatingExit = (FloatingActionButton) findViewById(R.id.exit);

        newsList.clear();
        adapter = new AdapterGitar(HomePembeliActivity.this, newsList);
        list.setAdapter(adapter);
        getAllGitar();

        floatingExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setLogin(false);
                session.setSessid(0);
                Intent i = new Intent(HomePembeliActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void getAllGitar() {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, BaseURL.dataGitar, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            if (status == false) {
                                Log.d("data gitar = ", response.toString());
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    final ModelGitar gitar = new ModelGitar();
                                    final String _id = jsonObject.getString("_id");
                                    final String Tipegitar = jsonObject.getString("tipeGitar");
                                    final String Kodegitar = jsonObject.getString("kodeGitar");
                                    final String Jenisgitar = jsonObject.getString("jenisGitar");
                                    final String MerkGitar = jsonObject.getString("merkGitar");
                                    final String Hargagitar = jsonObject.getString("hargaGitar");
                                    final String Gambar = jsonObject.getString("gambar");
                                    gitar.setKodeGitar(Kodegitar);
                                    gitar.setTipeGitar(Tipegitar);
                                    gitar.setJenisGitar(Jenisgitar);
                                    gitar.setMerkGitar(MerkGitar);
                                    gitar.setHargaGitar(Hargagitar);
                                    gitar.setGambar(Gambar);
                                    gitar.set_id(_id);

                                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            // TODO Auto-generated method stub
                                            Intent a = new Intent(HomePembeliActivity.this, DetailPembeli.class);
                                            a.putExtra("kodeGitar", newsList.get(position).getKodeGitar());
                                            a.putExtra("_id", newsList.get(position).get_id());
                                            a.putExtra("tipeGitar", newsList.get(position).getTipeGitar());
                                            a.putExtra("jenisGitar", newsList.get(position).getJenisGitar());
                                            a.putExtra("merkGitar", newsList.get(position).getMerkGitar());
                                            a.putExtra("hargaGitar", newsList.get(position).getHargaGitar());
                                            a.putExtra("gambar", newsList.get(position).getGambar());
                                            startActivity(a);
                                        }
                                    });
                                    newsList.add(gitar);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });

        /* Add your Requests to the RequestQueue to execute */
        mRequestQueue.add(req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}

