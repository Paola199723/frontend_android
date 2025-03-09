import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.prueba.R

class CustomAdapter(
    private val items: List<String>,
    private val onItemClick: (Int) -> Unit // Agregamos la función de clic
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = items[position]

        // Asignamos el click listener para cada item
        holder.itemView.setOnClickListener {
            onItemClick(position) // Llamamos la función con la posición del ítem
        }
    }

    override fun getItemCount() = items.size
}
