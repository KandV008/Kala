package com.example.kala.model

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.kala.configuration.MAIN_SCREEN_ROUTE
import com.example.kala.entities.MoneyExchange
import com.example.kala.entities.MoneyExchangeScope
import com.example.kala.entities.MoneyExchangeType
import com.example.kala.entities.MonthInformation
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.toObject
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.TreeMap

object FireBaseService {

    private const val COLLECTION_TAG = "users"
    private const val SUB_COLLECTION_TAG = "summary"
    private const val DOCUMENT_TAG = "registeredMonths"

    fun saveUser(email: String) {
        val db = Firebase.firestore
        val documentRef = db.collection(COLLECTION_TAG).document(email)

        for ((key, monthInfo) in MoneyExchangeService.moneyExchangeStorage.monthInformationMap) {
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
        val db = Firebase.firestore

        db.collection(COLLECTION_TAG)
            .document(email)
            .collection(SUB_COLLECTION_TAG)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val monthInformation = documentToMonthInformation(document)
                    MoneyExchangeService.addMonthInformation(monthInformation)
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

    @Composable
    fun DeleteUser(
        current: Context,
        navController: NavController?
    ) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val db = Firebase.firestore

        currentUser
            ?.delete()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    currentUser.email?.let {
                        db.collection(COLLECTION_TAG)
                            .document(it)
                            .delete()
                    }
                    Toast.makeText(current, "Account deleted successfully.", Toast.LENGTH_SHORT).show()
                    navController?.navigate(route = MAIN_SCREEN_ROUTE)
                } else {
                    Toast.makeText(current, "Account deletion failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

}