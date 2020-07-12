package com.ardi.gitaraja.pembeli;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.ardi.gitaraja.R;
import com.ardi.gitaraja.server.BaseURL;
import com.squareup.picasso.Picasso;

public class DetailPembeli extends AppCompatActivity {

    EditText edtKodeGitar, edtTipeGitar, edtJenisGitar, edtMerkGitar, edtHargaGitar;
    ImageView imgGambarGitar;

    String strKodeGitar, strTipeGitar, strJenisGitar, strMerkGitar, strHargaGitar, strGamabr, _id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pembeli);

        edtKodeGitar = (EditText) findViewById(R.id.edtKodeGitar);
        edtTipeGitar = (EditText) findViewById(R.id.edtTipeGitar);
        edtJenisGitar = (EditText) findViewById(R.id.edtJenisGitar);
        edtMerkGitar = (EditText) findViewById(R.id.edtMerkGitar);
        edtHargaGitar = (EditText) findViewById(R.id.edtHargaGitar);

        imgGambarGitar = (ImageView) findViewById(R.id.gambar);

        Intent i = getIntent();
        strKodeGitar = i.getStringExtra("kodeGitar");
        strTipeGitar = i.getStringExtra("tipeGitar");
        strJenisGitar = i.getStringExtra("jenisGitar");
        strMerkGitar = i.getStringExtra("merkGitar");
        strHargaGitar = i.getStringExtra("hargaGitar");
        strGamabr = i.getStringExtra("gambar");
        _id = i.getStringExtra("_id");

        edtKodeGitar.setText(strKodeGitar);
        edtTipeGitar.setText(strTipeGitar);
        edtJenisGitar.setText(strJenisGitar);
        edtMerkGitar.setText(strMerkGitar);
        edtHargaGitar.setText(strHargaGitar);
        Picasso.get().load(BaseURL.baseUrl + "gambar/" + strGamabr)
                .into(imgGambarGitar);
    }
}
