package models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.aa1_hangedman.R

class LevelAdapter(private val level:List<Level>,  private val listener: OnButtonClickListener) : RecyclerView.Adapter<LevelAdapter.LevelViewHolder>() {
    interface OnButtonClickListener {
        fun onButtonClick(level: Level)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelAdapter.LevelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_layout_manager,parent,false)

        return LevelViewHolder(view);
    }

    override fun onBindViewHolder(holder: LevelAdapter.LevelViewHolder, position: Int) {
        val level = level[position]
        holder.bind(level)

        holder.levelButton.setOnClickListener {
            listener.onButtonClick(level)
        }
    }

    override fun getItemCount(): Int {
        return level.size
    }

    class LevelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        private val levelWord: TextView = itemView.findViewById(R.id.word)
        private val levelLetter: TextView = itemView.findViewById(R.id.letter)
        private val levelImage: ImageView = itemView.findViewById(R.id.level_image)
        val levelButton: Button = itemView.findViewById(R.id.levelScreenButton)

        fun bind(level: Level)
        {
            levelWord.text = level.word
            levelLetter.text = level.difficulty
            levelImage.setImageResource(level.imageResId)
        }
    }

}