package com.example.prueba

import com.example.prueba.model.RecomendatiosItems
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.prueba.model.Items

class StockDetailBottomSheetRecomendatios(private val item: RecomendatiosItems) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), theme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_stock_detail, container, false)

        // Configurar los valores en los TextView
        view.findViewById<TextView>(R.id.txtCompany).text = item.company
        view.findViewById<TextView>(R.id.txtTicker).text = "Ticker: ${item.ticker}"
        view.findViewById<TextView>(R.id.txtTarget).text = "Target from: ${item.target_from} ➝ ${item.target_to}"
        view.findViewById<TextView>(R.id.txtAction).text = "target to: ${item.target_to}"
        view.findViewById<TextView>(R.id.txtAction).text = "Cambio Porcentual: ${item.cambio_porcentual}"

        return view
    }
    override fun onStart() {
        super.onStart()
        dialog?.setOnShowListener { dialog ->
            val bottomSheetDialog = dialog as BottomSheetDialog
            val bottomSheet = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                val layoutParams = it.layoutParams
                layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT // Hace que ocupe toda la pantalla
                it.layoutParams = layoutParams
            }
        }
    }


}
