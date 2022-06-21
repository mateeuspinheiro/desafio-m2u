package com.pinheiro.desafiom2u.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pinheiro.desafiom2u.R
import com.pinheiro.desafiom2u.databinding.FragmentSecondBinding
import com.pinheiro.desafiom2u.ui.adapter.FilmesAdapter
import com.pinheiro.desafiom2u.ui.adapter.FilmesListener
import com.pinheiro.desafiom2u.viewmodel.DetailsFilmeViewModel
import com.pinheiro.desafiom2u.viewmodel.FilmesViewModel


class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsFilmeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idDetalhes = arguments?.getInt("id") ?: 0


        binding.recyclerMaisComoEste.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.listarMaisComoEsse(idDetalhes)
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.listarMaisComoEsse.observe(viewLifecycleOwner) {
                it.results?.let { results ->
                    binding.recyclerMaisComoEste.adapter =
                        FilmesAdapter(
                            dataSet = results,
                            filmeListener = object : FilmesListener {
                                override fun onClickFilmeListener(id: Int) {}
                            }
                        )
                }
            }
        }

        binding.recyclerComentarios.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.listarComentariosFilmes(idDetalhes)
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.listarComentariosFilmes.observe(viewLifecycleOwner) {
                it.results?.let { results ->
                    binding.recyclerComentarios.adapter =
                        FilmesAdapter(
                            dataSet = results,
                            filmeListener = object : FilmesListener {
                                override fun onClickFilmeListener(id: Int) {}
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