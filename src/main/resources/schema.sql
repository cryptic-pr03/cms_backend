create table if not exists User(
    userId int primary key AUTO_INCREMENT,
    firstName varchar(50),
    lastName varchar(50),
    email varchar(100) NOT NULL UNIQUE,
    password varchar(100) NOT NULL,
    contactNo varchar(15),
    gender varchar(10),
    DOB datetime
);

create table if not exists Venue(
    venueId int primary key AUTO_INCREMENT,
    name varchar(50),
    capacity int,
    city varchar(50),
    landmark varchar(50),
    state varchar(50),
    isFuntional boolean,
--     seatMatrixDescription varchar(100),
    picSeatMatrixUrl varchar(100)
);


-- staff table modified
create table if not exists Staff (
    staffId int PRIMARY KEY AUTO_INCREMENT ,
    firstName varchar(50),
    lastName varchar(50),
--     COMPANY EMAIL
    email varchar(100) NOT NULL UNIQUE,
    password varchar(100) NOT NULL default "password123.",
    contactNo varchar(15),
    gender varchar(10),
    DOB datetime
    role int NOT NULL default 0, --caterer, sweeper, etc
    groupNumber int,
    salary int,
--     timings time,
    venueId int ,
    joiningDate date,
    leavingDate date ,
    accountNo varchar(20) primary key,
    IFSCCode varchar(20),
    bankName varchar(50),
    branchName varchar(50),
    foreign key (venueId) references Venue(venueId),
);

create table if not exists Event(
    eventId int primary key AUTO_INCREMENT,
    name varchar(50),
    startTime time,
    endTime time,
    ageLimit int,
    eventDate date,
    logoUrl varchar(100)
);


create table if not exists Seat(
    seatId int not null,
    venueId int,
    seatType varchar(20), 
    primary key (seatId, venueId)
    foreign key (venueId) references venue(venueId)
    );

-- venue id was removed
create table if not exists EventSeatStatus (
    seatId int,
    eventId int,
    isBooked boolean default false,
    price int,
    foreign key (seatId) references Seat(seatId),
    foreign key (eventId) references Event(eventId),
    primary key (seatId, eventId)
    );


create table if not exists Review(
    reviewId int primary key AUTO_INCREMENT,
    reviewData varchar(100),
    userId int,
    eventId int,
    foreign key (userId) references User(userId),
    foreign key (eventId) references Event(eventId)
);


create table if not exists Slot (
    slotId int not null,
    venueId int,
    startTime datetime,
    endTime datetime,
    price int,
    isRented boolean default false,
    primary key (slotId, venueId)
    foreign key (venueId) references Venue(venueId),
    );

-- event takes places in slot
create table if not exists TakesPlace (
    venueId int not null,
    slotId int not null,
    eventId int not null,
    foreign key (venueId) references Venue(venueId),
    foreign key (slotId) references Slot(slotId),
    foreign key (eventId) references Event(eventId),
    primary key (venueId, slotId, eventId)
);


--  staff works for which event
create table if not exists WorksFor (
    staffId int,
    eventId int,
    foreign key (staffId) references Staff(staffId),
    foreign key (eventId) references Event(eventId),
    primary key (staffId,  eventId)
);



create table if not exists Sponsor (
    eventId int,
    sponsorName varchar(50),
    foreign key (eventId) references Event(eventId),
    primary key (eventId, sponsorName)
);

create table if not exists Pic(
    venueId int,
    venuePicUrl varchar(100),
    foreign key (venueId) references Venue(venueId),
    primary key (venueId, venuePicUrl)
);

create table Salary(
    staffId int,
    timeOfPayment datetime,
    amount int,
    bonus int,
    paidStatus boolean default false,
    foreign key (staffId) references Staff(staffId),
    primary key (staffId, timeOfPayment)
);


    

create table if not exists Transaction (
    transactionId int primary key AUTO_INCREMENT,
    date date,
    time time,
    type varchar(20),
    amount int,
    userId int,
    eventId int,
    transactionImage varchar(100),
    foreign key (userId) references user(userId),
    foreign key (eventId) references event(eventId)
);


create table if not exists SeatBook(
    eventId int,
    seatId int,
    transactionId int,
    foreign key (venueId) references Event(eventId),
    foreign key (seatId) references Seat(seatId),
    foreign key (transactionId) references Transaction(TransactionId),
    primary key (venueId, seatId, transactionId)
    );


create table if not exists BankDetails(
    accountNo varchar(20) primary key,
    IFSCCode varchar(20),
    bankName varchar(50),
    branchName varchar(50),
    userId int,
    foreign key (userId) references User(userId)
);

create table if not exists TypeUser(
    userId int,
    role int,
    foreign key (userId) references User(userId),
    primary key (userId, role)
);