package com.ardi.gitaraja.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ardi.gitaraja.R;
import com.ardi.gitaraja.model.ModelGitar;
import com.ardi.gitaraja.server.BaseURL;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterGitar extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<ModelGitar> item;

    public AdapterGitar(Activity activity, List<ModelGitar> item) {
        this.activity = activity;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.content_gitar, null);


        TextView kodeGitar = (TextView) convertView.findViewById(R.id.txtKodeGitar);
        TextView tipeGitar = (TextView) convertView.findViewById(R.id.txtTipeGitar);
        TextView jenisGitar = (TextView) convertView.findViewById(R.id.txtJenisGitar);
        TextView merkGitar  = (TextView) convertView.findViewById(R.id.txtMerkGitar);
        TextView hargaGitar = (TextView) convertView.findViewById(R.id.txtHargaGitar);
        ImageView gambarGitar = (ImageView) convertView.findViewById(R.id.gambarGitar);

        kodeGitar.setText(item.get(position).getKodeGitar());
        tipeGitar.setText(item.get(position).getTipeGitar());
        jenisGitar.setText(item.get(position).getJenisGitar());
        hargaGitar.setText("Rp." + item.get(position).getHargaGitar());
        merkGitar.setText(item.get(position).getMerkGitar());
        Picasso.get().load(BaseURL.baseUrl + "gambar/" + item.get(position).getGambar())
                .resize(40, 40)
                .centerCrop()
                .into(gambarGitar);
        return convertView;
    }

}