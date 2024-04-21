package com.example.advprojectuts160421118.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.advprojectuts160421118.R
import com.example.advprojectuts160421118.databinding.FragmentDetailHobbyBinding
import com.example.advprojectuts160421118.databinding.FragmentHobbyListBinding
import com.example.advprojectuts160421118.viewmodel.ListViewModelHobby
import com.squareup.picasso.Picasso


/**
 * A simple [Fragment] subclass.
 * Use the [DetailHobbyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailHobbyFragment : Fragment() {
    private lateinit var viewModel: ListViewModelHobby
    private lateinit var binding: FragmentDetailHobbyBinding
    private var paragraphs: List<String> = listOf()
    private var currentPage = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailHobbyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mendapatkan data dari arguments
        val judul = DetailHobbyFragmentArgs.fromBundle(requireArguments()).judul
        val nama = DetailHobbyFragmentArgs.fromBundle(requireArguments()).nama
        val isi = DetailHobbyFragmentArgs.fromBundle(requireArguments()).isi
        paragraphs = isi.split("\n\n")
        val photo = DetailHobbyFragmentArgs.fromBundle(requireArguments()).photo

        // Menampilkan judul, nama, dan gambar
        binding.txtJudul.text = judul
        binding.txtNama.text = nama
        Picasso.get().load(photo).into(binding.image)

        setPageText(currentPage)

        binding.btnNext.setOnClickListener {
            if (currentPage < paragraphs.size) {
                currentPage++
                setPageText(currentPage)
            }
        }

        binding.btnPrevious.setOnClickListener {
            if (currentPage > 1) {
                currentPage--
                setPageText(currentPage)
            }
        }
    }

    private fun setPageText(pageNumber: Int) {
        val pageSize = 3 // Jumlah paragraf yang ingin ditampilkan per halaman
        val startIndex = (pageNumber - 1) * pageSize
        val endIndex = minOf(pageNumber * pageSize, paragraphs.size)
        val paragraphToShow = paragraphs.subList(startIndex, endIndex).joinToString("\n\n")

        binding.txtIsi.text = paragraphToShow // Atur teks pada TextView untuk paragraf tersebut
        binding.txtPage.text = "Page $pageNumber" // Atur teks pada TextView untuk menampilkan nomor halaman
    }
}

