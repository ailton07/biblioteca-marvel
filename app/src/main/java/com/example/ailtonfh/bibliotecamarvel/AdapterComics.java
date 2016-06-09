package com.example.ailtonfh.bibliotecamarvel;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ailtonfh.bibliotecamarvel.comicsModels.Result;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by AiltonFH on 09/06/2016.
 */
public class AdapterComics extends BaseAdapter {
    private final List<Result> results;
    ImageLoader imageLoader;
    private final Activity act;

    public AdapterComics(List<Result> results, Activity act, ImageLoader imageLoader) {
        this.results = results;
        this.act = act;
    }

    @Override
    public int getCount() {
        return results.size();
    }

    @Override
    public Object getItem(int position) {
        return results.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater()
                .inflate(R.layout.comics_list, parent, false);
        // Result result = results.get(position);
        Result filteredResult = results.get(position);

        //pegando as referências das Views

        ImageView imageView = (ImageView)
                view.findViewById(R.id.comics_list_imagem);

        //populando as Views
        imageLoader = ImageLoader.getInstance();

        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).build();

        imageLoader.displayImage(filteredResult.getThumbnail().getPath()+"/portrait_xlarge."+filteredResult.getThumbnail().getExtension(), imageView, options);

        return view;
    }
}
