package dev.fstudio.weather.ui.fragment.weather

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import dev.fstudio.weather.R
import dev.fstudio.weather.databinding.FragmentWeatherBinding
import dev.fstudio.weather.new_api.OpenWeatherApi
import dev.fstudio.weather.util.MiscUtil.getDefaultSharedPreference
import kotlinx.coroutines.launch


class WeatherFragment : Fragment() {

    private lateinit var binding: FragmentWeatherBinding
    private val newService = OpenWeatherApi.create()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupFAB()
    }

    private fun setupFAB(){
        binding.fab.setOnClickListener { view ->
            Navigation.findNavController(binding.root)
                .navigate(WeatherFragmentDirections.actionWeatherFragmentToSearchFragment())
        }
    }
    private fun setupRecyclerView() = binding.rvWeatherItems.apply {
        lifecycleScope.launch {
            kotlin.runCatching {
                newService.getFullForecast(
                    getDefaultSharedPreference(context).getFloat("lat", 0F).toDouble(),
                    getDefaultSharedPreference(context).getFloat("lon", 0F).toDouble()
                )
            }.onSuccess {
                adapter = DailyWeatherAdapter(it.daily)
            }.onFailure {
                error(it.message.toString())
            }
        }
        layoutManager = LinearLayoutManager(requireContext())

        addOnItemTouchListener(
            RecyclerItemClickListener(this,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        val navigation = Navigation.findNavController(view)
                        navigation.navigate(
                            WeatherFragmentDirections.actionWeatherFragmentToHourlyWeatherFragment(
                                position
                            )
                        )
                    }
                })
        )
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuSettings -> {
                Navigation.findNavController(binding.root)
                    .navigate(WeatherFragmentDirections.actionWeatherFragmentToAlertsFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}