package com.example.kala.screens.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kala.R

const val SEE_REPORT = "See report"
const val SEE_RECORD = "See record"
const val DELETE = "Delete Card"
const val EDIT = "Edit Card"
const val EXPENSE = "Expense Details"
const val INCOME = "Income Details"

val svgMediumButton: Map<String, Int> = mapOf(
    SEE_REPORT to R.drawable.ic_see_report,
    SEE_RECORD to R.drawable.ic_see_record,
    DELETE to R.drawable.ic_delete,
    EDIT to R.drawable.ic_edit,
    EXPENSE to R.drawable.ic_expense,
    INCOME to R.drawable.ic_income,
)

val actionsMediumButton: Map<String, () -> Unit> = mapOf(
    /* TODO Functions associate to a configuration */
)

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun MediumButton(configuration: String) {
    Button(
        onClick = {
            actionsMediumButton.getOrDefault(configuration)
            { println("NOT VALID CONFIGURATION") }
        },
        modifier = Modifier
            .height(150.dp)
            .width(130.dp),
        colors = ButtonDefaults.buttonColors(Color.White),
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(10.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Black, shape = CircleShape)
                    .padding(8.dp)
                    .clip(CircleShape)
                    .size(50.dp)
                    .clip(CircleShape)
                    .padding(7.dp),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(id = svgMediumButton.getOrDefault(configuration, -1)),
                    contentDescription = "SVG FILE"
                )
            }
            Text(
                text = configuration,
                color = Color.Black,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Preview
@Composable
fun PreviewMediumButton() {
    Column {
        MediumButton(SEE_REPORT)
        Spacer(modifier = Modifier.padding(1.dp))
        MediumButton(SEE_RECORD)
        Spacer(modifier = Modifier.padding(1.dp))
        MediumButton(DELETE)
        Spacer(modifier = Modifier.padding(1.dp))
        MediumButton(EDIT)
        Spacer(modifier = Modifier.padding(1.dp))
        MediumButton(EXPENSE)
        Spacer(modifier = Modifier.padding(1.dp))
        MediumButton(INCOME)
        Spacer(modifier = Modifier.padding(1.dp))
    }
}