package adapters

import Entity.cidade
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.room.R

class CityAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var cidade = emptyList<cidade>() // Cached copy of cities

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return CityViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val current = cidade[position]
        holder.cityItemView.text = current.id.toString() + " - " + current.cidade + "-" + current.pais
    }

    internal fun setCities(cities: List<cidade>) {
        this.cidade = cities
        notifyDataSetChanged()
    }

    override fun getItemCount() = cidade.size
}