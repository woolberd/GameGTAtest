package com.example.gamegtatest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gamegtatest.databinding.PhoneNotifyBinding

class NotificationState(
    private val context: Context,
    private val parentView: ViewGroup
) : BaseFragment<PhoneNotifyBinding>(context, parentView) {

    private lateinit var state: PhoneUiState

    override fun inflate(inflater: LayoutInflater): PhoneNotifyBinding {
        return PhoneNotifyBinding.inflate(inflater, null, false)
    }

    public override fun onCreateView() {
        super.onCreateView()

        state = PhoneUiState(type = getServerValue())

        handleNotification(state.type)
    }

    override fun closeCurrentFragment() {
        if (isShow) {
            onDestroy()
        }
    }

    private fun handleNotification(type: Int) {
        val imgViews = mapOf(
            PhoneNotifyType.CALL_IN.value to mViewUI.imgNotification0,
            PhoneNotifyType.CALL_TO.value to mViewUI.imgNotification1,
            PhoneNotifyType.MASSAGE.value to mViewUI.imgNotification2,
            PhoneNotifyType.MED.value to mViewUI.imgNotification3,
            PhoneNotifyType.TAXI.value to mViewUI.imgNotification4,
            PhoneNotifyType.POLICE.value to mViewUI.imgNotification5,
            PhoneNotifyType.MECHANIC.value to mViewUI.imgNotification6
        )

        val notificationsViews = mapOf(
            PhoneNotifyType.CALL_IN.value to mViewUI.notifications0,
            PhoneNotifyType.CALL_TO.value to mViewUI.notifications1,
            PhoneNotifyType.MASSAGE.value to mViewUI.notifications2,
            PhoneNotifyType.MED.value to mViewUI.notifications3,
            PhoneNotifyType.TAXI.value to mViewUI.notifications4,
            PhoneNotifyType.POLICE.value to mViewUI.notifications5,
            PhoneNotifyType.MECHANIC.value to mViewUI.notifications6
        )

        imgViews.values.forEach { it.visibility = View.GONE }
        notificationsViews.values.forEach { it.visibility = View.GONE }

        imgViews[type]?.visibility = View.VISIBLE
        notificationsViews[type]?.visibility = View.VISIBLE
    }

    private fun getServerValue(): Int {
        return PhoneNotifyType.POLICE.value
    }
}
