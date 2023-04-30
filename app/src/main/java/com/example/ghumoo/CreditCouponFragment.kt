package com.example.ghumoo

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView

class CreditCouponFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View =  inflater.inflate(R.layout.fragment_credit_coupon, container, false)

        val zomato = view.findViewById<CardView>(R.id.ZomatoRedeem)
        val netmeds = view.findViewById<CardView>(R.id.netmeds)
        val oyo = view.findViewById<CardView>(R.id.oyo)
        val swiggy = view.findViewById<CardView>(R.id.swiggy)
        val ixigo = view.findViewById<CardView>(R.id.ixigo)

        val offer_zomato = R.layout.zomato_offer_bottom_sheet
        val offer_netmeds = R.layout.netmeds_offer_bottom_sheet
        val oyo_offer = R.layout.oyo_offer_bottom_sheet
        val swiggy_offer = R.layout.swiggy_offer_bottom_sheet
        val ixigo_offer = R.layout.ixigo_offer_bottom_sheet

        zomato.setOnClickListener {
            showDialog(offer_zomato)
        }
        netmeds.setOnClickListener {
            showDialog(offer_netmeds)
        }
        oyo.setOnClickListener {
            showDialog(oyo_offer)
        }
        swiggy.setOnClickListener {
            showDialog(swiggy_offer)
        }
        ixigo.setOnClickListener {
            showDialog(ixigo_offer)
        }

        return view
    }

    private fun showDialog(offer: Int) {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(offer)
        val copy = dialog.findViewById<CardView>(R.id.copyPromo)
        copy.setOnClickListener {
            val copyTxt = dialog.findViewById<TextView>(R.id.promoCode).text.toString()
            Toast.makeText(requireContext(), "Promo code ' $copyTxt ' copied to Clipboard", Toast.LENGTH_SHORT).show()
        }
        dialog.show()
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)
    }
}