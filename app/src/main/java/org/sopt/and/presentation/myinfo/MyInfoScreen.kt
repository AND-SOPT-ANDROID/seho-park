package org.sopt.and.presentation.myinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.presentation.components.EmptyInfoBox
import org.sopt.and.presentation.myinfo.components.MyInfoPaymentInducementBox
import org.sopt.and.presentation.myinfo.components.MyInfoProfile
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.Black100

@Composable
fun MyInfoScreen(
    paddingValues: PaddingValues,
    myHobby: String,
    getMyHobby: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Black100)
            .padding(paddingValues)
    ) {
        MyInfoProfile(
            myHobby = myHobby,
            getMyHobby = getMyHobby,
            modifier = Modifier.weight(0.16f)
        )

        MyInfoPaymentInducementBox(
            paymentInducementText = stringResource(id = R.string.my_first_payment_text),
            modifier = Modifier.weight(0.12f)
        )

        Spacer(modifier = Modifier.height(2.dp))

        MyInfoPaymentInducementBox(
            paymentInducementText = stringResource(id = R.string.my_no_ticket_text),
            modifier = Modifier.weight(0.12f)
        )

        EmptyInfoBox(
            title = stringResource(R.string.my_viewing_history_box_title),
            description = stringResource(R.string.my_viewing_history_box_empty_text),
            modifier = Modifier.weight(0.3f)
        )

        EmptyInfoBox(
            title = stringResource(R.string.my_program_of_interest_box_title),
            description = stringResource(R.string.my_program_of_interest_empty_text),
            modifier = Modifier.weight(0.3f)
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun MyScreenPreview() {
    ANDANDROIDTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            MyInfoScreen(
                paddingValues = innerPadding,
                myHobby = "...Loading",
                getMyHobby = {}
            )
        }
    }
}