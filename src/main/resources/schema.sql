create table if not exists User(
    userId int primary key AUTO_INCREMENT,
    firstName varchar(50),
    lastName varchar(50),
    email varchar(100) NOT NULL UNIQUE,
    password varchar(100) NOT NULL,
    contactNo varchar(15),
    gender varchar(10),
    DOB date
);

create table if not exists Venue(
    venueId int primary key AUTO_INCREMENT,
    name varchar(50),
    capacity int,
    city varchar(50),
    landmark varchar(50),
    state varchar(50),
    isFunctional boolean,
    seatMatrixDescription varchar(100),
    picSeatMatrixUrl varchar(100)
);


-- staff table modified
create table if not exists Staff (
    staffId int PRIMARY KEY AUTO_INCREMENT,
    firstName varchar(50),
    lastName varchar(50),
--     COMPANY EMAIL
    email varchar(100) NOT NULL UNIQUE,
    password varchar(100) NOT NULL default 'password123.',
    contactNo varchar(15),
    gender varchar(10),
    DOB date,
    role int NOT NULL default 0,
    groupNumber int,
    salary int default 0,
--     timings time,
    venueId int NOT NULL,
    joiningDate date,
    leavingDate date ,
    accountNo varchar(20),
    IFSCCode varchar(20),
--     bankName varchar(50),
--     branchName varchar(50),
    foreign key (venueId) references Venue(venueId) on delete cascade
);

create table if not exists Event(
    eventId int primary key AUTO_INCREMENT,
    name varchar(50),
    startTime time,
    endTime time,
    ageLimit int,
    eventDate date,
    description varchar(200),
    logoUrl varchar(100)
);


create table if not exists Seat(
    seatId int not null,
    venueId int,
    seatType varchar(20), 
    primary key (seatId, venueId),
    foreign key (venueId) references Venue(venueId) on delete cascade
);

-- venue id was removed and newName EventSeat
create table if not exists EventSeat (
    seatId int,
    eventId int,
    isBooked boolean default false,
    price int default 0,
    foreign key (seatId) references Seat(seatId) on delete cascade,
    foreign key (eventId) references Event(eventId) on delete cascade,
    primary key (seatId, eventId)
);


create table if not exists Review(
    reviewId int primary key AUTO_INCREMENT,
    reviewData varchar(100),
    userId int,
    eventId int,
    foreign key (userId) references User(userId) on delete cascade,
    foreign key (eventId) references Event(eventId) on delete cascade
);


create table if not exists Slot (
    slotId int not null,
    venueId int,
    startTime time,
    endTime time,
    slotDate date,
    price int default 0,
    isRented boolean default false,
    primary key (slotId, venueId, slotDate),
    foreign key (venueId) references Venue(venueId) on delete cascade
);

-- event takes places in slot
create table if not exists TakesPlace (
    venueId int not null,
    slotId int not null,
    eventId int not null,
    foreign key (venueId) references Venue(venueId) on delete cascade,
    foreign key (slotId) references Slot(slotId) on delete cascade,
    foreign key (eventId) references Event(eventId) on delete cascade,
    primary key (venueId, slotId, eventId)
);


--  staff works for which event
create table if not exists WorksFor (
    staffId int,
    eventId int,
    foreign key (staffId) references Staff(staffId) on delete cascade,
    foreign key (eventId) references Event(eventId)on delete cascade,
    primary key (staffId,  eventId)
);

create table if not exists Sponsor (
    eventId int,
    sponsorName varchar(50),
    foreign key (eventId) references Event(eventId) on delete cascade,
    primary key (eventId, sponsorName)
);

create table if not exists Pic(
    venueId int,
    venuePicUrl varchar(100),
    foreign key (venueId) references Venue(venueId) on delete cascade,
    primary key (venueId, venuePicUrl)
);

create table if not exists Salary(
    staffId int,
    timeOfPayment datetime,
--     amount int,
    bonus int default 0,
    paidStatus boolean default false,
    foreign key (staffId) references Staff(staffId) on delete cascade,
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
    transactionImageUrl varchar(100),
    foreign key (userId) references User(userId) on delete cascade,
    foreign key (eventId) references Event(eventId) on delete cascade
);


create table if not exists SeatBook(
    eventId int,
    seatId int,
    transactionId int,
    foreign key (eventId) references Event(eventId) on delete cascade,
    foreign key (seatId) references Seat(seatId) on delete cascade,
    foreign key (transactionId) references Transaction(TransactionId),
    primary key (eventId, seatId, transactionId)
);


create table if not exists BankDetails(
    accountNo varchar(20),
    IfscCode varchar(20),
--     bankName varchar(50),
--     branchName varchar(50),
    userId int,
    primary key (userId, accountNo),
    foreign key (userId) references User(userId) on delete cascade
);

create table if not exists TypeUser(
    userId int,
    role int,
    foreign key (userId) references User(userId) on delete cascade,
    primary key (userId, role)
);
