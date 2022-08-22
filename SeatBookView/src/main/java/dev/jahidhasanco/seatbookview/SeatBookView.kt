package dev.jahidhasanco.seatbookview

import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast


class SeatBookView
constructor(private val context: Context)
{

    private lateinit var viewGroupLayout: ViewGroup
    private var seats = (
            "UU_RR/"+
                    "AA_AA/"+
                    "UA_AR/"+
                    "AA_AA/"+
                    "AA_AU/"+
                    "RA_AA/"+
                    "AA_AA/"+
                    "AAAAA/")

    private var seatViewList: ArrayList<TextView> = arrayListOf()
    private var seatSize = 300
    private var seatGaping = 10

    private val STATUS_AVAILABLE = 1
    private val STATUS_BOOKED = 2
    private val STATUS_RESERVED = 3
    private var selectedIds = ""

  //  private var dp = 0f

    fun setSeatGaping(size:Int): SeatBookView {
        seatGaping = size
        return this
    }

    fun setSeatSize(size:Int): SeatBookView {
        seatSize = size
        return this
    }

    fun setSeatsLayoutString(seats: String):SeatBookView{
        this.seats = "/$seats"
        return this
    }

    fun setSeatViewLayout(layout: ViewGroup){
        viewGroupLayout = layout
    }

    fun getSelectedIds(): String {
        return selectedIds
    }

//    dp = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, context.resources.displayMetrics)

        





    private var count = 0

    fun show() {
        val layoutSeat = LinearLayout(context)
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutSeat.orientation = LinearLayout.VERTICAL
        layoutSeat.layoutParams = params
        layoutSeat.setPadding(8 * seatGaping, 8 * seatGaping, 8 * seatGaping, 8 * seatGaping)
        viewGroupLayout.addView(layoutSeat)

        var layout: LinearLayout? = null
        for (index in seats.indices) {
            if (seats[index] == '/') {
                layout = LinearLayout(context)
                layout.orientation = LinearLayout.HORIZONTAL
                layout.gravity = Gravity.CENTER
                layoutSeat.addView(layout)
            } else if (seats[index] == 'U') {
                count++
                val view = TextView(context)
                val layoutParams = LinearLayout.LayoutParams(seatSize, seatSize)
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping)
                view.layoutParams = layoutParams
                view.setPadding(0, 0, 0, 2 * seatGaping)
                view.id = count
                view.gravity = Gravity.CENTER
                view.setBackgroundResource(R.drawable.seats_booked)
                view.setTextColor(Color.WHITE)
                view.tag = STATUS_BOOKED
                view.text = "$count"
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14f)
                layout!!.addView(view)
                seatViewList.add(view)
                view.setOnClickListener {
                    seatClick(it)
                }
            } else if (seats[index] == 'A') {
                count++
                val view = TextView(context)
                val layoutParams = LinearLayout.LayoutParams(seatSize, seatSize)
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping)
                view.layoutParams = layoutParams
                view.setPadding(0, 0, 0, 2 * seatGaping)
                view.id = count
                view.gravity = Gravity.CENTER
                view.setBackgroundResource(R.drawable.seats_book)
                view.text = "$count"
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14f)
                view.setTextColor(Color.BLACK)
                view.tag = STATUS_AVAILABLE
                layout!!.addView(view)
                seatViewList.add(view)
                view.setOnClickListener {
                    seatClick(it)
                }
            } else if (seats[index] == 'R') {
                count++
                val view = TextView(context)
                val layoutParams = LinearLayout.LayoutParams(seatSize, seatSize)
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping)
                view.layoutParams = layoutParams
                view.setPadding(0, 0, 0, 2 * seatGaping)
                view.id = count
                view.gravity = Gravity.CENTER
                view.setBackgroundResource(R.drawable.seats_reserved)
                view.text = "$count"
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14f)
                view.setTextColor(Color.WHITE)
                view.tag = STATUS_RESERVED
                layout!!.addView(view)
                seatViewList.add(view)
                view.setOnClickListener {
                    seatClick(it)
                }
            } else if (seats[index] == '_') {
                val view = TextView(context)
                val layoutParams = LinearLayout.LayoutParams(seatSize, seatSize)
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping)
                view.layoutParams = layoutParams
                view.setBackgroundColor(Color.TRANSPARENT)
                view.text = ""
                layout!!.addView(view)
            }
        }

    }
     fun seatClick(view: View) {
        if (view.tag as Int == STATUS_AVAILABLE) {
            if (selectedIds.contains(view.id.toString() + ",")) {
                selectedIds = selectedIds.replace(view.id.toString() + ",", "")
                view.setBackgroundResource(R.drawable.seats_book)
            } else {
                selectedIds = selectedIds + view.id.toString() + ","
                view.setBackgroundResource(R.drawable.seats_selected)
            }
        } else if (view.tag as Int == STATUS_BOOKED) {
            Toast.makeText(
                context,
                "Seat " + view.id.toString() + " is Booked",
                Toast.LENGTH_SHORT
            ).show()
        } else if (view.tag as Int == STATUS_RESERVED) {
            Toast.makeText(
                context,
                "Seat " + view.id.toString() + " is Reserved",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}