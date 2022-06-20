package com.pinheiro.desafiom2u.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pinheiro.desafiom2u.R
import com.pinheiro.desafiom2u.databinding.FragmentFirstBinding
import com.pinheiro.desafiom2u.ui.adapter.FilmesAdapter
import com.pinheiro.desafiom2u.ui.adapter.FilmesListener
import com.pinheiro.desafiom2u.viewmodel.FilmesViewModel

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FilmesViewModel by viewModels()

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerFilmes.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.listarFilmesExibicao()
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.listaFilme.observe(viewLifecycleOwner) {
                it.results?.let { results ->
                    binding.recyclerFilmes.adapter =
                        FilmesAdapter(
                            dataSet = results,
                            filmeListener = object : FilmesListener {
                                override fun onClickFilmeListener(id: Int) {
                                    val bundle = bundleOf("id" to id)
                                    findNavController().navigate(
                                        R.id.action_FirstFragment_to_SecondFragment,
                                        bundle
                                    )
                                }
                            }
                        )
                }
            }
        }

        binding.recyclerLancamentos.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.listarFilmesLancamentos()
        }
          viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.listarFilmesLancamentos.observe(viewLifecycleOwner) {
                it.results?.let { results ->
                    binding.recyclerLancamentos.adapter =
                        FilmesAdapter(
                            dataSet = results,
                            filmeListener = object : FilmesListener {
                                override fun onClickFilmeListener(id: Int) {
                                    val bundle = bundleOf("id" to id)
                                    findNavController().navigate(
                                        R.id.action_FirstFragment_to_SecondFragment,
                                        bundle
                                    )
                                }
                            }
                        )
                }
            }
        }

        binding.recyclerMaisPopulares.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.listarFilmesPopular()
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.listarFilmesPopular.observe(viewLifecycleOwner) {
                it.results?.let { results ->
                    binding.recyclerMaisPopulares.adapter =
                        FilmesAdapter(
                            dataSet = results,
                            filmeListener = object : FilmesListener {
                                override fun onClickFilmeListener(id: Int) {
                                    val bundle = bundleOf("id" to id)
                                    findNavController().navigate(
                                        R.id.action_FirstFragment_to_SecondFragment,
                                        bundle
                                    )
                                }
                            }
                        )
                }
            }
        }

        binding.recyclerMaisPopulares.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.listarFilmesPopular()
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.listarFilmesPopular.observe(viewLifecycleOwner) {
                it.results?.let { results ->
                    binding.recyclerMaisPopulares.adapter =
                        FilmesAdapter(
                            dataSet = results,
                            filmeListener = object : FilmesListener {
                                override fun onClickFilmeListener(id: Int) {
                                    val bundle = bundleOf("id" to id)
                                    findNavController().navigate(
                                        R.id.action_FirstFragment_to_SecondFragment,
                                        bundle
                                    )
                                }
                            }
                        )
                }
            }
        }

        binding.recyclerMelhoresAvaliados.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.listarFilmesRated()
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.listarFilmesRated.observe(viewLifecycleOwner) {
                it.results?.let { results ->
                    binding.recyclerMelhoresAvaliados.adapter =
                        FilmesAdapter(
                            dataSet = results,
                            filmeListener = object : FilmesListener {
                                override fun onClickFilmeListener(id: Int) {
                                    val bundle = bundleOf("id" to id)
                                    findNavController().navigate(
                                        R.id.action_FirstFragment_to_SecondFragment,
                                        bundle
                                    )
                                }
                            }
                        )
                }
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}