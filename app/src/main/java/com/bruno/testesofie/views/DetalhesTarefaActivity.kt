package com.bruno.testesofie.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import com.bruno.testesofie.R
import com.bruno.testesofie.config.MessageHelper
import com.bruno.testesofie.models.Tarefa
import com.bruno.testesofie.models.TarefaResponse
import com.bruno.testesofie.services.RetrofitService
import com.bruno.testesofie.services.TarefaApi
import com.example.awesomedialog.AwesomeDialog
import com.example.awesomedialog.body
import com.example.awesomedialog.onPositive
import com.example.awesomedialog.title
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetalhesTarefaActivity : AppCompatActivity() {

    private lateinit var tarefa: Tarefa
    private lateinit var toolBar: Toolbar
    private lateinit var taskEmailEdit: EditText
    private lateinit var taskTituloEdit: EditText
    private lateinit var taskDescricaoEdit: EditText
    private lateinit var saveTaskBtn: Button
    private lateinit var deleteTaskBtn: Button
    private var tarefaApi: TarefaApi? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_tarefa)

        tarefa = intent.extras!!.get("extra_tarefa") as Tarefa

        taskEmailEdit = findViewById(R.id.taskEmailEdit)
        taskTituloEdit = findViewById(R.id.taskTituloEdit)
        taskDescricaoEdit = findViewById(R.id.taskDescricaoEdit)
        saveTaskBtn = findViewById(R.id.saveTaskBtn)
        deleteTaskBtn = findViewById(R.id.deleteTaskBtn)

        taskTituloEdit.setText(tarefa.title)
        taskEmailEdit.setText(tarefa.email)
        taskDescricaoEdit.setText(tarefa.description)

        tarefaApi = RetrofitService().cteateService(TarefaApi::class.java)


        toolBar = findViewById(R.id.toolbarTarefaDetalhe)
        setToolbar()


        saveTaskBtn.setOnClickListener {
            saveTask()
        }

        deleteTaskBtn.setOnClickListener {
            deleteTask()
        }
    }


    private fun saveTask() {

        if (!TextUtils.isEmpty(taskEmailEdit.text) && !TextUtils.isEmpty(taskTituloEdit.text)
            && !TextUtils.isEmpty(taskDescricaoEdit.text)
        ) {

            val tarefaToSend = Tarefa(
                title = taskTituloEdit.text.toString(),
                email = taskEmailEdit.text.toString(),
                description = taskDescricaoEdit.text.toString(),
                _id = tarefa._id,
                `when` = tarefa.`when`
            )

            tarefaApi!!.alterarTarefa(tarefa._id.toString(), tarefaToSend)!!
                .enqueue(object : Callback<TarefaResponse?> {
                    override fun onResponse(
                        call: Call<TarefaResponse?>,
                        response: Response<TarefaResponse?>
                    ) {

                        if (response.isSuccessful()) {

                            MessageHelper().mostrarToast(
                                "Sua tarefa foi salva",
                                context = this@DetalhesTarefaActivity
                            )


                        } else {

                            MessageHelper().mostrarToast(
                                "Ocorreu um erro , tente novamente",
                                context = this@DetalhesTarefaActivity
                            )

                        }
                    }

                    override fun onFailure(call: Call<TarefaResponse?>, t: Throwable) {
                        print(t.message)

                        MessageHelper().mostrarToast(
                            "Ocorreu um erro , tente novamente",
                            context = this@DetalhesTarefaActivity
                        )

                    }
                })


        } else {

            taskEmailEdit.error = "Não pode estar vazio"
            taskTituloEdit.error = "Não pode estar vazio"
            taskDescricaoEdit.error = "Não pode estar vazio"
        }

    }

    private fun deleteTask() {


        tarefaApi!!.removerTarefa(tarefa._id.toString())!!
            .enqueue(object : Callback<TarefaResponse?> {
                override fun onResponse(
                    call: Call<TarefaResponse?>,
                    response: Response<TarefaResponse?>
                ) {

                    print(response.message())

                    if (response.isSuccessful()) {

                        AwesomeDialog.build(this@DetalhesTarefaActivity)
                            .title("Sucesso!")
                            .body("Sua tarefa foi removida!")
                            .onPositive(
                                "Muito bom",
                                buttonBackgroundColor = R.color.colorPrimaryDark
                            ) {
                                finish()
                            }


                    } else {

                        MessageHelper().mostrarToast(
                            "Ocorreu um erro , tente novamente",
                            context = this@DetalhesTarefaActivity
                        )

                    }
                }

                override fun onFailure(call: Call<TarefaResponse?>, t: Throwable) {
                    print(t.message)

                    MessageHelper().mostrarToast(
                        "Ocorreu um erro , tente novamente",
                        context = this@DetalhesTarefaActivity
                    )

                }
            })

    }

    private fun setToolbar() {
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}