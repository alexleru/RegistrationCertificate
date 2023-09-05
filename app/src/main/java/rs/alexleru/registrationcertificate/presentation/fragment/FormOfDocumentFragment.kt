package rs.alexleru.registrationcertificate.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import rs.alexleru.registrationcertificate.RegCertApp
import rs.alexleru.registrationcertificate.databinding.FragmentFormOfDocumentBinding
import rs.alexleru.registrationcertificate.domain.model.Document
import rs.alexleru.registrationcertificate.presentation.state.DocumentState
import rs.alexleru.registrationcertificate.presentation.viewmodel.DocumentViewModelProvider
import rs.alexleru.registrationcertificate.presentation.viewmodel.FormOfDocumentViewModel
import rs.alexleru.registrationcertificate.presentation.viewmodel.ListOfDocumentsViewModel
import javax.inject.Inject

class FormOfDocumentFragment : Fragment() {

    @Inject
    internal lateinit var viewModelProvider: DocumentViewModelProvider
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelProvider)[FormOfDocumentViewModel::class.java]
    }

    private var _binding: FragmentFormOfDocumentBinding? = null
    private val binding: FragmentFormOfDocumentBinding
        get() = _binding ?: throw RuntimeException("Not found FragmentFormOfDocumentBinding")

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as RegCertApp).component.inject(this)
        super.onCreate(savedInstanceState)
        parseArg()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormOfDocumentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun parseArg() {
        val argMode = requireArguments().getString(ARG_MODE)
        val s = argMode.toString()
        when (argMode) {
            ARG_MODE_ADD -> launchAddMode()
            ARG_MODE_EDIT -> {
                val argDocumentId = requireArguments().getLong(ARG_ID)
                viewModel.getDocument(argDocumentId)
                launchEditMode()
            }

            else -> throw RuntimeException("Unknown args in FormOfDocumentFragment")
        }
    }

    private fun launchAddMode() {

    }

    private fun launchEditMode() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.documentState.collect {
                    when (it) {
                        is DocumentState.Loading -> "progressbar"//TODO progressbar
                        is DocumentState.Content -> fillDataToFields(it.document)//TODO почему могу вызвать не suspend функцию?
                        is DocumentState.Error -> Toast.makeText(
                            context,
                            "Ошибка", //TODO change
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun fillDataToFields(document: Document) {
        with(binding) {
            editFormName.setText(document.name)
            editFormSurname.setText(document.surname)
            editFormStayAddress.setText(document.addressStay)
            editFormDateIn.setText(document.dateOfRegistration.toString())
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_ID = "arg_id"
        private const val ARG_MODE = "mode"
        private const val ARG_MODE_ADD = "mode_add"
        private const val ARG_MODE_EDIT = "mode_edit"

        fun newInstanceAddItem() = FormOfDocumentFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_MODE, ARG_MODE_ADD)
            }
        }

        fun newInstanceEditItem(documentId: Long) = FormOfDocumentFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_MODE, ARG_MODE_EDIT)
                putLong(ARG_ID, documentId)
            }
        }
    }
}