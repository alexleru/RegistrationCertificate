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
import rs.alexleru.registrationcertificate.pdfCreator.PdfConstructor
import rs.alexleru.registrationcertificate.pdfCreator.form.MockData
import rs.alexleru.registrationcertificate.pdfCreator.form.RegistrationOfPlaceOfStay
import rs.alexleru.registrationcertificate.pdfCreator.util.toPDF
import rs.alexleru.registrationcertificate.presentation.adapter.ListOfDocumentsAdapter
import rs.alexleru.registrationcertificate.presentation.state.ListState
import rs.alexleru.registrationcertificate.presentation.viewmodel.DocumentViewModelProvider
import rs.alexleru.registrationcertificate.presentation.viewmodel.ListOfDocumentsViewModel
import javax.inject.Inject

class ListOfDocumentsFragment : Fragment() {
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


    private val listOfDocumentsAdapter by lazy {
        ListOfDocumentsAdapter { launchFormFragmentEditItem(it) }
    }

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
        PdfConstructor(
            RegistrationOfPlaceOfStay(MockData().mock().toPDF()),
            requireContext()
        ).apply {
            this.savePdf()
        }
    }

    private fun view() {
        binding.rvListOfDocuments.adapter = listOfDocumentsAdapter
        binding.floatingButton.setOnClickListener {
            launchFormFragmentNewItem()
        }
    }


//    // Storage Permissions
//    val REQUEST_EXTERNAL_STORAGE = 1
//    val PERMISSIONS_STORAGE = arrayOf(
//        Manifest.permission.READ_EXTERNAL_STORAGE,
//        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//        Manifest.permission.MANAGE_EXTERNAL_STORAGE
//    )
//
//    fun verifyStoragePermissions(activity: Activity) {
//        // Check if we have write permission
//        val permission = ActivityCompat.checkSelfPermission(
//            activity,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE
//        );
//
//        if (permission != PackageManager.PERMISSION_GRANTED) {
//            // We don't have permission so prompt the user
//            ActivityCompat.requestPermissions(
//                activity, PERMISSIONS_STORAGE,
//                REQUEST_EXTERNAL_STORAGE
//            );
//        }
//    }

    private fun observer() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.listState.collect {
                    when (it) {
                        is ListState.Loading -> binding.listProgressBar.isVisible = true
                        is ListState.Content -> {
                            binding.listProgressBar.isVisible = false
                            listOfDocumentsAdapter.submitList(it.listOfDocuments)
                        }

                        is ListState.Error -> {
                            binding.listProgressBar.isVisible = false
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

    private fun launchFormFragmentNewItem() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_activity, FormOfDocumentFragment.newInstanceNewItem())
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