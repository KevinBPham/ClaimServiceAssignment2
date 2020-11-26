package com.example.assignment2

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import java.lang.reflect.Type

class ClaimService (val ctx:MainActivity) {
    lateinit var claimList: MutableList<Claim>
    var currentIndx: Int = 0

    companion object {
        private var cService : ClaimService? = null

        fun getInstance(act : MainActivity) : ClaimService {
            if (cService == null) {
                cService = ClaimService(act)
            }

            return cService!!
        }
    }

    fun next() : Claim {
        currentIndx = currentIndx + 1
        return claimList[currentIndx]
    }

    fun isLastObject() : Boolean  {
        if (currentIndx == claimList.count()-1) return true
        return false
    }

    fun fetchAt(e : Int) : Claim {
        currentIndx = e
        return claimList[currentIndx]
    }

    inner class GetAllServiceRespHandler : AsyncHttpResponseHandler() {
        override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
        ) {
            if (responseBody != null) {
                Log.d("Claim Service", "The response JSON string is ${String(responseBody)}")
                val gson = Gson()
                val claimListType: Type = object : TypeToken<List<Claim>>() {}.type
                claimList = gson.fromJson(String(responseBody), claimListType)

                ctx.refreshScreen(claimList[currentIndx])

                Log.d("Claim Service", "The Claim List: ${claimList}")
            }
        }

        override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {
            TODO("Not yet implemented")
        }
    }

    inner class addServiceRespHandler : AsyncHttpResponseHandler() {
        override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
        ) {
            if (responseBody != null) {
                val respStr = String(responseBody)
                Log.d("Claim Service", "The add Service response : ${respStr}")
            }
        }

        override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
        ) {
            TODO("Not yet implemented")
        }
    }

    fun addClaim(cObj : Claim) {
        val client = AsyncHttpClient()
        val requestUrl = "http://192.168.0.110:8010/ClaimService/add"
        val pJsonString= Gson().toJson(cObj)
        val entity = StringEntity(pJsonString)

        client.post(ctx, requestUrl, entity,"application/json", addServiceRespHandler())
    }

    fun getAll()  {
        val client = AsyncHttpClient()
        val requestUrl = "http://192.168.0.110:8010/ClaimService/getAll"

        Log.d("CLaim Service", "About Sending the HTTP Request. ")

        client.get(requestUrl, GetAllServiceRespHandler())
    }
}