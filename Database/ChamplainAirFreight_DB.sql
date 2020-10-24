USE [CAFDB]
GO
/****** Object:  Table [dbo].[Aircraft]    Script Date: 10/24/2020 1:14:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Aircraft](
	[AircraftID] [int] IDENTITY(1,1) NOT NULL,
	[ACModelID] [int] NOT NULL,
	[AircraftStatusID] [int] NOT NULL,
 CONSTRAINT [PK_Aircraft] PRIMARY KEY CLUSTERED 
(
	[AircraftID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[AircraftModels]    Script Date: 10/24/2020 1:14:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AircraftModels](
	[ACModelID] [int] IDENTITY(1,1) NOT NULL,
	[ACMake] [varchar](20) NOT NULL,
	[ACModel] [varchar](20) NOT NULL,
	[ACRange] [varchar](20) NOT NULL,
	[ACRangeClassification] [varchar](20) NOT NULL,
	[ACPayload] [float] NOT NULL,
	[ACLoadVolume] [float] NOT NULL,
 CONSTRAINT [PK_AircraftModels] PRIMARY KEY CLUSTERED 
(
	[ACModelID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Airports]    Script Date: 10/24/2020 1:14:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Airports](
	[AirportID] [int] IDENTITY(1,1) NOT NULL,
	[AirportName] [varchar](20) NOT NULL,
	[AirportLocation] [varchar](20) NOT NULL,
	[AirportHub] [bit] NOT NULL,
	[AirportDistanceFromHub] [float] NOT NULL,
 CONSTRAINT [PK_Airports] PRIMARY KEY CLUSTERED 
(
	[AirportID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CargoManifests]    Script Date: 10/24/2020 1:14:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CargoManifests](
	[FlightID] [int] NOT NULL,
	[ShipmentID] [int] NOT NULL,
 CONSTRAINT [PK_CargoManifests] PRIMARY KEY CLUSTERED 
(
	[FlightID] ASC,
	[ShipmentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ClientAddresses]    Script Date: 10/24/2020 1:14:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ClientAddresses](
	[ClientAddressID] [int] IDENTITY(1,1) NOT NULL,
	[ClientID] [int] NOT NULL,
	[ClientAddressLine1] [varchar](120) NOT NULL,
	[ClientAddressLine2] [varchar](120) NULL,
	[ClientAddressCity] [varchar](50) NOT NULL,
	[ClientAddressState] [varchar](2) NOT NULL,
	[ClientAddressZip] [int] NOT NULL,
 CONSTRAINT [PK_ClientAddresses] PRIMARY KEY CLUSTERED 
(
	[ClientAddressID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Clients]    Script Date: 10/24/2020 1:14:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Clients](
	[ClientID] [int] IDENTITY(1,1) NOT NULL,
	[ClientName] [varchar](30) NOT NULL,
	[ClientTypeID] [int] NOT NULL,
	[ClientPhoneNumber] [int] NOT NULL,
 CONSTRAINT [PK_Clients] PRIMARY KEY CLUSTERED 
(
	[ClientID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Flights]    Script Date: 10/24/2020 1:14:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Flights](
	[FlightID] [int] IDENTITY(1,1) NOT NULL,
	[AircraftID] [int] NOT NULL,
	[PilotID] [int] NOT NULL,
	[StartAirport] [int] NOT NULL,
	[EndAirport] [int] NOT NULL,
	[FlightStartTime] [smalldatetime] NULL,
	[FlightEndTime] [smalldatetime] NULL,
 CONSTRAINT [PK_Flights] PRIMARY KEY CLUSTERED 
(
	[FlightID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LUAircraftStatus]    Script Date: 10/24/2020 1:14:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LUAircraftStatus](
	[AircraftStatusID] [int] IDENTITY(1,1) NOT NULL,
	[AircraftStatus] [varchar](20) NULL,
 CONSTRAINT [PK_LUAircraftStatus] PRIMARY KEY CLUSTERED 
(
	[AircraftStatusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LUClientType]    Script Date: 10/24/2020 1:14:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LUClientType](
	[ClientTypeID] [int] IDENTITY(1,1) NOT NULL,
	[ClientType] [varchar](30) NULL,
 CONSTRAINT [PK_LUClientType] PRIMARY KEY CLUSTERED 
(
	[ClientTypeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LUShipmentStatus]    Script Date: 10/24/2020 1:14:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LUShipmentStatus](
	[ShipmentStatusID] [int] IDENTITY(1,1) NOT NULL,
	[ShipmentStatus] [varchar](20) NULL,
 CONSTRAINT [PK_LUShipmentStatus] PRIMARY KEY CLUSTERED 
(
	[ShipmentStatusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Pilots]    Script Date: 10/24/2020 1:14:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Pilots](
	[PilotID] [int] IDENTITY(1,1) NOT NULL,
	[FirstName] [varchar](20) NOT NULL,
	[LastName] [varchar](30) NOT NULL,
	[DateOfBirth] [date] NOT NULL,
	[EmployeeNumber] [varchar](20) NOT NULL,
	[DateOFHire] [date] NOT NULL,
	[DateLeftCAF] [date] NULL,
 CONSTRAINT [PK_Pilots] PRIMARY KEY CLUSTERED 
(
	[PilotID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Shipments]    Script Date: 10/24/2020 1:14:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Shipments](
	[ShipmentID] [int] IDENTITY(1,1) NOT NULL,
	[ClientID] [int] NOT NULL,
	[ShipmentVolume] [float] NOT NULL,
	[ShipmentWeight] [float] NOT NULL,
	[ShipmentStatusID] [int] NOT NULL,
	[ShipmentStartDate] [date] NULL,
	[ShipmentEndDate] [date] NULL,
	[ShipmentNotes] [varchar](1000) NULL,
 CONSTRAINT [PK_Shipments] PRIMARY KEY CLUSTERED 
(
	[ShipmentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 10/24/2020 1:14:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[UserID] [int] IDENTITY(1,1) NOT NULL,
	[UserName] [varchar](50) NOT NULL,
	[PasswordHash] [binary](64) NOT NULL,
 CONSTRAINT [PK_UserNames] PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[AircraftModels] ON 

INSERT [dbo].[AircraftModels] ([ACModelID], [ACMake], [ACModel], [ACRange], [ACRangeClassification], [ACPayload], [ACLoadVolume]) VALUES (1, N'Aero Spacelines', N'Mini Guppy', N'1995', N'Large Wide Bodied', 32000, 18.91)
SET IDENTITY_INSERT [dbo].[AircraftModels] OFF
GO
SET IDENTITY_INSERT [dbo].[LUAircraftStatus] ON 

INSERT [dbo].[LUAircraftStatus] ([AircraftStatusID], [AircraftStatus]) VALUES (1, N'Newly Aquired')
SET IDENTITY_INSERT [dbo].[LUAircraftStatus] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Users__C9F28456202DDD31]    Script Date: 10/24/2020 1:14:22 PM ******/
ALTER TABLE [dbo].[Users] ADD UNIQUE NONCLUSTERED 
(
	[UserName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Aircraft]  WITH CHECK ADD  CONSTRAINT [FK_Aircraft_AircraftModels] FOREIGN KEY([ACModelID])
REFERENCES [dbo].[AircraftModels] ([ACModelID])
GO
ALTER TABLE [dbo].[Aircraft] CHECK CONSTRAINT [FK_Aircraft_AircraftModels]
GO
ALTER TABLE [dbo].[Aircraft]  WITH CHECK ADD  CONSTRAINT [FK_Aircraft_LUAircraftStatus] FOREIGN KEY([AircraftStatusID])
REFERENCES [dbo].[LUAircraftStatus] ([AircraftStatusID])
GO
ALTER TABLE [dbo].[Aircraft] CHECK CONSTRAINT [FK_Aircraft_LUAircraftStatus]
GO
ALTER TABLE [dbo].[CargoManifests]  WITH CHECK ADD  CONSTRAINT [FK_CargoManifests_Flights] FOREIGN KEY([FlightID])
REFERENCES [dbo].[Flights] ([FlightID])
GO
ALTER TABLE [dbo].[CargoManifests] CHECK CONSTRAINT [FK_CargoManifests_Flights]
GO
ALTER TABLE [dbo].[CargoManifests]  WITH CHECK ADD  CONSTRAINT [FK_CargoManifests_Shipments] FOREIGN KEY([ShipmentID])
REFERENCES [dbo].[Shipments] ([ShipmentID])
GO
ALTER TABLE [dbo].[CargoManifests] CHECK CONSTRAINT [FK_CargoManifests_Shipments]
GO
ALTER TABLE [dbo].[ClientAddresses]  WITH CHECK ADD  CONSTRAINT [FK_ClientAddresses_Clients] FOREIGN KEY([ClientID])
REFERENCES [dbo].[Clients] ([ClientID])
GO
ALTER TABLE [dbo].[ClientAddresses] CHECK CONSTRAINT [FK_ClientAddresses_Clients]
GO
ALTER TABLE [dbo].[Clients]  WITH CHECK ADD  CONSTRAINT [FK_Clients_LUClientType] FOREIGN KEY([ClientTypeID])
REFERENCES [dbo].[LUClientType] ([ClientTypeID])
GO
ALTER TABLE [dbo].[Clients] CHECK CONSTRAINT [FK_Clients_LUClientType]
GO
ALTER TABLE [dbo].[Flights]  WITH CHECK ADD  CONSTRAINT [FK_Flights_Aircraft] FOREIGN KEY([AircraftID])
REFERENCES [dbo].[Aircraft] ([AircraftID])
GO
ALTER TABLE [dbo].[Flights] CHECK CONSTRAINT [FK_Flights_Aircraft]
GO
ALTER TABLE [dbo].[Flights]  WITH CHECK ADD  CONSTRAINT [FK_Flights_Airports_End] FOREIGN KEY([EndAirport])
REFERENCES [dbo].[Airports] ([AirportID])
GO
ALTER TABLE [dbo].[Flights] CHECK CONSTRAINT [FK_Flights_Airports_End]
GO
ALTER TABLE [dbo].[Flights]  WITH CHECK ADD  CONSTRAINT [FK_Flights_Airports_Start] FOREIGN KEY([StartAirport])
REFERENCES [dbo].[Airports] ([AirportID])
GO
ALTER TABLE [dbo].[Flights] CHECK CONSTRAINT [FK_Flights_Airports_Start]
GO
ALTER TABLE [dbo].[Flights]  WITH CHECK ADD  CONSTRAINT [FK_Flights_Pilots] FOREIGN KEY([PilotID])
REFERENCES [dbo].[Pilots] ([PilotID])
GO
ALTER TABLE [dbo].[Flights] CHECK CONSTRAINT [FK_Flights_Pilots]
GO
ALTER TABLE [dbo].[Shipments]  WITH CHECK ADD  CONSTRAINT [FK_Shipments_Clients] FOREIGN KEY([ClientID])
REFERENCES [dbo].[Clients] ([ClientID])
GO
ALTER TABLE [dbo].[Shipments] CHECK CONSTRAINT [FK_Shipments_Clients]
GO
ALTER TABLE [dbo].[Shipments]  WITH CHECK ADD  CONSTRAINT [FK_Shipments_LUShipmentStatus] FOREIGN KEY([ShipmentStatusID])
REFERENCES [dbo].[LUShipmentStatus] ([ShipmentStatusID])
GO
ALTER TABLE [dbo].[Shipments] CHECK CONSTRAINT [FK_Shipments_LUShipmentStatus]
GO
/****** Object:  StoredProcedure [dbo].[Add_Aircraft]    Script Date: 10/24/2020 1:14:22 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*
*Champlain Air Freight - Add_Aircraft
*Author - Kelly May
*Date - Oct 24, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored procedure for adding a new aircraft into the database. User inputs an existing
* ACModelID for the new aircraft, and the procedure creates a new AircraftID and sets the 
* AircraftStatusID to 1. 
*
*/

CREATE PROCEDURE [dbo].[Add_Aircraft]

@ACModelID int
AS
BEGIN

INSERT INTO dbo.Aircraft (ACModelID, AircraftStatusID)
VALUES (@ACModelID, '1')

END
GO
/****** Object:  StoredProcedure [dbo].[Add_Aircraft_Model]    Script Date: 10/24/2020 1:14:22 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - Add_Aircraft_Model
*Author - Kelly May
*Date - Oct 24, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored procedure for adding a new aircraft model into the database. User inputs values for
*ACMake, ACModel, ACRange, ACRangeClassification, ACPayload, and ACLoadVolume. ACModelID will be automatically 
*generated
*
*/

CREATE PROCEDURE [dbo].[Add_Aircraft_Model]

@ACMake varchar, 
@ACModel varchar,
@ACRange varchar,
@ACRangeClassification varchar,
@ACPayload float, 
@ACLoadVolume float

AS
BEGIN

INSERT INTO dbo.AircraftModels (ACMake, ACModel, ACRange, ACRangeClassification, ACPayload, ACLoadVolume)
VALUES (@ACMake, @ACModel, @ACRange, @ACRangeClassification, @ACPayload, @ACLoadVolume)

END
GO
/****** Object:  StoredProcedure [dbo].[Add_Pilot]    Script Date: 10/24/2020 1:14:22 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - Add_Pilot
*Author - Kelly May
*Date - Oct 24, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored Procedure adds FirstName, LastName, DateOfBirth, EmployeeNumber, DateOfHire information 
* into the Pilots table. DateLeftCaf is left NULL, and PilotID is autogenerated. 
*
*/

CREATE PROCEDURE [dbo].[Add_Pilot]

@FirstName varchar, 
@LastName varchar, 
@DateOfBirth date, 
@EmployeeNumber varchar, 
@DateOfHire date

AS
BEGIN
BEGIN TRANSACTION

INSERT INTO Pilots (FirstName, LastName, DateOfBirth, EmployeeNumber, DateOFHire)
Values (@FirstName, @LastName, @DateOfBirth, @EmployeeNumber, @DateOfHire) 

COMMIT
END
GO
/****** Object:  StoredProcedure [dbo].[Delete_Aircraft]    Script Date: 10/24/2020 1:14:22 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - Delete_Aircraft
*Author - Kelly May
*Date - Oct 24, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored Procedure deletes an aircraft table entry that matches the given AircraftID
*
*/

CREATE PROCEDURE [dbo].[Delete_Aircraft]

@AircraftID int

AS
BEGIN

DELETE FROM Aircraft
WHERE AircraftID = @AircraftID

END
GO
/****** Object:  StoredProcedure [dbo].[Delete_Pilot]    Script Date: 10/24/2020 1:14:22 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - Delete_Pilot
*Author - Kelly May
*Date - Oct 24, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - deletes all pilot information for a given pilot, based on the PilotID
*
*/

CREATE PROCEDURE [dbo].[Delete_Pilot]
@PilotID int

AS
BEGIN
BEGIN TRANSACTION

DELETE FROM Pilots
WHERE PilotID = @PilotID

COMMIT
END
GO
/****** Object:  StoredProcedure [dbo].[Update_Aircraft]    Script Date: 10/24/2020 1:14:22 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - Update_Aircraft
*Author - Kelly May
*Date - Oct 24, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored Procedure allows user to make changes to the ACModelID for a given AircraftID in the aircraft database. 
*
*/

CREATE PROCEDURE [dbo].[Update_Aircraft]

@AircraftID int,
@ACModelID int

AS
BEGIN

UPDATE Aircraft 
SET
ACModelID = @ACModelID
WHERE AircraftID = @AircraftID
END
GO
/****** Object:  StoredProcedure [dbo].[Update_Pilot]    Script Date: 10/24/2020 1:14:22 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - Update_Pilot
*Author - Kelly May
*Date - Oct 24, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Updates an individual Pilot's information based on a given PilotID. User can 
* change the FirstName, LastName, DateOfBirth, EmployeeNumber, DateOfHire, and DateLeftCAF fields. 
*
*/

CREATE PROCEDURE [dbo].[Update_Pilot]
@PilotID int, 
@FirstName varchar, 
@LastName varchar, 
@DateOfBirth date, 
@EmployeeNumber varchar, 
@DateOfHire date, 
@DateLeftCAF date

AS
BEGIN
BEGIN TRANSACTION

UPDATE Pilots 
SET
FirstName = @FirstName, 
LastName = @LastName, 
DateOfBirth = @DateOfBirth, 
EmployeeNumber = @EmployeeNumber, 
DateOFHire = @DateOfHire, 
DateLeftCAF = @DateLeftCAF
WHERE PilotID = @PilotID

COMMIT
END
GO
/****** Object:  StoredProcedure [dbo].[View_All_Aircraft]    Script Date: 10/24/2020 1:14:22 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - View_All_Aircraft
*Author - Kelly May
*Date - Oct 24, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored Procedure allows user to view Aircraft table, joined with the AircraftModels table
*
*/

CREATE PROCEDURE [dbo].[View_All_Aircraft]

AS
BEGIN

SELECT * FROM Aircraft
LEFT JOIN AircraftModels ON Aircraft.ACModelID = AircraftModels.ACModelID

END
GO
/****** Object:  StoredProcedure [dbo].[View_All_Pilot]    Script Date: 10/24/2020 1:14:22 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - View_All_Pilot
*Author - Kelly May
*Date - Oct 24, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored Procedure shows pilot information for all pilots
*
*/

CREATE PROCEDURE [dbo].[View_All_Pilot]

AS
BEGIN
BEGIN TRANSACTION

SELECT * FROM Pilots

COMMIT
END
GO
/****** Object:  StoredProcedure [dbo].[View_Selected_Aircraft]    Script Date: 10/24/2020 1:14:22 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - View_Selected_Aircraft
*Author - Kelly May
*Date - Oct 24, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored Procedure allows user to view Aircraft table, joined with the AircraftModels table, for 
* a given AircraftID
*
*/

CREATE PROCEDURE [dbo].[View_Selected_Aircraft]

@AircraftID int

AS
BEGIN

SELECT * FROM Aircraft
LEFT JOIN AircraftModels ON Aircraft.ACModelID = AircraftModels.ACModelID
WHERE AircraftID = @AircraftID

END
GO
/****** Object:  StoredProcedure [dbo].[View_Selected_Pilot]    Script Date: 10/24/2020 1:14:22 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/*
*Champlain Air Freight - View_Selected_Pilot
*Author - Kelly May
*Date - Oct 24, 2020 
*Date Last Modified - 
*Last Modified By - 
*Changes Made - 
*Description - Stored Procedure shows pilot information based on the first and last name in the db
*
*/

CREATE PROCEDURE [dbo].[View_Selected_Pilot]

@FirstName varchar, 
@LastName varchar
AS
BEGIN
BEGIN TRANSACTION

SELECT * FROM Pilots WHERE 
FirstName = @FirstName AND
LastName = @LastName

COMMIT
END
GO
