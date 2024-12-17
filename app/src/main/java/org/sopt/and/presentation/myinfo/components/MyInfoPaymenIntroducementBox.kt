package org.sopt.and.presentation.myinfo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.ui.theme.Grey100
import org.sopt.and.ui.theme.Grey200
import org.sopt.and.ui.theme.White100


@Composable
fun MyInfoPaymentInducementBox(
    paymentInducementText: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Grey100)
            .padding(16.dp)
    ) {
        Text(
            text = paymentInducementText,
            color = Grey200
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(id = R.string.my_to_payment_button),
            color = White100
        )
    }
}