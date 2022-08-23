package dev.jahidhasanco.demo_app


import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginBottom
import androidx.core.view.setPadding
import dev.jahidhasanco.seatbookview.SeatBookView


class MainActivity : AppCompatActivity() {

    private lateinit var seatBookView: SeatBookView
    private lateinit var viewGroupLayout: ViewGroup
    private var seats = (
            "U____/" +
                    "_____/" +
                    "AA_AA/" +
                    "UA_AR/" +
                    "AA_AA/" +
                    "AAAAA/")

    private var title = (
            "EEEEE" +
                    "_____" +
                    "AAAAA" +
                    "BBBBB" +
                    "CCCCC" +
                    "DDDDD"
            )

    private val STATUS_AVAILABLE = 1
    private val STATUS_BOOKED = 2
    private val STATUS_RESERVED = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewGroupLayout = findViewById(R.id.layoutSeat)
        seatBookView = SeatBookView(this)
            .setSeatGaping(5)
            .setSeatsLayoutString(seats)

        seatBookView.setSeatTextSize(21f)
        seatBookView.setSeatViewLayout(viewGroupLayout)
        seatBookView.show()

        seatBookView.setSeatClickListener(object : SeatBookView.SeatClickListener {

            override fun onAvailableSeatClick(selectedIds: String, view: View) {
                Toast.makeText(
                    this@MainActivity,
                    "Seat " + view.id.toString() + " is Selected",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onBookedSeatClick(seatId: String, view: View) {
                Toast.makeText(
                    this@MainActivity,
                    "Seat " + view.id.toString() + " is Booked",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onReservedSeatClick(seatId: String, view: View) {
                Toast.makeText(
                    this@MainActivity,
                    "Seat " + view.id.toString() + " is Reserved",
                    Toast.LENGTH_SHORT
                ).show()
            }


        })

    }


}