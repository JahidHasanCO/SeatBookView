package dev.jahidhasanco.demo_app


import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.jahidhasanco.seatbookview.SeatBookView


class MainActivity : AppCompatActivity() {

    private lateinit var seatBookView: SeatBookView
    private lateinit var viewGroupLayout: ViewGroup
    private var seats = (
            "/U____" +
                    "/_____" +
                    "/AA_AA" +
                    "/UA_AR" +
                    "/AA_AA" +
                    "/AAAAA")

    private var title = listOf(
        "/", "E1", "", "", "", "",
        "/", "", "", "", "", "",
        "/", "A1", "A2", "", "A3", "A4",
        "/", "B1", "B2", "", "B2", "B4",
        "/", "C1", "C2", "", "C3", "C4",
        "/", "D1", "D2", "D3", "D4", "D5",
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
            .setSeatLayoutPadding(1)
            .isCustomTitle(true)
            .setCustomTitle(title)

        seatBookView.setSeatTextSize(21f)
        seatBookView.setAvailableSeatsBackground(R.drawable.book)
        seatBookView.setSelectedSeatsBackground(R.drawable.selected)
        seatBookView.setReservedSeatsBackground(R.drawable.reserved)
        seatBookView.setBookedSeatsBackground(R.drawable.booked)
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

            override fun onBookedSeatClick(view: View) {
                Toast.makeText(
                    this@MainActivity,
                    "Seat " + view.id.toString() + " is Booked",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onReservedSeatClick(view: View) {
                Toast.makeText(
                    this@MainActivity,
                    "Seat " + view.id.toString() + " is Reserved",
                    Toast.LENGTH_SHORT
                ).show()
            }


        })

    }


}