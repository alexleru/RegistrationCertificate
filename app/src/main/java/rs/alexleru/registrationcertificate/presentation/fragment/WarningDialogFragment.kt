package rs.alexleru.registrationcertificate.presentation.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import rs.alexleru.registrationcertificate.R

class WarningDialogFragment : DialogFragment() {

    private lateinit var onDialogClick: (Int) -> Unit //TODO lateinit?
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val alertDialog: AlertDialog = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setMessage(getString(R.string.message_warn_dialog))
                setPositiveButton(getString(R.string.save_button_warn_dialog)) { _, which ->
                    onDialogClick(which)
                }
                setNegativeButton(getString(R.string.discard_button_warn_dialog)) { _, which ->
                    onDialogClick(which)
                }
                setNeutralButton(getString(R.string.cancel_button_warn_dialog)) { dialog, _ ->
                    dialog.cancel()
                }
            }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
        return alertDialog
    }

    companion object {

        fun newInstance(onDialogClick: (Int) -> Unit) = WarningDialogFragment().apply {
            this.onDialogClick = onDialogClick
        }
    }
}