package dev.fstudio.weather.ui.fragment.search_items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dev.fstudio.weather.R
import dev.fstudio.weather.databinding.FragmentSearchBinding
import dev.fstudio.weather.ui.view_object.LatLng
import dev.fstudio.weather.util.MiscUtil


class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var binding: FragmentSearchBinding
    var arrayList: MutableList<String> = ArrayList()
    var latLngList: MutableList<LatLng> = ArrayList()
    var adapter: ArrayAdapter<String>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        arrayList.add("Saint-Petersburg")
        arrayList.add("Tyumen")
        arrayList.add("Voronezh")

        latLngList.add(LatLng(59.9342802F,  30.3350986F))
        latLngList.add(LatLng(58.200348F,  68.256332F))
        latLngList.add(LatLng(51.6754966F,  39.2088823F))

        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, arrayList)
        binding.listView.adapter = adapter

        binding.listView.setOnItemClickListener { _, _, pos, _ ->
            println("click!!!!")
            MiscUtil.getDefaultSharedPreference(requireContext()).edit()
                .putFloat("lat", latLngList[pos].lat)
                .putFloat("lon", latLngList[pos].lon)
                .putString("city", arrayList[pos])
                .apply()
            (activity as AppCompatActivity?)!!.supportActionBar!!.subtitle =  arrayList[pos]
        }

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter!!.filter.filter(newText)
                return false
            }
        })
        return  binding.root;
    }


}