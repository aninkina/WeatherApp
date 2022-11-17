package dev.fstudio.weather.ui.fragment.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dev.fstudio.weather.databinding.FragmentHourlyWeatherBinding
import dev.fstudio.weather.util.MiscUtil
import kotlinx.coroutines.launch


class HourlyWeatherFragment : Fragment() {

    private lateinit var binding: FragmentHourlyWeatherBinding
    private lateinit var args: HourlyWeatherFragmentArgs
    private val newService = dev.fstudio.weather.new_api.OpenWeatherApi.create()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHourlyWeatherBinding.inflate(inflater, container, false)
        args = HourlyWeatherFragmentArgs.fromBundle(requireArguments())
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() = binding.rvHourlyWeatherItems.apply {
        lifecycleScope.launch {
            kotlin.runCatching {
                newService.getForecast(
                    MiscUtil.getDefaultSharedPreference(context).getFloat("lat", 0F).toDouble(),
                    MiscUtil.getDefaultSharedPreference(context).getFloat("lon", 0F).toDouble()
                )
            }.onSuccess {
                val newList = MiscUtil.getSubList(it.list, args.pos)
                adapter = HourlyWeatherAdapter(newList)
            }.onFailure {
                error(it.message.toString())
            }
        }
        layoutManager = LinearLayoutManager(requireContext())
    }
}