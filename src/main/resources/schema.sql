create table if not exists User
(
    userId    int primary key AUTO_INCREMENT,
    firstName varchar(50),
    lastName  varchar(50),
    email     varchar(100) NOT NULL UNIQUE,
    password  varchar(100) NOT NULL,
    contactNo varchar(15),
    gender    varchar(10),
    DOB       date
);

create table if not exists Venue
(
    venueId          int primary key AUTO_INCREMENT,
    name             varchar(50),
    silverSeats      int,
    goldSeats        int,
    platinumSeats    int,
    city             varchar(50),
    landmark         varchar(50),
    state            varchar(50),
    isFunctional     boolean default false,
    picSeatMatrixUrl varchar(100)
);


create table if not exists Staff
(
    staffId     int PRIMARY KEY AUTO_INCREMENT,
    firstName   varchar(50),
    lastName    varchar(50),
--     COMPANY EMAIL
    email       varchar(100) NOT NULL UNIQUE,
    password    varchar(100) default '$2a$12$l7l4xHc9C4sKUoc4H7Itle9IRKhdqM6glMFm8.I3kH7aVCWh6.FMe',
    contactNo   varchar(15),
    gender      varchar(10),
    DOB         date,
    role        int          NOT NULL,
    groupNumber int,
    salary      int          default 0,
    venueId     int,
    joiningDate date,
    leavingDate date,
    accountNo   varchar(20),
    IFSCCode    varchar(20),
    foreign key (venueId) references Venue (venueId) on delete cascade
);

create table if not exists Event
(
    eventId     int primary key AUTO_INCREMENT,
    name        varchar(50),
    startTime   time,
    endTime     time,
    ageLimit    int,
    eventDate   date,
    description varchar(500),
    logoUrl     varchar(100)
);


create table if not exists Seat
(
    seatId   int not null,
    venueId  int,
    seatType varchar(20),
    primary key (seatId, venueId),
    foreign key (venueId) references Venue (venueId) on delete cascade
);

create table if not exists EventSeat
(
    seatId   int,
    eventId  int,
    isBooked boolean default false,
    price    int     default 0,
    foreign key (seatId) references Seat (seatId) on delete cascade,
    foreign key (eventId) references Event (eventId) on delete cascade,
    primary key (seatId, eventId)
);


create table if not exists Review
(
    reviewId   int primary key AUTO_INCREMENT,
    reviewData varchar(100),
    userId     int,
    eventId    int,
    foreign key (userId) references User (userId) on delete cascade,
    foreign key (eventId) references Event (eventId) on delete cascade
);


create table if not exists Slot
(
    slotId    int not null,
    venueId   int,
    startTime time,
    endTime   time,
    slotDate  date,
    price     int     default 0,
    isRented  boolean default false,
    primary key (slotId, venueId, slotDate),
    foreign key (venueId) references Venue (venueId) on delete cascade
);

-- event takes places in slot
create table if not exists TakesPlace
(
    venueId int not null,
    slotId  int not null,
    eventId int not null,
    foreign key (venueId) references Venue (venueId) on delete cascade,
    foreign key (slotId) references Slot (slotId) on delete cascade,
    foreign key (eventId) references Event (eventId) on delete cascade,
    primary key (venueId, slotId, eventId)
);


--  staff works for which event
create table if not exists WorksFor
(
    staffId int,
    eventId int,
    foreign key (staffId) references Staff (staffId) on delete cascade,
    foreign key (eventId) references Event (eventId) on delete cascade,
    primary key (staffId, eventId)
);

create table if not exists Sponsor
(
    eventId     int,
    sponsorName varchar(50),
    foreign key (eventId) references Event (eventId) on delete cascade,
    primary key (eventId, sponsorName)
);

create table if not exists Pic
(
    venueId     int,
    venuePicUrl varchar(100),
    foreign key (venueId) references Venue (venueId) on delete cascade,
    primary key (venueId, venuePicUrl)
);

# create table if not exists Salary
# (
#     staffId       int,
#     timeOfPayment datetime,
#     amount int,
#     bonus         int     default 0,
#     paidStatus    boolean default false,
#     foreign key (staffId) references Staff (staffId) on delete cascade,
#     primary key (staffId, timeOfPayment)
# );

create table if not exists Transaction
(
    transactionId       varchar(30) primary key,
    date                date,
    time                time,
    type                varchar(20),
    amount              int,
    userId              int,
    eventId             int,
    transactionImageUrl varchar(100),
    foreign key (userId) references User (userId) on delete cascade,
    foreign key (eventId) references Event (eventId) on delete cascade
);


create table if not exists SeatBook
(
    eventId       int,
    seatId        int,
    transactionId varchar(30),
    foreign key (eventId) references Event (eventId) on delete cascade,
    foreign key (seatId) references Seat (seatId) on delete cascade,
    foreign key (transactionId) references Transaction (transactionId),
    primary key (eventId, seatId, transactionId)
);


create table if not exists BankDetails
(
    accountNo varchar(20),
    IfscCode  varchar(20),
    userId    int,
    primary key (userId, accountNo),
    foreign key (userId) references User (userId) on delete cascade
);

create table if not exists TypeUser
(
    userId int,
    role   int,
    foreign key (userId) references User (userId) on delete cascade,
    primary key (userId, role)
);

# INSERT INTO Staff
# values (0, "Prince", "Roy", "princeeroyy@cms.com", "$2a$12$l7l4xHc9C4sKUoc4H7Itle9IRKhdqM6glMFm8.I3kH7aVCWh6.FMe",
#         "7658842288", "Male", "1994-12-19", 5, 0, 0, NULL, "2020-10-10", NULL, "1007441030021453", "CBIN0R1001");