package com.bruno.testesofie.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DataReponse {

    @SerializedName("data")
    @Expose
    var data: Array<Tarefa>? = null
}