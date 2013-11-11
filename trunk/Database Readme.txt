			DATABASE README
This file contains the description for all the database:
Note: all constants are stored in Constant.java
1. movie.txt
	- This file contains the info of all movies in the system
	- Each line contains one string for each movie:
	Format: "movieid|movietype|moviename|movieStatus|rating"
	movietype = 0/1/2 
	0 = MOVIE_TYPE_REGULAR
	1 = MOVIE_TYPE_BLOCK_BUSTER
	2 = MOVIE_TYPE_3D

	movieStatus= 0/1/2/3
	0 = MOVIE_STATUS_COMING_SOON
	1 = MOVIE_STATUS_PREVIEW
	2 = MOVIE_STATUS_NOW_SHOWING
	3 = MOVIE_STATUS_END_OF_SHOWING

2. cinema.txt
	- This file contains the info of all cinemas in the system
	- Each line contains one string for each movie
	Format: "cinemaId|cinemaClass|name|cineplexID|cinemaCode|templateLayoutId"
	cinemaClass= = 0/1
	0 = CINEMA_REGULAR
	1 = CINEMA_VIP
3. cineplex.txt
	- This file contains the info of all cineplex in the system
	- Each line contains one string for each cineplex
	Format: "cineplexID|cineplexName"
4. showtime.txt
	- This file contains the info of all available showtimes in all cineplexs
	- Each line contains one string for each showtime
	Format: "showtimeId|time|movieId|cinemaId|seatLayoutId"
5. booking.txt
	- This file contains the info of all booking made in the system
	- Each line contains one string for each booking
	Format: "bookingId|transactionId|showtimeId|name|handphone|email|age|bookingtime|seatsBooked|price
	seatsbooked = "seat1*seat2*...*seatk*"
	e.g. seatbooked = "1*2*3*4*8*"
6. seat_layout.txt
	- This file contains the seating status of all showtimes
	- Each line contains one string for each seat-layout
	Format: "seatlayoutId|templateLayoutId|seatbooked"
	seatsbooked = "seat1_row*seat1_col*seat2_row*seat2_col*...*"
	e.g. seatbooked = "*3*4*4*3*4*4*"
7. staff.txt
	- This file contains the username/password of a staff
	- Each line contains the username and the hash value of the password
	- Currently, only one staff: "username: admin, password: password"
8.public_holiday.txt
	- This file contains the list of public holiday
	- Each line contains one public holiday
9.price_config.txt
	- This file contains the price config for different cases:
		+ price for regular movie
		+ price for 3D movie
		+ discount for senior citizen
		+ additional charge for VIP class Cinema
		+ addtional charge for Holiday
		+ additional charge for Weekend
10. id.txt
	- This file contains the maximum id generated for each entity,
	- this database is used to generate unique Id whenever a entity is created

