package com.bruno.testesofie.repositories

import androidx.lifecycle.MutableLiveData
import com.bruno.testesofie.models.DataReponse
import com.bruno.testesofie.services.RetrofitService
import com.bruno.testesofie.services.TarefaApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TarefasRepository {
    private var tarefaApi: TarefaApi? = null
    fun getTarefas(): MutableLiveData<DataReponse?> {
        val tarefasData: MutableLiveData<DataReponse?> = MutableLiveData()
        tarefaApi!!.getTarefasList()!!.enqueue(object : Callback<DataReponse?> {
            override fun onResponse(
                call: Call<DataReponse?>,
                response: Response<DataReponse?>
            ) {

                if (response.isSuccessful()) {
                    tarefasData.setValue(response.body())
                }
            }

            override fun onFailure(
                call: Call<DataReponse?>,
                t: Throwable
            ) {
                print(t.message)
                tarefasData.setValue(DataReponse())
            }
        })
        return tarefasData
    }



    companion object {
        private var newsRepository: TarefasRepository? = null
        val instance: TarefasRepository?
            get() {
                if (newsRepository == null) {
                    newsRepository = TarefasRepository()
                }
                return newsRepository
            }
    }

    init {
        tarefaApi = RetrofitService().cteateService(TarefaApi::class.java)
    }
}