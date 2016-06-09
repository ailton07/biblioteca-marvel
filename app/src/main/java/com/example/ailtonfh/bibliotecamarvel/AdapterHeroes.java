package com.example.ailtonfh.bibliotecamarvel;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ailtonfh.bibliotecamarvel.heroesModels.Result;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AiltonFH on 08/06/2016.
 */
public class AdapterHeroes extends BaseAdapter implements Filterable{

    private final List<Result> results;
    private List<Result> filteredResults;
    private final Activity act;
    ImageLoader imageLoader;
    private ItemFilter mFilter = new ItemFilter();

    public AdapterHeroes(List<Result> result, Activity act, ImageLoader imageLoader) {
        this.results = result;
        this.filteredResults = result;
        this.act = act;
        this.imageLoader = imageLoader;

        imageLoader.init(ImageLoaderConfiguration.createDefault(act));
    }


    @Override
    public int getCount() {
        return filteredResults.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredResults.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = act.getLayoutInflater()
                .inflate(R.layout.heroes_list, parent, false);
       // Result result = results.get(position);
        Result filteredResult = filteredResults.get(position);

        //pegando as referências das Views
        TextView nameView = (TextView)
                view.findViewById(R.id.heroes_list_nome);

        ImageView imageView = (ImageView)
                view.findViewById(R.id.heroes_list_imagem);

        //populando as Views
        nameView.setText(filteredResult.getName());

        //imageView.setImageResource(R.drawable.java);
        imageLoader = ImageLoader.getInstance();

        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).build();
        imageLoader.displayImage(filteredResult.getThumbnail().getPath()+"/landscape_incredible."+filteredResult.getThumbnail().getExtension(), imageView, options);

        return view;
    }


    public Filter getFilter() {
        return mFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected Filter.FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();

            final List<Result> list = results;

            int count = list.size();
            final ArrayList<Result> nlist = new ArrayList<Result>(count);

            String filterableString ;

            for (int i = 0; i < count; i++) {
                filterableString = list.get(i).getName();
                if (filterableString.toLowerCase().contains(filterString)) {
                    nlist.add(list.get(i));
                }
            }

            filterResults.values = nlist;
            filterResults.count = nlist.size();

            return filterResults;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredResults = (List<Result>) results.values;
            notifyDataSetChanged();
        }

    }

}
