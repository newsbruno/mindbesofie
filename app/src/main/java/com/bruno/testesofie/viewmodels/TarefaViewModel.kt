package com.bruno.testesofie.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bruno.testesofie.models.DataReponse
import com.bruno.testesofie.repositories.TarefasRepository


/** View Model para gerenciamento da UI e melhor desempenho do APP */
class TarefaViewModel : ViewModel() {

    private var mutableLiveData: MutableLiveData<DataReponse?>? = null
    private var tarefaRepository: TarefasRepository? = null

    fun init() {
        if (mutableLiveData != null) {
            return
        }
        tarefaRepository = TarefasRepository.instance
        mutableLiveData = tarefaRepository!!.getTarefas()
    }


    fun getTarefasRepository(): LiveData<DataReponse?>? {
        return mutableLiveData
    }


}