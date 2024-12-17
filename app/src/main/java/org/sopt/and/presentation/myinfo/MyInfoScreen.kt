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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.and.R
import org.sopt.and.core.utils.SnackBarUtils
import org.sopt.and.presentation.components.EmptyInfoBox
import org.sopt.and.presentation.myinfo.components.MyInfoPaymentInducementBox
import org.sopt.and.presentation.myinfo.components.MyInfoProfile
import org.sopt.and.presentation.mypage.viewmodel.MyViewModel
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.Black100

@Composable
fun MyInfo(
    navigateToSignIn : () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MyViewModel = hiltViewModel()
){
   val context = LocalContext.current

    val myPageState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.sendEvent(MyInfoContract.MyPageUiEvent.LoadHobby)
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is MyInfoContract.MyPageUiEffect.ShowErrorSnackBar -> {
                    SnackBarUtils.showSnackBar(
                        message = effect.message,
                        actionLabel = context.getString(R.string.sign_in_snackbar_action_close)
                    )
                }

                MyInfoContract.MyPageUiEffect.NavigateToSignIn -> {
                    navigateToSignIn()
                }
            }
        }
    }

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

