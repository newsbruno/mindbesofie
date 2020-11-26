package com.bruno.testesofie.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TableRow
import androidx.annotation.LayoutRes
import com.bruno.testesofie.models.Tarefa
import kotlinx.android.synthetic.main.task_item.view.*

class TarefaAdapter(
    context: Context,
    @LayoutRes private val layoutResource: Int,
    private val tarefas: List<Tarefa>
) :
    ArrayAdapter<Tarefa>(context, layoutResource, tarefas) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createViewFromResource(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return createViewFromResource(position, convertView, parent)
    }

    private fun createViewFromResource(
        position: Int,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val view: TableRow = convertView as TableRow? ?: LayoutInflater.from(context)
            .inflate(layoutResource, parent, false) as TableRow
        view.task_title.text = tarefas[position].title;
        view.task_email.text = tarefas[position].email;
        return view
    }

}