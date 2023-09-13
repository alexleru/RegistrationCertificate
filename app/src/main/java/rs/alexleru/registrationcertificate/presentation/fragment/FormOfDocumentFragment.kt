package rs.alexleru.registrationcertificate.presentation.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import rs.alexleru.registrationcertificate.RegCertApp
import rs.alexleru.registrationcertificate.databinding.FragmentFormOfDocumentBinding
import rs.alexleru.registrationcertificate.domain.model.Document
import rs.alexleru.registrationcertificate.presentation.mapper.toCalendar
import rs.alexleru.registrationcertificate.presentation.mapper.toStr
import rs.alexleru.registrationcertificate.presentation.state.FormAction
import rs.alexleru.registrationcertificate.presentation.state.FormEvent
import rs.alexleru.registrationcertificate.presentation.state.FormState
import rs.alexleru.registrationcertificate.presentation.viewmodel.DocumentViewModelProvider
import rs.alexleru.registrationcertificate.presentation.viewmodel.FormOfDocumentViewModel
import java.util.Calendar
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerEvent()
        onClickView()
    }

    private fun parseArg() {
        val argMode = requireArguments().getString(ARG_MODE)
        when (argMode) {
            ARG_MODE_ADD -> launchAddMode()
            ARG_MODE_EDIT -> {
                val argDocumentId = requireArguments().getLong(ARG_ID)
                viewModel.setDocument(argDocumentId)
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
                viewModel.formState.collect {
                    when (it) {
                        is FormState.Loading -> binding.formProgressBar.isVisible = true
                        is FormState.Content -> {
                            fillDataToFields(it.document)
                            binding.formProgressBar.isVisible = false
                        }

                        is FormState.Error -> {
                            Toast.makeText(
                                context,
                                "Ошибка", //TODO change
                                Toast.LENGTH_SHORT
                            ).show()
                            binding.formProgressBar.isVisible = false
                        }
                    }
                }
            }
        }
    }

    private fun observerEvent() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.formEvent.collect {
                    it.data?.let { event ->
                        handleEvent(event)
                    }
                }
            }
        }
    }

    private fun onClickView() {
        with(binding) {
            imageViewClose.setOnClickListener {
                viewModel.handleAction(FormAction.CloseWithCheck(getDataFromFields()))
            }
            buttonDelete.setOnClickListener {
                viewModel.handleAction(FormAction.Delete)
            }
            buttonSave.setOnClickListener {
                viewModel.handleAction(FormAction.Save(getDataFromFields()))
            }


            textFormDateBirthday.setOnClickListener {
                onClickForShowPickerDialog(it)
            }
            editFormDateEntry.setOnClickListener {
                onClickForShowPickerDialog(it)
            }
            editFormDateRegistration.setOnClickListener {
                onClickForShowPickerDialog(it)
            }
        }
    }

    private fun onClickForShowPickerDialog(view: View) {
        val calendar = (view as EditText).text.toString().toCalendar()
        val viewId = view.id
        viewModel.handleAction(
            FormAction.ShowDatePickerDialog(calendar, viewId)
        )
    }


    private fun handleEvent(event: FormEvent) {
        when (event) {
            is FormEvent.Close -> toBack()
            is FormEvent.DatePickerDialog ->
                showDatePicker(event.calendar, event.viewId)
            is FormEvent.WarningDialog -> showWarningDialog()
        }
    }

    private fun fillDataToFields(document: Document) {
        with(binding) {
            editFormName.setText(document.name)
            editFormSurname.setText(document.surname)

            textFormDateBirthday.setText(document.dateOfBirth?.toStr())

            editFormSex.setText(document.sex.toString()) //TODO use map Enum
            editFormPlaceBirth.setText(document.placeOfBirthday)
            editFormNationality.setText(document.nationality)
            editFormTypeDocument.setText(document.typeOfDocument)
            editFormNumberDocument.setText(document.numberOfDocument)

            editFormDateEntry.setText(document.dateIn?.toStr())
            editFormPlaceEntry.setText(document.entryInto) //TODO use vocabulary

            editFormStayAddress.setText(document.addressStay)
            editFormNameHost.setText(document.nameOfHost)
            editFormIdHost.setText(document.numberIdOfHost)
            editFormDateRegistration.setText(document.dateOfRegistration.toStr())
        }
    }

    private fun getDataFromFields() =
        Document(
            name = binding.editFormName.text.toString(),
            surname = binding.editFormSurname.text.toString(),

            dateOfBirth = binding.textFormDateBirthday.text.toString().toCalendar(),
            sex = 'M', //TODO use map Enum
            placeOfBirthday = binding.editFormPlaceBirth.text.toString(),
            nationality = binding.editFormNationality.text.toString(),
            typeOfDocument = binding.editFormTypeDocument.text.toString(),
            numberOfDocument = binding.editFormNumberDocument.text.toString(),

            dateIn = binding.editFormDateEntry.text.toString().toCalendar(),
            entryInto = binding.editFormPlaceEntry.text.toString(), //TODO use vocabulary

            addressStay = binding.editFormStayAddress.text.toString(),
            nameOfHost = binding.editFormNameHost.text.toString(),
            numberIdOfHost = binding.editFormIdHost.text.toString(),
            dateOfRegistration = binding.editFormDateRegistration.text.toString()
                .toCalendar()!! //TODO Change !!
        )

    private fun showDatePicker(calendar: Calendar?, viewId: Int) {
        DatePickerFragment.newInstance(calendar, viewId) { c, v -> setCalendarToView(c, v) }
            .show(parentFragmentManager, null)
        binding.textFormDateBirthday.id
    }

    private fun setCalendarToView(calendar: Calendar, viewId: Int) {
        with(binding) {
            when (viewId) {
                textFormDateBirthday.id -> textFormDateBirthday.setText(calendar.toStr())
                editFormDateEntry.id -> editFormDateEntry.setText(calendar.toStr())
                editFormDateRegistration.id -> editFormDateRegistration.setText(calendar.toStr())
                else -> throw RuntimeException("Not found relation view ID")
            }
        }
    }

    private fun showWarningDialog() {
        WarningDialogFragment.newInstance { getResultFromWarningDialog(it) }
            .show(parentFragmentManager, null)
    }

    private fun getResultFromWarningDialog(result: Int) {
        when (result) {
            DialogInterface.BUTTON_NEGATIVE -> viewModel.handleAction(
                FormAction.ForceClose
            )

            DialogInterface.BUTTON_POSITIVE -> viewModel.handleAction(
                FormAction.Save(
                    getDataFromFields()
                )
            )

            else -> throw RuntimeException()
        }
    }

    private fun toBack() {
        requireActivity().supportFragmentManager.popBackStack()
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