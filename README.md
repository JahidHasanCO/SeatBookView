# SeatBookView
SeatBookView is an Android Studio Library that helps to make it easier to create Bus, Train, Cinema Theater Seat UI and all functionalities are given. Based on [varunjohn/Booking-Seats-Layout-Sample](https://github.com/varunjohn/Booking-Seats-Layout-Sample) [![](https://jitpack.io/v/JahidHasanCO/SeatBookView.svg)](https://jitpack.io/#JahidHasanCO/SeatBookView)


## Preview 
<img src="https://github.com/JahidHasanCO/SeatBookView/blob/master/ART/Home.jpg" width="270" height="585"> 

# Installation
**For Gradle:**

**Step 1:** Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```sh
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

**Step 2:** Add the dependency
```sh
dependencies {
	        implementation 'com.github.JahidHasanCO:SeatBookView:1.0.2'
	}
```

**For Maven:**

To get a Git project into your build:
**Step 1:**  Add the JitPack repository to your build file

```sh
<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```

**Step 2:** Add the dependency

```
	<dependency>
	    <groupId>com.github.JahidHasanCO</groupId>
	    <artifactId>SeatBookView</artifactId>
	    <version>Tag</version>
	</dependency>
```

# Usage

```xml
     <dev.jahidhasanco.seatbookview.SeatBookView
            android:layout_marginTop="10dp"
            android:id="@+id/layoutSeat"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical"
            />
```

For creating a seat system you have to create a String for your seat position.
```kotlin
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
                    "/AAAAA"
            )
```
| Symbol | Uses For |
| ------ | ------ |
| A | Use to create a seat that hasn't book |
| R | Use to create a seat that is already reserved. |
| U | Use to create a seat that is already booked. |
| S | Use to create an empty seat that you can assign Drawable. |
| / | Use to create a new row. |
| _ | Use to create a space.|

---


For setting the custom title of a seat, you need to create an ArrayList and set the value by index (You need to add value the same as your seat plan).
```kotlin
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
        "/", "H1", "H2", "H3", "H4", "H5"
    )
```

Create SeatBookView Object and set Layout, Titles, Seat String, and some Attributes.
```kotlin
    private lateinit var seatBookView: SeatBookView
    private lateinit var viewGroupLayout: ViewGroup
        
    viewGroupLayout = findViewById(R.id.layoutSeat)
    seatBookView = SeatBookView(this)
        .setSeatGaping(5)
        .setSeatSizeBySeatsColumn(6)
        .setSeatsLayoutString(seats)
        .isCustomTitle(true)
        .setCustomTitle(title)
        .setSelectSeatLimit(2)
        
    seatBookView.setSeatViewLayout(viewGroupLayout)
    seatBookView.show()
```

------------


For track click on seat to call setSeatLongClickListener()

```kotlin
        seatBookView.setSeatClickListener(object : SeatBookView.SeatClickListener {
            override fun onAvailableSeatClick(selectedIdList: List<Int>, view: View) {
            }
            override fun onBookedSeatClick(view: View) {
            }
            override fun onReservedSeatClick(view: View) {
            }
        })
```

------------


For track Long press on seat to call setSeatLongClickListener()

```kotlin
    seatBookView.setSeatLongClickListener(object:SeatBookView.SeatLongClickListener{
            override fun onAvailableSeatLongClick(view: View) {
                Toast.makeText(this@MainActivity,"Long Pressed",Toast.LENGTH_SHORT).show()
            }
            override fun onBookedSeatLongClick(view: View) {
            }
            override fun onReservedSeatLongClick(view: View) {
            }
        })
```
