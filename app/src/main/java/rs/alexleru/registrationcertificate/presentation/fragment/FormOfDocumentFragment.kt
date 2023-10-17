package rs.alexleru.registrationcertificate.presentation.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import rs.alexleru.registrationcertificate.R
import rs.alexleru.registrationcertificate.RegCertApp
import rs.alexleru.registrationcertificate.databinding.FragmentFormOfDocumentBinding
import rs.alexleru.registrationcertificate.domain.model.Document
import rs.alexleru.registrationcertificate.presentation.mapper.toCalendar
import rs.alexleru.registrationcertificate.presentation.mapper.toStr
import rs.alexleru.registrationcertificate.presentation.model.ErrorFields
import rs.alexleru.registrationcertificate.presentation.state.FormAction
import rs.alexleru.registrationcertificate.presentation.state.FormEvent
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
        launchState()
        onClickView()
        onTextEdit()
        observerEvent()
    }

    private fun parseArg() {
        val argMode = requireArguments().getString(ARG_MODE)
        when (argMode) {
            ARG_MODE_ADD -> viewModel.newDocument()
            ARG_MODE_EDIT -> {
                val argDocumentId = requireArguments().getLong(ARG_ID)
                viewModel.setDocument(argDocumentId)
            }

            else -> throw RuntimeException("Unknown args in FormOfDocumentFragment")
        }
    }

    private fun launchState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.formState.collect {
                    binding.formProgressBar.isVisible = it.isRefreshing
                    it.content?.let { value -> fillDataToFields(value) }
                    fillErrorToFields(it.errorContent)
                    it.error?.let {
                        Toast.makeText(
                            context,
                            "Ошибка", //TODO change
                            Toast.LENGTH_SHORT
                        ).show()
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

            editFormSex.setOnClickListener {
                viewModel.handleAction(FormAction.ShowChooseSexDialog)
            }

            editFormDateBirthday.setOnClickListener {
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

    private fun onTextEdit() {
        with(binding) {
            editFormName.doOnTextChanged { str, _, _, _ ->
                if (str != null && str.trim().isNotBlank()) {
                    viewModel.handleAction(
                        FormAction.ResetErrorField(
                            FormAction.RESET_ERROR_NAME,
                            getDataFromFields()
                        )
                    )
                }
            }
            editFormSurname.doOnTextChanged { str, _, _, _ ->
                if (str != null && str.trim().isNotBlank()) {
                    viewModel.handleAction(
                        FormAction.ResetErrorField(
                            FormAction.RESET_ERROR_SURNAME,
                            getDataFromFields()
                        )
                    )
                }
            }
            editFormStayAddress.doOnTextChanged { str, _, _, _ ->
                if (str != null && str.trim().isNotBlank()) {
                    viewModel.handleAction(
                        FormAction.ResetErrorField(
                            FormAction.RESET_ERROR_ADDRESS,
                            getDataFromFields()
                        )
                    )
                }
            }
            editFormDateRegistration.doOnTextChanged { str, _, _, _ ->
                if (str != null && str.trim().isNotBlank()) {
                    viewModel.handleAction(
                        FormAction.ResetErrorField(
                            FormAction.RESET_ERROR_REG_DATE,
                            getDataFromFields()
                        )
                    )
                }
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
            is FormEvent.ChooseSexDialog -> showChooseSexDialog()
        }
    }

    private fun fillDataToFields(document: Document) {
        with(binding) {
            with(document) {
                editFormName.setText(name)
                editFormSurname.setText(surname)

                editFormDateBirthday.setText(dateOfBirth)

                editFormSex.setText(sex)
                editFormPlaceBirth.setText(placeOfBirthday)
                editFormNationality.setText(nationality)
                editFormTypeDocument.setText(typeOfDocument)
                editFormNumberDocument.setText(numberOfDocument)

                editFormDateEntry.setText(dateIn)
                editFormPlaceEntry.setText(entryInto) //TODO use vocabulary

                editFormStayAddress.setText(addressStay)
                editFormNameHost.setText(nameOfHost)
                editFormIdHost.setText(numberIdOfHost)
                editFormDateRegistration.setText(dateOfRegistration)
            }
        }
    }

    private fun checkForErrorFields(field: Boolean): String? {
        val message = if (field) {
            getString(R.string.field_can_t_be_empty)
        } else {
            null
        }
        return message
    }

    private fun fillErrorToFields(errorFields: ErrorFields) {
        Log.d("fillErrorToFields++++", "$errorFields")
        with(errorFields) {
            with(binding) {
                textFormName.error = checkForErrorFields(name)
                textFormName.isErrorEnabled = name

                textFormSurname.error = checkForErrorFields(surname)
                textFormSurname.isErrorEnabled = surname

                textFormStayAddress.error = checkForErrorFields(addressStay)
                textFormStayAddress.isErrorEnabled = addressStay

                textFormDateRegistration.error = checkForErrorFields(dateOfRegistration)
                textFormDateRegistration.isErrorEnabled = dateOfRegistration
            }
        }
    }

    private fun getDataFromFields() =
        Document(
            name = binding.editFormName.text.toString(),
            surname = binding.editFormSurname.text.toString(),

            dateOfBirth = binding.editFormDateBirthday.text.toString(),
            sex = binding.editFormSex.text.toString(),
            placeOfBirthday = binding.editFormPlaceBirth.text.toString(),
            nationality = binding.editFormNationality.text.toString(),
            typeOfDocument = binding.editFormTypeDocument.text.toString(),
            numberOfDocument = binding.editFormNumberDocument.text.toString(),

            dateIn = binding.editFormDateEntry.text.toString(),
            entryInto = binding.editFormPlaceEntry.text.toString(), //TODO use vocabulary

            addressStay = binding.editFormStayAddress.text.toString(),
            nameOfHost = binding.editFormNameHost.text.toString(),
            numberIdOfHost = binding.editFormIdHost.text.toString(),
            dateOfRegistration = binding.editFormDateRegistration.text.toString()
        )

    private fun showDatePicker(calendar: Calendar?, viewId: Int) {
        DatePickerFragment.newInstance(calendar, viewId) { c, v -> setCalendarToView(c, v) }
            .show(parentFragmentManager, null)
    }

    private fun setCalendarToView(calendar: Calendar, viewId: Int) {
        with(binding) {
            when (viewId) {
                editFormDateBirthday.id -> editFormDateBirthday.setText(calendar.toStr())
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

    private fun showChooseSexDialog() {
        ChooseSexDialogFragment.newInstance { getResultFromChooseSexDialog(it) }
            .show(parentFragmentManager, null)
    }

    private fun getResultFromChooseSexDialog(result: String) {
        when (result) {
            ChooseSexDialogFragment.ARG_MALE -> binding.editFormSex.setText(getString(R.string.male_for_form))
            ChooseSexDialogFragment.ARG_FEMALE -> binding.editFormSex.setText(getString(R.string.female_for_form))
        }
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

        fun newInstanceNewItem() = FormOfDocumentFragment().apply {
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