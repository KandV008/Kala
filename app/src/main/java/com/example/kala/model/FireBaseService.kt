package com.example.kala.model

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import com.example.kala.model.entities.MoneyExchange
import com.example.kala.model.entities.MoneyExchangeScope
import com.example.kala.model.entities.MoneyExchangeType
import com.example.kala.model.entities.MonthInformation
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import java.time.LocalDateTime

@SuppressLint("StaticFieldLeak")
object FireBaseService {
    private const val COLLECTION_TAG = "users"
    private const val SUB_COLLECTION_TAG = "summary"
    private val database = Firebase.firestore

    fun saveUser(userId: String) {
        val documentRef = database.collection(COLLECTION_TAG).document(userId)

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

    fun loadUser(userId: String, onComplete: () -> Unit) {
        database.collection(COLLECTION_TAG)
            .document(userId)
            .collection(SUB_COLLECTION_TAG)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val monthInformation = documentToMonthInformation(document)
                    MonthInformationService.addMonthInformation(monthInformation)
                }
                onComplete()
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
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
        FirebaseAuth.getInstance().currentUser?.uid?.let {
            this.saveUser(it)
        }
    }

    fun deleteUser(
        onFailure: () -> Unit,
        onComplete: () -> Unit
    ) {
        val currentUser = FirebaseAuth.getInstance().currentUser

        currentUser!!.uid.let {
            val users = database.collection(COLLECTION_TAG)
            val userId = it

            users
                .document(userId)
                .delete()
                .addOnSuccessListener {
                    eraseSubCollection(users, userId, currentUser, onComplete)
                }
                .addOnFailureListener {exception ->
                    Log.d(TAG, "Error getting documents: ", exception)
                    onFailure()
                }
        }
    }

    private fun eraseSubCollection(
        users: CollectionReference,
        userId: String,
        currentUser: FirebaseUser,
        onComplete: () -> Unit
    ) {
        users
            .document(userId)
            .collection(SUB_COLLECTION_TAG)
            .get()
            .addOnSuccessListener { result ->
                eraseDocuments(result, currentUser, onComplete)
            }
            .addOnFailureListener { exception ->
                throw exception
            }
    }

    private fun eraseDocuments(
        result: QuerySnapshot,
        currentUser: FirebaseUser,
        onComplete: () -> Unit
    ) {
        val batch = database.batch()

        for (document in result) {
            batch.delete(document.reference)
        }

        batch.commit()
            .addOnSuccessListener {
                eraseUserFromAuthentication(currentUser, onComplete)
            }
            .addOnFailureListener { exception ->
                throw exception
            }
    }

    private fun eraseUserFromAuthentication(
        currentUser: FirebaseUser,
        onComplete: () -> Unit
    ) {
        currentUser
            .delete()
            .addOnSuccessListener {
                MonthInformationService.clean()
                onComplete()
            }
            .addOnFailureListener { exception ->
                throw exception
            }
    }

}