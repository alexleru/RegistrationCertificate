package rs.alexleru.registrationcertificate.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import rs.alexleru.registrationcertificate.databinding.DialogFragmentChooseSexBinding

class ChooseSexDialogFragment : DialogFragment() {

    internal lateinit var onDialogClick: (String) -> Unit

    private var _binding: DialogFragmentChooseSexBinding? = null
    private val binding: DialogFragmentChooseSexBinding
        get() = _binding ?: throw RuntimeException("Not found DialogFragmentChooseSexBinding")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.let { it.setCanceledOnTouchOutside(true) }
        _binding = DialogFragmentChooseSexBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view()
    }

    private fun view() {
        binding.maleLinearLayout.setOnClickListener {
            onDialogClick(ARG_MALE)
            dismiss()
        }

        binding.femaleLinearLayout.setOnClickListener {
            onDialogClick(ARG_FEMALE)
            dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val ARG_MALE = "arg_male_dialog"
        const val ARG_FEMALE = "arg_female_dialog"

        fun newInstance(onDialogClick: (String) -> Unit) =
            ChooseSexDialogFragment().apply { this.onDialogClick = onDialogClick }
    }
}