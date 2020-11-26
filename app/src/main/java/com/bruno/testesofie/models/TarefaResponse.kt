package com.bruno.testesofie.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class TarefaResponse {
    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("_id")
    @Expose
    var _id: String? = null

    @SerializedName("quando")
    @Expose
    var quando: String? = null

}