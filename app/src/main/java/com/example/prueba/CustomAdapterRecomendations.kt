package com.example.prueba

import com.example.prueba.model.RecomendatiosItems
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView


class CustomAdapterRecomendations(
    private val context: Context,
    private val items: List<RecomendatiosItems> // Aquí pasamos la lista de Items completos
) : RecyclerView.Adapter<CustomAdapterRecomendations.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtCompany: TextView = view.findViewById(R.id.txtCompany)
        val txtTicker: TextView = view.findViewById(R.id.txtTicker)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.txtCompany.text = item.company
        holder.txtTicker.text = "Ticker: ${item.ticker}"

        // 📌 Agregar el evento onClick para abrir el BottomSheet
        holder.itemView.setOnClickListener {

            val bottomSheet = StockDetailBottomSheetRecomendatios(item)
            bottomSheet.show((context as AppCompatActivity).supportFragmentManager, "StockDetailBottomSheet")
        }
    }

    override fun getItemCount() = items.size
}
