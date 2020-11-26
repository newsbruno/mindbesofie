package com.bruno.testesofie.views


import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.view.Menu
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.bruno.testesofie.R
import com.bruno.testesofie.adapters.TarefaAdapter
import com.bruno.testesofie.models.Tarefa
import com.bruno.testesofie.viewmodels.TarefaViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var floatButton: FloatingActionButton
    private lateinit var toolBar: Toolbar
    val tarefasArrayList: ArrayList<Tarefa> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val policy =
            StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        listView = findViewById(R.id.task_list_view)
        floatButton = findViewById(R.id.floatBtn)
        toolBar = findViewById(R.id.toolbar)


        runOnUiThread {
            initList()
        }

        initFloatBtn()
        setToolbar()


    }

    private fun initList() {

        val model: TarefaViewModel by viewModels()
        model.init()

        val adapter = TarefaAdapter(
            this,
            R.layout.task_item,
            tarefasArrayList
        )

        model.getTarefasRepository()!!.observe(this, Observer { dataResponse ->
            tarefasArrayList.clear()
            tarefasArrayList.addAll(dataResponse!!.data!!)
            adapter.notifyDataSetChanged()
        })


        listView.adapter = adapter

        listView.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->

                val intent = Intent(this, DetalhesTarefaActivity::class.java)
                intent.putExtra("extra_tarefa", tarefasArrayList[position])
                startActivity(intent)
            }

    }

    private fun setToolbar() {
        setSupportActionBar(toolBar)
    }

    private fun initFloatBtn() {

        floatButton.setOnClickListener {

            val intent = Intent(this, NovaTarefaActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }


}