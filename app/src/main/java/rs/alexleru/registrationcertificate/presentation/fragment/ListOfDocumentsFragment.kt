package rs.alexleru.registrationcertificate.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import rs.alexleru.registrationcertificate.R
import rs.alexleru.registrationcertificate.RegCertApp
import rs.alexleru.registrationcertificate.data.MockDataForDB
import rs.alexleru.registrationcertificate.databinding.FragmentListOfDocumentsBinding
import rs.alexleru.registrationcertificate.presentation.adapter.ListOfDocumentsAdapter
import rs.alexleru.registrationcertificate.presentation.state.ListState
import rs.alexleru.registrationcertificate.presentation.viewmodel.DocumentViewModelProvider
import rs.alexleru.registrationcertificate.presentation.viewmodel.ListOfDocumentsViewModel
import javax.inject.Inject

class ListOfDocumentsFragment : Fragment() {

    lateinit var listOfDocumentsAdapter: ListOfDocumentsAdapter

    @Inject
    lateinit var mockDataForDB: MockDataForDB //TODO удалить

    @Inject
    internal lateinit var viewModelFactory: DocumentViewModelProvider
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ListOfDocumentsViewModel::class.java]
    }

    private var _binding: FragmentListOfDocumentsBinding? = null
    private val binding: FragmentListOfDocumentsBinding
        get() = _binding ?: throw RuntimeException("Not found FragmentListOfDocumentsBinding")

    override fun onAttach(context: Context) {
        (requireActivity().application as RegCertApp).component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentListOfDocumentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view()
        observer()
    }

    private fun view() {
        listOfDocumentsAdapter = ListOfDocumentsAdapter{launchFormFragmentEditItem(it)}
        binding.rvListOfDocuments.adapter = listOfDocumentsAdapter
    }

    private fun observer() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.listState.collect {
                    when (it) {
                        is ListState.Loading -> binding.listProgressbar.isVisible = true
                        is ListState.Content -> {
                            binding.listProgressbar.isVisible = false
                            listOfDocumentsAdapter.submitList(it.listOfDocuments)
                        }

                        is ListState.Error -> {
                            binding.listProgressbar.isVisible = false
                            Toast.makeText(
                                this@ListOfDocumentsFragment.context,
                                "Ошибка", // Todo
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                }

            }
        }
    }

    private fun launchFormFragmentAddItem() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_activity, FormOfDocumentFragment.newInstanceAddItem())
            .addToBackStack(null)
            .commit()
    }

    private fun launchFormFragmentEditItem(documentId: Long) {
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.main_activity,
                FormOfDocumentFragment.newInstanceEditItem(documentId = documentId)
            )
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}