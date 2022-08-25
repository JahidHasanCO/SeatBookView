package dev.jahidhasanco.demo_app


import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding
import dev.jahidhasanco.seatbookview.SeatBookView
import dev.jahidhasanco.seatbookview.SeatClickListener
import dev.jahidhasanco.seatbookview.SeatLongClickListener


class MainActivity : AppCompatActivity() {

    private lateinit var seatBookView: SeatBookView
    private var seats = (
            "/U___S" +
                    "/_____" +
                    "/AA_AA" +
                    "/UA_AR" +
                    "/AA_AA" +
                    "/RU_AA" +
                    "/AA_AR" +
                    "/AU_AA" +
                    "/AA_AA" +
                    "/AA_AA" +
                    "/RU_AA" +
                    "/AA_AR" +
                    "/AU_AA" +
                    "/AA_AA" +
                    "/AAAAA"

            )


    private var title = listOf(
        "/", "I1", "", "", "", "E5",
        "/", "", "", "", "", "",
        "/", "A1", "A2", "", "A3", "A4",
        "/", "B1", "B2", "", "B2", "B4",
        "/", "C1", "C2", "", "C3", "C4",
        "/", "D1", "D2", "", "D3", "D4",
        "/", "E1", "E2", "", "E3", "E4",
        "/", "F1", "F2", "", "F3", "F4",
        "/", "G1", "G2", "", "G3", "G4",
        "/", "C1", "C2", "", "C3", "C4",
        "/", "D1", "D2", "", "D3", "D4",
        "/", "E1", "E2", "", "E3", "E4",
        "/", "F1", "F2", "", "F3", "F4",
        "/", "G1", "G2", "", "G3", "G4",
        "/", "H1", "H2", "H3", "H4", "H5"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seatBookView = findViewById(R.id.layoutSeat)
        seatBookView.setSeatGaping(5)
            .setSeatSizeBySeatsColumn(6)
            .setSeatsLayoutString(seats)
            .isCustomTitle(true)
            .setCustomTitle(title)
            .setSelectSeatLimit(2)

        seatBookView.show()


        seatBookView.getSeatView(2).apply {
            seatBookView.markAsTransparentSeat(this as TextView)
            this.setBackgroundResource(R.drawable.ic_steering)
            this.setPadding(5)
        }

        seatBookView.setSeatClickListener(object : SeatClickListener {

            override fun onAvailableSeatClick(selectedIdList: List<Int>, view: View) {

            }

            override fun onBookedSeatClick(view: View) {

            }

            override fun onReservedSeatClick(view: View) {

            }


        })

        seatBookView.setSeatLongClickListener(object : SeatLongClickListener {

            override fun onAvailableSeatLongClick(view: View) {
                Toast.makeText(this@MainActivity, "Long Pressed", Toast.LENGTH_SHORT).show()
            }

            override fun onBookedSeatLongClick(view: View) {

            }

            override fun onReservedSeatLongClick(view: View) {

            }

        })

    }


}