package com.example.pokemonchallenge.ui.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.pokemonchallenge.R

class BusyDialogFragment: DialogFragment() {
    companion object {
        private const val FRAGMENT_TAG = "busy"

        fun newInstance() = BusyDialogFragment()

        fun show(supportFragmentManager: FragmentManager): BusyDialogFragment {
            val dialog = BusyDialogFragment.newInstance()
            dialog.isCancelable = false
            dialog.show(supportFragmentManager, FRAGMENT_TAG)
            return dialog
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return requireActivity().layoutInflater.inflate(R.layout.fragment_busy_dialog, container)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.also { window ->
            window.attributes?.also { attributes ->
                window.attributes = attributes
            }
        }
    }
}