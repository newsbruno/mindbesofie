package com.bruno.testesofie.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import com.bruno.testesofie.R
import com.bruno.testesofie.config.MessageHelper
import com.bruno.testesofie.models.Tarefa
import com.bruno.testesofie.models.TarefaResponse
import com.bruno.testesofie.services.RetrofitService
import com.bruno.testesofie.services.TarefaApi
import com.example.awesomedialog.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NovaTarefaActivity : AppCompatActivity() {

    private lateinit var toolBar: Toolbar
    private lateinit var taskEmail: EditText
    private lateinit var taskTitulo: EditText
    private lateinit var taskDescricao: EditText
    private var tarefaApi: TarefaApi? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova_tarefa)

        toolBar = findViewById(R.id.toolbarNovaTarefa)
        taskEmail = findViewById(R.id.taskEmail)
        taskTitulo = findViewById(R.id.taskTitulo)
        taskDescricao = findViewById(R.id.taskDescricao)

        tarefaApi = RetrofitService().cteateService(TarefaApi::class.java)

        setToolbar()

    }


    private fun setToolbar() {
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_novo_tarefa, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.doneBtn -> {
                novaTarefa()
                true
            }

            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun novaTarefa() {

        if (!TextUtils.isEmpty(taskEmail.text) && !TextUtils.isEmpty(taskTitulo.text)
            && !TextUtils.isEmpty(taskDescricao.text)
        ) {

            val tarefa = Tarefa(
                title = taskTitulo.text.toString(),
                email = taskEmail.text.toString(),
                description = taskDescricao.text.toString(),
                _id = "",
                `when` = ""
            )
            tarefaApi!!.incrementarTarefasList(tarefa)!!
                .enqueue(object : Callback<TarefaResponse?> {
                    override fun onResponse(
                        call: Call<TarefaResponse?>,
                        response: Response<TarefaResponse?>
                    ) {

                        if (response.isSuccessful()) {

                            AwesomeDialog.build(this@NovaTarefaActivity)
                                .title("Sucesso!")
                                .body("Sua tarefa foi adicionada!")
                                .onPositive(
                                    "Muito bom",
                                    buttonBackgroundColor = R.color.colorPrimaryDark
                                ) {
                                    finish()
                                }

                        } else {
                            MessageHelper().mostrarMensagem(
                                titulo = "Ops!",
                                mensagem = "Ocorreu um erro , tente novamente",
                                positive = "OK",
                                context = this@NovaTarefaActivity
                            )
                        }
                    }

                    override fun onFailure(call: Call<TarefaResponse?>, t: Throwable) {
                        print(t.message)

                        MessageHelper().mostrarMensagem(
                            titulo = "Ops!",
                            mensagem = "Ocorreu um erro , tente novamente",
                            positive = "OK",
                            context = this@NovaTarefaActivity
                        )

                    }
                })


        } else {

            taskEmail.error = "Não pode estar vazio"
            taskTitulo.error = "Não pode estar vazio"
            taskDescricao.error = "Não pode estar vazio"
        }
    }


}