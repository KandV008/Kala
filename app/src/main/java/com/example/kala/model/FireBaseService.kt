package com.example.kala.model

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.kala.model.entities.MoneyExchange
import com.example.kala.model.entities.MoneyExchangeScope
import com.example.kala.model.entities.MoneyExchangeType
import com.example.kala.model.entities.MonthInformation
import com.example.kala.ui.screens.navigation.MAIN_SCREEN_ROUTE
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.firestore
import java.time.LocalDateTime

@SuppressLint("StaticFieldLeak")
object FireBaseService {
    private const val COLLECTION_TAG = "users"
    private const val SUB_COLLECTION_TAG = "summary"
    private val database = Firebase.firestore

    fun saveUser(email: String) {
        val documentRef = database.collection(COLLECTION_TAG).document(email)

        for ((key, monthInfo) in MonthInformationService.getAllMonthInformation()) {
            val docRef = documentRef.collection(SUB_COLLECTION_TAG).document(key)
            val monthInfoMap = monthInfo.monthInformationToMap()
            docRef.set(monthInfoMap)
                .addOnSuccessListener {
                    println("MonthInformation successfully written!")
                }
                .addOnFailureListener { e ->
                    println("Error writing MonthInformation: $e")
                }
        }
    }

    fun loadUser(email: String) {
        database.collection(COLLECTION_TAG)
            .document(email)
            .collection(SUB_COLLECTION_TAG)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val monthInformation = documentToMonthInformation(document)
                    MonthInformationService.addMonthInformation(monthInformation)
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }

        println()
    }

    private fun documentToMonthInformation(document: QueryDocumentSnapshot): MonthInformation {
        val id = document.id
        val expensedMoney: Double = document.getDouble("expensedMoney") ?: throw IllegalStateException("expensedMoney cannot be null")
        val incomeMoney = document.getDouble("incomeMoney") ?: throw IllegalStateException("expensedMoney cannot be null")
        val dateCreation = document.getString("dateCreation") ?: throw IllegalStateException("expensedMoney cannot be null")

        val monthInformation = MonthInformation(id, expensedMoney, incomeMoney, dateCreation)

        val entries = (document.get("summary") as HashMap<String, HashMap<String, Any>>).entries

        for (entry in entries){
            val moneyExchange: MoneyExchange = documentToMoneyExchange(entry.value)
            monthInformation.summary[moneyExchange.id.toString()] = moneyExchange
        }

        return monthInformation
    }

    private fun documentToMoneyExchange(doc: HashMap<String, Any>): MoneyExchange {
        val value = doc["value"] as Double
        val type = MoneyExchangeType.valueOf(doc["type"] as String)
        val scope = MoneyExchangeScope.valueOf(doc["scope"] as String)
        val description = doc["description"] as String?

        val moneyExchange = MoneyExchange(value, type, scope, description)
        moneyExchange.id = (doc["id"] as Long).toInt()
        moneyExchange.monthAssociated = doc["monthAssociated"] as String
        moneyExchange.date = LocalDateTime.parse(doc["date"] as String)

        return moneyExchange
    }

    fun updateUser() {
        FirebaseAuth.getInstance().currentUser?.email?.let {
            this.saveUser(it)
        }
    }

    @Composable
    fun DeleteUser(
        navController: NavController?
    ) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val current = LocalContext.current

        currentUser!!.email?.let {
            database.collection(COLLECTION_TAG)
                .document(it)
                .delete()
                .addOnSuccessListener {
                    currentUser
                        .delete()
                        .addOnSuccessListener {
                            Toast.makeText(
                                current,
                                "Account deleted successfully.",
                                Toast.LENGTH_SHORT
                            ).show()
                            MonthInformationService.clean()
                            navController?.navigate(route = MAIN_SCREEN_ROUTE)
                        }
                        .addOnFailureListener { exception ->
                            throw exception
                        }
                }
                .addOnFailureListener {exception ->
                    Log.d(TAG, "Error getting documents: ", exception)
                    Toast.makeText(
                        current,
                        "Account deletion failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }

    }

}