package com.bruno.testesofie.services

import com.bruno.testesofie.models.DataReponse
import com.bruno.testesofie.models.Tarefa
import com.bruno.testesofie.models.TarefaResponse

import retrofit2.Call
import retrofit2.http.*


public interface TarefaApi {

    /** REQUEST PARA RETORNAR A LISTA DE TAREFAS **/
    @GET("task")
    fun getTarefasList(): Call<DataReponse?>?

    /** REQUEST PARA ADICIONAR UMA NOVA TASK A LISTA DE TAREFAS **/
    @POST("task")
    fun incrementarTarefasList(@Body tarefa: Tarefa): Call<TarefaResponse?>?

    /** REQUEST PARA REMOVER UMA TASK NA LISTA DE TAREFAS **/
    @DELETE("task/{id}")
    fun removerTarefa(@Path("id") id: String): Call<TarefaResponse?>?

    /** REQUEST PARA ALTERAR UMA TASK DA LISTA DE TAREFAS **/
    @PUT("task/{id}")
    fun alterarTarefa(@Path("id") id: String, @Body tarefa: Tarefa): Call<TarefaResponse?>?

}