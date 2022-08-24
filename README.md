![GitHub Cards Preview](https://github.com/JahidHasanCO/SeatBookView/blob/master/ART/cover.png)
# SeatBookView
SeatBookView is an Android Studio Library that helps to make it easier to create Bus 🚍, Train 🚉, Cinema Theater Seat UI and all functionalities are given. Based on [varunjohn/Booking-Seats-Layout-Sample](https://github.com/varunjohn/Booking-Seats-Layout-Sample) [![](https://jitpack.io/v/JahidHasanCO/SeatBookView.svg)](https://jitpack.io/#JahidHasanCO/SeatBookView)


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


**Attributes of this Library**

|  XML Attributes  | Description   |
| ------------ | ------------ |
| app:available_seat_background  | This attribute used to set background of non booked seat for any Drawable file.  |
| app:reserved_seat_background  | This attribute used to set background of reserved seat for any Drawable file.    |
| app:booked_seat_background  | This attribute used to set background of  booked seat for any Drawable file.    |
| app:selected_seats_background  | This attribute used to set background of selected seat for any Drawable file.    |
| app:reserved_seats_text_color  | Color of the reserved seat text.   |
| app:booked_seats_text_color  | Color of the booked seat text.  |
| app:available_seats_text_color  | Color of the non booked seat text.  |
| app:seat_size  | Size of the Seat  |
| app:seat_size_by_seats_column  | This attribute assigns an "importance" value to a seat in terms of how much space it should occupy on the screen. A larger weight value allows it to expand to fill any remaining space in the parent view.   | |

Check All XML Attributes [here](https://github.com/JahidHasanCO/SeatBookView/blob/master/ATTRS.md)

------------


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

**Functions of this Library**

| Functions  | Description  |
| ------------ | ------------ |
| isCustomTitle(r: Boolean)  | use to set custom title on seat title.  |
| setCustomTitle(titles: List<String>)  | To set custom titles this function needs ArrayList of String.  |
| setSelectSeatLimit(limit: Int)  | Use to set selected seat limit.  |
| setSeatGaping(size: Int)  | use for each seat margin.  |
| setSeatLayoutPadding(size: Int) | Use to set Container Layout Padding. |
| setSeatSize(size: Int) | Use to set seat size. This size uses for height and weight. |
| setSeatSizeBySeatsColumn(seatsInColumn: Int) | This function assigns an "importance" value to a seat in terms of how much space it should occupy on the screen. A larger weight value allows it to expand to fill any remaining space in the parent view. | |

Check all functions [here](https://github.com/JahidHasanCO/SeatBookView/blob/master/KT_ATTRS.md)

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


## LICENSE


This project is distributed under the MIT license, check the license for more info.


```
MIT License

Copyright (c) 2022 Md. Zahidul Islam

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```


### Contributing 💡
If you want to contribute to this project and make it better with new ideas, your pull request is very welcomed.
If you find any issue just put it in the repository issue section, thank you.
