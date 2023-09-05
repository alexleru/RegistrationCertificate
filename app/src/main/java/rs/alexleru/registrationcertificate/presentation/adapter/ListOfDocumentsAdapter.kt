package rs.alexleru.registrationcertificate.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.alexleru.registrationcertificate.databinding.ItemOfDocumentBinding
import rs.alexleru.registrationcertificate.domain.model.Document
import javax.inject.Inject

class ListOfDocumentsAdapter @Inject constructor(private val onClickItemListener: (Long) -> Unit)
    : ListAdapter<Document, ListOfDocumentsViewHolder>(ListOfDocumentsDiffUtils) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListOfDocumentsViewHolder {
        val binding =
            ItemOfDocumentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ListOfDocumentsViewHolder(binding, onClickItemListener)
    }

    override fun onBindViewHolder(holder: ListOfDocumentsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}